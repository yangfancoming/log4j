

package org.apache.log4j;

import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.LoggerFactory;
import org.apache.log4j.spi.RepositorySelector;
import org.apache.log4j.spi.DefaultRepositorySelector;
import org.apache.log4j.spi.RootLogger;
import org.apache.log4j.spi.NOPLoggerRepository;
import org.apache.log4j.helpers.Loader;
import org.apache.log4j.helpers.OptionConverter;
import org.apache.log4j.helpers.LogLog;

import java.net.URL;
import java.net.MalformedURLException;


import java.util.Enumeration;
import java.io.StringWriter;
import java.io.PrintWriter;

/**
 * Use the <code>LogManager</code> class to retreive {@link Logger}
 * instances or to operate on the current {@link
 * LoggerRepository}. When the <code>LogManager</code> class is loaded
 * into memory the default initalzation procedure is inititated. The
 * default intialization procedure</a> is described in the <a
 * href="../../../../manual.html#defaultInit">short log4j manual</a>.
 *
 * @author Ceki G&uuml;lc&uuml; */
public class LogManager {

    /**
     * @deprecated This variable is for internal use only. It will
     * become package protected in future versions.
     * */
    static public final String DEFAULT_CONFIGURATION_FILE = "log4j.properties";

    static final String DEFAULT_XML_CONFIGURATION_FILE = "log4j.xml";

    /**
     * @deprecated This variable is for internal use only. It will
     * become private in future versions.
     * */
    static final public String DEFAULT_CONFIGURATION_KEY = "log4j.configuration";

    /**
     * @deprecated This variable is for internal use only. It will
     * become private in future versions.
     * */
    static final public String CONFIGURATOR_CLASS_KEY = "log4j.configuratorClass";

    /**
     * @deprecated This variable is for internal use only. It will
     * become private in future versions.
     */
    public static final String DEFAULT_INIT_OVERRIDE_KEY =  "log4j.defaultInitOverride";

    static private Object guard = null;

    static private RepositorySelector repositorySelector;

    static {
        //默认设置的日志级别为DEBUGB
        Hierarchy h = new Hierarchy(new RootLogger(Level.DEBUG));
        // By default we use a DefaultRepositorySelector which always returns 'h'.
        //默认使用的是DefaultRepositorySelector
        repositorySelector = new DefaultRepositorySelector(h);

        /** Search for the properties file log4j.properties in the CLASSPATH.  */
        //读取操作系统配置的环境变量log4j.defaultInitOverride的值
        String override =OptionConverter.getSystemProperty(DEFAULT_INIT_OVERRIDE_KEY,null);

        // if there is no default init override, then get the resource specified by the user or the default config file.
        // 如果操作系统没有配置环境变量log4j.defaultInitOverride，那么这里会进入
        if(override == null || "false".equalsIgnoreCase(override)) {
            //读取操作系统配置的环境变量log4j.configuration的值
            String configurationOptionStr = OptionConverter.getSystemProperty( DEFAULT_CONFIGURATION_KEY,null);
            //读取操作系统配置的环境变量log4j.configuratorClass的值
            String configuratorClassName = OptionConverter.getSystemProperty( CONFIGURATOR_CLASS_KEY,null);
            URL url;
            // if the user has not specified the log4j.configuration  property, we search first for the file "log4j.xml" and then "log4j.properties"
            //如果没有配置环境变量log4j.configuration，那么就去寻找用户是否配置了文件log4j.xml，如果log4j.xml也找不到那么就加载默认配置文件log4j.properties
            if(configurationOptionStr == null) {
                url = Loader.getResource(DEFAULT_XML_CONFIGURATION_FILE);
                if(url == null) {
                    url = Loader.getResource(DEFAULT_CONFIGURATION_FILE);
                }
            } else {
                try {
                    //如果配置了环境变量log4j.configuration，那么以环境变量log4j.configuration的值构造URL对象
                    url = new URL(configurationOptionStr);
                } catch (MalformedURLException ex) {
                    // so, resource is not a URL: attempt to get the resource from the class path
                    //环境变量log4j.configuration配置的值非URL，尝试在classpath读取相关文件资源
                    url = Loader.getResource(configurationOptionStr);
                }
            }

            // If we have a non-null url, then delegate the rest of the configuration to the OptionConverter.selectAndConfigure  method.
            // 如果url非空，那么让OptionConverter.selectAndConfigure代理剩下的配置过程
            if(url != null) {
                LogLog.debug("Using URL ["+url+"] for automatic log4j configuration.");
                try {
                    // 这里进行初始化！！！
                    OptionConverter.selectAndConfigure(url, configuratorClassName, LogManager.getLoggerRepository());
                } catch (NoClassDefFoundError e) {
                    LogLog.warn("Error during default initialization", e);
                }
            } else {
                LogLog.debug("Could not find resource: ["+configurationOptionStr+"].");
            }
        } else {
            LogLog.debug("Default initialization of overridden by " +  DEFAULT_INIT_OVERRIDE_KEY + "property.");
        }
    }

    /**
     Sets <code>LoggerFactory</code> but only if the correct
     <em>guard</em> is passed as parameter.

     <p>Initally the guard is null.  If the guard is
     <code>null</code>, then invoking this method sets the logger
     factory and the guard. Following invocations will throw a {@link
    IllegalArgumentException}, unless the previously set
     <code>guard</code> is passed as the second parameter.

     <p>This allows a high-level component to set the {@link
    RepositorySelector} used by the <code>LogManager</code>.

     <p>For example, when tomcat starts it will be able to install its
     own repository selector. However, if and when Tomcat is embedded
     within JBoss, then JBoss will install its own repository selector
     and Tomcat will use the repository selector set by its container,
     JBoss.  */
    static
    public
    void setRepositorySelector(RepositorySelector selector, Object guard)
            throws IllegalArgumentException {
        if((LogManager.guard != null) && (LogManager.guard != guard)) {
            throw new IllegalArgumentException(
                    "Attempted to reset the LoggerFactory without possessing the guard.");
        }

        if(selector == null) {
            throw new IllegalArgumentException("RepositorySelector must be non-null.");
        }

        LogManager.guard = guard;
        LogManager.repositorySelector = selector;
    }


    /**
     * This method tests if called from a method that
     * is known to result in class members being abnormally
     * set to null but is assumed to be harmless since the
     * all classes are in the process of being unloaded.
     *
     * @param ex exception used to determine calling stack.
     * @return true if calling stack is recognized as likely safe.
     */
    private static boolean isLikelySafeScenario(final Exception ex) {
        StringWriter stringWriter = new StringWriter();
        ex.printStackTrace(new PrintWriter(stringWriter));
        String msg = stringWriter.toString();
        return msg.indexOf("org.apache.catalina.loader.WebappClassLoader.stop") != -1;
    }


    public static LoggerRepository getLoggerRepository() {
        if (repositorySelector == null) {
            repositorySelector = new DefaultRepositorySelector(new NOPLoggerRepository());
            guard = null;
            Exception ex = new IllegalStateException("Class invariant violation");
            String msg = "log4j called after unloading, see http://logging.apache.org/log4j/1.2/faq.html#unload.";
            if (isLikelySafeScenario(ex)) {
                LogLog.debug(msg, ex);
            } else {
                LogLog.error(msg, ex);
            }
        }
        return repositorySelector.getLoggerRepository();
    }

    /**
     Retrieve the appropriate root logger.
     */
    public static Logger getRootLogger() {
        // Delegate the actual manufacturing of the logger to the logger repository.
        return getLoggerRepository().getRootLogger();
    }

    /**
     Retrieve the appropriate {@link Logger} instance.
     获取最适当的实例
     repositorySelector在LogManager的静态代码块已经创建，默认实现为DefaultRepositorySelector
     */
    public static Logger getLogger(final String name) {
        // Delegate the actual manufacturing of the logger to the logger repository.
        LoggerRepository loggerRepository = getLoggerRepository();
        return loggerRepository.getLogger(name);
    }

    /**
     Retrieve the appropriate {@link Logger} instance.
     */
    public static Logger getLogger(final Class clazz) {
        // Delegate the actual manufacturing of the logger to the logger repository.
        return getLoggerRepository().getLogger(clazz.getName());
    }


    /**
     Retrieve the appropriate {@link Logger} instance.
     */
    public static  Logger getLogger(final String name, final LoggerFactory factory) {
        // Delegate the actual manufacturing of the logger to the logger repository.
        return getLoggerRepository().getLogger(name, factory);
    }

    public static Logger exists(final String name) {
        return getLoggerRepository().exists(name);
    }

    public static Enumeration getCurrentLoggers() {
        return getLoggerRepository().getCurrentLoggers();
    }

    public static void shutdown() {
        getLoggerRepository().shutdown();
    }

    public static void resetConfiguration() {
        getLoggerRepository().resetConfiguration();
    }
}


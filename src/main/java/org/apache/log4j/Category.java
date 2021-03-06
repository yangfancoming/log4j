

// Contibutors: Alex Blewitt <Alex.Blewitt@ioshq.com>
//              Markus Oestreicher <oes@zurich.ibm.com>
//              Frank Hoering <fhr@zurich.ibm.com>
//              Nelson Minar <nelson@media.mit.edu>
//              Jim Cakalic <jim_cakalic@na.biomerieux.com>
//              Avy Sharell <asharell@club-internet.fr>
//              Ciaran Treanor <ciaran@xelector.com>
//              Jeff Turner <jeff@socialchange.net.au>
//              Michael Horwitz <MHorwitz@siemens.co.za>
//              Calvin Chan <calvin.chan@hic.gov.au>
//              Aaron Greenhouse <aarong@cs.cmu.edu>
//              Beat Meier <bmeier@infovia.com.ar>
//              Colin Sampaleanu <colinml1@exis.com>

package org.apache.log4j;

import org.apache.log4j.spi.AppenderAttachable;
import org.apache.log4j.spi.LoggingEvent;
import org.apache.log4j.spi.LoggerRepository;
import org.apache.log4j.spi.HierarchyEventListener;
import org.apache.log4j.helpers.NullEnumeration;
import org.apache.log4j.helpers.AppenderAttachableImpl;

import java.util.Enumeration;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.Vector;


/**
 * <font color="#AA2222"><b>This class has been deprecated and
 * replaced by the {@link Logger} <em>subclass</em></b></font>. It
 * will be kept around to preserve backward compatibility until mid
 * 2003.
 *
 * Logger is a subclass of Category, i.e. it extends
 * Category. In other words, a logger <em>is</em> a category. Thus,
 * all operations that can be performed on a category can be
 * performed on a logger. Internally, whenever log4j is asked to
 * produce a Category object, it will instead produce a Logger
 * object. Log4j 1.2 will <em>never</em> produce Category objects but
 * only Logger instances. In order to preserve backward
 * compatibility, methods that previously accepted category objects
 * still continue to accept category objects.
 *
 * For example, the following are all legal and will work as
 * expected.
 *
 <pre>
 &nbsp;&nbsp;&nbsp;// Deprecated form:
 &nbsp;&nbsp;&nbsp;Category cat = Category.getInstance("foo.bar")

 &nbsp;&nbsp;&nbsp;// Preferred form for retrieving loggers:
 &nbsp;&nbsp;&nbsp;Logger logger = Logger.getLogger("foo.bar")
 </pre>

 *  The first form is deprecated and should be avoided.
 *
 *  <b>There is absolutely no need for new client code to use or
 *  refer to the Category class.</b> Whenever possible,
 *  please avoid referring to it or using it.
 *
 * See the <a href="../../../../manual.html">short manual</a> for an
 * introduction on this class.
 * 
 * See the document entitled <a href="http://www.qos.ch/logging/preparingFor13.html">preparing
 *  for log4j 1.3</a> for a more detailed discussion.

 */
public class Category implements AppenderAttachable {

    /**
     The hierarchy where categories are attached to by default.
     */
    //static
    //public
    //final Hierarchy defaultHierarchy = new Hierarchy(new
    //					   RootCategory(Level.DEBUG));

    /** The name of this category.  */
    protected String name;

    /** The assigned level of this category.  The level variable need not be assigned a value in  which case it is inherited form the hierarchy.  */
    // 此类别的指定级别。不需要为级别变量分配值，在这种情况下，它是从层次结构继承的。
    volatile protected Level level;

    /** The parent of this category. All categories have at least one ancestor which is the root category. */
    volatile protected Category parent;

    /** The fully qualified name of the Category class. See also the getFQCN method. */
    private static final String FQCN = Category.class.getName();

    protected ResourceBundle resourceBundle;

    // Categories need to know what Hierarchy they are in
    protected LoggerRepository repository;

    AppenderAttachableImpl aai;

    /**
     * Additivity is set to true by default, that is children inherit the appenders of their ancestors by default.
     If this variable is set to false then the appenders found in the ancestors of this category are not used.
     However, the children of this category will inherit its appenders,
     unless the children have their additivity flag set to false too.
     See the user manual for more details.
      */
    protected boolean additive = true;

    /**
     This constructor created a new Category instance and sets its name.
     It is intended to be used by sub-classes only. You should not
     create categories directly.
     @param name The name of the category.
     */
    protected Category(String name) {
        this.name = name;
    }

    /**
     Add newAppender to the list of appenders of this Category instance.
     If newAppender is already in the list of
     appenders, then it won't be added again.
     */
    @Override
    public synchronized void addAppender(Appender newAppender) {
        if(aai == null) {
            aai = new AppenderAttachableImpl();
        }
        aai.addAppender(newAppender);
        repository.fireAddAppenderEvent(this, newAppender);
    }

    /**
     If assertion parameter is false, then
     logs msg as an {@link #error(Object) error} statement.
     The assert method has been renamed to
     assertLog because assert is a language  reserved word in JDK 1.4.
     @param assertion
     @param msg The message to print if assertion is false.
     @since 1.2 */

    public void assertLog(boolean assertion, String msg) {
        if(!assertion)
            this.error(msg);
    }


    /**
     Call the appenders in the hierrachy starting at
     this.  If no appenders could be found, emit a  warning.
     This method calls all the appenders inherited from the
     hierarchy circumventing any evaluation of whether to log or not
     to log the particular log request.

     @param event the event to log.  */

    public void callAppenders(LoggingEvent event) {
        int writes = 0;
        for(Category c = this; c != null; c=c.parent) {
            // Protected against simultaneous call to addAppender, removeAppender,...
            synchronized(c) {
                if(c.aai != null) {
                    writes += c.aai.appendLoopOnAppenders(event);
                }
                if(!c.additive) {
                    break;
                }
            }
        }
        if(writes == 0) {
            repository.emitNoAppenderWarning(this);
        }
    }

    /**
     Close all attached appenders implementing the AppenderAttachable  interface.
     @since 1.0
     */
    synchronized void closeNestedAppenders() {
        Enumeration enumeration = this.getAllAppenders();
        if(enumeration != null) {
            while(enumeration.hasMoreElements()) {
                Appender a = (Appender) enumeration.nextElement();
                if(a instanceof AppenderAttachable) {
                    a.close();
                }
            }
        }
    }

    /**
     Log a message object with the {@link Level#DEBUG DEBUG} level.

     This method first checks if this category is DEBUG
     enabled by comparing the level of this category with the {@link
    Level#DEBUG DEBUG} level. If this category is
     DEBUG enabled, then it converts the message object
     (passed as parameter) to a string by invoking the appropriate
     {@link org.apache.log4j.or.ObjectRenderer}. It then proceeds to call all the
     registered appenders in this category and also higher in the
     hierarchy depending on the value of the additivity flag.

     <b>WARNING</b> Note that passing a {@link Throwable} to this
     method will print the name of the Throwable but no
     stack trace. To print a stack trace use the {@link #debug(Object,Throwable)} form instead.
     @param message the message object to log. */
    public void debug(Object message) {
        if(repository.isDisabled(Level.DEBUG_INT))
            return;
        if(Level.DEBUG.isGreaterOrEqual(this.getEffectiveLevel())) {
            forcedLog(FQCN, Level.DEBUG, message, null);
        }
    }

    /**
     Log a message object with the DEBUG level including
     the stack trace of the {@link Throwable} t passed as parameter.
     See {@link #debug(Object)} form for more detailed information.
     @param message the message object to log.
     @param t the exception to log, including its stack trace.  */
    public void debug(Object message, Throwable t) {
        if(repository.isDisabled(Level.DEBUG_INT))
            return;
        if(Level.DEBUG.isGreaterOrEqual(this.getEffectiveLevel()))
            forcedLog(FQCN, Level.DEBUG, message, t);
    }

    /**
     Log a message object with the {@link Level#ERROR ERROR} Level.
     This method first checks if this category is ERROR
     enabled by comparing the level of this category with {@link  Level#ERROR ERROR} Level.
   If this category is ERROR  enabled,then it converts the message object passed as parameter
     to a string by invoking the appropriate {@link org.apache.log4j.or.ObjectRenderer}.
    It proceeds to call all the  registered appenders in this category and also higher in the
     hierarchy depending on the value of the additivity flag.
     <b>WARNING</b> Note that passing a {@link Throwable} to this
     method will print the name of the Throwable but no  stack trace.
    To print a stack trace use the {@link #error(Object,Throwable)} form instead.
     @param message the message object to log */
    public void error(Object message) {
        if(repository.isDisabled(Level.ERROR_INT))
            return;
        if(Level.ERROR.isGreaterOrEqual(this.getEffectiveLevel()))
            forcedLog(FQCN, Level.ERROR, message, null);
    }

    /**
     Log a message object with the ERROR level including
     the stack trace of the {@link Throwable} t passed as  parameter.
     See {@link #error(Object)} form for more detailed information.
     @param message the message object to log.
     @param t the exception to log, including its stack trace.  */
    public void error(Object message, Throwable t) {
        if(repository.isDisabled(Level.ERROR_INT))
            return;
        if(Level.ERROR.isGreaterOrEqual(this.getEffectiveLevel()))
            forcedLog(FQCN, Level.ERROR, message, t);
    }


    /**
     If the named category exists (in the default hierarchy) then it
     returns a reference to the category, otherwise it returns  null.
     @deprecated Please use {@link LogManager#exists} instead.
     @since 0.8.5 */
    public static Logger exists(String name) {
        return LogManager.exists(name);
    }

    /**
     Log a message object with the {@link Level#FATAL FATAL} Level.
     This method first checks if this category is FATAL
     enabled by comparing the level of this category with {@link Level#FATAL FATAL} Level.
     If the category is FATAL
     enabled, then it converts the message object passed as parameter
     to a string by invoking the appropriate
     {@link org.apache.log4j.or.ObjectRenderer}. It
     proceeds to call all the registered appenders in this category and
     also higher in the hierarchy depending on the value of the additivity flag.

     <b>WARNING</b> Note that passing a {@link Throwable} to this
     method will print the name of the Throwable but no stack trace. To
     print a stack trace use the {@link #fatal(Object, Throwable)} form
     instead.

     @param message the message object to log */

    public void fatal(Object message) {
        if(repository.isDisabled(Level.FATAL_INT))
            return;
        if(Level.FATAL.isGreaterOrEqual(this.getEffectiveLevel()))
            forcedLog(FQCN, Level.FATAL, message, null);
    }

    /**
     Log a message object with the FATAL level including the stack trace of the {@link Throwable} t passed as parameter.
     See {@link #fatal(Object)} for more detailed information.
     @param message the message object to log.
     @param t the exception to log, including its stack trace.  */
    public void fatal(Object message, Throwable t) {
        if(repository.isDisabled(Level.FATAL_INT))
            return;
        if(Level.FATAL.isGreaterOrEqual(this.getEffectiveLevel()))
            forcedLog(FQCN, Level.FATAL, message, t);
    }

    /**  This method creates a new logging event and logs the event  without further checks.  */
    protected void forcedLog(String fqcn, Priority level, Object message, Throwable t) {
        callAppenders(new LoggingEvent(fqcn, this, level, message, t));
    }


    /**
     Get the additivity flag for this Category instance.
     */

    public boolean getAdditivity() {
        return additive;
    }

    /**
     Get the appenders contained in this category as an {@link  Enumeration}.
    If no appenders can be found, then a {@link NullEnumeration} is returned.
     @return Enumeration An enumeration of the appenders in this category.
     */
    @Override
    public synchronized Enumeration getAllAppenders() {
        if(aai == null)
            return NullEnumeration.getInstance();
        else
            return aai.getAllAppenders();
    }

    /**
     Look for the appender named as name.
     Return the appender with that name if in the list. Return null otherwise.
     */
    @Override
    public synchronized Appender getAppender(String name) {
        if(aai == null || name == null)
            return null;
        return aai.getAppender(name);
    }

    /**
     Starting from this category, search the category hierarchy for a
     non-null level and return it. Otherwise, return the level of the root category.

     The Category class is designed so that this method executes as
     quickly as possible.
     */
    public Level getEffectiveLevel() {
        for(Category c = this; c != null; c=c.parent) {
            if(c.level != null)
                return c.level;
        }
        return null; // If reached will cause an NullPointerException.
    }

    /**
     * @deprecated Please use the the {@link #getEffectiveLevel} method instead.
     * */
    public Priority getChainedPriority() {
        for(Category c = this; c != null; c=c.parent) {
            if(c.level != null)
                return c.level;
        }
        return null; // If reached will cause an NullPointerException.
    }


    /**
     Returns all the currently defined categories in the default
     hierarchy as an {@link java.util.Enumeration Enumeration}.
     The root category is <em>not</em> included in the returned
     {@link Enumeration}.
     @deprecated Please use {@link LogManager#getCurrentLoggers()} instead.
     */
    public static Enumeration getCurrentCategories() {
        return LogManager.getCurrentLoggers();
    }


    /**
     Return the default Hierarchy instance.
     @deprecated Please use {@link LogManager#getLoggerRepository()} instead.
     @since 1.0
     */
    public static LoggerRepository getDefaultHierarchy() {
        return LogManager.getLoggerRepository();
    }

    /**
     Return the the {@link Hierarchy} where this Category instance is attached.
     @deprecated Please use {@link #getLoggerRepository} instead.
     @since 1.1 */

    public  LoggerRepository  getHierarchy() {
        return repository;
    }

    /**
     Return the the {@link LoggerRepository} where this
     Category is attached.
     @since 1.2 */
    public LoggerRepository  getLoggerRepository() {
        return repository;
    }


    /**
     * @deprecated Make sure to use {@link Logger#getLogger(String)} instead.
     */
    public static Category getInstance(String name) {
        return LogManager.getLogger(name);
    }

    /**
     * @deprecated Please make sure to use {@link Logger#getLogger(Class)} instead.
     */
    public static Category getInstance(Class clazz) {
        return LogManager.getLogger(clazz);
    }


    /**
     Return the category name.
     */
    public final String getName() {
        return name;
    }


    /**
     Returns the parent of this category. Note that the parent of a
     given category may change during the lifetime of the category.
     The root category will return null.
     @since 1.2
     */

    public final Category getParent() {
        return this.parent;
    }

    /**
     Returns the assigned {@link Level}, if any, for this Category.
     @return Level - the assigned Level, can be null.
     */
    public final  Level getLevel() {
        return this.level;
    }

    /**
     @deprecated Please use {@link #getLevel} instead.
     */
    public final  Level getPriority() {
        return this.level;
    }


    /**
     *  @deprecated Please use {@link Logger#getRootLogger()} instead.
     */
    public static final Category getRoot() {
        return LogManager.getRootLogger();
    }

    /**
     Return the <em>inherited</em> {@link ResourceBundle} for this  category.

     This method walks the hierarchy to find the appropriate  resource bundle.
     It will return the resource bundle attached to the closest ancestor of this category,
      much like the way  priorities are searched. In case there is no bundle in the hierarchy then null is returned.
     @since 0.9.0 */

    public ResourceBundle getResourceBundle() {
        for(Category c = this; c != null; c=c.parent) {
            if(c.resourceBundle != null)
                return c.resourceBundle;
        }
        // It might be the case that there is no resource bundle
        return null;
    }

    /**
     Returns the string resource coresponding to key in
     this category's inherited resource bundle. See also {@link #getResourceBundle}.
     If the resource cannot be found, then an {@link #error error}
     message will be logged complaining about the missing resource.
     */
    protected String getResourceBundleString(String key) {
        ResourceBundle rb = getResourceBundle();
        // This is one of the rare cases where we can use logging in order
        // to report errors from within log4j.
        if(rb == null) {
            //if(!hierarchy.emittedNoResourceBundleWarning) {
            //error("No resource bundle has been set for category "+name);
            //hierarchy.emittedNoResourceBundleWarning = true;
            //}
            return null;
        }
        else {
            try {
                return rb.getString(key);
            }
            catch(MissingResourceException mre) {
                error("No resource is associated with key \""+key+"\".");
                return null;
            }
        }
    }

    /**
     Log a message object with the {@link Level#INFO INFO} Level.

     This method first checks if this category is INFO
     enabled by comparing the level of this category with {@link
    Level#INFO INFO} Level. If the category is INFO
     enabled, then it converts the message object passed as parameter
     to a string by invoking the appropriate
     {@link org.apache.log4j.or.ObjectRenderer}. It
     proceeds to call all the registered appenders in this category and
     also higher in the hierarchy depending on the value of the
     additivity flag.

     <b>WARNING</b> Note that passing a {@link Throwable} to this
     method will print the name of the Throwable but no stack trace. To
     print a stack trace use the {@link #info(Object, Throwable)} form instead.
     @param message the message object to log */

    public void info(Object message) {
        if(repository.isDisabled(Level.INFO_INT))
            return;
        if(Level.INFO.isGreaterOrEqual(this.getEffectiveLevel()))
            forcedLog(FQCN, Level.INFO, message, null);
    }

    /**
     Log a message object with the INFO level including
     the stack trace of the {@link Throwable} t passed as
     parameter.

     See {@link #info(Object)} for more detailed information.

     @param message the message object to log.
     @param t the exception to log, including its stack trace.  */

    public void info(Object message, Throwable t) {
        if(repository.isDisabled(Level.INFO_INT))
            return;
        if(Level.INFO.isGreaterOrEqual(this.getEffectiveLevel()))
            forcedLog(FQCN, Level.INFO, message, t);
    }

    /**
     Is the appender passed as parameter attached to this category?
     */
    @Override
    public boolean isAttached(Appender appender) {
        if(appender == null || aai == null)
            return false;
        else {
            return aai.isAttached(appender);
        }
    }

    /**
     *  Check whether this category is enabled for the DEBUG
     *  Level.
     *
     *   This function is intended to lessen the computational cost of
     *  disabled log debug statements.
     *
     *   For some cat Category object, when you write,
     *  <pre>
     *      cat.debug("This is entry number: " + i );
     *  </pre>
     *
     *  You incur the cost constructing the message, concatenatiion in
     *  this case, regardless of whether the message is logged or not.
     *
     *  If you are worried about speed, then you should write
     *  <pre>
     * 	 if(cat.isDebugEnabled()) {
     * 	   cat.debug("This is entry number: " + i );
     * 	 }
     *  </pre>
     *
     *  This way you will not incur the cost of parameter
     *  construction if debugging is disabled for cat. On
     *  the other hand, if the cat is debug enabled, you
     *  will incur the cost of evaluating whether the category is debug
     *  enabled twice. Once in isDebugEnabled and once in
     *  the debug.  This is an insignificant overhead
     *  since evaluating a category takes about 1%% of the time it
     *  takes to actually log.
     *
     *  @return boolean - true if this category is debug
     *  enabled, false otherwise.
     *   */
    public boolean isDebugEnabled() {
        if(repository.isDisabled( Level.DEBUG_INT))
            return false;
        return Level.DEBUG.isGreaterOrEqual(this.getEffectiveLevel());
    }

    /**
     Check whether this category is enabled for a given {@link Level} passed as parameter.
     See also {@link #isDebugEnabled}.
     @return boolean True if this category is enabled for level.
     */
    public boolean isEnabledFor(Priority level) {
        if(repository.isDisabled(level.level))
            return false;
        return level.isGreaterOrEqual(this.getEffectiveLevel());
    }

    /**
     Check whether this category is enabled for the info Level.
     See also {@link #isDebugEnabled}.
     @return boolean - true if this category is enabled
     for level info, false otherwise.
     */
    public boolean isInfoEnabled() {
        if(repository.isDisabled(Level.INFO_INT))
            return false;
        return Level.INFO.isGreaterOrEqual(this.getEffectiveLevel());
    }

    /**
     Log a localized message. The user supplied parameter key is replaced by its localized version from the resource bundle.
     @see #setResourceBundle
     @since 0.8.4 */
    public void l7dlog(Priority priority, String key, Throwable t) {
        if(repository.isDisabled(priority.level)) {
            return;
        }
        if(priority.isGreaterOrEqual(this.getEffectiveLevel())) {
            String msg = getResourceBundleString(key);
            // if message corresponding to 'key' could not be found in the
            // resource bundle, then default to 'key'.
            if(msg == null) {
                msg = key;
            }
            forcedLog(FQCN, priority, msg, t);
        }
    }
    /**
     Log a localized and parameterized message. First, the user
     supplied key is searched in the resource
     bundle. Next, the resulting pattern is formatted using
     {@link java.text.MessageFormat#format(String,Object[])} method with the
     user supplied object array params.
     @since 0.8.4
     */
    public void l7dlog(Priority priority, String key,  Object[] params, Throwable t) {
        if(repository.isDisabled(priority.level)) {
            return;
        }
        if(priority.isGreaterOrEqual(this.getEffectiveLevel())) {
            String pattern = getResourceBundleString(key);
            String msg;
            if(pattern == null)
                msg = key;
            else
                msg = java.text.MessageFormat.format(pattern, params);
            forcedLog(FQCN, priority, msg, t);
        }
    }

    /**
     This generic form is intended to be used by wrappers.
     */

    public void log(Priority priority, Object message, Throwable t) {
        if(repository.isDisabled(priority.level)) {
            return;
        }
        if(priority.isGreaterOrEqual(this.getEffectiveLevel()))
            forcedLog(FQCN, priority, message, t);
    }

    /**
     This generic form is intended to be used by wrappers.
     */
    public void log(Priority priority, Object message) {
        if(repository.isDisabled(priority.level)) {
            return;
        }
        if(priority.isGreaterOrEqual(this.getEffectiveLevel()))
            forcedLog(FQCN, priority, message, null);
    }

    /**
     This is the most generic printing method. It is intended to be
     invoked by <b>wrapper</b> classes.

     @param callerFQCN The wrapper class' fully qualified class name.
     @param level The level of the logging request.
     @param message The message of the logging request.
     @param t The throwable of the logging request, may be null.  */
    public void log(String callerFQCN, Priority level, Object message, Throwable t) {
        if(repository.isDisabled(level.level)) {
            return;
        }
        if(level.isGreaterOrEqual(this.getEffectiveLevel())) {
            forcedLog(callerFQCN, level, message, t);
        }
    }

    /**
     *  LoggerRepository forgot the fireRemoveAppenderEvent method,
     *     if using the stock Hierarchy implementation, then call its fireRemove.
     *     Custom repositories can implement HierarchyEventListener if they
     *     want remove notifications.
     * @param appender appender, may be null.
     */
    private void fireRemoveAppenderEvent(final Appender appender) {
        if (appender != null) {
            if (repository instanceof Hierarchy) {
                ((Hierarchy) repository).fireRemoveAppenderEvent(this, appender);
            } else if (repository instanceof HierarchyEventListener) {
                ((HierarchyEventListener) repository).removeAppenderEvent(this, appender);
            }
        }
    }

    /**
     Remove all previously added appenders from this Category instance.
     This is useful when re-reading configuration information.
     */
    @Override
    public synchronized void removeAllAppenders() {
        if(aai != null) {
            Vector appenders = new Vector();
            for (Enumeration iter = aai.getAllAppenders(); iter != null && iter.hasMoreElements();) {
                appenders.add(iter.nextElement());
            }
            aai.removeAllAppenders();
            for(Enumeration iter = appenders.elements(); iter.hasMoreElements();) {
                fireRemoveAppenderEvent((Appender) iter.nextElement());
            }
            aai = null;
        }
    }


    /**
     Remove the appender passed as parameter form the list of appenders.
     @since 0.8.2
     */
    @Override
    public synchronized void removeAppender(Appender appender) {
        if(appender == null || aai == null)
            return;
        boolean wasAttached = aai.isAttached(appender);
        aai.removeAppender(appender);
        if (wasAttached) {
            fireRemoveAppenderEvent(appender);
        }
    }

    /**
     Remove the appender with the name passed as parameter form the list of appenders.
     @since 0.8.2
     */
    @Override
    public synchronized  void removeAppender(String name) {
        if(name == null || aai == null) return;
        Appender appender = aai.getAppender(name);
        aai.removeAppender(name);
        if (appender != null) {
            fireRemoveAppenderEvent(appender);
        }
    }

    /**
     Set the additivity flag for this Category instance.
     @since 0.8.1
     */

    public void setAdditivity(boolean additive) {
        this.additive = additive;
    }

    /**
     Only the Hiearchy class can set the hiearchy of a
     category. Default package access is MANDATORY here.  */

    final void setHierarchy(LoggerRepository repository) {
        this.repository = repository;
    }

    /**
     Set the level of this Category. If you are passing any of
     Level.DEBUG, Level.INFO,
     Level.WARN, Level.ERROR,
     Level.FATAL as a parameter, you need to case them as  Level.
     As in <pre> &nbsp;&nbsp;&nbsp;logger.setLevel((Level) Level.DEBUG); </pre>
     Null values are admitted.  */

    public void setLevel(Level level) {
        this.level = level;
    }

    /**
     Set the level of this Category.
     Null values are admitted.
     @deprecated Please use {@link #setLevel} instead.
     */

    public void setPriority(Priority priority) {
        this.level = (Level) priority;
    }


    /**
     Set the resource bundle to be used with localized logging
     methods {@link #l7dlog(Priority,String,Throwable)} and {@link
    #l7dlog(Priority,String,Object[],Throwable)}.
     @since 0.8.4
     */
    public void setResourceBundle(ResourceBundle bundle) {
        resourceBundle = bundle;
    }

    /**
     Calling this method will <em>safely</em> close and remove all
     appenders in all the categories including root contained in the
     default hierachy.

     Some appenders such as {@link org.apache.log4j.net.SocketAppender}
     and {@link AsyncAppender} need to be closed before the
     application exists. Otherwise, pending logging events might be lost.

     The shutdown method is careful to close nested
     appenders before closing regular appenders. This is allows
     configurations where a regular appender is attached to a category
     and again to a nested appender.

     @deprecated Please use {@link LogManager#shutdown()} instead.
     @since 1.0
     */

    public static void shutdown() {
        LogManager.shutdown();
    }


    /**
     Log a message object with the {@link Level#WARN WARN} Level.

     This method first checks if this category is WARN
     enabled by comparing the level of this category with {@link
    Level#WARN WARN} Level. If the category is WARN
     enabled, then it converts the message object passed as parameter
     to a string by invoking the appropriate
     {@link org.apache.log4j.or.ObjectRenderer}. It
     proceeds to call all the registered appenders in this category and
     also higher in the hieararchy depending on the value of the
     additivity flag.

     <b>WARNING</b> Note that passing a {@link Throwable} to this
     method will print the name of the Throwable but no stack trace. To
     print a stack trace use the {@link #warn(Object, Throwable)} form
     instead.  
     @param message the message object to log.  */
    public void warn(Object message) {
        if(repository.isDisabled( Level.WARN_INT))
            return;
        if(Level.WARN.isGreaterOrEqual(this.getEffectiveLevel()))
            forcedLog(FQCN, Level.WARN, message, null);
    }

    /**
     Log a message with the WARN level including the
     stack trace of the {@link Throwable} t passed as
     parameter.

     See {@link #warn(Object)} for more detailed information.

     @param message the message object to log.
     @param t the exception to log, including its stack trace.  */

    public void warn(Object message, Throwable t) {
        if(repository.isDisabled(Level.WARN_INT))
            return;
        if(Level.WARN.isGreaterOrEqual(this.getEffectiveLevel()))
            forcedLog(FQCN, Level.WARN, message, t);
    }
}

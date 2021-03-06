

package org.apache.log4j.spi;

import java.io.InputStream;
import java.net.URL;

/**
   Implemented by classes capable of configuring log4j using a URL.
 由能够使用URL配置log4j的类 进行实现
   @since 1.0
 */
public interface Configurator {

  /**
     Special level value signifying inherited behaviour. The current
     value of this string constant is <b>inherited</b>. {@link #NULL}
     is a synonym.  */
  public static final String INHERITED = "inherited";

  /**
     Special level signifying inherited behaviour, same as {@link
     #INHERITED}. The current value of this string constant is
     <b>null</b>. */
  public static final String NULL = "null";


  /**
    Interpret a resource pointed by a InputStream and set up log4j accordingly.
    解释输入流所指向的资源，并相应地设置log4j
    The configuration is done relative to the hierarchy parameter.
   配置是相对于层次结构参数完成的
    @param inputStream The InputStream to parse
    @param repository The hierarchy to operation upon.
    @since 1.2.17
   */
  void doConfigure(InputStream inputStream, LoggerRepository repository);

  /**
     Interpret a resource pointed by a URL and set up log4j accordingly.
     The configuration is done relative to the hierarchy parameter.
     @param url The URL to parse
     @param repository The hierarchy to operation upon.
   */
  void doConfigure(URL url, LoggerRepository repository);
}

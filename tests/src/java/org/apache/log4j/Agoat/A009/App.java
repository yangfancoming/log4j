package org.apache.log4j.Agoat.A009;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**

 */
public class App {

    private static Logger logger = Logger.getLogger(App.class);

    public static void main(String[] args) {
        PropertyConfigurator.configure("E:\\Code\\Log\\GitHub\\log4j-1_2_17\\tests\\src\\java\\org\\apache\\log4j\\Agoat\\A009\\goat.properties");
        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
        logger.fatal("fatal");
    }

}

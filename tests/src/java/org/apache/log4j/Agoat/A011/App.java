package org.apache.log4j.Agoat.A011;

import org.apache.log4j.Logger;

/**
 * 查看  E:\Code\Log\GitHub\log4j-1_2_17\tests\resources\log4j.xml  配置内容学习
 */
public class App {

    private static Logger logger = Logger.getLogger(App.class);

    public static void main(String[] args) {
        logger.trace("trace");
        logger.debug("debug");
        logger.info("info");
        logger.warn("warn");
        logger.error("error");
        logger.fatal("fatal");
    }

}

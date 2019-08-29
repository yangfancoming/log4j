package org.apache.log4j.Agoat;

import org.apache.log4j.Logger;

/**
 * Created by 64274 on 2019/8/29.
 *
 * @ Description: TODO
 * @ author  山羊来了
 * @ date 2019/8/29---22:12
 */
public class App {

    private static Logger logger = Logger.getLogger(App.class);

    public static void main(String[] args) {
        logger.debug("debug");
        logger.info("info");
        logger.error("error");
    }

}

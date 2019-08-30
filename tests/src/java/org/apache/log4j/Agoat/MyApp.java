package org.apache.log4j.Agoat;

import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Logger;

/**
 * Created by 64274 on 2019/8/30.
 *
 * @ Description: TODO
 * @ author  山羊来了
 * @ date 2019/8/30---13:17
 */
public class MyApp {

    // Define a static logger variable so that it references the
    // Logger instance named "MyApp".
    static Logger logger = Logger.getLogger(MyApp.class);

    public static void main(String[] args) {

        // Set up a simple configuration that logs on the console.
        BasicConfigurator.configure();

        logger.info("Entering application.");
        Bar bar = new Bar();
        bar.doIt();
        logger.info("Exiting application.");
    }
}
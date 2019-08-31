package org.apache.log4j.Agoat.A001;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

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
        PropertyConfigurator.configure("E:\\Code\\Log\\GitHub\\log4j-1_2_17\\tests\\src\\java\\org\\apache\\log4j\\Agoat\\A001\\goat.properties");
        logger.debug("debug");
    }

}

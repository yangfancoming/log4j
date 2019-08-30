package org.apache.log4j.Agoat;

import org.apache.log4j.Logger;

/**
 * Created by 64274 on 2019/8/30.
 *
 * @ Description: TODO
 * @ author  山羊来了
 * @ date 2019/8/30---13:18
 */
public class Bar {

    static Logger logger = Logger.getLogger(Bar.class);

    public void doIt() {
        logger.debug("Did it again!");
    }
}
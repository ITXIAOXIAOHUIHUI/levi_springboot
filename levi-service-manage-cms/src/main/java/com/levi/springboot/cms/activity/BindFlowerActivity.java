package com.levi.springboot.cms.activity;

import com.levi.springboot.cms.workflower.BindingCallback;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * @author jianghaihui
 * @date 2019/10/11 11:36
 */
@Component
public class BindFlowerActivity {
    private Logger LOGGER = LoggerFactory.getLogger(BindFlowerActivity.class);


    @BindingCallback(actionId="testActivity")
    public void testActivity(){
        LOGGER.info("this is a test");
    }
}

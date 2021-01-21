package com.springboot.levi.leviweb1.wcs.si;

import com.springboot.levi.leviweb1.wcs.AbstractRunnable;
import com.springboot.levi.leviweb1.wcs.assign.G2PAgvAssignHelper;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.util.Map;

/**
 * @author jianghaihui
 * @date 2021/1/5 15:32
 */
public class G2pAgvAssigInfo extends AbstractRunnable implements ApplicationContextAware {

    private G2PAgvAssignHelper[] agvAssignHelpers = null;

    @Override
    protected void doRun() {

    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Map<String, G2PAgvAssignHelper> allHelpers = applicationContext.getBeansOfType(G2PAgvAssignHelper.class);
        this.agvAssignHelpers = allHelpers.values()
                .toArray(new G2PAgvAssignHelper[0]);
    }


}

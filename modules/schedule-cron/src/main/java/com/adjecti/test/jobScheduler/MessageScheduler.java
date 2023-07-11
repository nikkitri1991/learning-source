package com.adjecti.test.jobScheduler;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;

import java.text.SimpleDateFormat;
import java.util.Date;

public class MessageScheduler {

    private static final Log _log = LogFactoryUtil.getLog(MessageScheduler.class);

    public void printMessage() {
        Date now = new Date();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String message = "Printing a message at " + format.format(now);
        _log.info(message);
        System.out.println(message);
    }

}

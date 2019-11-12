package com.bysy.tools.notify4j.worker;

import com.bysy.tools.notify4j.model.Message;

/**
 * @Author : leonwang
 * @Descpriction
 * @Date:created 2018/8/14
 */
public interface NotificationWorker {
    /**
     * 提交信息通知任务
     * @param message
     * @return
     */
    boolean submitNotificationTask(Message message);
}

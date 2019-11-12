package com.bysy.tools.notify4j;

import com.bysy.tools.notify4j.model.Message;
import com.bysy.tools.notify4j.worker.NotificationWorker;

/**
 * @Author : leonwang
 * @Descpriction
 * @Date:created 2018/8/15
 */
public class Notifier {
    private NotificationWorker notificationWorker;

    public void notify(Message msg){
        notificationWorker.submitNotificationTask(msg);
    }

    public NotificationWorker getNotificationWorker() {
        return notificationWorker;
    }

    public void setNotificationWorker(NotificationWorker notificationWorker) {
        this.notificationWorker = notificationWorker;
    }
}

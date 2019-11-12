package com.bysy.tools.notify4j.worker.impl;

import com.bysy.tools.notify4j.component.NotificationModule;
import com.bysy.tools.notify4j.component.NotificationModuleBox;
import com.bysy.tools.notify4j.model.Message;
import com.bysy.tools.notify4j.worker.NotificationWorker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.*;

/**
 * @Author : leonwang
 * @Descpriction 通知工作者，拥有众多通知方式，指定的线程数和工作队列容量，当工作队列满后放弃接收工作队列
 * @Date:created 2018/8/14
 */
public class ThreadPoolBasedWorker implements NotificationWorker {
    private static Logger logger = LoggerFactory.getLogger(ThreadPoolBasedWorker.class);
    private ThreadPoolExecutor executor;
    private NotificationModuleBox notificationModuleBox;
    private int workQueueMaxSize;

    public void init() {
        BlockingQueue workQueue = new LinkedBlockingQueue(workQueueMaxSize);
        executor = new ThreadPoolExecutor(1, 1,
                0L, TimeUnit.MILLISECONDS,
                workQueue);
        ;
    }

    @Override
    public boolean submitNotificationTask(Message message) {
        NotificationTask task = new NotificationTask(message, notificationModuleBox.getNotificationModulesInUse());
        logger.debug("工作队列剩余容量：" + executor.getQueue().remainingCapacity());
        if (executor.getQueue().remainingCapacity() > 0) {
            executor.submit(task);
            logger.info("新增一个通知任务");
            return true;
        } else {
            logger.warn("当前工作队列已满，新增任务失败,messageId:" + message.getId());
        }

        return false;
    }

    public int getWorkQueueMaxSize() {
        return workQueueMaxSize;
    }

    public void setWorkQueueMaxSize(int workQueueMaxSize) {
        this.workQueueMaxSize = workQueueMaxSize;
    }

    public NotificationModuleBox getNotificationModuleBox() {
        return notificationModuleBox;
    }

    public void setNotificationModuleBox(NotificationModuleBox notificationModuleBox) {
        this.notificationModuleBox = notificationModuleBox;
    }
}

class NotificationTask implements Runnable {
    private static Logger logger = LoggerFactory.getLogger(NotificationTask.class);
    private Message message;
    private List<NotificationModule> notifyMethods;

    public NotificationTask(Message message, List<NotificationModule> notificationMethods) {
        this.message = message;
        this.notifyMethods = notificationMethods;
    }

    @Override
    public void run() {
        notifyMethods.forEach(notifyMethod -> {
            try {
                logger.info("sending...");
                notifyMethod.notify(message);
                logger.info("sending over");
            } catch (Exception e) {
                logger.error(notifyMethod.getModuleName() + "notify fail,id:" + message.getId(), e);
            }
        });
    }
}

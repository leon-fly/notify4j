package com.bysy.tools.notify4j.component;

import com.bysy.tools.notify4j.model.Message;

/**
 * @Author : leonwang
 * @Descpriction
 * @Date:created 2018/8/13
 */
public interface NotificationModule {
    /**
     * 发送通知
     * @param message  信息内容
     * @throws Exception
     */
    void notify(Message message) throws Exception;

    /**
     * 获取组件名称
     * @return
     */
    String getModuleName();
}

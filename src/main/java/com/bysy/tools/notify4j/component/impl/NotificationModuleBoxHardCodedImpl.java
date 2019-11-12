package com.bysy.tools.notify4j.component.impl;

import com.bysy.tools.notify4j.component.NotificationModule;
import com.bysy.tools.notify4j.component.NotificationModuleBox;

import java.util.LinkedList;
import java.util.List;

/**
 * @Author : leonwang
 * @Descpriction
 * @Date:created 2018/8/14
 */
public class NotificationModuleBoxHardCodedImpl implements NotificationModuleBox {
    private GitlabIssueCreator gitlabIssueCreator;
    private List<NotificationModule> notificationModulesInUse;

    @Override
    public List<NotificationModule> getNotificationModulesInUse() {
        if(notificationModulesInUse == null){
            synchronized (this){
                if (notificationModulesInUse == null){
                    notificationModulesInUse = new LinkedList<>();
                    notificationModulesInUse.add(gitlabIssueCreator);
                }
            }
        }
        return notificationModulesInUse;
    }

    public void setGitlabIssueCreator(GitlabIssueCreator gitlabIssueCreator) {
        this.gitlabIssueCreator = gitlabIssueCreator;
    }
}

package com.bysy.tools.notify4j.component.impl;

import com.bysy.tools.notify4j.component.NotificationModule;
import com.bysy.tools.notify4j.model.GitlabIssueCreationRequest;
import com.bysy.tools.notify4j.model.Message;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

/**
 * @Author : leonwang
 * @Descpriction 通过gitlab创建issue，依赖gitlab的notification进行告警.
 * @Date:created 2018/8/13
 */
public class GitlabIssueCreator implements NotificationModule {
    Logger logger = LoggerFactory.getLogger(GitlabIssueCreator.class);
    private OkHttpClient okHttpClient = new OkHttpClient();
    private Random random = new Random();
    private final String moduleName = "Gitlab-Issue";
    private final String titlePreFix = "ServerWarning:";

    //gitlab用户的用户菜单中生成的访问api的token
    private String privateToken;
    private String projectId;
    private String apiUrl;
    private static ObjectMapper mapper;

    static {
        mapper = new ObjectMapper();
        mapper.setSerializationInclusion(JsonInclude.Include.NON_EMPTY);
        mapper.configure(MapperFeature.REQUIRE_SETTERS_FOR_GETTERS, true);
        mapper.configure(MapperFeature.CAN_OVERRIDE_ACCESS_MODIFIERS, false);
    }

    private void createIssue(String id, String title, String description, String[] assigneeIds) throws IOException {
        logger.info("create issue start>>>>>>>>>>");
        GitlabIssueCreationRequest gitlabIssueAddRequestRequest = new GitlabIssueCreationRequest();
        gitlabIssueAddRequestRequest.setTitle(titlePreFix+title+"[id:"+id+"]");
        gitlabIssueAddRequestRequest.setLabels("server warning");
        gitlabIssueAddRequestRequest.setAssigneeIds(assigneeIds); //任务分配机制为：从指定的id中取一个项目成员进行分配
        gitlabIssueAddRequestRequest.setDescription(description);

        String requestContent = mapper.writeValueAsString(gitlabIssueAddRequestRequest);
        logger.debug("requestContent:" + requestContent);
        String url = apiUrl + "/projects/" + projectId + "/issues";
        RequestBody requestBody = okhttp3.RequestBody.create(
                MediaType.parse("application/json;charset=utf-8"), requestContent);
        Request request = new Request.Builder().url(url).post(requestBody).addHeader("PRIVATE-TOKEN", privateToken).build();
        Response tokenResponse = okHttpClient.newCall(request).execute();

        String responseContent = tokenResponse.body().string();
        logger.debug("responseContent :" + responseContent);
        logger.info("create issue end<<<<<<<");
    }

    /**
     * @param message
     * @throws Exception
     */
    @Override
    public void notify(Message message) throws Exception {
        String timestamp = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date()) ;
        String randomNum = random.nextInt(10000)+"";
        String id = timestamp+"-"+randomNum;
        createIssue(id, message.getHeader(), message.getBody(), null);
    }

    @Override
    public String getModuleName() {
        return this.moduleName;
    }

    public String getPrivateToken() {
        return privateToken;
    }

    public void setPrivateToken(String privateToken) {
        this.privateToken = privateToken;
    }

    public String getProjectId() {
        return projectId;
    }

    public void setProjectId(String projectId) {
        this.projectId = projectId;
    }

    public String getApiUrl() {
        return apiUrl;
    }

    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }
}

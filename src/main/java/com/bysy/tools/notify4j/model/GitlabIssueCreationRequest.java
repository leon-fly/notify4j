package com.bysy.tools.notify4j.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @Author : leonwang
 * @Descpriction
 * @Date:created 2018/8/13
 */
public class GitlabIssueCreationRequest {
    private String id;
    private String iid;
    private String title;
    private String description;
    private String confidential;

    @JsonProperty("assignee_ids")
    private String[] assigneeIds;

    @JsonProperty("milestone_id")
    private String milestoneId;

    private String labels;

    @JsonProperty("created_at")
    private String createdAt;

    @JsonProperty("due_Date")
    private String dueDate;

    @JsonProperty()
    private String merge_request_to_resolve_discussions_of;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIid() {
        return iid;
    }

    public void setIid(String iid) {
        this.iid = iid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getConfidential() {
        return confidential;
    }

    public void setConfidential(String confidential) {
        this.confidential = confidential;
    }

    public String[] getAssigneeIds() {
        return assigneeIds;
    }

    public void setAssigneeIds(String[] assigneeIds) {
        this.assigneeIds = assigneeIds;
    }

    public String getMilestoneId() {
        return milestoneId;
    }

    public void setMilestoneId(String milestoneId) {
        this.milestoneId = milestoneId;
    }

    public String getLabels() {
        return labels;
    }

    public void setLabels(String labels) {
        this.labels = labels;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getDueDate() {
        return dueDate;
    }

    public void setDueDate(String dueDate) {
        this.dueDate = dueDate;
    }

    public String getMerge_request_to_resolve_discussions_of() {
        return merge_request_to_resolve_discussions_of;
    }

    public void setMerge_request_to_resolve_discussions_of(String merge_request_to_resolve_discussions_of) {
        this.merge_request_to_resolve_discussions_of = merge_request_to_resolve_discussions_of;
    }
}

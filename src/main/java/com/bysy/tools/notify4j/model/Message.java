package com.bysy.tools.notify4j.model;

/**
 * @Author : leonwang
 * @Descpriction
 * @Date:created 2018/8/13
 */
public class Message {
    private String id;
    private String header;
    private String body;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getHeader() {
        return header;
    }

    public void setHeader(String header) {
        this.header = header;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public static Message build(){
        return new Message();
    }

    public Message header(String header){
        this.header = header;
        return this;
    }
    public Message body(String body){
        this.body = body;
        return this;
    }
}

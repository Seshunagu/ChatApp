package com.chat_App.dto;

import java.util.Date;

public class ChatMessageOut {
    private Long fromUserId;
    private String fromUsername;
    private Long toUserId;
    private String content;
    private Date timestamp;

    public ChatMessageOut() {}

    public ChatMessageOut(Long fromUserId, String fromUsername, Long toUserId, String content, Date timestamp) {
        this.fromUserId = fromUserId;
        this.fromUsername = fromUsername;
        this.toUserId = toUserId;
        this.content = content;
        this.timestamp = timestamp;
    }

    public Long getFromUserId() { return fromUserId; }
    public void setFromUserId(Long fromUserId) { this.fromUserId = fromUserId; }

    public String getFromUsername() { return fromUsername; }
    public void setFromUsername(String fromUsername) { this.fromUsername = fromUsername; }

    public Long getToUserId() { return toUserId; }
    public void setToUserId(Long toUserId) { this.toUserId = toUserId; }

    public String getContent() { return content; }
    public void setContent(String content) { this.content = content; }

    public Date getTimestamp() { return timestamp; }
    public void setTimestamp(Date timestamp) { this.timestamp = timestamp; }
}

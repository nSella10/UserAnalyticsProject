package com.analytics.backend.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "user_actions")
public class UserAction {

    @Id
    private String id;

    private String actionName;
    private String userId;
    private LocalDateTime timestamp;

    //constructor
    public UserAction() {
    }

    public UserAction(String actionName, String userId, LocalDateTime timestamp) {
        this.actionName = actionName;
        this.userId = userId;
        this.timestamp = timestamp;
    }

    //Getter & Setter

    public String getId() {
        return id;
    }



    public String getActionName() {
        return actionName;
    }

    public void setActionName(String actionName) {
        this.actionName = actionName;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }
}

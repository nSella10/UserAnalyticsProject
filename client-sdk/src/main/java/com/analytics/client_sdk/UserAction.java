package com.analytics.client_sdk;

public class UserAction {
    private String actionName;
    private String userId;
    private String timestamp;

    public UserAction(String actionName, String userId, String timestamp) {
        this.actionName = actionName;
        this.userId = userId;
        this.timestamp = timestamp;
    }
}

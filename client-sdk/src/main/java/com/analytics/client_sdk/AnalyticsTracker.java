package com.analytics.client_sdk;

import java.time.LocalDateTime;
public class AnalyticsTracker {

    public static void trackAction(String actionName,String userId,LocalDateTime timestamp){
        System.out.println("Tracking action:");
        System.out.println("Action: " + actionName);
        System.out.println("User ID: " + userId);
        System.out.println("Timestamp: " + timestamp);
    }

}

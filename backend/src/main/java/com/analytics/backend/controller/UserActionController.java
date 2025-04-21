package com.analytics.backend.controller;

import com.analytics.backend.model.UserAction;
import com.analytics.backend.repository.UserActionRepository;

import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController //מסמן ששזה מחלקת controller שמחזירה json
@RequestMapping("/track")
public class UserActionController {

    @Autowired //הספרינג יזריק את הrepository שלנו
    private UserActionRepository repository;

    @PostMapping //מאזין לבקשות post
    public String trackUserAction(@RequestBody UserAction action){ //ממיר json לאובייקט user action
        repository.save(action);
        return "Action tracked successfully";
    }

    @GetMapping("/actions") //יוצר כתובת אינטרנט חדשה action/ שמגיבה לGET
    public List<UserAction> getAllAction(){
        return repository.findAll();
    }
    @GetMapping("/stats/count")
    public long getTotalActionsCount(){

        return repository.count(); //מחזיר מספר מסמכים במסד נתונים
    }

    @GetMapping("/stats/by-action")
    public Map<String,Long> getActionCountByName(){
        List<UserAction> actions = repository.findAll();

        return actions.stream()
                .collect(Collectors.groupingBy(
                        UserAction::getActionName,
                        Collectors.counting()
                ));

    }

    @GetMapping("/states/by-user")
    public Map<String,Long> getActionCountByUser(){
        List<UserAction> actions = repository.findAll();

        return  actions.stream()
                .collect(Collectors.groupingBy(
                        UserAction::getUserId,
                        Collectors.counting()
                ));
    }

}

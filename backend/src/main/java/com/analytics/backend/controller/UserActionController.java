package com.analytics.backend.controller;

import com.analytics.backend.model.UserAction;
import com.analytics.backend.repository.UserActionRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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


}

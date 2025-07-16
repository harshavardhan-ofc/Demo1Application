package com.postgresql.demo1.controller;


import com.postgresql.demo1.enums.Status;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/status")
public class StatusController {
//     to get through req param
    @GetMapping("/get")
    public String getStatus(@RequestParam Status value){
        return "you selected the status : "+value.name();
    }
    @GetMapping("/All")
    public Status[] getAllStatuses(){
        return Status.values();
    }

}

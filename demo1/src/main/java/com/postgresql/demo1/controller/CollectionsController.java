package com.postgresql.demo1.controller;
import java.util.HashMap;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;


@RestController
@RequestMapping("/collections")
public class CollectionsController {

    @Value("${myProperty.name}")
    private String name;

    // array list
    @GetMapping("/getArrayList")
    public List<String> getFruits(){
        List<String> fruitslist=new ArrayList<>();
//        List a = new ArrayList();
//        a.add("aaaa");
//        a.add(123);

        fruitslist.add("Mango");
        //fruitslist.
        fruitslist.add("Banana");
        fruitslist.add("grapes");
        return fruitslist;
    }

@GetMapping("/getHashSet")
    public Set<String> getHashSet(){
        Set<String> hashset=new HashSet<>();
        hashset.add("Siddesh");
        hashset.add("gopi");
        hashset.add("ram");
    System.out.println("getHashSet");
    int[] l = new int[] { 1,2,3};
        return hashset;

    }


    @GetMapping("/getHashMap")
    public Map<Integer,String> getHashMap(){
        Map<Integer,String> studentmap=new HashMap<>();
        studentmap.put(1,"Cricket");
        studentmap.put(2,"Badminton");
        studentmap.put(3,"Table Tennis ");
        return  studentmap;

    }

    @GetMapping("/getQueue")
    public List<String> getQueueProcess() {
        Queue<String> taskQueue = new LinkedList<>();
        taskQueue.add("Task 1");
        taskQueue.add("Task 2");
        taskQueue.add("Task 3");

        List<String> processed = new ArrayList<>();
        while (!taskQueue.isEmpty()) {
            processed.add("Processing: " + taskQueue.poll());
        }
        return processed;
    }
}

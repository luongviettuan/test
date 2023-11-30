package com.example.demo.controller;

import com.example.demo.config.SseEmitterManager;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student")
public class StudentController {


    @PostMapping("/{id}")
    public ResponseEntity<String> updateStudent(@PathVariable("id") Integer id) {
        SseEmitterManager.sendSseEventToClients(id.toString(), "refresh laij trang di");
        return new ResponseEntity("goij api thanh cong", HttpStatus.OK);
    }
}

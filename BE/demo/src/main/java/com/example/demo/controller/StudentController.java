package com.example.demo.controller;

import com.example.demo.config.SseEmitterManager;
import com.example.demo.model.CommonRes;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student")
@CrossOrigin(origins = "http://localhost:4200")
public class StudentController {


    @PostMapping("/{id}")
    public ResponseEntity<CommonRes> updateStudent(@PathVariable("id") Integer id) {
        SseEmitterManager.sendSseEventToClients(id.toString(), "refresh laij trang di");
        return new ResponseEntity(new CommonRes("thanh cong"), HttpStatus.OK);
    }
}

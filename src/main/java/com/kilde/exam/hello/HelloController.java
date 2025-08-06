package com.kilde.exam.hello;

import com.kilde.exam.responses.RespondSuccess;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @RequestMapping("/hello")
    public ResponseEntity<Object> Hello() {
        return RespondSuccess.generateResponse(HttpStatus.OK, true, "Hello!");
    }
}

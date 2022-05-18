package uz.project.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MainTestController {

    @GetMapping("/hello")
    public ResponseEntity<String> getHelloMessage(){
        return ResponseEntity.ok("Hello Abbos");
    }
}

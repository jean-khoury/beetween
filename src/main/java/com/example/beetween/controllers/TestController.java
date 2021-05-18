package com.example.beetween.controllers;

import com.example.beetween.models.Test;
import com.example.beetween.repositories.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
public class TestController {
    @Autowired
    private final TestRepository testRepository;

    public TestController(TestRepository testRepository){
        this.testRepository = testRepository;
    }
    @CrossOrigin
    @GetMapping("")
    public ResponseEntity<Iterable<Test>> getAllTests(){
        Iterable<Test> tests = testRepository.findAll();
        return ResponseEntity.ok().body(tests);
    }

    @CrossOrigin
    @GetMapping("/{id}")
    public ResponseEntity<Test> getTestById(@PathVariable (value="id") long id ) throws Exception {
        Test movie = testRepository.findById(id).orElseThrow(()->new Exception("Id not found - " + id));

        return ResponseEntity.ok().body(movie);
    }
    @CrossOrigin
    @PostMapping("")
    public ResponseEntity<String> addTest(@RequestBody Test movie){
        this.testRepository.save(movie);
        return new ResponseEntity<String>("Created", HttpStatus.CREATED);
    }
}
package com.example.beetween.controllers;

import com.example.beetween.models.Test;
import com.example.beetween.services.TestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tests")
public class TestController {

    private final TestService testService;

    @Autowired
    public TestController(TestService testService) {
        this.testService = testService;
    }

    @GetMapping("")
    public ResponseEntity<Iterable<Test>> getAllTests(@RequestParam (defaultValue = "1") int page, @RequestParam (defaultValue = "5") int size){
        page--;
        PageRequest request = PageRequest.of(page,size);
        Iterable<Test> tests = testService.getAllTests(request);
        return ResponseEntity.ok().body(tests);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Test> getTestById(@PathVariable (value="id") long id ) {
        Test test = testService.getTestsById(id);
        return ResponseEntity.ok().body(test);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> delete(@PathVariable("id") long id)
    {
        testService.delete(id);
        return new ResponseEntity<String>("Deleted", HttpStatus.ACCEPTED);
    }

    @PostMapping("")
    public ResponseEntity<String> createTest(@RequestBody Test test){
        testService.createTest(test);
        return new ResponseEntity<String>("Created", HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Test> update(@PathVariable Long id, @RequestBody Test test)
    {
        Test result = testService.update(id, test.getName());
        return ResponseEntity.ok().body(result);
    }

}
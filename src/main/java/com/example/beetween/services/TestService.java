package com.example.beetween.services;


import com.example.beetween.exceptions.ResourceNotFoundException;
import com.example.beetween.models.Test;
import com.example.beetween.repositories.TestRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TestService {

    private final TestRepository testRepository;

    @Autowired
    public TestService(TestRepository testRepository) {
        this.testRepository = testRepository;
    }

    public List<Test> getAllTests() {
        return testRepository.findAll();
    }

    public Test createTest(Test test) {
        return testRepository.save(test);
    }

    public Test getTestsById(long id) {
        return testRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Requested id : " + id + " was not found."));
    }

    public void delete(long id) {
        testRepository.deleteById(id);
    }

    public Test update(long id, String name) {
        Test test = testRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Requested id : " + id + " was not found."));
        test.setName(name);
        return testRepository.save(test);
    }

}

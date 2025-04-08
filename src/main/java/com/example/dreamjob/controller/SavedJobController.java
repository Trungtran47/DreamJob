package com.example.dreamjob.controller;

import com.example.dreamjob.dto.request.SavedJobRequest;
import com.example.dreamjob.dto.response.PostWithCompanyDTO;
import com.example.dreamjob.dto.response.SavedJobResponse;
import com.example.dreamjob.service.SavedJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/saved_job")
public class SavedJobController {
    @Autowired
    private SavedJobService savedJobService;

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody SavedJobRequest savedJobRequest) {
        try {
            SavedJobResponse savedJobResponse = savedJobService.saveJob(savedJobRequest);
            return ResponseEntity.ok(savedJobResponse);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @DeleteMapping("/delete/{userId}/{postId}")
    public ResponseEntity<?> delete(@PathVariable Long postId,@PathVariable Long userId) {
        try {
            savedJobService.deleteSavedJob(userId,postId);
            return ResponseEntity.status(HttpStatus.OK).body("SavedJob deleted");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping(value="/list/{userId}", produces = "application/json; charset=utf-8")
    public ResponseEntity<List<?>> getAll(@PathVariable Long userId) {
        try {
            List<PostWithCompanyDTO> list = savedJobService.getAllSavedJob(userId);
            return ResponseEntity.ok(list);
        }catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

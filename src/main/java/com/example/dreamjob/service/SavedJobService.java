package com.example.dreamjob.service;

import com.example.dreamjob.dto.request.SavedJobRequest;
import com.example.dreamjob.dto.response.PostWithCompanyDTO;
import com.example.dreamjob.dto.response.SavedJobResponse;

import java.util.List;

public interface SavedJobService {
    SavedJobResponse saveJob(SavedJobRequest request);
    void deleteSavedJob(Long userId,Long postId);
    List<PostWithCompanyDTO> getAllSavedJob(Long userId);
}

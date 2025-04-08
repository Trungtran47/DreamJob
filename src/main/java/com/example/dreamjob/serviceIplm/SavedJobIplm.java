package com.example.dreamjob.serviceIplm;

import com.example.dreamjob.dto.request.SavedJobRequest;

import com.example.dreamjob.dto.response.PostWithCompanyDTO;
import com.example.dreamjob.dto.response.SavedJobResponse;
import com.example.dreamjob.entity.PostEntity;
import com.example.dreamjob.entity.SavedJobEntity;
import com.example.dreamjob.mapp.SavedJobMapper;
import com.example.dreamjob.repository.PostRepository;
import com.example.dreamjob.repository.SavedJobRepository;
import com.example.dreamjob.service.SavedJobService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
public class SavedJobIplm implements SavedJobService {
   @Autowired
   private SavedJobRepository savedJobRepository;
   @Autowired
   private PostRepository postRepository;
   @Autowired
   private SavedJobMapper savedJobMapper;
    @Override
    public SavedJobResponse saveJob(SavedJobRequest request) {
        SavedJobEntity savedJobEntity = savedJobRepository.findByPost_PostIdAndUserId( request.getPostId(), request.getUserId());
        if (savedJobEntity != null) {
            savedJobEntity.setId(savedJobEntity.getId());
            savedJobEntity.setUserId(request.getUserId()); // Dù có thể không cần thiết vì đã tồn tại
            savedJobEntity.setSavedAt(LocalDateTime.now());
            savedJobEntity.setPost(postRepository.findById(request.getPostId()).orElseThrow(() -> new RuntimeException("Post not found")));
        } else {
            savedJobEntity = new SavedJobEntity();
            savedJobEntity.setUserId(request.getUserId());
            savedJobEntity.setPost(postRepository.findById(request.getPostId()).orElseThrow(() -> new RuntimeException("Post not found")));
        }
        return savedJobMapper.toSavedJobResponse(savedJobRepository.save(savedJobEntity));
    }


    @Override
    public void deleteSavedJob(Long userId, Long postId) {
          SavedJobEntity savedJobEntity = savedJobRepository.findByPost_PostIdAndUserId(postId, userId);
          if (savedJobEntity != null) {
              savedJobRepository.deleteById(savedJobEntity.getId());
          }
    }

    @Override
    public List<PostWithCompanyDTO> getAllSavedJob(Long userId) {
       try {
        List<PostEntity> savedJobEntityList = postRepository.findBySaved_UserId(userId);
        List<PostWithCompanyDTO> postDTOs = new ArrayList<>();
        LocalDateTime localDateTime = LocalDateTime.now();
        // Lọc và sắp xếp bài đăng theo thời gian tạo tăng dần
        savedJobEntityList.stream()
                .filter(post -> post != null && post.getExpirationDate().isAfter(localDateTime))
                .sorted(Comparator.comparing(PostEntity::getExpirationDate)) // Tăng dần
                .forEach(post -> postDTOs.add(new PostWithCompanyDTO(post)));

        return postDTOs;
    }catch (Exception e){
           throw new RuntimeException(e);
    }
}
}

package com.example.dreamjob.service;

import com.example.dreamjob.dto.PostDTO;
import com.example.dreamjob.dto.response.PostWithCompanyDTO;
import com.example.dreamjob.entity.PostEntity;
import com.example.dreamjob.entity.UserEntity;

import java.util.List;

public interface PostService {
    PostDTO createPost(PostEntity post);
    List<PostEntity> getPosts();
    PostEntity getPost(Long id);
    void deletePost(Long id);
    PostDTO updatePost(Long id,PostEntity post);
    List<PostWithCompanyDTO> getPostsByUserId(Long userId);
    List<PostEntity> getPostByLocation(String location);

}

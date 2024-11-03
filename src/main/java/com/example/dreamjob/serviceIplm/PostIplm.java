package com.example.dreamjob.serviceIplm;

import com.example.dreamjob.dto.PostDTO;
import com.example.dreamjob.dto.response.PostWithCompanyDTO;
import com.example.dreamjob.entity.PostEntity;
import com.example.dreamjob.entity.UserEntity;
import com.example.dreamjob.mapp.PostMapper;
import com.example.dreamjob.repository.PostRepository;
import com.example.dreamjob.repository.UserRepository;
import com.example.dreamjob.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class PostIplm implements PostService {
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private UserRepository userRepository ;
    @Autowired
    private PostMapper postMapper;


    @Override
    public PostDTO createPost(PostEntity post) {
        try {
            return postMapper.toDTO(postRepository.save(post));
        }catch (Exception e){
             throw new RuntimeException(e.getMessage());
        }
    }

    @Override
    public List<PostEntity> getPosts() {
        return postRepository.findAll();
    }

    @Override
    public PostEntity getPost(Long id) {
        return postRepository.findById(id).get();
    }

    @Override
    public void deletePost(Long id) {
         postRepository.deleteById(id);
    }

    @Override
    public PostDTO updatePost(Long id, PostEntity post) {
    try {
        PostEntity postEntity = postRepository.findById(id).
                orElseThrow(() -> new RuntimeException("User không tồn tại."));

            // Cập nhật các thuộc tính từ đối tượng `post`
            postEntity.setPostId(postEntity.getPostId());
            postEntity.setUser(postEntity.getUser());
            postEntity.setTitle(post.getTitle());
            postEntity.setSalary(post.getSalary());
            postEntity.setLocation(post.getLocation());
            postEntity.setExperience(post.getExperience());
            postEntity.setEmploymentType(post.getEmploymentType());
            postEntity.setVacancies(post.getVacancies());
            postEntity.setGender(post.getGender());
            postEntity.setLevel(post.getLevel());
            postEntity.setJobDescription(post.getJobDescription());
            postEntity.setApplicationRequirements(post.getApplicationRequirements());
            postEntity.setBenefits(post.getBenefits());
            postEntity.setWorkLocation(post.getWorkLocation());
            postEntity.setWorkingHours(post.getWorkingHours());
            postEntity.setPostedDate(postEntity.getPostedDate());
            postEntity.setExpirationDate(post.getExpirationDate());
            // Lưu các thay đổi vào database
            return postMapper.toDTO(postRepository.save(postEntity));

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<PostWithCompanyDTO> getPostsByUserId(Long userId) {
          try {
              List<PostEntity>  posts = postRepository.findByUser_UserId(userId);
              List<PostWithCompanyDTO> postDTOs = new ArrayList<>();
              for (PostEntity post : posts) {
                  if (post != null) {
                      PostWithCompanyDTO postDTO = new PostWithCompanyDTO(post);
                      postDTOs.add(postDTO);
                  }
              }
              return postDTOs;
          }catch (Exception e){
              throw new RuntimeException(e.getMessage());
          }
    }

    @Override
    public List<PostEntity> getPostByLocation(String location) {
        return List.of();
    }
}
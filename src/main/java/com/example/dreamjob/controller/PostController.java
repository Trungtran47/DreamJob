package com.example.dreamjob.controller;

import com.example.dreamjob.dto.PostDTO;
import com.example.dreamjob.dto.response.PostWithCompanyDTO;
import com.example.dreamjob.entity.PostEntity;

import com.example.dreamjob.repository.PostRepository;
import com.example.dreamjob.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(value="/employer")
public class PostController {
    @Autowired
    PostRepository postRepository;
    @Autowired
    private PostService postService;

    @PostMapping(value="/post/create")
    public ResponseEntity<?> createPost(@RequestBody PostEntity post) {
        try {
            PostDTO postDTO = postService.createPost(post);
            return ResponseEntity.status(HttpStatus.CREATED).body(postDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
//    @GetMapping("/post/{id}")
//    public ResponseEntity<?> getPostsByUserId(@PathVariable long id) {
//        try {
//            PostEntity postEntity = postService.getPost(id);
//            return ResponseEntity.status(HttpStatus.OK).body(postEntity);
//        }catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
//        }
//    }
@PutMapping("/post/{postId}/update")
public ResponseEntity<?> update(@PathVariable Long postId,@RequestBody PostEntity post) {
    try {
        PostDTO postDTO = postService.updatePost(postId,post);
        System.out.println(ResponseEntity.status(HttpStatus.OK).body(postDTO));
        return ResponseEntity.status(HttpStatus.OK).body(postDTO);
    }catch (Exception e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}
@GetMapping("/post/{postId}")
public ResponseEntity<?> getPostById(@PathVariable Long postId) {
    Optional<PostEntity> postEntityOptional = postRepository.findById(postId);

    if (postEntityOptional.isPresent()) {
        PostWithCompanyDTO postDTO = new PostWithCompanyDTO(postEntityOptional.get());
        return ResponseEntity.ok(postDTO);
    } else {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Post not found");
    }
}
    @GetMapping("/post/all/{userId}")
    public ResponseEntity<List<?>> getAllPostsByUserId(@PathVariable Long userId) {
        try {
            List<PostWithCompanyDTO> posts = postService.getPostsByUserId(userId);
            return ResponseEntity.status(HttpStatus.OK).body(posts);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @DeleteMapping("/delete/post/{postId}")
    public ResponseEntity<?> deletePost(@PathVariable Long postId) {
        try {
            postService.deletePost(postId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("Post deleted");
        }catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }

    }
    @GetMapping("/applicant/post/all")
    public ResponseEntity<List<?>> getAllPosts() {
        try {
            List<PostWithCompanyDTO> posts = postService.getAllPosts();
            return ResponseEntity.status(HttpStatus.OK).body(posts);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}

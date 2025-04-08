package com.example.dreamjob.controller;

import com.example.dreamjob.dto.BlogDTO;
import com.example.dreamjob.entity.BlogEntity;
import com.example.dreamjob.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/blog")
public class BlogController {
    @Autowired
    private BlogService blogService;

    @PostMapping("/create")
    public ResponseEntity<?> createBlog(@RequestParam(value = "user") Long user,
                                        @RequestParam(value = "title") String title,
                                        @RequestParam(value = "content") String content,
                                        @RequestParam(value = "author") String author,
                                        @RequestParam(value = "time") String time,
                                        @RequestParam(value = "image", required = false) MultipartFile image ) {
        try {
            BlogDTO blogDTO = new BlogDTO();
            blogDTO.setUser(user);
            blogDTO.setTitle(title);
            blogDTO.setContent(content);
            blogDTO.setAuthor(author);
            blogDTO.setTime(time);

            BlogDTO blog = blogService.create(blogDTO, image);
            return ResponseEntity.status(HttpStatus.CREATED).body(blog);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @PutMapping("/updateBlog/{blogId}")
    public ResponseEntity<?> updateBlog(@PathVariable Long blogId,
                                        @RequestParam(value = "user") Long user,
                                        @RequestParam(value = "title") String title,
                                        @RequestParam(value = "content") String content,
                                        @RequestParam(value = "author") String author,
                                        @RequestParam(value = "image", required = false) MultipartFile image){
        try {
            BlogDTO blogDTO = new BlogDTO();
            blogDTO.setUser(user);
            blogDTO.setTitle(title);
            blogDTO.setContent(content);
            blogDTO.setAuthor(author);
            BlogDTO blog = blogService.updateBlog(blogId, blogDTO, image);
            return ResponseEntity.status(HttpStatus.OK).body(blog);
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @GetMapping("/listByUserId/{userId}")
    public ResponseEntity<List<BlogDTO>> listBlogs(@PathVariable Long userId) {
        try {
            List<BlogDTO> blog = blogService.getBlogsByUserId(userId);
            return ResponseEntity.status(HttpStatus.OK).body(blog);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @DeleteMapping("/deleteBlog/{blogId}")
    public ResponseEntity<?> deleteBlog(@PathVariable Long blogId) {
        try {
            blogService.deleteBlog(blogId);
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping("/list")
    public ResponseEntity<List<?>> listBlogs() {
        try {
            List<BlogDTO> blogDTOS = blogService.getAllBlogsByStatus();
            return ResponseEntity.status(HttpStatus.OK).body(blogDTOS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

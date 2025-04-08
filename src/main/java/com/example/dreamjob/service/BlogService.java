package com.example.dreamjob.service;

import com.example.dreamjob.dto.BlogDTO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface BlogService {
    BlogDTO create(BlogDTO blog, MultipartFile image);
    List<BlogDTO> getAllBlogsByStatus();
    List<BlogDTO> getBlogsByUserId(Long userId);
    BlogDTO getBlogById(Long id);
    BlogDTO updateBlog(Long blogId, BlogDTO blog,MultipartFile image);
    void deleteBlog(Long id);

    //admin
    List<BlogDTO> getAllBlogs();
    void updateStatus(Long id, int status);
}

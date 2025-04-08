package com.example.dreamjob.Admin.Controller;

import com.example.dreamjob.dto.BlogDTO;
import com.example.dreamjob.service.BlogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/blog")
public class BlogAdminController {
    @Autowired
    private BlogService blogService;
    @GetMapping("/list")
    public ResponseEntity<List<?>> listBlogs() {
        try {
            List<BlogDTO> blogDTOS = blogService.getAllBlogs();
            return ResponseEntity.status(HttpStatus.OK).body(blogDTOS);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
    @PutMapping("updateStatus/{id}/{status}")
    public ResponseEntity<?> updateBlog( @PathVariable Long id, @PathVariable int status) {
        try {
            blogService.updateStatus(id, status);
            return ResponseEntity.status(HttpStatus.OK).build();
        }catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}

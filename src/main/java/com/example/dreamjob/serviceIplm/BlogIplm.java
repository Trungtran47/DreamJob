package com.example.dreamjob.serviceIplm;

import com.example.dreamjob.dto.BlogDTO;
import com.example.dreamjob.entity.BlogEntity;
import com.example.dreamjob.entity.UserEntity;
import com.example.dreamjob.mapp.BlogMapper;
import com.example.dreamjob.repository.BlogRepository;
import com.example.dreamjob.repository.UserRepository;
import com.example.dreamjob.service.BlogService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BlogIplm implements BlogService {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private BlogMapper blogMapper;
    @Autowired
    private UserRepository userRepository;
    @Override
    public BlogDTO create(BlogDTO blog, MultipartFile image) {
        try {
            BlogEntity blogEntity = new BlogEntity();
            if (image != null) {
                String fileName = blog.getBlogId() + "_" + image.getOriginalFilename();
                Path imagePath = Paths.get("uploads/Blog/" + fileName);
                Files.createDirectories(imagePath.getParent());
                Files.write(imagePath, image.getBytes());
                blogEntity.setImage(fileName);
            }
            blogEntity.setUser(userRepository.findById(blog.getUser()).orElseThrow());
            blogEntity.setTitle(blog.getTitle());
            blogEntity.setContent(blog.getContent());
            blogEntity.setAuthor(blog.getAuthor());
            blogEntity.setTime(blog.getTime());
            blogEntity.setStatus(1);
            return blogMapper.toBlogDto(blogRepository.save(blogEntity));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<BlogDTO> getAllBlogsByStatus() {
        try {
            List<BlogEntity> blogEntities = blogRepository.findByStatusOrderByTimeDesc(0);
            return blogMapper.toBlogDtos(blogEntities);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<BlogDTO> getBlogsByUserId(Long userId) {
        try {
            List<BlogEntity> blogEntity = blogRepository.findByUser_UserIdOrderByTimeDesc(userId);
            return blogMapper.toBlogDtos(blogEntity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public BlogDTO getBlogById(Long id) {
        try {
            BlogEntity blogEntity = blogRepository.findById(id).get();
            return blogMapper.toBlogDto(blogEntity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public BlogDTO updateBlog(Long blogId, BlogDTO blog,MultipartFile image) {
        try {
            BlogEntity blogEntity = blogRepository.findById(blogId).get();
            if (image != null) {
                // Xóa ảnh cũ nếu có
                String existingImage = blogEntity.getImage();
                if (existingImage != null) {
                    Path existingAvatarPath = Paths.get("uploads/Blog/" + existingImage);
                    if (Files.exists(existingAvatarPath)) {
                        Files.delete(existingAvatarPath);
                    }
                }
                String fileName = blog.getBlogId() + "_" + image.getOriginalFilename();
                Path imagePath = Paths.get("uploads/Blog/" + fileName);
                Files.createDirectories(imagePath.getParent());
                Files.write(imagePath, image.getBytes());
                blogEntity.setImage(fileName);
            }
            blogEntity.setBlogId(blogId);
            blogEntity.setUser(userRepository.findById(blog.getUser()).orElseThrow());
            blogEntity.setTitle(blog.getTitle());
            blogEntity.setContent(blog.getContent());
            blogEntity.setAuthor(blog.getAuthor());
            blogEntity.setTime(blogEntity.getTime());
            blogEntity.setStatus(blogEntity.getStatus());
            return blogMapper.toBlogDto(blogRepository.save(blogEntity));
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void deleteBlog(Long id) {
        try {
            BlogEntity blogEntity = blogRepository.findById(id).get();
            // Xóa ảnh cũ nếu có
            String existingImage = blogEntity.getImage();
            if (existingImage != null) {
                Path existingAvatarPath = Paths.get("uploads/Blog/" + existingImage);
                if (Files.exists(existingAvatarPath)) {
                    Files.delete(existingAvatarPath);
                }
            }
            blogRepository.deleteById(id);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


    }
 // admin
    @Override
    public List<BlogDTO> getAllBlogs() {
        try {
            List<BlogEntity> blogEntities = blogRepository.findAllByOrderByTimeDesc();
            return blogMapper.toBlogDtos(blogEntities);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void updateStatus(Long id, int status ) {
        try {
            BlogEntity blogEntity = blogRepository.findById(id).get();
            blogEntity.setStatus(status);
            blogRepository.save(blogEntity);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}

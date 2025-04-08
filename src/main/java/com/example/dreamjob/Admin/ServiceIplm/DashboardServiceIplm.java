package com.example.dreamjob.Admin.ServiceIplm;

import com.example.dreamjob.Admin.DTO.DashboardDTO;
import com.example.dreamjob.Admin.Service.DashboardService;
import com.example.dreamjob.repository.BlogRepository;
import com.example.dreamjob.repository.CompanyRepository;
import com.example.dreamjob.repository.PostRepository;
import com.example.dreamjob.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DashboardServiceIplm implements DashboardService {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private BlogRepository blogRepository;

    @Override
    public DashboardDTO dashboardDTO() {
        try {
            DashboardDTO dashboardDTO = new DashboardDTO();
            dashboardDTO.setUserCount(userRepository.countAllUsers());
            dashboardDTO.setJobCount(postRepository.countTotalPosts());
            dashboardDTO.setCompanyCount(companyRepository.countAllCompanies());
            dashboardDTO.setApplicantCount(userRepository.countUsersWithRole1());
            dashboardDTO.setBlogCount(blogRepository.countAllBlogs());
             return dashboardDTO;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}

package com.example.dreamjob.serviceIplm;

import com.example.dreamjob.dto.CompanyDTO;
import com.example.dreamjob.entity.CompanyEntity;
import com.example.dreamjob.entity.UserEntity;
import com.example.dreamjob.mapp.CompanyMapper;
import com.example.dreamjob.repository.CompanyRepository;
import com.example.dreamjob.repository.UserRepository;
import com.example.dreamjob.service.CompanyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CompanyIplm implements CompanyService {
    private static final String UPLOAD_DIR = "uploads/companies/";
    @Autowired
    private CompanyRepository companyRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private final CompanyMapper companyMapper;
    @Override
    public CompanyEntity addCompany(Long userId, CompanyEntity companyEntity, MultipartFile image) {
        try {
            // Đảm bảo thư mục upload tồn tại
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            String fileName = userId + "_" + image.getOriginalFilename();
            Path filePath = Paths.get(UPLOAD_DIR + fileName);
            Files.write(filePath, image.getBytes());
            companyEntity.setCompanyLogo(fileName);

        } catch (IOException e) {
            throw new RuntimeException("Failed to save image", e);
        }
//        companyEntity.setManager(userRepository.findById(userId).get());
        companyEntity.setUser(userRepository.findById(userId).get());
        companyEntity.setCompanyName(companyEntity.getCompanyName());
        return  companyRepository.save(companyEntity);
    }

    @Override
    public CompanyEntity updateCompany(Long id, Long userId, CompanyEntity companyEntity, MultipartFile image) {
        CompanyEntity company = companyRepository.findById(id).orElseThrow(() -> new RuntimeException("Company not found"));
        try {
            // Đảm bảo thư mục upload tồn tại
            File uploadDir = new File(UPLOAD_DIR);
            if (!uploadDir.exists()) {
                uploadDir.mkdirs();
            }
            if (image != null && !image.isEmpty()) {
                String fileName = userId + "_" + image.getOriginalFilename();
                Path filePath = Paths.get(UPLOAD_DIR + fileName);
                Files.write(filePath, image.getBytes());
                company.setCompanyLogo(fileName);
                System.out.println(fileName);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to save image", e);
        }
        company.setCompanyName(companyEntity.getCompanyName());
        company.setCompanyLocation(companyEntity.getCompanyLocation());
        company.setCompanySize(companyEntity.getCompanySize());
        company.setCompanyWebsite(companyEntity.getCompanyWebsite());
        company.setCompanyCategory(companyEntity.getCompanyCategory());
        company.setCompanyIntroduce(companyEntity.getCompanyIntroduce());
        return companyRepository.save(company);
    }

    @Override
    public CompanyDTO getCompanyByUserId(Long id) {
        return companyMapper.toCompanyDto(companyRepository.findByUser_UserId(id));
    }

    @Override
    public List<CompanyDTO> getAllCompanies() {
        List<CompanyEntity> company = companyRepository.findAll();
        return companyMapper.toCompanyDtoList(company);
    }
}

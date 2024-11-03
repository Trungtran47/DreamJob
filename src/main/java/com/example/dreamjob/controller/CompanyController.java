package com.example.dreamjob.controller;

import com.example.dreamjob.entity.CompanyEntity;
import com.example.dreamjob.entity.UserEntity;
import com.example.dreamjob.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping(value= "/company")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @GetMapping("/{userId}")
    public ResponseEntity<?> getCompanyByUserId(@PathVariable Long userId) {
        try {
            CompanyEntity companyEntity = companyService.getCompanyByUserId(userId);
            return ResponseEntity.status(HttpStatus.OK).body(companyEntity);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @PostMapping(value="/save", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> saveCompany(@RequestParam(value="userId", required = true) Long userId ,
                                         @RequestParam(value="companyName") String companyName,
                                         @RequestParam(value = "companyIntroduce") String companyIntroduce,
                                         @RequestParam(value = "companyLocation") String companyLocation,
                                         @RequestParam(value = "companyWebsite") String companyWebsite,
                                         @RequestParam(value="companySize") String companySize,
                                         @RequestParam(value="companyLogo", required = false) MultipartFile companyLogo) {
        try {
            if (userId == null ) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("userId are required.");
            }
            CompanyEntity companyEntity = new CompanyEntity();
            companyEntity.setCompanyName(companyName);
            companyEntity.setCompanyIntroduce(companyIntroduce);
            companyEntity.setCompanyLocation(companyLocation);
            companyEntity.setCompanyWebsite(companyWebsite);
            companyEntity.setCompanySize(companySize);
            CompanyEntity company =  companyService.addCompany(userId, companyEntity, companyLogo);
            return ResponseEntity.status(HttpStatus.CREATED).body(company);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
    @PutMapping(value="/update/{companyId}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<?> updateCompany(@PathVariable Long companyId,
                                         @RequestParam(value="userId", required = true) Long userId ,
                                         @RequestParam(value="companyName") String companyName,
                                         @RequestParam(value = "companyIntroduce") String companyIntroduce,
                                         @RequestParam(value = "companyLocation") String companyLocation,
                                         @RequestParam(value = "companyWebsite") String companyWebsite,
                                         @RequestParam(value="companySize") String companySize,
                                         @RequestParam(value="companyLogo", required = false) MultipartFile companyLogo) {
        try {
            if (userId == null ) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("userId are required.");
            }

            CompanyEntity companyEntity = new CompanyEntity();
            companyEntity.setCompanyName(companyName);
            companyEntity.setCompanyIntroduce(companyIntroduce);
            companyEntity.setCompanyLocation(companyLocation);
            companyEntity.setCompanyWebsite(companyWebsite);
            companyEntity.setCompanySize(companySize);

            CompanyEntity company =  companyService.updateCompany(companyId,userId, companyEntity, companyLogo);
            return ResponseEntity.status(HttpStatus.CREATED).body(company);
        }catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}

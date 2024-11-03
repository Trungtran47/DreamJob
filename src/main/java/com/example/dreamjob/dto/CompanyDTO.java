package com.example.dreamjob.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CompanyDTO {
    private Long companyId;
    private String companyName;
    private String companyIntroduce;
    private String companyLocation;
    private String companyWebsite;
    private String companySize;
    private String companyLogo;
}

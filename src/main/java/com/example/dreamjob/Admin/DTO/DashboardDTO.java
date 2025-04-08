package com.example.dreamjob.Admin.DTO;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class DashboardDTO {
    private Long userCount;
    private Long companyCount;
    private Long blogCount;
    private Long jobCount;
    private Long applicantCount;
}

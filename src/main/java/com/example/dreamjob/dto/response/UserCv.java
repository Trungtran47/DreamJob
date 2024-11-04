package com.example.dreamjob.dto.response;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCv {
    private int userId;
    private String cv;
}

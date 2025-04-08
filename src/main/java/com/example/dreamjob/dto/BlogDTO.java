package com.example.dreamjob.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BlogDTO {
    private Long blogId;
    private Long user;
    private String title;
    private String content;
    private String author;
    private String image;
    private String time;
    private  int status;
}

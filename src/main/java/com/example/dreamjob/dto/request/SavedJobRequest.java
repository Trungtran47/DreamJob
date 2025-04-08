package com.example.dreamjob.dto.request;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SavedJobRequest {
    private Long id;
    private Long userId;
    private Long postId;
}

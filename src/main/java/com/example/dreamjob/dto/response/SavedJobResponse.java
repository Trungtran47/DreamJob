package com.example.dreamjob.dto.response;

import com.example.dreamjob.dto.PostDTO;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SavedJobResponse {
    private Long id;
    private Long userId;
//    private PostDTO postId;
}

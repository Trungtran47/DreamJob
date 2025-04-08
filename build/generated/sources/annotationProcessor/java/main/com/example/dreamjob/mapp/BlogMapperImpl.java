package com.example.dreamjob.mapp;

import com.example.dreamjob.dto.BlogDTO;
import com.example.dreamjob.entity.BlogEntity;
import com.example.dreamjob.entity.UserEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class BlogMapperImpl implements BlogMapper {

    @Override
    public BlogDTO toBlogDto(BlogEntity blogEntity) {

        BlogDTO.BlogDTOBuilder blogDTO = BlogDTO.builder();

        if ( blogEntity != null ) {
            blogDTO.user( blogEntityUserUserId( blogEntity ) );
            blogDTO.blogId( blogEntity.getBlogId() );
            blogDTO.title( blogEntity.getTitle() );
            blogDTO.content( blogEntity.getContent() );
            blogDTO.author( blogEntity.getAuthor() );
            blogDTO.image( blogEntity.getImage() );
            blogDTO.time( blogEntity.getTime() );
            blogDTO.status( blogEntity.getStatus() );
        }

        return blogDTO.build();
    }

    @Override
    public List<BlogDTO> toBlogDtos(List<BlogEntity> blogEntities) {
        if ( blogEntities == null ) {
            return new ArrayList<BlogDTO>();
        }

        List<BlogDTO> list = new ArrayList<BlogDTO>( blogEntities.size() );
        for ( BlogEntity blogEntity : blogEntities ) {
            list.add( toBlogDto( blogEntity ) );
        }

        return list;
    }

    private Long blogEntityUserUserId(BlogEntity blogEntity) {
        if ( blogEntity == null ) {
            return null;
        }
        UserEntity user = blogEntity.getUser();
        if ( user == null ) {
            return null;
        }
        Long userId = user.getUserId();
        if ( userId == null ) {
            return null;
        }
        return userId;
    }
}

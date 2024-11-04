package com.example.dreamjob.mapp;

import com.example.dreamjob.dto.PostDTO;
import com.example.dreamjob.dto.response.PostWithCompanyDTO;
import com.example.dreamjob.entity.PostEntity;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.processing.Generated;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2024-11-05T02:24:25+0700",
    comments = "version: 1.5.2.Final, compiler: IncrementalProcessingEnvironment from gradle-language-java-8.10.2.jar, environment: Java 17.0.12 (Amazon.com Inc.)"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PostDTO toDTO(PostEntity postEntity) {
        if ( postEntity == null ) {
            return null;
        }

        PostDTO postDTO = new PostDTO();

        postDTO.setPostId( postEntity.getPostId() );
        postDTO.setTitle( postEntity.getTitle() );
        postDTO.setSalary( postEntity.getSalary() );
        postDTO.setLocation( postEntity.getLocation() );
        postDTO.setExperience( postEntity.getExperience() );
        postDTO.setEmploymentType( postEntity.getEmploymentType() );
        postDTO.setVacancies( postEntity.getVacancies() );
        postDTO.setGender( postEntity.getGender() );
        postDTO.setLevel( postEntity.getLevel() );
        postDTO.setJobDescription( postEntity.getJobDescription() );
        postDTO.setApplicationRequirements( postEntity.getApplicationRequirements() );
        postDTO.setBenefits( postEntity.getBenefits() );
        postDTO.setWorkLocation( postEntity.getWorkLocation() );
        postDTO.setWorkingHours( postEntity.getWorkingHours() );
        postDTO.setPostedDate( postEntity.getPostedDate() );
        postDTO.setExpirationDate( postEntity.getExpirationDate() );
        postDTO.setUser( userMapper.UserToUserDTO( postEntity.getUser() ) );

        return postDTO;
    }

    @Override
    public PostWithCompanyDTO toWithCompanyDTO(PostEntity postEntity) {
        if ( postEntity == null ) {
            return null;
        }

        PostWithCompanyDTO.PostWithCompanyDTOBuilder postWithCompanyDTO = PostWithCompanyDTO.builder();

        postWithCompanyDTO.postId( postEntity.getPostId() );
        postWithCompanyDTO.title( postEntity.getTitle() );
        postWithCompanyDTO.salary( postEntity.getSalary() );
        postWithCompanyDTO.location( postEntity.getLocation() );
        postWithCompanyDTO.experience( postEntity.getExperience() );
        postWithCompanyDTO.employmentType( postEntity.getEmploymentType() );
        postWithCompanyDTO.vacancies( postEntity.getVacancies() );
        postWithCompanyDTO.gender( postEntity.getGender() );
        postWithCompanyDTO.level( postEntity.getLevel() );
        postWithCompanyDTO.jobDescription( postEntity.getJobDescription() );
        postWithCompanyDTO.applicationRequirements( postEntity.getApplicationRequirements() );
        postWithCompanyDTO.benefits( postEntity.getBenefits() );
        postWithCompanyDTO.workLocation( postEntity.getWorkLocation() );
        postWithCompanyDTO.workingHours( postEntity.getWorkingHours() );
        postWithCompanyDTO.postedDate( postEntity.getPostedDate() );
        postWithCompanyDTO.expirationDate( postEntity.getExpirationDate() );

        return postWithCompanyDTO.build();
    }

    @Override
    public List<PostWithCompanyDTO> toWithCompanyDTOs(List<PostEntity> postEntities) {
        if ( postEntities == null ) {
            return null;
        }

        List<PostWithCompanyDTO> list = new ArrayList<PostWithCompanyDTO>( postEntities.size() );
        for ( PostEntity postEntity : postEntities ) {
            list.add( toWithCompanyDTO( postEntity ) );
        }

        return list;
    }
}

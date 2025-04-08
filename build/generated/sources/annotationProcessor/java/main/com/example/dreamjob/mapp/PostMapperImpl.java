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
    value = "org.mapstruct.ap.MappingProcessor"
)
@Component
public class PostMapperImpl implements PostMapper {

    @Autowired
    private UserMapper userMapper;

    @Override
    public PostDTO toDTO(PostEntity postEntity) {

        PostDTO.PostDTOBuilder postDTO = PostDTO.builder();

        if ( postEntity != null ) {
            postDTO.postId( postEntity.getPostId() );
            postDTO.title( postEntity.getTitle() );
            postDTO.salary( postEntity.getSalary() );
            postDTO.location( postEntity.getLocation() );
            postDTO.experience( postEntity.getExperience() );
            postDTO.employmentType( postEntity.getEmploymentType() );
            postDTO.vacancies( postEntity.getVacancies() );
            postDTO.gender( postEntity.getGender() );
            postDTO.level( postEntity.getLevel() );
            postDTO.jobDescription( postEntity.getJobDescription() );
            postDTO.applicationRequirements( postEntity.getApplicationRequirements() );
            postDTO.benefits( postEntity.getBenefits() );
            postDTO.workLocation( postEntity.getWorkLocation() );
            postDTO.workingHours( postEntity.getWorkingHours() );
            postDTO.postedDate( postEntity.getPostedDate() );
            postDTO.expirationDate( postEntity.getExpirationDate() );
            postDTO.user( userMapper.UserToUserDTO( postEntity.getUser() ) );
        }

        return postDTO.build();
    }

    @Override
    public PostWithCompanyDTO toWithCompanyDTO(PostEntity postEntity) {

        PostWithCompanyDTO.PostWithCompanyDTOBuilder postWithCompanyDTO = PostWithCompanyDTO.builder();

        if ( postEntity != null ) {
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
        }

        return postWithCompanyDTO.build();
    }

    @Override
    public List<PostWithCompanyDTO> toWithCompanyDTOs(List<PostEntity> postEntities) {
        if ( postEntities == null ) {
            return new ArrayList<PostWithCompanyDTO>();
        }

        List<PostWithCompanyDTO> list = new ArrayList<PostWithCompanyDTO>( postEntities.size() );
        for ( PostEntity postEntity : postEntities ) {
            list.add( toWithCompanyDTO( postEntity ) );
        }

        return list;
    }
}

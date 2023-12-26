package com.furnifinders.backend.Mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.furnifinders.backend.Entity.User;
import com.furnifinders.backend.dto.Request.EditProfileRequest;

@Mapper(componentModel = "spring")
public interface UserMapper {
    
    @Mapping(target = "user_first_name", source = "user_first_name")
    @Mapping(target = "user_last_name", source = "user_last_name")
    @Mapping(target = "user_email", source = "user_email")
    @Mapping(target = "user_phone", source = "user_phone")
    @Mapping(target = "user_id", ignore = true)
    @Mapping(target = "user_role", ignore = true)
    @Mapping(target = "user_password", ignore = true)
    @Mapping(target = "productUserLinkList", ignore = true)
    @Mapping(target = "cartList", ignore = true)
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void mergeProfileRequestToUserProfile(@MappingTarget User user, EditProfileRequest editProfileRequest);
}

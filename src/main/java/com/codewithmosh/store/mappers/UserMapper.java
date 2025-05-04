package com.codewithmosh.store.mappers;

import com.codewithmosh.store.dtos.UserDTO;
import com.codewithmosh.store.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    UserDTO toDTO(User user);
}

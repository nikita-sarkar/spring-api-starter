package com.codewithmosh.store.mappers;

import com.codewithmosh.store.dtos.UserDTO;
import com.codewithmosh.store.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDTO toDTO(User user);
}

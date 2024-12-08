package com.stefanyshyn.airbnbcloneback.user.mapper;

import com.stefanyshyn.airbnbcloneback.user.application.dto.ReadUserDTO;
import com.stefanyshyn.airbnbcloneback.user.domain.Authority;
import com.stefanyshyn.airbnbcloneback.user.domain.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    ReadUserDTO readUserDTOToUser(User user);

    default String mapAuthoritiesToString(Authority authority) {
        return authority.getName();
    }
}

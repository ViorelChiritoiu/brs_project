package com.brs.dto.mapper;

/*@Mapper
public interface UserMapper {
    UserDto toUserDto(UserAccount userAccount);
    List<UserDto> toUserDtos(List<UserAccount> users);
    UserAccount toUserAccount(UserDto userDtoDto);
}*/

import com.brs.dto.model.user.RoleDto;
import com.brs.dto.model.user.UserDto;
import com.brs.model.user.UserAccount;
import org.modelmapper.ModelMapper;

import java.util.HashSet;
import java.util.stream.Collectors;

public class UserMapper {

    public static UserDto toUserDto(UserAccount userAccount) {

        UserDto userDto = new UserDto();

        userDto.setEmail(userAccount.getEmail());
        userDto.setFirstName(userAccount.getFirstName());
        userDto.setLastName(userAccount.getLastName());
        userDto.setMobileNumber(userAccount.getMobileNumber());
        userDto.setRoles(new HashSet<RoleDto>(userAccount
                .getRoles()
                .stream()
                .map(role -> new ModelMapper().map(role, RoleDto.class))
                .collect(Collectors.toSet())));

        return userDto;
    }
}

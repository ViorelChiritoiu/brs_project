package com.brs.service.user;

import com.brs.dto.model.user.UserDto;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    UserDto signUp(UserDto userDto);
    UserDto findUserByEmail(String email);
    UserDto updateProfile(UserDto userDto);
    UserDto changePassword(UserDto userDto, String newPassword);
}

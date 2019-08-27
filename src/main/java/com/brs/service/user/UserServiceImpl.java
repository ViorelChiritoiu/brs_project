package com.brs.service.user;

import com.brs.dto.mapper.UserMapper;
import com.brs.dto.model.user.UserDto;
import com.brs.exception.BRSException;
import com.brs.exception.EntityType;
import com.brs.exception.ExceptionType;
import com.brs.model.user.Role;
import com.brs.model.user.UserAccount;
import com.brs.model.user.UserRoles;
import com.brs.repository.user.RoleRepository;
import com.brs.repository.user.UserRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    private UserMapper userMapper;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public UserDto signUp(UserDto userDto) {
        Role userRole;
        UserAccount userAccount = userRepository.findByEmail(userDto.getEmail());
        if(userAccount == null) {
            if(userDto.isAdmin()) {
                userRole = roleRepository.findByRole(UserRoles.ADMIN.name());
            } else {
                userRole = roleRepository.findByRole(UserRoles.PASSENGER.name());
            }
            userAccount = new UserAccount();
            userAccount.setEmail(userDto.getEmail());
            userAccount.setPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
            userAccount.setRoles(new HashSet<Role>(Arrays.asList(userRole)));
            userAccount.setFirstName(userDto.getFirstName());
            userAccount.setLastName(userDto.getLastName());
            userAccount.setMobileNumber(userDto.getMobileNumber());
            return userMapper.toUserDto(userRepository.save(userAccount));
        }
        throw exception(EntityType.USER, ExceptionType.DUPLICATE_ENTITY, userDto.getEmail());
    }


    /**
     * Search an existing user
     *
     * @param email
     * @return
     */
    @Override
    public UserDto findUserByEmail(String email) {
        Optional<UserAccount> userAccount = Optional.ofNullable(userRepository.findByEmail(email));
        if(userAccount.isPresent()) {
            return modelMapper.map(userAccount.get(), UserDto.class);
        }
        throw exception(EntityType.USER, ExceptionType.ENTITY_NOT_FOUND, email);
    }

    @Override
    public UserDto updateProfile(UserDto userDto) {
        Optional<UserAccount> userAccount = Optional.ofNullable(userRepository.findByEmail(userDto.getEmail()));
        if(userAccount.isPresent()) {
            UserAccount userModel = userAccount.get();
            userModel.setFirstName(userDto.getFirstName());
            userModel.setLastName(userDto.getLastName());
            userModel.setMobileNumber(userDto.getMobileNumber());
            return userMapper.toUserDto(userRepository.save(userModel));
        }
        throw exception(EntityType.USER, ExceptionType.ENTITY_NOT_FOUND, userDto.getEmail());
    }


    /**
     * Change Password
     *
     * @param userDto
     * @param newPassword
     * @return
     */
    @Override
    public UserDto changePassword(UserDto userDto, String newPassword) {
        Optional<UserAccount> userAccount = Optional.ofNullable(userRepository.findByEmail(userDto.getEmail()));
        if(userAccount.isPresent()) {
            UserAccount userModel = userAccount.get();
            userModel.setPassword(bCryptPasswordEncoder.encode(newPassword));
            return userMapper.toUserDto(userRepository.save(userModel));
        }
        throw exception(EntityType.USER, ExceptionType.ENTITY_NOT_FOUND, userDto.getEmail());
    }

    /**
     * Returns a new RuntimeException
     *
     * @param entityType
     * @param exceptionType
     * @param args
     * @return
     */
    private RuntimeException exception(EntityType entityType, ExceptionType exceptionType, String... args) {
        return BRSException.throwException(entityType, exceptionType, args);
    }
}

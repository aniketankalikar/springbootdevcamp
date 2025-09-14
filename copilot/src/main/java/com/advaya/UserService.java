package com.advaya;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class UserService {
    private final Map<Long, UserDTO> users = new ConcurrentHashMap<>();
    private long idCounter = 1;

    @Autowired
    private UserServiceUtil userServiceUtil;

    public UserDTO createUser(UserDTO userDTO) {
        // Validate input
        if (userDTO == null) {
            throw UserException.UserExceptions.invalidUserData("userDTO cannot be null");
        }
        if (userDTO.getUsername() == null || userDTO.getUsername().trim().isEmpty()) {
            throw UserException.UserExceptions.invalidUserData("username");
        }
        if (userDTO.getEmail() == null || userDTO.getEmail().trim().isEmpty()) {
            throw UserException.UserExceptions.invalidUserData("email");
        }

        // Check if username already exists
        boolean usernameExists = users.values().stream()
            .anyMatch(user -> user.getUsername().equals(userDTO.getUsername()));
        if (usernameExists) {
            throw UserException.UserExceptions.userAlreadyExists(userDTO.getUsername());
        }

        try {
            userDTO.setId(idCounter++);
            users.put(userDTO.getId(), userDTO);
            System.out.println("User created with id=" + userDTO.getId());
            return userDTO;
        } catch (Exception e) {
            throw UserException.UserExceptions.userCreationFailed(e.getMessage());
        }
    }

    public UserDTO updateUser(Long id, UserDTO userDTO) {
        if (id == null) {
            throw UserException.UserExceptions.invalidUserData("id cannot be null");
        }
        if (!users.containsKey(id)) {
            throw UserException.UserExceptions.userNotFound(id);
        }

        // Validate input
        if (userDTO == null) {
            throw UserException.UserExceptions.invalidUserData("userDTO cannot be null");
        }
        if (userDTO.getUsername() == null || userDTO.getUsername().trim().isEmpty()) {
            throw UserException.UserExceptions.invalidUserData("username");
        }
        if (userDTO.getEmail() == null || userDTO.getEmail().trim().isEmpty()) {
            throw UserException.UserExceptions.invalidUserData("email");
        }

        try {
            userDTO.setId(id);
            users.put(id, userDTO);
            System.out.println("User updated with id=" + id);
            return userDTO;
        } catch (Exception e) {
            throw UserException.UserExceptions.userUpdateFailed(id, e.getMessage());
        }
    }

    public boolean deleteUser(Long id) {
        if (id == null) {
            throw UserException.UserExceptions.invalidUserData("id cannot be null");
        }

        if (!users.containsKey(id)) {
            throw UserException.UserExceptions.userNotFound(id);
        }

        try {
            boolean removed = users.remove(id) != null;
            System.out.println("User deleted with id=" + id);
            return removed;
        } catch (Exception e) {
            throw UserException.UserExceptions.userDeletionFailed(id);
        }
    }

    public UserDTO getUser(Long id) {
        if (id == null) {
            throw UserException.UserExceptions.invalidUserData("id cannot be null");
        }

        UserDTO user = users.get(id);
        if (user == null) {
            throw UserException.UserExceptions.userNotFound(id);
        }
        return user;
    }

    public List<UserDTO> getAllUsers() {

        userServiceUtil.isValidEmail("aniket@clayfin.com");
        return new ArrayList<>(users.values());
    }

    public UserDTO getUserByName(String username) throws UserException {
        if (username == null || username.trim().isEmpty()) {
            throw UserException.UserExceptions.invalidUserData("username cannot be null or empty");
        }

        return users.values().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> UserException.UserExceptions.userNotFound(null));
    }
}

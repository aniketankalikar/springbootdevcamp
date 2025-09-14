package com.advaya;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserDTO> createUser(@RequestBody UserDTO userDTO) {
        try {
            UserDTO createdUser = userService.createUser(userDTO);
            return new ResponseEntity<>(createdUser, HttpStatus.CREATED);
        } catch (UserException e) {
            return handleUserException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<UserDTO> updateUser(@PathVariable Long id, @RequestBody UserDTO userDTO) {
        try {
            UserDTO updatedUser = userService.updateUser(id, userDTO);
            return new ResponseEntity<>(updatedUser, HttpStatus.OK);
        } catch (UserException e) {
            return handleUserException(e);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        try {
            userService.deleteUser(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (UserException e) {
            return new ResponseEntity<>(getHttpStatusForException(e));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUser(@PathVariable Long id) {
        try {
            UserDTO user = userService.getUser(id);
            return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (UserException e) {
            return handleUserException(e);
        }
    }

    @GetMapping
    public ResponseEntity<List<UserDTO>> getAllUsers() {
        List<UserDTO> users = userService.getAllUsers();
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    private ResponseEntity<UserDTO> handleUserException(UserException e) {
        HttpStatus status = getHttpStatusForException(e);
        // Log the exception for debugging
        System.err.println("UserException: " + e);
        return new ResponseEntity<>(status);
    }

    private HttpStatus getHttpStatusForException(UserException e) {
        String errorCode = e.getErrorCode();
        switch (errorCode) {
            case "USER_NOT_FOUND":
                return HttpStatus.NOT_FOUND;
            case "USER_ALREADY_EXISTS":
                return HttpStatus.CONFLICT;
            case "INVALID_USER_DATA":
                return HttpStatus.BAD_REQUEST;
            case "USER_CREATION_FAILED":
            case "USER_UPDATE_FAILED":
            case "USER_DELETION_FAILED":
                return HttpStatus.INTERNAL_SERVER_ERROR;
            default:
                return HttpStatus.INTERNAL_SERVER_ERROR;
        }
    }


}
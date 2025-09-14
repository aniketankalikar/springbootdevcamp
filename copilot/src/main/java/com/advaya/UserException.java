package com.advaya;

/**
 * Custom exception for user-related operations.
 * This exception is thrown when user operations fail due to business logic violations
 * or data integrity issues.
 */
public class UserException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String errorCode;
    private  Long userId =0L;
    private  String username="";

    /**
     * Default constructor
     */
    public UserException() {
        super();
        this.errorCode = "USER_ERROR";
        this.userId = null;
    }

    /**
     * Constructor with message
     * @param message the detail message
     */
    public UserException(String message) {
        super(message);
        this.errorCode = "USER_ERROR";
        this.userId = null;
    }

    /**
     * Constructor with message and error code
     * @param message the detail message
     * @param errorCode the error code
     */
    public UserException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.userId = null;
    }

    /**
     * Constructor with message, error code and user ID
     * @param message the detail message
     * @param errorCode the error code
     * @param userId the ID of the user related to this exception
     */
    public UserException(String message, String errorCode, Long userId) {
        super(message);
        this.errorCode = errorCode;
        this.userId = userId;
    }

    /**
     * Constructor with cause
     * @param cause the cause of this exception
     */
    public UserException(Throwable cause) {
        super(cause);
        this.errorCode = "USER_ERROR";
        this.userId = null;
    }

    /**
     * Constructor with message and cause
     * @param message the detail message
     * @param cause the cause of this exception
     */
    public UserException(String message, Throwable cause) {
        super(message, cause);
        this.errorCode = "USER_ERROR";
        this.userId = null;
    }

    /**
     * Constructor with message, error code and cause
     * @param message the detail message
     * @param errorCode the error code
     * @param cause the cause of this exception
     */
    public UserException(String message, String errorCode, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.userId = null;
    }

    /**
     * Full constructor with all parameters
     * @param message the detail message
     * @param errorCode the error code
     * @param userId the user ID
     * @param cause the cause of this exception
     */
    public UserException(String message, String errorCode, Long userId, Throwable cause) {
        super(message, cause);
        this.errorCode = errorCode;
        this.userId = userId;
    }

    public UserException(String s, String userNotFoundByUsername, String username) {
        super(s);
        this.errorCode = userNotFoundByUsername;
        this.username = username;
    }

    /**
     * Gets the error code
     * @return the error code
     */
    public String getErrorCode() {
        return errorCode;
    }

    /**
     * Gets the user ID related to this exception
     * @return the user ID, or null if not applicable
     */
    public Long getUserId() {
        return userId;
    }

    /**
     * Static factory methods for common user exceptions
     */
    public static class UserExceptions {

        public static UserException userNotFound(Long userId) {
            return new UserException(
                "User not found with ID: " + userId,
                "USER_NOT_FOUND",
                userId
            );
        }

        public static UserException userAlreadyExists(String username) {
            return new UserException(
                "User already exists with username: " + username,
                "USER_ALREADY_EXISTS"
            );
        }

        public static UserException invalidUserData(String field) {
            return new UserException(
                "Invalid user data for field: " + field,
                "INVALID_USER_DATA"
            );
        }

        public static UserException userCreationFailed(String reason) {
            return new UserException(
                "Failed to create user: " + reason,
                "USER_CREATION_FAILED"
            );
        }

        public static UserException userUpdateFailed(Long userId, String reason) {
            return new UserException(
                "Failed to update user: " + reason,
                "USER_UPDATE_FAILED",
                userId
            );
        }

        public static UserException userDeletionFailed(Long userId) {
            return new UserException(
                "Failed to delete user with ID: " + userId,
                "USER_DELETION_FAILED",
                userId
            );
        }

        public static UserException userNotFoundByUsername(String username) {
            return new UserException(
                    "User not found by name : " + username,
                    "USER NOT_FOUND_BY_USERNAME",
                    username
            );
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName()).append(": ");

        if (errorCode != null) {
            sb.append("[").append(errorCode).append("] ");
        }

        if (getMessage() != null) {
            sb.append(getMessage());
        }

        if (userId != null) {
            sb.append(" (User ID: ").append(userId).append(")");
        }

        return sb.toString();
    }
}

package com.advaya;

public class BeneficiaryException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    private final String errorCode;
    private Long beneficiaryId = 0L;
    private String beneficiaryName = "";

    /**
     * Default constructor
     */
    public BeneficiaryException() {
        super();
        this.errorCode = "BENEFICIARY_ERROR";
        this.beneficiaryId = null;
    }

    /**
     * Constructor with message
     *
     * @param message the detail message
     */
    public BeneficiaryException(String message) {
        super(message);
        this.errorCode = "BENEFICIARY_ERROR";
        this.beneficiaryId = null;
    }

    /**
     * Constructor with message and error code
     *
     * @param message   the detail message
     * @param errorCode the error code
     */
    public BeneficiaryException(String message, String errorCode) {
        super(message);
        this.errorCode = errorCode;
        this.beneficiaryId = null;
    }

    /**
     * Constructor with message, error code and beneficiary ID
     *
     * @param message       the detail message
     * @param errorCode     the error code
     * @param beneficiaryId the ID of the beneficiary related to this exception
     */
    public BeneficiaryException(String message, String errorCode, Long beneficiaryId) {
        super(message);
        this.errorCode = errorCode;
        this.beneficiaryId = beneficiaryId;
    }

    /**
     * Constructor with cause
     *
     * @param cause the cause of this exception
     */
    public BeneficiaryException(Throwable cause) {
        super(cause);
        this.errorCode = "BENEFICIARY_ERROR";
        this.beneficiaryId = null;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public Long getBeneficiaryId() {
        return beneficiaryId;
    }


}

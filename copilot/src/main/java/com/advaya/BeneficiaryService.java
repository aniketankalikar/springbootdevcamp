package com.advaya;

import org.springframework.stereotype.Service;

@Service
public class BeneficiaryService {


    public BeneficiaryDTO getBeneficiaryById(Long id) throws BeneficiaryException
    {
        // Dummy implementation for illustration
        if (id == null || id <= 0) {
            throw new BeneficiaryException("Invalid beneficiary ID", "INVALID_ID", id);
        }
        // In a real implementation, fetch the beneficiary from a database
        return new BeneficiaryDTO(id, "John Doe", "aniket@clayfin.com");

    }
}

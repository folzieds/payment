package com.threeline.payment.repository;


import com.threeline.payment.model.ContractingInstitutionWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ContractingInstitutionRepository
        extends JpaRepository<ContractingInstitutionWallet, Long>, JpaSpecificationExecutor<ContractingInstitutionWallet> {
}

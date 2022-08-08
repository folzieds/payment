package com.threeline.payment.repository;


import com.threeline.payment.model.ContractingInstitutionWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ContractingInstitutionRepository
        extends JpaRepository<ContractingInstitutionWallet, Long>, JpaSpecificationExecutor<ContractingInstitutionWallet> {

    @Query(value = "select  s.account_number from ContractingInstitutionWallet s order by id desc limit 1", nativeQuery = true)
    String findLastAccountNo();

    ContractingInstitutionWallet findByName(String name);


}

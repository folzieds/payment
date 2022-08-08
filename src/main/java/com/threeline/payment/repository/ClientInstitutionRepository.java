package com.threeline.payment.repository;

import com.threeline.payment.model.ClientInstitutionWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ClientInstitutionRepository
    extends JpaRepository<ClientInstitutionWallet, Long>, JpaSpecificationExecutor<ClientInstitutionWallet> {

    @Query(value = "select  s.account_number from client_institution_wallet s order by id desc limit 1", nativeQuery = true)
    String findLastAccountNo();

    ClientInstitutionWallet findByName(String name);
}

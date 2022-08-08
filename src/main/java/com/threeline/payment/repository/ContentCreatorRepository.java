package com.threeline.payment.repository;

import com.threeline.payment.model.ContentCreatorWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface ContentCreatorRepository
        extends JpaRepository<ContentCreatorWallet, Long>, JpaSpecificationExecutor<ContentCreatorWallet> {

    @Query(value = "select  s.account_number from ContentCreatorWallet s order by id desc limit 1", nativeQuery = true)
    String findLastAccountNo();

    ContentCreatorWallet findByAccountNo(String accountNo);
}

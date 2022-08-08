package com.threeline.payment.repository;

import com.threeline.payment.model.ContentCreatorWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ContentCreatorRepository
        extends JpaRepository<ContentCreatorWallet, Long>, JpaSpecificationExecutor<ContentCreatorWallet> {
}

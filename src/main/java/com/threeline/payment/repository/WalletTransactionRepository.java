package com.threeline.payment.repository;

import com.threeline.payment.model.WalletTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface WalletTransactionRepository
        extends JpaRepository<WalletTransaction, Long>, JpaSpecificationExecutor<WalletTransaction> {


}

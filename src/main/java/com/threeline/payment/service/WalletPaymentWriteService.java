package com.threeline.payment.service;

import com.threeline.payment.data.CreatorDepositData;
import com.threeline.payment.data.WalletData;
import org.springframework.http.ResponseEntity;

public interface WalletPaymentWriteService {
    ResponseEntity create(WalletData data);

    ResponseEntity deposit(CreatorDepositData data);
}

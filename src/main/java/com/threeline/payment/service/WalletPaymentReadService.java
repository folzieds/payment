package com.threeline.payment.service;

import com.threeline.payment.model.WalletTransaction;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WalletPaymentReadService {

    List<WalletTransaction> fetchAllTransactions();

    ResponseEntity fetchAllWallets(String accountType);
}

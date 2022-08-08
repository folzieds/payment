package com.threeline.payment.service;

import com.threeline.payment.data.WalletTransactionResponseData;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface WalletPaymentReadService {

    List<WalletTransactionResponseData> fetchAllTransactions();

    ResponseEntity fetchAllWallets(String accountType);
}

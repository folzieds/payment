package com.threeline.payment.service;

import com.threeline.payment.data.ContentCreatorTransactionData;

import java.util.List;

public interface WalletPaymentReadService {

    List<ContentCreatorTransactionData> fetchAll();
}

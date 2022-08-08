package com.threeline.payment.service;

import com.threeline.payment.model.WalletTransaction;

import java.util.List;

public interface WalletPaymentReadService {

    List<WalletTransaction> fetchAll();
}

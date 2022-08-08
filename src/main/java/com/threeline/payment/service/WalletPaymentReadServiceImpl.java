package com.threeline.payment.service;

import com.threeline.payment.data.ContentCreatorTransactionData;
import com.threeline.payment.model.WalletTransaction;
import com.threeline.payment.repository.WalletTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletPaymentReadServiceImpl implements WalletPaymentReadService{

    private final WalletTransactionRepository walletTransactionRepository;

    @Autowired
    public WalletPaymentReadServiceImpl(WalletTransactionRepository walletTransactionRepository) {
        this.walletTransactionRepository = walletTransactionRepository;
    }

    @Override
    public List<WalletTransaction> fetchAll() {
        return walletTransactionRepository.findAll();
    }
}

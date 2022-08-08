package com.threeline.payment.service;

import com.threeline.payment.data.ContentCreatorTransactionData;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WalletPaymentReadServiceImpl implements WalletPaymentReadService{

    @Override
    public List<ContentCreatorTransactionData> fetchAll() {
        return null;
    }
}

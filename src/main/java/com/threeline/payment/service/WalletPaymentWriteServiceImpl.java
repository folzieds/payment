package com.threeline.payment.service;

import com.threeline.payment.data.CreatorDepositData;
import com.threeline.payment.data.WalletData;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class WalletPaymentWriteServiceImpl implements WalletPaymentWriteService {

    @Override
    public ResponseEntity create(WalletData data) {
        try{
            if(data != null && data.getAccountType().equalsIgnoreCase("content-creator")){
                return createContentCreator(data);
            }else if(data != null && data.getAccountType().equalsIgnoreCase("client")){
                return createContentCreator(data);
            }else if(data != null && data.getAccountType().equalsIgnoreCase("")){
                return createContentCreator(data);
            }
        }catch (Exception ex){

        }
        return null;
    }

    private ResponseEntity createContentCreator(WalletData data) {
        return null;
    }

    @Override
    public ResponseEntity deposit(CreatorDepositData data) {
        return null;
    }
}

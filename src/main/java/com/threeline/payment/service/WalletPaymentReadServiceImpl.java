package com.threeline.payment.service;

import com.threeline.payment.data.ContentCreatorTransactionData;
import com.threeline.payment.model.ClientInstitutionWallet;
import com.threeline.payment.model.ContentCreatorWallet;
import com.threeline.payment.model.ContractingInstitutionWallet;
import com.threeline.payment.model.WalletTransaction;
import com.threeline.payment.repository.ClientInstitutionRepository;
import com.threeline.payment.repository.ContentCreatorRepository;
import com.threeline.payment.repository.ContractingInstitutionRepository;
import com.threeline.payment.repository.WalletTransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class WalletPaymentReadServiceImpl implements WalletPaymentReadService{

    private final WalletTransactionRepository walletTransactionRepository;
    private final ClientInstitutionRepository clientInstitutionRepository;
    private final ContentCreatorRepository contentCreatorRepository;
    private final ContractingInstitutionRepository contractingInstitutionRepository;

    @Autowired
    public WalletPaymentReadServiceImpl(WalletTransactionRepository walletTransactionRepository,
                                        ClientInstitutionRepository clientInstitutionRepository,
                                        ContentCreatorRepository contentCreatorRepository,
                                        ContractingInstitutionRepository contractingInstitutionRepository) {
        this.walletTransactionRepository = walletTransactionRepository;
        this.clientInstitutionRepository = clientInstitutionRepository;
        this.contentCreatorRepository = contentCreatorRepository;
        this.contractingInstitutionRepository = contractingInstitutionRepository;
    }

    @Override
    public List<WalletTransaction> fetchAllTransactions() {
        return walletTransactionRepository.findAll();
    }

    @Override
    public ResponseEntity fetchAllWallets(String accountType) {
        if(accountType.equalsIgnoreCase("content-creator")){
            List<ContentCreatorWallet> wallets = contentCreatorRepository.findAll();
            return ResponseEntity.ok(Map.of("status","success", "data", wallets));
        }
        else if(accountType.equalsIgnoreCase("client-institution")){
            List<ClientInstitutionWallet> wallets = clientInstitutionRepository.findAll();
            return ResponseEntity.ok(Map.of("status","success", "data", wallets));
        }
        else if(accountType.equalsIgnoreCase("contracting-institution")){
            List<ContractingInstitutionWallet> wallets = contractingInstitutionRepository.findAll();
            return ResponseEntity.ok(Map.of("status","success", "data", wallets));
        }
        return ResponseEntity.ok(Map.of("status","success", "data", new ArrayList<>()));
    }
}

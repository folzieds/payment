package com.threeline.payment.service;

import com.threeline.payment.data.WalletResponseData;
import com.threeline.payment.data.WalletTransactionResponseData;
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
import java.util.Optional;

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
    public List<WalletTransactionResponseData> fetchAllTransactions() {
        List<WalletTransaction> transactions = walletTransactionRepository.findAll();
        return mapTransactionsToResponseData(transactions);
    }

    private List<WalletTransactionResponseData> mapTransactionsToResponseData(List<WalletTransaction> transactions) {
        List<WalletTransactionResponseData> dataList = new ArrayList<>();
        for (WalletTransaction transaction :
                transactions) {
            WalletTransactionResponseData data = new WalletTransactionResponseData();

            data.setTransactionType(transaction.getTransactionType());
            data.setAmount(transaction.getAmount());
            data.setAccountNo(transaction.getAccountNo());
            data.setDescription(transaction.getDescription());

            dataList.add(data);
        }

        return dataList;
    }

    @Override
    public ResponseEntity fetchAllWallets(String accountType) {
        if(accountType.equalsIgnoreCase("creator")){
            List<ContentCreatorWallet> wallets = contentCreatorRepository.findAll();
            List<WalletResponseData> dataList = mapContentWalletToResponseData(wallets, accountType);
            return ResponseEntity.ok(Map.of("status","success", "data", dataList));
        }
        else if(accountType.equalsIgnoreCase("client")){
            List<ClientInstitutionWallet> wallets = clientInstitutionRepository.findAll();
            List<WalletResponseData> dataList = mapClientWalletToResponseData(wallets, accountType);
            return ResponseEntity.ok(Map.of("status","success", "data", dataList));
        }
        else if(accountType.equalsIgnoreCase("contracting")){
            List<ContractingInstitutionWallet> wallets = contractingInstitutionRepository.findAll();
            List<WalletResponseData> dataList = mapContractingWalletToResponseData(wallets, accountType);
            return ResponseEntity.ok(Map.of("status","success", "data", dataList));
        }
        return ResponseEntity.ok(Map.of("status","success", "data", new ArrayList<>()));
    }

    private List<WalletResponseData> mapContentWalletToResponseData(List<ContentCreatorWallet> wallets, String accountType) {
        List<WalletResponseData> dataList = new ArrayList<>();
        for (ContentCreatorWallet wallet :
                wallets) {
            WalletResponseData data = new WalletResponseData();
            data.setName(String.format("%s %s",wallet.getFirstName(), wallet.getLastName()));
            data.setAccountNo(wallet.getAccountNo());

            dataList.add(data);
        }
        return dataList;
    }

    private List<WalletResponseData> mapClientWalletToResponseData(List<ClientInstitutionWallet> wallets, String accountType) {
        List<WalletResponseData> dataList = new ArrayList<>();

        for (ClientInstitutionWallet wallet :
                wallets) {
            WalletResponseData data = new WalletResponseData();
            data.setName(wallet.getName());
            data.setAccountNo(wallet.getAccountNo());

            dataList.add(data);
        }

        return dataList;
    }

    private List<WalletResponseData> mapContractingWalletToResponseData(List<ContractingInstitutionWallet> wallets, String accountType) {
        List<WalletResponseData> dataList = new ArrayList<>();

        for (ContractingInstitutionWallet wallet :
                wallets) {
            WalletResponseData data = new WalletResponseData();
            data.setName(wallet.getName());
            data.setAccountNo(wallet.getAccountNo());

            dataList.add(data);
        }

        return dataList;
    }
}

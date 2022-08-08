package com.threeline.payment.service;

import com.threeline.payment.data.CreatorDepositData;
import com.threeline.payment.data.WalletData;
import com.threeline.payment.model.ClientInstitutionWallet;
import com.threeline.payment.model.ContentCreatorWallet;
import com.threeline.payment.model.ContractingInstitutionWallet;
import com.threeline.payment.repository.ClientInstitutionRepository;
import com.threeline.payment.repository.ContentCreatorRepository;
import com.threeline.payment.repository.ContractingInstitutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class WalletPaymentWriteServiceImpl implements WalletPaymentWriteService {

    private final ClientInstitutionRepository clientInstitutionRepository;

    private final ContentCreatorRepository contentCreatorRepository;

    private final ContractingInstitutionRepository contractingInstitutionRepository;

    @Autowired
    public WalletPaymentWriteServiceImpl(ClientInstitutionRepository clientInstitutionRepository,
                                         ContentCreatorRepository contentCreatorRepository,
                                         ContractingInstitutionRepository contractingInstitutionRepository) {
        this.clientInstitutionRepository = clientInstitutionRepository;
        this.contentCreatorRepository = contentCreatorRepository;
        this.contractingInstitutionRepository = contractingInstitutionRepository;
    }

    @Override
    public ResponseEntity create(WalletData data) {
        try{
            if(data != null && data.getAccountType().equalsIgnoreCase("content-creator")){
                return createContentCreator(data);
            }else if(data != null && data.getAccountType().equalsIgnoreCase("client-institution")){
                return createClientInstitution(data);
            }else if(data != null && data.getAccountType().equalsIgnoreCase("contracting-institution")){
                return createContractingInstitution(data);
            }
        }catch (Exception ex){

        }
        return null;
    }

    private ResponseEntity createContractingInstitution(WalletData data) {

        ContractingInstitutionWallet wallet = MapDataToContractingWallet(data);

        contractingInstitutionRepository.save(wallet);
        return ResponseEntity.ok().body(Map.of("status", "success"));
    }

    private ContractingInstitutionWallet MapDataToContractingWallet(WalletData data) {
        String businessName = data.getBusinessName();
        String accountType = data.getAccountType();

        String accountNo;
        String lastAccountNo = contractingInstitutionRepository.findLastAccountNo();

        if(lastAccountNo == null)
            accountNo = generateAccountNo(accountType);
        else
            accountNo = String.format("%s%d",lastAccountNo.substring(0,1),Long.parseLong(lastAccountNo) + 1L);

        BigDecimal amount = BigDecimal.ZERO;

        ContractingInstitutionWallet wallet = ContractingInstitutionWallet.isInstance(businessName, amount,accountNo);

        return wallet;
    }

    private String generateAccountNo(String accountType) {
        Long number = Math.round(Math.random() * 1000000000);
        StringBuilder accountNo = new StringBuilder(10);
        if(accountType.equalsIgnoreCase("contracting-institution")){
            accountNo.append(String.format("2%d", number));
        }else if(accountType.equalsIgnoreCase("client-institution")) {
            accountNo.append(String.format("3%d", number));
        }else{
            accountNo.append(String.format("1%d", number));
        }
        return accountNo.toString();
    }

    private ResponseEntity createClientInstitution(WalletData data) {

        ClientInstitutionWallet wallet = MapDataToClientWallet(data);
        clientInstitutionRepository.save(wallet);

        return ResponseEntity.ok().body(Map.of("status", "success"));
    }

    private ClientInstitutionWallet MapDataToClientWallet(WalletData data) {
        String businessName = data.getBusinessName();
        String accountType = data.getAccountType();

        String accountNo;
        String lastAccountNo = contractingInstitutionRepository.findLastAccountNo();

        if(lastAccountNo == null)
            accountNo = generateAccountNo(accountType);
        else
            accountNo = String.format("%s%d",lastAccountNo.substring(0,1),Long.parseLong(lastAccountNo) + 1L);

        BigDecimal amount = BigDecimal.ZERO;

        ClientInstitutionWallet wallet = ClientInstitutionWallet.isInstance(businessName, amount,accountNo);

        return wallet;
    }

    private ResponseEntity createContentCreator(WalletData data) {

        ContentCreatorWallet wallet = MapDataToCreatorWallet(data);
        contentCreatorRepository.save(wallet);

        return ResponseEntity.ok().body(Map.of("status", "success"));
    }

    private ContentCreatorWallet MapDataToCreatorWallet(WalletData data) {
        return null;
    }

    @Override
    public ResponseEntity deposit(CreatorDepositData data) {

        return ResponseEntity.ok().body(Map.of("status", "success"));
    }
}

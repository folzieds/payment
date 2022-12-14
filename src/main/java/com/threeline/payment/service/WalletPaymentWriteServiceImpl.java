package com.threeline.payment.service;

import com.threeline.payment.data.WalletDepositData;
import com.threeline.payment.data.WalletData;
import com.threeline.payment.exception.ResourceCreationException;
import com.threeline.payment.model.ClientInstitutionWallet;
import com.threeline.payment.model.ContentCreatorWallet;
import com.threeline.payment.model.ContractingInstitutionWallet;
import com.threeline.payment.model.WalletTransaction;
import com.threeline.payment.repository.ClientInstitutionRepository;
import com.threeline.payment.repository.ContentCreatorRepository;
import com.threeline.payment.repository.ContractingInstitutionRepository;
import com.threeline.payment.repository.WalletTransactionRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

@Service
public class WalletPaymentWriteServiceImpl implements WalletPaymentWriteService {

    private final Logger logger = LoggerFactory.getLogger(WalletPaymentWriteServiceImpl.class);

    private final ClientInstitutionRepository clientInstitutionRepository;
    private final ContentCreatorRepository contentCreatorRepository;
    private final ContractingInstitutionRepository contractingInstitutionRepository;
    private final WalletTransactionRepository walletTransactionRepository;

    @Autowired
    public WalletPaymentWriteServiceImpl(ClientInstitutionRepository clientInstitutionRepository,
                                         ContentCreatorRepository contentCreatorRepository,
                                         ContractingInstitutionRepository contractingInstitutionRepository,
                                         WalletTransactionRepository walletTransactionRepository) {
        this.clientInstitutionRepository = clientInstitutionRepository;
        this.contentCreatorRepository = contentCreatorRepository;
        this.contractingInstitutionRepository = contractingInstitutionRepository;
        this.walletTransactionRepository = walletTransactionRepository;
    }

    @Override
    public ResponseEntity create(WalletData data) {
        logger.info("Creating wallet...");
        try{
            if(data != null && data.getAccountType() != null && data.getAccountType().equalsIgnoreCase("contracting")){
                return createContractingInstitution(data);

            }else if(data != null && data.getAccountType() != null && data.getAccountType().equalsIgnoreCase("client")){
                return createClientInstitution(data);
            }else if(data != null && data.getAccountType() != null && data.getAccountType().equalsIgnoreCase("creator")){
                return createContentCreator(data);
            }else{
                throw new ResourceCreationException("Account Type not passed");
            }
        }catch (Exception ex){
            throw new ResourceCreationException("Could not create the account...");
        }
    }

    private ResponseEntity createContractingInstitution(WalletData data) {

        ContractingInstitutionWallet wallet = MapDataToContractingWallet(data);

        contractingInstitutionRepository.save(wallet);
        logger.info("Contracting institution wallet successfully created");
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
        if(accountType.equalsIgnoreCase("contracting")){
            accountNo.append(String.format("2%d", number));
        }else if(accountType.equalsIgnoreCase("client")) {
            accountNo.append(String.format("3%d", number));
        }else{
            accountNo.append(String.format("1%d", number));
        }
        return accountNo.toString();
    }

    private ResponseEntity createClientInstitution(WalletData data) {

        ClientInstitutionWallet wallet = MapDataToClientWallet(data);
        clientInstitutionRepository.save(wallet);

        logger.info("Client institution wallet successfully created");
        return ResponseEntity.ok().body(Map.of("status", "success"));
    }

    private ClientInstitutionWallet MapDataToClientWallet(WalletData data) {
        String businessName = data.getBusinessName();
        String accountType = data.getAccountType();

        String accountNo;
        String lastAccountNo = clientInstitutionRepository.findLastAccountNo();

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

        logger.info("Content Creator wallet sucessfully created");
        return ResponseEntity.ok().body(Map.of("status", "success"));
    }

    private ContentCreatorWallet MapDataToCreatorWallet(WalletData data) {
        String firstname = data.getFirstname();
        String lastname = data.getLastname();
        String email = data.getEmail();
        String accountType = data.getAccountType();

        BigDecimal accountBalance = BigDecimal.ZERO;
        String accountNo;


        String lastAccountNo = contentCreatorRepository.findLastAccountNo();

        if(lastAccountNo == null)
            accountNo = generateAccountNo(accountType);
        else
            accountNo = String.format("%s%d",lastAccountNo.substring(0,1),Long.parseLong(lastAccountNo) + 1L);

        String clientInstitutionName = data.getClientInstitutionName();
        ClientInstitutionWallet institutionWallet = clientInstitutionRepository.findByName(clientInstitutionName);

        String contractingInstitutionName = data.getContractingInstitutionName();
        ContractingInstitutionWallet contractingInstitutionWallet = contractingInstitutionRepository.findByName(contractingInstitutionName);

        ContentCreatorWallet wallet = ContentCreatorWallet.instance(firstname,  lastname,  accountNo,
                email,  institutionWallet,  contractingInstitutionWallet, accountBalance);

        return wallet;
    }

    @Override
    public ResponseEntity deposit(WalletDepositData data) {

        String accountNo = data.getAccountNo();
        BigDecimal amount = data.getAmount();

        BigDecimal clientAmount = amount.multiply(BigDecimal.valueOf(0.1));
        BigDecimal contractingInstitutionAmount = amount.multiply(BigDecimal.valueOf(0.05));
        BigDecimal contentCreatorAmount = amount.multiply(BigDecimal.valueOf(0.85));

        ContentCreatorWallet wallet =  contentCreatorRepository.findByAccountNo(accountNo);
        wallet.deposit(contentCreatorAmount);
        contentCreatorRepository.saveAndFlush(wallet);

        WalletTransaction contentCreatorTransaction = WalletTransaction.build(contentCreatorAmount,
                accountNo, "deposit", data.getDescription());
        walletTransactionRepository.save(contentCreatorTransaction);

        ClientInstitutionWallet clientInstitutionWallet = wallet.getClientInstitutionWallet();
        clientInstitutionWallet.deposit(clientAmount);
        clientInstitutionRepository.saveAndFlush(clientInstitutionWallet);

        WalletTransaction clientInstitutionTransaction = WalletTransaction.build(clientAmount,
                clientInstitutionWallet.getAccountNo(), "Charge", "Client Institution Charge");
        walletTransactionRepository.save(clientInstitutionTransaction);

        ContractingInstitutionWallet contractingInstitutionWallet = wallet.getContractingInstitutionWallet();
        contractingInstitutionWallet.deposit(contractingInstitutionAmount);
        contractingInstitutionRepository.saveAndFlush(contractingInstitutionWallet);

        WalletTransaction contractingInstitutionTransaction = WalletTransaction.build(contractingInstitutionAmount,
                contractingInstitutionWallet.getAccountNo(), "Charge", "Contracting Institution Charge");
        walletTransactionRepository.save(contractingInstitutionTransaction);

        return ResponseEntity.ok().body(Map.of("status", "success"));
    }
}

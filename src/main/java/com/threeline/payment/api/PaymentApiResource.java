package com.threeline.payment.api;

import com.threeline.payment.data.CreatorDepositData;
import com.threeline.payment.data.WalletData;
import com.threeline.payment.model.WalletTransaction;
import com.threeline.payment.service.WalletPaymentReadService;
import com.threeline.payment.service.WalletPaymentWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("payment")
public class PaymentApiResource {


    private final WalletPaymentWriteService walletPaymentWriteService;
    private final WalletPaymentReadService walletPaymentReadService;

    @Autowired
    public PaymentApiResource(WalletPaymentWriteService walletPaymentService, WalletPaymentReadService walletPaymentReadService) {
        this.walletPaymentWriteService = walletPaymentService;
        this.walletPaymentReadService = walletPaymentReadService;
    }

    @PostMapping("wallet")
    public ResponseEntity createWallet(@RequestBody WalletData data){
        return walletPaymentWriteService.create(data);
    }

    @GetMapping("wallet")
    public ResponseEntity fetchWallets(@RequestParam String accountType){
        return walletPaymentReadService.fetchAllWallets(accountType);
    }

    @PostMapping("deposit")
    public ResponseEntity deposit(@RequestBody CreatorDepositData data){
        return walletPaymentWriteService.deposit(data);
    }

    @GetMapping("transactions")
    public ResponseEntity transactions(){
        List<WalletTransaction> transactions = walletPaymentReadService.fetchAllTransactions();

        return ResponseEntity.ok()
                .body(Map.of("status", "Success", "data", transactions));
    }

    @GetMapping("health")
    public ResponseEntity getHealthStatus(){
        return ResponseEntity.ok().body(Map.of("status","running"));
    }
}

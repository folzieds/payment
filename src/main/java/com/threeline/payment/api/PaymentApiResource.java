package com.threeline.payment.api;

import com.threeline.payment.data.WalletData;
import com.threeline.payment.service.WalletPaymentWriteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("payment")
public class PaymentApiResource {

    @Autowired
    WalletPaymentWriteService walletPaymentService;

    @PostMapping("wallet")
    public ResponseEntity createWallet(@RequestBody WalletData data){
        walletPaymentService.create(data);
        return ResponseEntity.ok().body(Map.of("status","running"));
    }

    @GetMapping("health")
    public ResponseEntity getHealthStatus(){
        return ResponseEntity.ok().body(Map.of("status","running"));
    }
}

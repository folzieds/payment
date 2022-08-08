package com.threeline.payment.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletDepositResponseData {

    private String accountNo;
    private BigDecimal amount;
}

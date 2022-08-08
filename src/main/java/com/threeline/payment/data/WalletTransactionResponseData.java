package com.threeline.payment.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletTransactionResponseData {

    private BigDecimal amount;
    private String accountNo;
    private String transactionType;
    private String description;
}

package com.threeline.payment.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "wallet_transaction")
public class WalletTransaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "amount")
    private BigDecimal amount;

    @Column(name = "account_no")
    private String accountNo;

    @Column(name = "transaction_type")
    private String transactionType;

    @Column(name = "description")
    private String description;

    protected WalletTransaction(){}

    private WalletTransaction(BigDecimal amount, String accountNo, String transactionType, String description){
        this.accountNo = accountNo;
        this.amount = amount;
        this.transactionType = transactionType;
        this.description = description;
    }

    public static WalletTransaction build(BigDecimal amount, String accountNo, String transactionType, String description){
        return new WalletTransaction(amount, accountNo, transactionType, description);
    }
}

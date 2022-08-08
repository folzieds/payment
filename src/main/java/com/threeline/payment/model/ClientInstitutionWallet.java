package com.threeline.payment.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Client_institution_wallet")
public class ClientInstitutionWallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "account_balance")
    private BigDecimal accountBalance;

    @Column(name = "account_no")
    private String accountNo;



    private ClientInstitutionWallet(String name, BigDecimal accountBalance, String accountNo) {
        this.name = name;
        this.accountBalance = accountBalance;
        this.accountNo = accountNo;
    }

    protected ClientInstitutionWallet() {}

    public static ClientInstitutionWallet isInstance(String businessName, BigDecimal amount, String accountNo) {
        return new ClientInstitutionWallet(businessName, amount, accountNo);
    }

    public  void deposit(BigDecimal amount){
        this.accountBalance.add(amount);
    }

    public String getAccountNo() {
        return accountNo;
    }
}

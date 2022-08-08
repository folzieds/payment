package com.threeline.payment.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "contracting_institution_wallet")
public class ContractingInstitutionWallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", unique = true)
    private String name;

    @Column(name = "account_balance")
    private BigDecimal accountBalance;

    @Column(name = "account_no")
    private String accountNo;


    private ContractingInstitutionWallet(String name, BigDecimal accountBalance, String accountNo) {
        this.name = name;
        this.accountBalance = accountBalance;
        this.accountNo = accountNo;
    }

    protected ContractingInstitutionWallet() {}


    public static ContractingInstitutionWallet isInstance(String name, BigDecimal amount, String accountNo){
        return new ContractingInstitutionWallet(name, amount,accountNo);
    }

    public  void deposit(BigDecimal amount){
        this.accountBalance.add(amount);
    }

    public String getAccountNo() {
        return accountNo;
    }
}

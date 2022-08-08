package com.threeline.payment.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "content_creator_wallet")
public class ContentCreatorWallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "firstname")
    private String firstName;

    @Column(name = "lasrname")
    private String lastName;

    @Column(name = "account_no")
    private String accountNo;

    @Column(name = "email")
    private String email;

    @ManyToOne
    @JoinColumn(name = "client_institution_id", nullable = false)
    private ClientInstitutionWallet clientInstitutionWallet;

    @ManyToOne
    @JoinColumn(name = "contracting_institution_id")
    private ContractingInstitutionWallet contractingInstitutionWallet;

    @Column(name = "account_balance")
    private BigDecimal accountBalance;

    protected ContentCreatorWallet(){}


    private ContentCreatorWallet(String firstName, String lastName, String accountNo, String email, ClientInstitutionWallet clientInstitutionWallet, ContractingInstitutionWallet contractingInstitutionWallet, BigDecimal accountBalance) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.accountNo = accountNo;
        this.email = email;
        this.clientInstitutionWallet = clientInstitutionWallet;
        this.contractingInstitutionWallet = contractingInstitutionWallet;
        this.accountBalance = accountBalance;
    }

    public static ContentCreatorWallet instance(String firstName, String lastName, String accountNo, String email, ClientInstitutionWallet clientInstitutionWallet, ContractingInstitutionWallet contractingInstitutionWallet, BigDecimal accountBalance){
        return new ContentCreatorWallet( firstName,  lastName,  accountNo,  email,  clientInstitutionWallet,  contractingInstitutionWallet, accountBalance);
    }

    public ClientInstitutionWallet getClientInstitutionWallet() {
        return clientInstitutionWallet;
    }

    public ContractingInstitutionWallet getContractingInstitutionWallet() {
        return contractingInstitutionWallet;
    }

    public  void deposit(BigDecimal amount){
        this.accountBalance.add(amount);
    }
}

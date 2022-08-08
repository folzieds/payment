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
    private Double accountBalance;

    @Column(name = "account_no")
    private BigDecimal accountNo;

}

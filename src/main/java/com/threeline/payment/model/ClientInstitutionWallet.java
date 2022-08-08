package com.threeline.payment.model;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "Client_institution_wallet")
public class ClientInstitutionWallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "account_balance")
    private BigDecimal accountBalance;


}

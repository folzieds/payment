package com.threeline.payment.model;

import javax.persistence.*;

@Entity
@Table(name = "Client_institution_wallet")
public class ClientInstitutionWallet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}

package com.threeline.payment.model;

import javax.persistence.*;

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
}

package com.threeline.payment.data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class WalletData {

    private String firstname;
    private String lastname;
    private String email;
    private String accountType;
    private String businessName;
}

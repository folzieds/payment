package com.threeline.payment.repository;

import com.threeline.payment.model.ClientInstitutionWallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface ClientInstitutionRepository
    extends JpaRepository<ClientInstitutionWallet, Long>, JpaSpecificationExecutor<ClientInstitutionWallet> {
}

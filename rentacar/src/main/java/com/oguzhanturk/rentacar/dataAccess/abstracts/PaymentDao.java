package com.oguzhanturk.rentacar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oguzhanturk.rentacar.entities.concretes.Payment;

@Repository
public interface PaymentDao extends JpaRepository<Payment, Integer> {

	Payment getPaymentByInvoiceInvoiceId(int invoiceNo);
}

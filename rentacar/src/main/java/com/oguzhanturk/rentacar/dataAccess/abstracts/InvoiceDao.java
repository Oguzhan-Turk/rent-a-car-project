package com.oguzhanturk.rentacar.dataAccess.abstracts;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oguzhanturk.rentacar.entities.concretes.Invoice;
@Repository
public interface InvoiceDao extends JpaRepository<Invoice, Integer> {
//	List<Invoice> findAllByBillingDateLessThanEqualAndBillingDateGreaterThanEqual(LocalDate startDate, LocalDate endDate);
	List<Invoice> findByBillingDateBetween(LocalDate startDate, LocalDate finishDate);	
}

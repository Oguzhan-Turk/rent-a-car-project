package com.oguzhanturk.rentacar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oguzhanturk.rentacar.entities.concretes.CreditCard;

@Repository
public interface CreditCardDao extends JpaRepository<CreditCard, Integer> {

}
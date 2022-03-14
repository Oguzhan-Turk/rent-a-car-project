package com.oguzhanturk.rentacar.dataAccess.abstracts;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oguzhanturk.rentacar.entities.abstracts.User;

@Repository
public interface UserDao extends JpaRepository<User, Integer> {

}

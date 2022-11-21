package com.cybage.ecommerce.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cybage.ecommerce.entities.User;
@Repository
public interface UserRepository extends JpaRepository<User, Long>{

}

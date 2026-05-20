package com.ecommerce.user.repository;



import com.ecommerce.user.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User,Long> {

}
//JpaRepository<User, Long>
//
//Means:
//
//Part	Meaning
//User	This repository manages User entity
//Long	The primary key (id) type is Long

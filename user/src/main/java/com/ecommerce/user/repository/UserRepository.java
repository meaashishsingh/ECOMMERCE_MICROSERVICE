package com.ecommerce.user.repository;



import com.ecommerce.user.models.User;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends MongoRepository<User, String> {

}
//JpaRepository<User, Long>
//
//Means:
//
//Part	Meaning
//User	This repository manages User entity
//Long	The primary key (id) type is Long

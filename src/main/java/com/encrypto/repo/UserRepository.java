package com.encrypto.repo;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.encrypto.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer>{

}

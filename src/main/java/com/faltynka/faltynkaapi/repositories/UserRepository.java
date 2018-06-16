package com.faltynka.faltynkaapi.repositories;


import com.faltynka.faltynkaapi.model.User;
import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@EnableScan
@Repository
public interface UserRepository extends CrudRepository<User, String> {

    List<User> findByEmail(String email);
}

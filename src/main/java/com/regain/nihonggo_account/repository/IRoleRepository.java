package com.regain.nihonggo_account.repository;

import com.regain.nihonggo_account.model.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository  extends JpaRepository<Role, Long> {
    Optional<Role> findByName(String name);
}

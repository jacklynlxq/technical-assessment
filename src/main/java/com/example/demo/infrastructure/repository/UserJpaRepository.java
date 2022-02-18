package com.example.demo.infrastructure.repository;

import com.example.demo.domain.repository.UserRepository;
import com.example.demo.infrastructure.entity.UserJpaEntity;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserJpaRepository extends UserRepository, CrudRepository<UserJpaEntity, Long> {
    UserJpaEntity findByUserEmail(String email);

}

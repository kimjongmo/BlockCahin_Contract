package com.contract.common.repository;

import com.contract.common.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    Optional<User> findById(Long id); //유저의 아이디가 아니라 유저의 번호를 가져와 전체를 받아 올 수 있도록한다.
    Optional<User> findByUserId(String id);
    boolean existsByUserId(String userId);
}

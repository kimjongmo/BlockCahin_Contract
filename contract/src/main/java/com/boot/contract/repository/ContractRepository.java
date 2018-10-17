package com.boot.contract.repository;

import com.boot.contract.model.Contract;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContractRepository extends JpaRepository<Contract,Long> {
    List<Contract> findByUserId(Long userId);


}

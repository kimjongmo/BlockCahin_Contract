package com.boot.contract.service;

import com.boot.contract.model.Contract;
import com.boot.contract.repository.ContractRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContractService {
    @Autowired
    private ContractRepository contractRepository;

    public List<Contract> selectAll(Long userId){
        return contractRepository.findByUserId(userId);
    }

    public void save(Contract contract){
        contractRepository.save(contract);
    }

    public Contract findById(Long id){ return contractRepository.findById(id).orElse(new Contract());}
}

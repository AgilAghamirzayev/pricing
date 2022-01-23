package com.hackathon.pricing.service.impl;

import com.hackathon.pricing.exception.classes.RecordNotFoundException;
import com.hackathon.pricing.repo.BranchRepo;
import com.hackathon.pricing.service.BranchService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class BranchServiceImpl implements BranchService {

    private final BranchRepo branchRepo;

    @Override
    public String getAddressById(Long id) {
        return branchRepo.findById(id).orElseThrow(()->new RecordNotFoundException("Branch not found by phone id")).getAddress();
    }
}

package com.hackathon.pricing.repo;

import com.hackathon.pricing.model.entity.BranchEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BranchRepo  extends JpaRepository<BranchEntity, Long> {

}

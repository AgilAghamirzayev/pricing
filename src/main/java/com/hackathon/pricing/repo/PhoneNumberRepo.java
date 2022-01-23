package com.hackathon.pricing.repo;

import com.hackathon.pricing.model.entity.PhoneNumberEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PhoneNumberRepo extends JpaRepository<PhoneNumberEntity, Long> {
    List<PhoneNumberEntity> findAllByNumberLike(String pattern);
}

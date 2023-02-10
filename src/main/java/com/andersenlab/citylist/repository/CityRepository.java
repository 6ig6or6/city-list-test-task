package com.andersenlab.citylist.repository;

import com.andersenlab.citylist.entity.CityEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends JpaRepository<CityEntity, Long> {
    Page<CityEntity> findAllByName(String name, Pageable pageable);
}

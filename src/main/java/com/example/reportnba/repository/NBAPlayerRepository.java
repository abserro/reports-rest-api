package com.example.reportnba.repository;

import com.example.reportnba.entity.NBAPlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NBAPlayerRepository extends JpaRepository<NBAPlayerEntity, Long> {
}

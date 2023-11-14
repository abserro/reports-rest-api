package com.example.reportnba.service;

import com.example.reportnba.entity.NBAPlayerEntity;
import com.example.reportnba.repository.NBAPlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class NBAPlayerService {
    private final NBAPlayerRepository repository;

    @Autowired
    public NBAPlayerService(NBAPlayerRepository repository) {
        this.repository = repository;
    }

    public List<Object> getLongestCareers() {
        return repository.findTop20Players();
    }

    public List<Object> getCountPlayersByTeam() {
        return repository.countPlayersByTeam();
    }
}

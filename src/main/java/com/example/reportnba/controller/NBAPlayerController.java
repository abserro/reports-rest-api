package com.example.reportnba.controller;

import com.example.reportnba.repository.NBAPlayerRepository;
import com.example.reportnba.service.NBAPlayerService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "NBAReport")
@RestController
@RequestMapping(path = "/v1/")
public class NBAPlayerController {
    private final NBAPlayerService service;

    @Autowired
    public NBAPlayerController(NBAPlayerRepository repository) {
        this.service = new NBAPlayerService(repository);
    }

    @GetMapping("longest-careers")
    public ResponseEntity<List<Object>> getLongestCareers() {
        List<Object> players = service.getLongestCareers();
        return players.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(players);
    }

    @GetMapping("count-players-by-team")
    public ResponseEntity<List<Object>> getCountPlayersByTeam() {
        List<Object> players = service.getCountPlayersByTeam();
        return players.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(players);
    }
}

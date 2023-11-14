package com.example.reportnba.repository;

import com.example.reportnba.entity.NBAPlayerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NBAPlayerRepository extends JpaRepository<NBAPlayerEntity, Long> {
    @Query(value = "select person_id, display_first_last, from_year, to_year, (to_year - from_year) as career " +
            "from nba_player_list " +
            "order by career desc, person_id " +
            "limit 20", nativeQuery = true)
    List<Object> findTop20Players();

    @Query(value = "select team_id, team_code, team_city, team_abbreviation, team_name, count(*) as count\n" +
            "from nba_player_list\n" +
            "where team_name != '' and team_name is not null\n" +
            "group by team_name, team_id, team_code, team_city, team_abbreviation\n" +
            "order by team_name, count",
            nativeQuery = true)
    List<Object> countPlayersByTeam();
}

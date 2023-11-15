package com.example.reportnba.repository;

import com.example.reportnba.entity.NBAPlayerEntity;
import com.example.reportnba.report.IReportCountPlayersByTeam;
import com.example.reportnba.report.IReportLongestCareer;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface INBAReportRepository extends JpaRepository<NBAPlayerEntity, Long> {
    @Query("SELECT p.personId as personId, p.displayFirstLast as displayFirstLast, p.fromYear as fromYear, p.toYear as toYear, (p.toYear - p.fromYear) as career " +
            "FROM NBAPlayerEntity p " +
            "ORDER BY (p.toYear - p.fromYear) DESC, p.personId")
    List<IReportLongestCareer> findPlayersByLongestCareer(Pageable pageable);

    @Query("SELECT p.teamId as teamId, p.teamCode as teamCode, p.teamCity as teamCity, p.teamAbbreviation as teamAbbreviation, p.teamName as teamName, COUNT(p) as count " +
            "FROM NBAPlayerEntity p " +
            "WHERE p.teamName != '' AND p.teamName IS NOT NULL " +
            "GROUP BY p.teamName, p.teamId, p.teamCode, p.teamCity, p.teamAbbreviation " +
            "ORDER BY COUNT(p) DESC")
    List<IReportCountPlayersByTeam> countPlayersByTeam();
}

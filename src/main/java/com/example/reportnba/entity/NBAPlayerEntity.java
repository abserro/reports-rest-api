package com.example.reportnba.entity;

import javax.persistence.*;
import javax.validation.constraints.*;

@Entity
@Table(name = "nba_player_list")
public class NBAPlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @NotBlank(message = "DisplayFirstLast is required")
    @Size(max = 255, message = "DisplayFirstLast cannot be longer than 255 characters")
    private String displayFirstLast;
    @NotBlank(message = "DisplayLastCommaFirst is required")
    @Size(max = 255, message = "DisplayLastCommaFirst cannot be longer than 255 characters")
    private String displayLastCommaFirst;
    @NotNull(message = "FromYear is required")
    @Max(2050)
    @Min(1900)
    private Integer fromYear;
    @NotBlank(message = "GamesPlayedFlag is required")
    private Character gamesPlayedFlag;
    @NotNull(message = "OtherleagueExperienceCh is required")
    @Max(0)
    @Min(11)
    private Integer otherleagueExperienceCh;
    @NotNull(message = "PersonId is required")
    private Long personId;
    @NotBlank(message = "Playercode is required")
    @Size(max = 255, message = "Playercode cannot be longer than 255 characters")
    private String playercode;
    @NotNull(message = "Rosterstatus is required")
    @Max(0)
    @Min(1)
    private Integer rosterstatus;
    @Size(max = 255, message = "TeamAbbreviation cannot be longer than 255 characters")
    private String teamAbbreviation;
    @Size(max = 255, message = "TeamCity cannot be longer than 255 characters")
    private String teamCity;
    @Size(max = 255, message = "TeamCode cannot be longer than 255 characters")
    private String teamCode;
    @NotNull(message = "TeamId is required")
    private Long teamId;
    @Size(max = 255, message = "TeamName cannot be longer than 255 characters")
    private String teamName;
    @NotNull(message = "ToYear is required")
    @Max(2050)
    @Min(1900)
    private Integer toYear;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getDisplayFirstLast() {
        return displayFirstLast;
    }

    public void setDisplayFirstLast(String displayFirstLast) {
        this.displayFirstLast = displayFirstLast;
    }

    public String getDisplayLastCommaFirst() {
        return displayLastCommaFirst;
    }

    public void setDisplayLastCommaFirst(String displayLastCommaFirst) {
        this.displayLastCommaFirst = displayLastCommaFirst;
    }

    public Integer getFromYear() {
        return fromYear;
    }

    public void setFromYear(Integer fromYear) {
        this.fromYear = fromYear;
    }

    public Character getGamesPlayedFlag() {
        return gamesPlayedFlag;
    }

    public void setGamesPlayedFlag(Character gamesPlayedFlag) {
        this.gamesPlayedFlag = gamesPlayedFlag;
    }

    public Integer getOtherleagueExperienceCh() {
        return otherleagueExperienceCh;
    }

    public void setOtherleagueExperienceCh(Integer otherleagueExperienceCh) {
        this.otherleagueExperienceCh = otherleagueExperienceCh;
    }

    public Long getPersonId() {
        return personId;
    }

    public void setPersonId(Long personId) {
        this.personId = personId;
    }

    public String getPlayercode() {
        return playercode;
    }

    public void setPlayercode(String playercode) {
        this.playercode = playercode;
    }

    public Integer getRosterstatus() {
        return rosterstatus;
    }

    public void setRosterstatus(Integer rosterstatus) {
        this.rosterstatus = rosterstatus;
    }

    public String getTeamAbbreviation() {
        return teamAbbreviation;
    }

    public void setTeamAbbreviation(String teamAbbreviation) {
        this.teamAbbreviation = teamAbbreviation;
    }

    public String getTeamCity() {
        return teamCity;
    }

    public void setTeamCity(String teamCity) {
        this.teamCity = teamCity;
    }

    public String getTeamCode() {
        return teamCode;
    }

    public void setTeamCode(String teamCode) {
        this.teamCode = teamCode;
    }

    public Long getTeamId() {
        return teamId;
    }

    public void setTeamId(Long teamId) {
        this.teamId = teamId;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public Integer getToYear() {
        return toYear;
    }

    public void setToYear(Integer toYear) {
        this.toYear = toYear;
    }
}

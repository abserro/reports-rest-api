package com.example.reportnba.entity;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.Objects;

@Entity
@Table(name = "nba_player_list")
public class NBAPlayerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "DisplayFirstLast is required")
    @Size(max = 255, message = "DisplayFirstLast cannot be longer than 255 characters")
    @Column(name = "display_first_last")
    private String displayFirstLast;

    @NotBlank(message = "DisplayLastCommaFirst is required")
    @Size(max = 255, message = "DisplayLastCommaFirst cannot be longer than 255 characters")
    @Column(name = "display_last_comma_first")
    private String displayLastCommaFirst;

    @NotNull(message = "FromYear is required")
    @Max(2050)
    @Min(1900)
    @Column(name = "from_year")
    private Integer fromYear;

    @NotBlank(message = "GamesPlayedFlag is required")
    @Column(name = "games_played_flag")
    private Character gamesPlayedFlag;

    @NotNull(message = "OtherleagueExperienceCh is required")
    @Max(0)
    @Min(11)
    @Column(name = "otherleague_experience_ch")
    private Integer otherleagueExperienceCh;

    @NotNull(message = "PersonId is required")
    @Column(name = "person_id")
    private Long personId;

    @NotBlank(message = "Playercode is required")
    @Size(max = 255, message = "Playercode cannot be longer than 255 characters")
    @Column(name = "playercode")
    private String playercode;

    @NotNull(message = "Rosterstatus is required")
    @Max(0)
    @Min(1)
    @Column(name = "rosterstatus")
    private Integer rosterstatus;

    @Size(max = 255, message = "TeamAbbreviation cannot be longer than 255 characters")
    @Column(name = "team_abbreviation")
    private String teamAbbreviation;

    @Size(max = 255, message = "TeamCity cannot be longer than 255 characters")
    @Column(name = "team_city")
    private String teamCity;

    @Size(max = 255, message = "TeamCode cannot be longer than 255 characters")
    @Column(name = "team_code")
    private String teamCode;

    @NotNull(message = "TeamId is required")
    @Column(name = "team_id")
    private Long teamId;

    @Size(max = 255, message = "TeamName cannot be longer than 255 characters")
    @Column(name = "team_name")
    private String teamName;

    @NotNull(message = "ToYear is required")
    @Max(2050)
    @Min(1900)
    @Column(name = "to_year")
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        NBAPlayerEntity that = (NBAPlayerEntity) o;
        return Objects.equals(id, that.id) &&
                Objects.equals(personId, that.personId) &&
                Objects.equals(teamId, that.teamId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, personId, teamId);
    }
}

package com.github.plan.controller;

import com.github.plan.persistence.client.dao.Team;
import com.github.plan.persistence.client.dao.TeamRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.List;

@RestController
public class TeamController {
    @Inject
    private TeamRepository teamRepository;

    @RequestMapping(value = "/team/list", method = RequestMethod.GET)
    public List<Team> getTeams() {
        return teamRepository.findAll();
    }

    @RequestMapping(value = "/team/save", method = RequestMethod.POST)
    public ResponseEntity<String> saveTeam(@RequestBody Team team) {
        if (teamRepository.countByName(team.getName()) == 0
                || teamRepository.countByName(team.getName()) == 1
                && teamRepository.findByName(team.getName()).getId() == team.getId()) {
            teamRepository.save(team);
            return new ResponseEntity<String>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "/team/list/save", method = RequestMethod.POST)
    public ResponseEntity<String> saveTeams(@RequestBody List<Team> teams) {
        if (teams.stream()
                .allMatch(team -> teamRepository.countByName(team.getName()) == 0
                        || teamRepository.countByName(team.getName()) == 1
                        && teamRepository.findByName(team.getName()).getId() == team.getId())) {
            teamRepository.save(teams);
            return new ResponseEntity<String>(HttpStatus.ACCEPTED);
        } else {
            return new ResponseEntity<String>(HttpStatus.NOT_ACCEPTABLE);
        }
    }

    @RequestMapping(value = "/team/remove", method = RequestMethod.POST)
    public ResponseEntity<String> removeTeam(@RequestParam Long teamId) {
        teamRepository.delete(teamId);
        return new ResponseEntity<String>(HttpStatus.OK);
    }
}

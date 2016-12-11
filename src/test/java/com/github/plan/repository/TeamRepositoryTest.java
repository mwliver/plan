package com.github.plan.repository;

import com.github.plan.base.GenericTestConfiguration;
import com.github.plan.persistence.client.dao.Team;
import com.github.plan.persistence.client.dao.TeamRepository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {GenericTestConfiguration.class}, loader = AnnotationConfigContextLoader.class)
@Transactional
public class TeamRepositoryTest extends Assert {

    @Autowired
    private TeamRepository teamRepository;

    @Test
    public void countByNameTest() throws Exception {
        // Given
        Team team = new Team();
        team.setName("dreamTeam");
        teamRepository.save(team);

        // When
        int countByName = teamRepository.countByName("dreamTeam");

        // Then
        assertEquals(1, countByName);
    }

    @Test
    public void countByNameTest2() throws Exception {
        // Given
        Team team = new Team();
        team.setName("dreamTeam");
        teamRepository.save(team);

        // When
        int countByName = teamRepository.countByName("dream");

        // Then
        assertEquals(0, countByName);
    }

    @Test
    public void countByNameTest3() throws Exception {
        // Given
        Team team = new Team();
        team.setName("dreamTeam");
        teamRepository.save(team);

        Team secondTeam = new Team();
        secondTeam.setName("dream");
        teamRepository.save(secondTeam);

        // When
        int countByName = teamRepository.countByName("dreamTeam");

        // Then
        assertEquals(1, countByName);
    }
}

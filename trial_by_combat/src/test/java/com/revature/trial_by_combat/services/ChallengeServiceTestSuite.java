package com.revature.trial_by_combat.services;

import org.assertj.core.api.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.revature.trial_by_combat.daos.AvatarDAO;
import com.revature.trial_by_combat.daos.ChallengeDAO;
import com.revature.trial_by_combat.models.Avatar;
import com.revature.trial_by_combat.models.Challenge;

@ExtendWith(MockitoExtension.class)
public class ChallengeServiceTestSuite {
	@Mock
	private ChallengeDAO challengeDAO;
	
	ChallengeService sut;
	
	Challenge challenge;

    private AvatarDAO avatarDAO;
	
	AvatarService zut;

    Avatar avatar;

    Avatar challenger;

	@BeforeEach
	public void testPrep() {
		sut = new ChallengeService(challengeDAO);
        zut = new AvatarService(avatarDAO);
        avatar = new Avatar();
        avatar.setId(2);
        challenger = new Avatar();
        challenger.setId(3);
		challenge = new Challenge();
        challenge.setAvatar(avatar);
        challenge.setChallenger(challenger);
        challenge.setId(1);

	}

	@Test
	public void test_ChallengeService_createNewChallenge() throws Exception{
		Challenge mockPersistedChallenge = sut.createNewChallenge(challenge);
        assertNotNull(mockPersistedChallenge);
	}
	
	@Test
	public void test_ChallengeService_findAllChallenges()
	{
        List<Challenge> mockList = new ArrayList<Challenge>();
        mockList.add(challenge);
        Iterable<Challenge> iterableMock = mockList;
        when(challengeDAO.findAll()).thenReturn(iterableMock);
		Iterable<Challenge> challengeList = sut.findAllChallenges();
        assertNotNull(challengeList);
	}
	
	@Test
	public void test_ChallengeService_findChallengeById(){

        Optional<Challenge> mockChallenge = Optional.of(challenge);
        when(challengeDAO.findById(1)).thenReturn(mockChallenge);
		assertTrue(sut.findChallengeById(1).isPresent());
	}

    @Test
	public void test_ChallengeService_findAllChallengesByAvatarId()
	{
        List<Challenge> mockList = new ArrayList<Challenge>();
        mockList.add(challenge);
        Iterable<Challenge> iterableMock = mockList;
        when(challengeDAO.findAllChallengesByAvatarId(2)).thenReturn(iterableMock);
        assertNotNull(sut.findAllChallengesByAvatarId(2));
	}

    @Test
	public void test_ChallengeService_findAllChallengesByChallengerId()
	{
        List<Challenge> mockList = new ArrayList<Challenge>();
        mockList.add(challenge);
        Iterable<Challenge> iterableMock = mockList;
        when(challengeDAO.findAllChallengesByChallengerId(3)).thenReturn(iterableMock);
        assertNotNull(sut.findAllChallengesByChallengerId(3));
	}
	
	@Test
	public void test_ChallengeService_updateChallenge()
	{
		assertNotNull(sut.updateChallenge(challenge));
	}
	
	// @Test
	// public void test_AvatarService_deleteAvatar()
	// {
	// 	sut.deleteAvatar(1);
	// }


    @Test
    public void test_ChallengeService_authenticateChallenge() throws Exception{

        assertTrue(sut.authenticateChallenge(avatar, challenger));
        assertFalse(sut.authenticateChallenge(avatar, avatar));
        avatar = null;
        assertFalse(sut.authenticateChallenge(avatar, challenger));

    }   
}
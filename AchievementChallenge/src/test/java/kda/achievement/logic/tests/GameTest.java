package kda.achievement.logic.tests;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;
import junit.framework.TestCase;
import kda.achievement.domain.Game;
import kda.achievement.domain.GamePlayer;

public class GameTest extends TestCase {

	protected Game poorlyCreatedGame;
	
	protected List<GamePlayer> redTeam = new ArrayList<GamePlayer>();
	protected List<GamePlayer> blueTeam = new ArrayList<GamePlayer>();
	
	@Override
	protected void setUp() throws Exception {
		
		setupTeams();
	}
	
	private void setupTeams() {

		for(int i = 1; i <= 5; i++) {
			redTeam.add(new GamePlayer("redPlayer" + i));
			blueTeam.add(new GamePlayer("bluePlayer" + i));
		}
	}

	@Override
	protected void tearDown() throws Exception {

		// Not much to tearDown
		super.tearDown();
	}
	
	/**
	 * Teams cannot have uneven teams (ie. 5 on red, 3 on blue)
	 */
	public void testGameWithUnevenTeams() {
		
		//Remove a player from one of the teams
		List<GamePlayer> newRedTeam = redTeam;
		newRedTeam.remove(0);
		try {
			poorlyCreatedGame = new Game(newRedTeam, blueTeam);
		}
		catch (IllegalArgumentException expected) {
			// ignore this because we expect an exception!
		}
	}

	/**
	 * Each team must have at least 3 players (total of 6).
	 */
	public void testGameWithTooFewPlayers() {
		
		List<GamePlayer> newRedTeam = redTeam;
		List<GamePlayer> newBlueTeam = blueTeam;
		
		newRedTeam.remove(0);
		newRedTeam.remove(0);
		newRedTeam.remove(0);
		Assert.assertEquals(2, newRedTeam.size());
		
		newBlueTeam.remove(0);
		newBlueTeam.remove(0);
		newBlueTeam.remove(0);
		Assert.assertEquals(2, newBlueTeam.size());
		
		try {
			poorlyCreatedGame = new Game(newRedTeam, newBlueTeam);
		}
		catch(IllegalArgumentException expected) {
			// ignore this because we expect an exception!
		}
	}
	
	public void testGameWithTooManyPlayers() {
		
		List<GamePlayer> newRedTeam = redTeam;
		List<GamePlayer> newBlueTeam = blueTeam;
		
		newRedTeam.add(new GamePlayer("Mike"));
		Assert.assertEquals(6, newRedTeam.size());
		
		newBlueTeam.add(new GamePlayer("Jimbo"));
		Assert.assertEquals(6, newBlueTeam.size());
		
		try {
			poorlyCreatedGame = new Game(newRedTeam, newBlueTeam);
		}
		catch(IllegalArgumentException expected) {
			// ignore this because we expect an exception!
		}
	}
}
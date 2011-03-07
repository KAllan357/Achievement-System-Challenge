package kda.achievement.logic.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.junit.Assert;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import junit.framework.TestCase;
import kda.achievement.domain.Achievement;
import kda.achievement.domain.Game;
import kda.achievement.domain.GamePlayer;
import kda.achievement.domain.Player;
import kda.achievement.helpers.GameHelper;
import kda.achievement.logic.AchievementHandler;

public class CodingChallengeTest extends TestCase {

	private XStream xstream;
	private AchievementHandler achievementHandler;
	
	@Override
	protected void setUp() throws Exception {
		xstream = new XStream(new DomDriver());
		xstream.processAnnotations(Achievement.class);
		achievementHandler = new AchievementHandler();
		super.setUp();
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	/**
	 * Tests for achievements that can be given when a game ends. This test includes
	 * all achievements that would be given to a player in a game - Sharpshooter and
	 * Bruiser.
	 */
	public void testGameOverDefaultAchievements() throws FileNotFoundException {

		System.out.println("------------------testGameOverDefaultAchievements------------------");
		Game game = GameHelper.createNewGame();
		List<Achievement> achievementList = getAchievementList();
		Map<GamePlayer, List<Achievement>> playerAchievementMap = achievementHandler.gameOver(game, achievementList);
		printGameAchievementListings(playerAchievementMap);
	}

	/**
	 * Tests for achievements that can be given when a game ends. This test includes
	 * the Sharpshooter, Bruiser, and the new achievement, Team Carry
	 */
	public void testGameOverAllAchievements() throws FileNotFoundException {

		System.out.println("------------------testGameOverAllAchievements------------------");
		Game game = GameHelper.createNewGame();
		
		//This gamePlayer will have all three game achievements, including the 
		//new Team Carry achievement, with a 2000 / 1000 = 2:1 ratio of damage 
		//inflicted to damage received.
		GamePlayer newRedPlayer = new GamePlayer();
		newRedPlayer.setPlayerName("Michael Jordan");
		newRedPlayer.setHitCount(900);
		newRedPlayer.setAttemptedAttackCount(999);
		newRedPlayer.setTotalMagicalDamageInflicted(1000);
		newRedPlayer.setTotalPhysicalDamageInflicted(1000);
		newRedPlayer.setTotalDamageInflicted();
		newRedPlayer.setTotalMagicalDamageReceived(500);
		newRedPlayer.setTotalPhysicalDamageReceived(500);
		newRedPlayer.setTotalDamageReceived();
		
		//This gamePlayer will have the new Team Carry achievement with a
		//10:1 ratio of damage inflicted to received.
		GamePlayer newBluePlayer = new GamePlayer();
		newBluePlayer.setPlayerName("Shaq");
		newBluePlayer.setTotalMagicalDamageInflicted(5000);
		newBluePlayer.setTotalPhysicalDamageInflicted(5000);
		newBluePlayer.setTotalDamageInflicted();
		newBluePlayer.setTotalMagicalDamageReceived(500);
		newBluePlayer.setTotalPhysicalDamageReceived(500);
		newBluePlayer.setTotalDamageReceived();
		
		game.addGamePlayers(newRedPlayer, newBluePlayer);
		List<Achievement> achievementList = getAchievementList();
		Map<GamePlayer, List<Achievement>> playerAchievementMap = achievementHandler.gameOver(game, achievementList);
		printGameAchievementListings(playerAchievementMap);
	}
	
	/**
	 * Tests whether a player has the global achievements that key
	 * off a player's global stats
	 */
	public void testGlobalAchievements() throws FileNotFoundException {

		System.out.println("------------------testGlobalAchievements------------------");
		
		//Create a player that has the Veteran Achievement
		Player player = new Player();
		player.setTotalGamesPlayed(5000);
		List<Achievement> achievementList = getAchievementList();
		List<Achievement> unlockedAchievements = achievementHandler.checkForGlobalAchievements(player, achievementList);
		System.out.println("The Player " + player.getPlayerName() + " has earned the following global achievements:");
		for(Achievement unlockedAchievement : unlockedAchievements) {
			System.out.println(unlockedAchievement.toString());
		}
		if(unlockedAchievements.isEmpty()) {
			System.out.println("No Achievements Earned!");
		}
	}
	
	private void printGameAchievementListings(
			Map<GamePlayer, List<Achievement>> playerAchievementMap) {
		Assert.assertFalse(playerAchievementMap.isEmpty());
		for(Entry<GamePlayer, List<Achievement>> entry : playerAchievementMap.entrySet()) {
			GamePlayer player = entry.getKey();
			List<Achievement> achievements = entry.getValue();
			System.out.println("The Player in this game " + player.getPlayerName() + " had the following achievements:");
			for(Achievement earned : achievements) {
				System.out.println(earned.toString());
			}
			if(achievements.isEmpty()) {
				System.out.println("No Achievements Earned!");
			}
			System.out.println();
		}
	}

	private List<Achievement> getAchievementList() throws FileNotFoundException {
		List<Achievement> achievementList = new ArrayList<Achievement>();
		
		//Load the XML files
		List<File> achievementFiles = new ArrayList<File>();
		File achievementDirectory = new File(this.getClass().getClassLoader().getResource("xml/").getFile());
		for(File achievement : achievementDirectory.listFiles()) {
			achievementFiles.add(achievement);
		}
		
		//Transform the XML into objects
		for(File achievementFile : achievementFiles) {
			InputStream achievementStream = new FileInputStream(achievementFile);
			Achievement achievement = (Achievement)xstream.fromXML(achievementStream);
			achievementList.add(achievement);
		}
		return achievementList;
	}
}

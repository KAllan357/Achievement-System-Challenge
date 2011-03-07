package kda.achievement.logic.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import junit.framework.TestCase;
import kda.achievement.domain.Achievement;
import kda.achievement.domain.GamePlayer;
import kda.achievement.domain.Player;
import kda.achievement.helpers.GameHelper;
import kda.achievement.logic.AchievementHandler;

public class AchievementHandlerTest extends TestCase {

	private XStream xstream;
	
	@Override
	protected void setUp() throws Exception {
		xstream = new XStream(new DomDriver());
		xstream.processAnnotations(Achievement.class);
		super.setUp();
	}
	
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
	
	/**
	 * Loads the xml achievement resources. In this case they are in src/main/resources
	 */
	public void testLoadingAllAchievementResources() {
		List<File> achievementFiles = new ArrayList<File>();
		File achievementDirectory = new File(this.getClass().getClassLoader().getResource("xml/").getFile());
		Assert.assertNotNull(achievementDirectory);
		for(File achievement : achievementDirectory.listFiles()) {
			achievementFiles.add(achievement);
		}
		Assert.assertTrue(!achievementFiles.isEmpty());
		Assert.assertEquals(5, achievementFiles.size());
	}
	
	/**
	 *  Tests to see if a Player has any Global achievements.
	 */
	public void testCheckForGlobalAchievements() throws FileNotFoundException {
		AchievementHandler achievementHandler = new AchievementHandler();
		Player player = GameHelper.createPlayerWithWins();
		List<Achievement> achievementList = getAchievementList();
		List<Achievement> playerAchievementList = achievementHandler.checkForGlobalAchievements(player, achievementList);
		Assert.assertNotNull(playerAchievementList);

		//There should be two achievements in here
		Assert.assertEquals(2, playerAchievementList.size());
	}
	
	/**
	 * Checks to see if a GamePlayer has any Game achievements
	 */
	public void testCheckForGameAchievements() throws FileNotFoundException {
		AchievementHandler achievementHandler = new AchievementHandler();
		GamePlayer gamePlayer = GameHelper.createGamePlayerWithHits();
		gamePlayer.setHitCount(50);
		List<Achievement> achievementList = getAchievementList();
		List<Achievement> playerAchievementList = achievementHandler.checkForGameAchievements(gamePlayer, achievementList);
		Assert.assertNotNull(playerAchievementList);

		//The gamePlayer should only have 1 achievement
		Assert.assertEquals(1, playerAchievementList.size());
	}
	
	/**
	 * Gets the list of achievements in the xml directory
	 * @return
	 * @throws FileNotFoundException
	 */
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

package kda.achievement.logic.tests;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.junit.Assert;
import org.junit.Ignore;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import junit.framework.TestCase;
import kda.achievement.domain.Achievement;
import kda.achievement.domain.Game;
import kda.achievement.domain.GamePlayer;
import kda.achievement.domain.Player;
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
		Assert.assertEquals(4, achievementFiles.size());
	}
	
	public void testCheckForGlobalAchievements() throws FileNotFoundException {
		AchievementHandler achievementHandler = new AchievementHandler();
		Player player = new Player();
		List<Achievement> achievementList = getAchievementList();
		List<Achievement> playerAchievementList = achievementHandler.checkForGlobalAchievements(player, achievementList);
		Assert.assertNotNull(playerAchievementList);
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

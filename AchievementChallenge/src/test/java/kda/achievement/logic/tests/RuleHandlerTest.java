package kda.achievement.logic.tests;

import java.io.InputStream;
import java.math.BigDecimal;
import java.util.List;

import org.junit.Assert;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import junit.framework.TestCase;
import kda.achievement.domain.Achievement;
import kda.achievement.domain.GamePlayer;
import kda.achievement.domain.Player;
import kda.achievement.domain.Rule;
import kda.achievement.helpers.GameHelper;
import kda.achievement.logic.RuleHandler;

public class RuleHandlerTest extends TestCase {
	
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
	
	public void testRuleHandlerConstruction() {
		
		//Get the ruleList for the Sharpshooter Achievement
		List<Rule> ruleList = getSharpshooterAchievementRuleList();
		Assert.assertNotNull(ruleList);
		Assert.assertFalse(ruleList.isEmpty());
		
		//Create a new RuleHandler
		RuleHandler ruleHandler = new RuleHandler(ruleList);
		Assert.assertEquals(new BigDecimal(.75), ruleHandler.getConstant());
	}
	
	public void testNumbersReflection() {
		
		//Get some rules and create a Handler
		List<Rule> ruleList = getSharpshooterAchievementRuleList();
		RuleHandler ruleHandler = new RuleHandler(ruleList);

		GamePlayer gamePlayer = GameHelper.createGamePlayerWithHits();
		
		//Process the XML section for getting information from objects
		List<Integer> achievementValues = ruleHandler.processMethodNameMap(null, gamePlayer);
		Assert.assertNotNull(achievementValues);

		//GameHelper.createPlayer creates a player with 30 'hits' in
		//conjunction with the Sharpshooter Achievement
		Assert.assertEquals(30, achievementValues.get(0));
	}
	
	public void testSharpshooterAchievementRulesEvaluation() {

		List<Rule> ruleList = getSharpshooterAchievementRuleList();
		RuleHandler ruleHandler = new RuleHandler(ruleList);

		GamePlayer gamePlayer = GameHelper.createGamePlayerWithHits();
		
		//Process the XML section for getting information from objects
		List<Integer> achievementValues = ruleHandler.processMethodNameMap(null, gamePlayer);
		boolean achievementGranted = ruleHandler.processRuleEvaluation(achievementValues);
		Assert.assertFalse(achievementGranted);
		
		//Make the gamePlayer have the appropriate accuracy for the sharpshooter achievement
		gamePlayer.setHitCount(8);
		gamePlayer.setAttemptedAttackCount(10);
		achievementValues = ruleHandler.processMethodNameMap(null, gamePlayer);
		achievementGranted = ruleHandler.processRuleEvaluation(achievementValues);
		Assert.assertTrue(achievementGranted);
	}
	
	public void testBigWinnerAchievement() {
		
		List<Rule> ruleList = getBigWinnerAchievementRuleList();
		RuleHandler ruleHandler = new RuleHandler(ruleList);
		
		Player player = GameHelper.createPlayerWithWins();
		player.setTotalWins(199);
		List<Integer> achievementValues = ruleHandler.processMethodNameMap(player, null);
		boolean achievementGranted = ruleHandler.processRuleEvaluation(achievementValues);
		Assert.assertFalse(achievementGranted);
		
		player.setTotalWins(205);
		achievementValues = ruleHandler.processMethodNameMap(player, null);
		achievementGranted = ruleHandler.processRuleEvaluation(achievementValues);
		Assert.assertTrue(achievementGranted);
	}
	
	public void testBruiserAchievement() {
		
		List<Rule> ruleList = getBruiserAchievementRuleList();
		RuleHandler ruleHandler = new RuleHandler(ruleList);
		
		GamePlayer gamePlayer = GameHelper.createGamePlayerWithHits();
		gamePlayer.setTotalPhysicalDamageInflicted(30);
		gamePlayer.setTotalMagicalDamageInflicted(20);
		gamePlayer.setTotalDamageInflicted();
		List<Integer> achievementValues = ruleHandler.processMethodNameMap(null, gamePlayer);
		boolean achievementGranted = ruleHandler.processRuleEvaluation(achievementValues);
		Assert.assertFalse(achievementGranted);
		
		gamePlayer.setTotalPhysicalDamageInflicted(260);
		gamePlayer.setTotalMagicalDamageInflicted(300);
		gamePlayer.setTotalDamageInflicted();
		achievementValues = ruleHandler.processMethodNameMap(null, gamePlayer);
		achievementGranted = ruleHandler.processRuleEvaluation(achievementValues);
		Assert.assertTrue(achievementGranted);
	}
	
	public void testVeteranAchievement() {
		
		List<Rule> ruleList = getVeteranAchievementRuleList();
		RuleHandler ruleHandler = new RuleHandler(ruleList);
		
		Player player = GameHelper.createPlayerWithWins();
		player.setTotalGamesPlayed(998);
		List<Integer> achievementValues = ruleHandler.processMethodNameMap(player, null);
		boolean achievementGranted = ruleHandler.processRuleEvaluation(achievementValues);
		Assert.assertFalse(achievementGranted);
		
		player.setTotalGamesPlayed(1002);
		achievementValues = ruleHandler.processMethodNameMap(player, null);
		achievementGranted = ruleHandler.processRuleEvaluation(achievementValues);
		Assert.assertTrue(achievementGranted);
	}
	
	private List<Rule> getSharpshooterAchievementRuleList() {
		
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("xml/sharpshooterAchievement.xml");
		
		//Create an Achievement instance out of my xml
		Achievement achievement = (Achievement)xstream.fromXML(inputStream);
		return achievement.getRules();
	}
	
	private List<Rule> getBigWinnerAchievementRuleList() {
		
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("xml/bigWinnerAchievement.xml");
		
		//Create an Achievement instance out of my xml
		Achievement achievement = (Achievement)xstream.fromXML(inputStream);
		return achievement.getRules();
	}
	
	private List<Rule> getBruiserAchievementRuleList() {
		
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("xml/bruiserAchievement.xml");
		
		//Create an Achievement instance out of my xml
		Achievement achievement = (Achievement)xstream.fromXML(inputStream);
		return achievement.getRules();
	}

	private List<Rule> getVeteranAchievementRuleList() {
	
	InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("xml/veteranAchievement.xml");
	
	//Create an Achievement instance out of my xml
	Achievement achievement = (Achievement)xstream.fromXML(inputStream);
	return achievement.getRules();
}
}

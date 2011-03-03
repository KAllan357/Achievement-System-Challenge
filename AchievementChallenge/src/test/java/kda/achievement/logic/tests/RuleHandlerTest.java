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
		List<Rule> ruleList = getRuleList();
		Assert.assertNotNull(ruleList);
		Assert.assertFalse(ruleList.isEmpty());
		
		//Create a new RuleHandler
		RuleHandler ruleHandler = new RuleHandler(ruleList);
		Assert.assertEquals(new BigDecimal(.75), ruleHandler.getConstant());
	}
	
	public void testNumbersReflection() {
		
		//Get some rules and create a Handler
		List<Rule> ruleList = getRuleList();
		RuleHandler ruleHandler = new RuleHandler(ruleList);

		Player player = GameHelper.createPlayer();
		GamePlayer gamePlayer = GameHelper.createGamePlayer();
		
		//Process the XML section for getting information from objects
		List<Integer> achievementValues = ruleHandler.processNumbersList(player, gamePlayer);
		Assert.assertNotNull(achievementValues);

		//GameHelper.createPlayer creates a player with 30 'hits' in
		//conjunction with the Sharpshooter Achievement
		Assert.assertEquals(30, achievementValues.get(0));
	}
	
	public void testRuleEvaluation() {

		List<Rule> ruleList = getRuleList();
		RuleHandler ruleHandler = new RuleHandler(ruleList);

		Player player = GameHelper.createPlayer();
		GamePlayer gamePlayer = GameHelper.createGamePlayer();
		
		//Process the XML section for getting information from objects
		List<Integer> achievementValues = ruleHandler.processNumbersList(player, gamePlayer);
		boolean achievementGranted = ruleHandler.processRuleEvaluation(achievementValues);
		Assert.assertFalse(achievementGranted);
		
		//Make the gamePlayer have the appropriate accuracy for the sharpshooter achievement
		gamePlayer.setHitCount(8);
		gamePlayer.setAttemptedAttackCount(10);
		achievementValues = ruleHandler.processNumbersList(player, gamePlayer);
		achievementGranted = ruleHandler.processRuleEvaluation(achievementValues);
		Assert.assertTrue(achievementGranted);
	}
	
	private List<Rule> getRuleList() {
		
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("xml/sharpshooterAchievement.xml");
		
		//Create an Achievement instance out of my xml
		Achievement achievement = (Achievement)xstream.fromXML(inputStream);
		return achievement.getRules();
	}
}

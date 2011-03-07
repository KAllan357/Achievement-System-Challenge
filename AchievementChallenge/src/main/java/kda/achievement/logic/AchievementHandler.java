package kda.achievement.logic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import kda.achievement.domain.Achievement;
import kda.achievement.domain.Game;
import kda.achievement.domain.GamePlayer;
import kda.achievement.domain.Player;

public class AchievementHandler {
	
	private static final String ACHIEVEMENT_TYPE_GLOBAL = "Global";
	private static final String ACHIEVEMENT_TYPE_GAME = "Game";
	private RuleHandler ruleHandler = new RuleHandler();

	/**
	 * Executed when a game is over, using the Game's players as an input and the achievements
	 * to test them for.
	 * 
	 * @param game
	 * @param achievementList
	 * @return
	 */
	public Map<GamePlayer, List<Achievement>> gameOver(Game game, List<Achievement> achievementList) {
		Map<GamePlayer, List<Achievement>> returnMap = new HashMap<GamePlayer, List<Achievement>>();
		
		//Every Achievement in the achievementList must be tested against both teams of gamePlayers		
		for(GamePlayer gamePlayer : game.getBlueTeam()) {
			List<Achievement> gamePlayerAchievements = new ArrayList<Achievement>();
			for(Achievement achievementToTest : achievementList) {
				ruleHandler.setupRuleHandler(achievementToTest.getRules());
				if(ruleHandler.processRule(null, gamePlayer)) {
					gamePlayerAchievements.add(achievementToTest);
				}
			}
			returnMap.put(gamePlayer, gamePlayerAchievements);
		}
		for(GamePlayer gamePlayer : game.getRedTeam()) {
			List<Achievement> gamePlayerAchievements = new ArrayList<Achievement>();
			for(Achievement achievementToTest : achievementList) {
				ruleHandler.setupRuleHandler(achievementToTest.getRules());
				if(ruleHandler.processRule(null, gamePlayer)) {
					gamePlayerAchievements.add(achievementToTest);
				}
			}
			returnMap.put(gamePlayer, gamePlayerAchievements);
		}
		return returnMap;
	}
	
	public List<Achievement> checkForGlobalAchievements(Player player, List<Achievement> achievementList) {
		List<Achievement> achievementsOwned = new ArrayList<Achievement>();
		for(Achievement achievementToTest : achievementList) {
			if(ACHIEVEMENT_TYPE_GLOBAL.equals(achievementToTest.getType())) {
				ruleHandler.setupRuleHandler(achievementToTest.getRules());
	
				//If the rule is processed successfully, this achievement is owned
				if(ruleHandler.processRule(player, null)) {
					achievementsOwned.add(achievementToTest);
				}
			}
		}
		return achievementsOwned;
	}
	
	public List<Achievement> checkForGameAchievements(GamePlayer gamePlayer, List<Achievement> achievementList) {
		List<Achievement> achievementsOwned = new ArrayList<Achievement>();
		for(Achievement achievementToTest : achievementList) {
			if(ACHIEVEMENT_TYPE_GAME.equals(achievementToTest.getType())) {
				ruleHandler.setupRuleHandler(achievementToTest.getRules());
	
				//If the rule is processed successfully, this achievement is owned
				if(ruleHandler.processRule(null, gamePlayer)) {
					achievementsOwned.add(achievementToTest);
				}
			}
		}
		return achievementsOwned;	
	}
}

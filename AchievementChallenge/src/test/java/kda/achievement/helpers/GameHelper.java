package kda.achievement.helpers;

import java.util.ArrayList;
import java.util.List;

import kda.achievement.domain.Game;
import kda.achievement.domain.GamePlayer;
import kda.achievement.domain.Player;

/**
 * Helper class for creating different objects for testing purposes
 * 
 * @author kyleallan
 *
 */
public class GameHelper {

	/**
	 * Creates a new game for testing that has a red team and a blue team
	 * with 3 players each.
	 * @return
	 */
	public static Game createNewGame() {
		Game game = new Game(createRedTeam(), createBlueTeam());
		return game;
	}
	
	/**
	 * Creates a team with some pre-populated values
	 * 
	 * @return List<GamePlayer>
	 */
	public static List<GamePlayer> createRedTeam() {
		List<GamePlayer> redTeam = new ArrayList<GamePlayer>();
		
		//Player 1; she has all achievements
		GamePlayer player1 = new GamePlayer("Jill");
		player1.setHitCount(300);
		player1.setAttemptedAttackCount(400);
		player1.setTotalMagicalDamageInflicted(280);
		player1.setTotalPhysicalDamageInflicted(300);
		player1.setTotalDamageInflicted();
		
		//Player 2; he has no achievements
		GamePlayer player2 = new GamePlayer("Mike");
		
		
		//Player 3; he has the bruiser achievement
		GamePlayer player3 = new GamePlayer("Steve");
		player3.setTotalMagicalDamageInflicted(280);
		player3.setTotalPhysicalDamageInflicted(300);
		player3.setTotalDamageInflicted();
		
		redTeam.add(player1);
		redTeam.add(player2);
		redTeam.add(player3);
		return redTeam;
	}

	/**
	 * Creates a team with some pre-populated players.
	 * 
	 * @return List<GamePlayer>
	 */
	public static List<GamePlayer> createBlueTeam() {
		List<GamePlayer> blueTeam = new ArrayList<GamePlayer>();
		
		//Player 1; he has the sharpshooterAchievement
		GamePlayer player1 = new GamePlayer("John");
		player1.setHitCount(500);
		player1.setAttemptedAttackCount(600);
		
		//Player 2; he has the bruiser and sharpshooterAchievements
		GamePlayer player2 = new GamePlayer("Ryan");
		player2.setHitCount(300);
		player2.setAttemptedAttackCount(400);
		player2.setTotalMagicalDamageInflicted(280);
		player2.setTotalPhysicalDamageInflicted(300);
		player2.setTotalDamageInflicted();
		
		//Player 3; she has no achievements
		GamePlayer player3 = new GamePlayer("Jaime");
		player3.setHitCount(20);
		player3.setAttemptedAttackCount(100);
		
		blueTeam.add(player1);
		blueTeam.add(player2);
		blueTeam.add(player3);
		return blueTeam;
	}

	/**
	 * Creates a player that has earned some winning related achievements
	 * 
	 * @return Player
	 */
	public static Player createPlayerWithWins() {
		Player player = new Player();
		player.setPlayerName("Jimbo");
		player.setTotalWins(305);
		player.setTotalGamesPlayed(1200);
		return player;
	}

	/**
	 * Creates a player that has earned some hit count related achievements
	 * 
	 * @return Player
	 */
	public static GamePlayer createGamePlayerWithHits() {

		GamePlayer player1 = new GamePlayer("Stu");
		player1.setHitCount(30);
		player1.setAttemptedAttackCount(65);
		return player1;
	}

}

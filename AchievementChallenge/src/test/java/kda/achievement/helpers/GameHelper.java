package kda.achievement.helpers;

import java.util.ArrayList;
import java.util.List;

import kda.achievement.domain.Game;
import kda.achievement.domain.GamePlayer;
import kda.achievement.domain.Player;

public class GameHelper {

	public static Game createNewGame() {
		Game game = new Game(createRedTeam(), createBlueTeam());
		return game;
	}
	

	public static List<GamePlayer> createRedTeam() {
		List<GamePlayer> redTeam = new ArrayList<GamePlayer>();
		
		//Player 1
		GamePlayer player1 = new GamePlayer("Kyle");
		
		//Player 2
		GamePlayer player2 = new GamePlayer("Mike");
		
		//Player 3
		GamePlayer player3 = new GamePlayer("Steve");
		
		redTeam.add(player1);
		redTeam.add(player2);
		redTeam.add(player3);
		return redTeam;
	}

	public static List<GamePlayer> createBlueTeam() {
		List<GamePlayer> blueTeam = new ArrayList<GamePlayer>();
		
		//Player 1
		GamePlayer player1 = createGamePlayer();
		
		//Player 2
		GamePlayer player2 = new GamePlayer("Ryan");
		
		//Player 3
		GamePlayer player3 = new GamePlayer("Jaime");
		
		blueTeam.add(player1);
		blueTeam.add(player2);
		blueTeam.add(player3);
		return blueTeam;
	}


	public static Player createPlayer() {
		Player player = new Player();
		
		return player;
	}


	public static GamePlayer createGamePlayer() {

		GamePlayer player1 = new GamePlayer("Stu");
		player1.setHitCount(30);
		player1.setAttemptedAttackCount(65);
		return player1;
	}

}

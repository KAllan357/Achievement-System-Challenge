package kda.achievement.domain;

import java.util.List;

public class Game {

	//Game statistics
	private int numberOfPlayers;
	private String mapName;
	
	//Teams
	List<GamePlayer> redTeam;
	List<GamePlayer> blueTeam;
	
	public Game(final List<GamePlayer> redTeam, final List<GamePlayer> blueTeam) {
		
		if(redTeam == null) throw new IllegalArgumentException("redTeam cannot be null!");
		if(blueTeam == null) throw new IllegalArgumentException("blueTeam cannot be null!");
		
		//Teams must be of equal size, and each must have 3-5 players.
		if(redTeam.size() != blueTeam.size()) throw new IllegalArgumentException("The redTeam's size must be equal to the blueTeam's!");
		int totalPlayers = redTeam.size() + blueTeam.size();
		if(totalPlayers > 10) throw new IllegalArgumentException("Both teams cannot have more than 10 players!");
		if(totalPlayers < 6) throw new IllegalArgumentException("Both teams cannot have less than 6 players");
		
		//Set the teams
		this.redTeam = redTeam;
		this.blueTeam = blueTeam;
		
		//Set up the game statistics
		setNumberOfPlayers(totalPlayers);
	}

	public int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	public void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	public String getMapName() {
		return mapName;
	}

	public void setMapName(String mapName) {
		this.mapName = mapName;
	}
}

package kda.achievement.domain;

import java.util.List;

public class Game {

	//Game statistics
	private int numberOfPlayers;
	private String mapName;
	
	//Game Over information
	private boolean completed;
	private String winningTeam;
	
	//Teams
	List<GamePlayer> redTeam;
	List<GamePlayer> blueTeam;
	
	/**
	 * Constructor to create a new Game. The provided teams must not be null,
	 * have an equal number of players, and have a size between 6 and 10.
	 * 
	 * @param redTeam
	 * @param blueTeam
	 */
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
	
	/**
	 * Adds a player to each team respectively. Adding players cannot increase
	 * the total size beyond 10.
	 * 
	 * @param redPlayer
	 * @param bluePlayer
	 */
	public void addGamePlayers(final GamePlayer redPlayer, final GamePlayer bluePlayer) {
		int totalPlayers = redTeam.size() + blueTeam.size();
		totalPlayers = totalPlayers + 2;
		if(totalPlayers > 10) throw new IllegalArgumentException("Both teams cannot have more than 10 players!");
		if(redPlayer == null || bluePlayer == null) throw new IllegalArgumentException("Both arguments must be passed with a value!");
		
		//We can add new players if the new total isn't greater than 10 and as long as 1 member is added to each team.
		this.redTeam.add(redPlayer);
		this.blueTeam.add(bluePlayer);
		
	}

	/**
	 * @return the numberOfPlayers
	 */
	public final int getNumberOfPlayers() {
		return numberOfPlayers;
	}

	/**
	 * @param numberOfPlayers the numberOfPlayers to set
	 */
	public final void setNumberOfPlayers(int numberOfPlayers) {
		this.numberOfPlayers = numberOfPlayers;
	}

	/**
	 * @return the mapName
	 */
	public final String getMapName() {
		return mapName;
	}

	/**
	 * @param mapName the mapName to set
	 */
	public final void setMapName(String mapName) {
		this.mapName = mapName;
	}

	/**
	 * @return the completed
	 */
	public final boolean isCompleted() {
		return completed;
	}

	/**
	 * Calling this method sets the winning team
	 * to the passed in parameter (a not-so-good idea)
	 * and sets the completed boolean to true.
	 * 
	 * @param completed the completed to set
	 */
	public final void setGameOver(String winningTeam) {
		this.winningTeam = winningTeam;
		this.completed = true;
	}

	/**
	 * @return the redTeam
	 */
	public final List<GamePlayer> getRedTeam() {
		return redTeam;
	}

	/**
	 * @param redTeam the redTeam to set
	 */
	public final void setRedTeam(List<GamePlayer> redTeam) {
		this.redTeam = redTeam;
	}

	/**
	 * @return the blueTeam
	 */
	public final List<GamePlayer> getBlueTeam() {
		return blueTeam;
	}

	/**
	 * @param blueTeam the blueTeam to set
	 */
	public final void setBlueTeam(List<GamePlayer> blueTeam) {
		this.blueTeam = blueTeam;
	}

	/**
	 * @return the winningTeam
	 */
	public final String getWinningTeam() {
		return winningTeam;
	}

	/**
	 * @param winningTeam the winningTeam to set
	 */
	public final void setWinningTeam(String winningTeam) {
		this.winningTeam = winningTeam;
	}
}

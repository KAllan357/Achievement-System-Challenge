package kda.achievement.domain;

import java.util.List;

public class Player {

	private String playerName;
	
	private int totalWins;
	private int totalGamesPlayed;
	
	private int totalHoursPlayed;
	
	private int totalKills;
	private int totalDeaths;
	
	//A persistent set of achievements this player has won over his lifetime
	private List<Achievement> achievementsList;

	/**
	 * @return the playerName
	 */
	public final String getPlayerName() {
		return playerName;
	}

	/**
	 * @param playerName the playerName to set
	 */
	public final void setPlayerName(String playerName) {
		this.playerName = playerName;
	}

	/**
	 * @return the totalWins
	 */
	public final int getTotalWins() {
		return totalWins;
	}

	/**
	 * @param totalWins the totalWins to set
	 */
	public final void setTotalWins(int totalWins) {
		this.totalWins = totalWins;
	}

	/**
	 * @return the totalGamesPlayed
	 */
	public final int getTotalGamesPlayed() {
		return totalGamesPlayed;
	}

	/**
	 * @param totalGamesPlayed the totalGamesPlayed to set
	 */
	public final void setTotalGamesPlayed(int totalGamesPlayed) {
		this.totalGamesPlayed = totalGamesPlayed;
	}

	/**
	 * @return the totalHoursPlayed
	 */
	public final int getTotalHoursPlayed() {
		return totalHoursPlayed;
	}

	/**
	 * @param totalHoursPlayed the totalHoursPlayed to set
	 */
	public final void setTotalHoursPlayed(int totalHoursPlayed) {
		this.totalHoursPlayed = totalHoursPlayed;
	}

	/**
	 * @return the totalKills
	 */
	public final int getTotalKills() {
		return totalKills;
	}

	/**
	 * @param totalKills the totalKills to set
	 */
	public final void setTotalKills(int totalKills) {
		this.totalKills = totalKills;
	}

	/**
	 * @return the totalDeaths
	 */
	public final int getTotalDeaths() {
		return totalDeaths;
	}

	/**
	 * @param totalDeaths the totalDeaths to set
	 */
	public final void setTotalDeaths(int totalDeaths) {
		this.totalDeaths = totalDeaths;
	}

	/**
	 * @return the achievementsList
	 */
	public final List<Achievement> getAchievementsList() {
		return achievementsList;
	}

	/**
	 * @param achievementsList the achievementsList to set
	 */
	public final void setAchievementsList(List<Achievement> achievementsList) {
		this.achievementsList = achievementsList;
	}
}

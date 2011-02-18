package kda.achievement.domain;

public class GamePlayer {

	//Relate this back to a Player somehow
	private String playerName;
	
	//Hit and Miss data
	private int attemptedAttackCount;
	private int hitCount;
	
	//Damage inflicted and received data
	private int totalDamageInflicted;
	private int totalPhysicalDamageInflicted;
	private int totalMagicalDamageInflicted;
	
	//New Data!
	private int totalDamageReceived;
	private int totalPhysicalDamageReceived;
	private int totalMagicalDamageReceived;
	
	//Kills and Deaths data
	private int killCount;
	private int deathCount;
	private int assistsCount;
	
	//These two are a bit obscure.
	private int firstHitKillCount;
	private int totalTimePlayed;
	
	public GamePlayer(String playerName) {
		this.playerName = playerName;
		this.totalPhysicalDamageInflicted = 0;
		this.totalMagicalDamageInflicted = 0;
		this.totalPhysicalDamageReceived = 0;
		this.totalMagicalDamageReceived = 0;
	}
	
	public GamePlayer() {
		this.totalPhysicalDamageInflicted = 0;
		this.totalMagicalDamageInflicted = 0;
		this.totalPhysicalDamageReceived = 0;
		this.totalMagicalDamageReceived = 0;
	}

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
	 * @return the attemptedAttackCount
	 */
	public final int getAttemptedAttackCount() {
		return attemptedAttackCount;
	}

	/**
	 * @param attemptedAttackCount the attemptedAttackCount to set
	 */
	public final void setAttemptedAttackCount(int attemptedAttackCount) {
		this.attemptedAttackCount = attemptedAttackCount;
	}

	/**
	 * @return the hitCount
	 */
	public final int getHitCount() {
		return hitCount;
	}

	/**
	 * @param hitCount the hitCount to set
	 */
	public final void setHitCount(int hitCount) {
		this.hitCount = hitCount;
	}

	/**
	 * @return the totalDamageInflicted
	 */
	public final int getTotalDamageInflicted() {
		return totalDamageInflicted;
	}

	/**
	 * totalDamageInflicted represents the addition of totalPhysicalDamageInflicted
	 * and totalMagicalDamageInflicted.
	 */
	public final void setTotalDamageInflicted() {
		this.totalDamageInflicted = this.totalPhysicalDamageInflicted + this.totalMagicalDamageInflicted;
	}

	/**
	 * @return the totalPhysicalDamageInflicted
	 */
	public final int getTotalPhysicalDamageInflicted() {
		return totalPhysicalDamageInflicted;
	}

	/**
	 * @param totalPhysicalDamageInflicted the totalPhysicalDamageInflicted to set
	 */
	public final void setTotalPhysicalDamageInflicted(
			int totalPhysicalDamageInflicted) {
		this.totalPhysicalDamageInflicted = totalPhysicalDamageInflicted;
	}

	/**
	 * @return the totalMagicalDamageInflicted
	 */
	public final int getTotalMagicalDamageInflicted() {
		return totalMagicalDamageInflicted;
	}

	/**
	 * @param totalMagicalDamageInflicted the totalMagicalDamageInflicted to set
	 */
	public final void setTotalMagicalDamageInflicted(int totalMagicalDamageInflicted) {
		this.totalMagicalDamageInflicted = totalMagicalDamageInflicted;
	}

	/**
	 * @return the totalDamageReceived
	 */
	public final int getTotalDamageReceived() {
		return totalDamageReceived;
	}

	/**
	 * totalDamageReceived represents the addition of totalPhysicalDamageReceived and
	 * totalMagicalDamageReceived.
	 */
	public final void setTotalDamageReceived() {
		this.totalDamageReceived = this.totalPhysicalDamageReceived + this.totalMagicalDamageReceived;
	}

	/**
	 * @return the totalPhysicalDamageReceived
	 */
	public final int getTotalPhysicalDamageReceived() {
		return totalPhysicalDamageReceived;
	}

	/**
	 * @param totalPhysicalDamageReceived the totalPhysicalDamageReceived to set
	 */
	public final void setTotalPhysicalDamageReceived(int totalPhysicalDamageReceived) {
		this.totalPhysicalDamageReceived = totalPhysicalDamageReceived;
	}

	/**
	 * @return the totalMagicalDamageReceived
	 */
	public final int getTotalMagicalDamageReceived() {
		return totalMagicalDamageReceived;
	}

	/**
	 * @param totalMagicalDamageReceived the totalMagicalDamageReceived to set
	 */
	public final void setTotalMagicalDamageReceived(int totalMagicalDamageReceived) {
		this.totalMagicalDamageReceived = totalMagicalDamageReceived;
	}

	/**
	 * @return the killCount
	 */
	public final int getKillCount() {
		return killCount;
	}

	/**
	 * @param killCount the killCount to set
	 */
	public final void setKillCount(int killCount) {
		this.killCount = killCount;
	}

	/**
	 * @return the deathCount
	 */
	public final int getDeathCount() {
		return deathCount;
	}

	/**
	 * @param deathCount the deathCount to set
	 */
	public final void setDeathCount(int deathCount) {
		this.deathCount = deathCount;
	}

	/**
	 * @return the assistsCount
	 */
	public final int getAssistsCount() {
		return assistsCount;
	}

	/**
	 * @param assistsCount the assistsCount to set
	 */
	public final void setAssistsCount(int assistsCount) {
		this.assistsCount = assistsCount;
	}

	/**
	 * @return the firstHitKillCount
	 */
	public final int getFirstHitKillCount() {
		return firstHitKillCount;
	}

	/**
	 * @param firstHitKillCount the firstHitKillCount to set
	 */
	public final void setFirstHitKillCount(int firstHitKillCount) {
		this.firstHitKillCount = firstHitKillCount;
	}

	/**
	 * @return the totalTimePlayed
	 */
	public final int getTotalTimePlayed() {
		return totalTimePlayed;
	}

	/**
	 * @param totalTimePlayed the totalTimePlayed to set
	 */
	public final void setTotalTimePlayed(int totalTimePlayed) {
		this.totalTimePlayed = totalTimePlayed;
	}
}
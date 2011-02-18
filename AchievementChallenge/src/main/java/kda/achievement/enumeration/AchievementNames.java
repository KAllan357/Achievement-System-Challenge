package kda.achievement.enumeration;

public enum AchievementNames {

	SHARPSHOOTER("sharpshooterAchievement.xml"),
	BRUISER("bruiserAchievement.xml"),
	VETERAN("veteranAchievement.xml"),
	BIGWINNER("bruiserAchievement.xml");
	
	private String achievementFileName;
	
	AchievementNames(String achievementFileName) {
		this.achievementFileName = achievementFileName;
	}
	
	public String getAchievementFileName() {
		return this.achievementFileName;
	}
}

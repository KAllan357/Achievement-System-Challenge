package kda.achievement.logic;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import kda.achievement.domain.GamePlayer;
import kda.achievement.domain.Player;
import kda.achievement.domain.Rule;
import kda.achievement.enumeration.Comparators;
import kda.achievement.enumeration.Operators;

public class RuleHandler {

	private Map<String, String> methodNameMap = new HashMap<String, String>();
	private List<Operators> operatorsList = new ArrayList<Operators>();
	private Comparators comparator;
	private BigDecimal constant;

	/**
	 * minimal constructor
	 */
	public RuleHandler() {
		
	}
	
	/**
	 * Creates a new RuleHandler using the provided Rules
	 * 
	 * @param rulesList
	 */
	public RuleHandler(List<Rule> rulesList) {

		setupRuleHandler(rulesList);
	}
	
	public boolean processRule(final Player player, final GamePlayer gamePlayer) {
		List<Integer> methodValues = processMethodNameMap(player, gamePlayer);
		return processRuleEvaluation(methodValues);
	}
	
	/**
	 * Processes this instance's methodNameMap using reflection. The parameters passed in should contain the 
	 * methods that have been loaded into the methodNameMap and they will be executed using reflection and
	 * returned.
	 * 
	 * @param player
	 * @param gamePlayer
	 * @return List<Integer>
	 */
	public List<Integer> processMethodNameMap(Player player, GamePlayer gamePlayer) {

		List<Integer> processedValues = new ArrayList<Integer>();
		Class<Player> playerClass = Player.class;
		Class<GamePlayer> gamePlayerClass = GamePlayer.class;
		for(Entry<String, String> entry : methodNameMap.entrySet()) {
			try {
				String ruleType = entry.getKey();
				String methodName = entry.getValue();
				String completeMethodName = "get" + methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
				Integer value = null;
				if(player != null && ruleType.startsWith("PLAYER")) {
					Method methodToInvoke = playerClass.getMethod(completeMethodName);
					value = (Integer) methodToInvoke.invoke(player, new Object[0]);
				}
				if(gamePlayer != null && ruleType.startsWith("GAMEPLAYER")) {
					Method methodToInvoke = gamePlayerClass.getMethod(completeMethodName);
					value = (Integer) methodToInvoke.invoke(gamePlayer, new Object[0]);
				}
				if(value != null) {
					processedValues.add(value);
				}
			} catch(Exception e) {

				//Needs better error handling
				e.printStackTrace();
			}
		}
		return processedValues;
	}

	/**
	 * Receives a List of values that have been processed and compares them against
	 * this instance's operatorsList, comparator, and constant members. First, operators 
	 * are applied and the appropriate math is executed. Next, the comparator is evaluated
	 * with the previous operation and the constant member. Finally, The result of that
	 * comparison is returned.
	 * 
	 * @param achievementValues
	 * @return boolean
	 */
	public boolean processRuleEvaluation(List<Integer> achievementValues) {

		// achievementValues are the values from the object being evaluated
		// get the first, in case it is the only entry.
		boolean achievementGranted = false;
		if(achievementValues != null && !achievementValues.isEmpty()) {
			BigDecimal total = new BigDecimal(achievementValues.get(0));
			for (Integer value : achievementValues.subList(1, achievementValues.size())) {
	
				// for the rest of the list, get an operator and do the math.
				Operators operator = operatorsList.get(0);
				BigDecimal valueBigDecimal = new BigDecimal(value);
				switch (operator) {
				case ADD:
					total = total.add(valueBigDecimal);
					break;
				case DIVIDE:
					if(!valueBigDecimal.equals(new BigDecimal(0))) {
						total = total.divide(valueBigDecimal, 2, RoundingMode.HALF_UP);
						break;
					}
				case MULTIPLY:
					total = total.multiply(valueBigDecimal);
					break;
				case SUBTRACT:
					total = total.subtract(valueBigDecimal);
					break;
				}
			}
	
			// Depending on the comparator, do a different evaluation to the constant
			switch (comparator) {
			case EQUAL:
				achievementGranted = total.equals(constant);
				break;
			case GREATER_THAN:
				achievementGranted = total.compareTo(constant) == 1;
				break;
			case GREATER_THAN_OR_EQUAL:
				achievementGranted = total.compareTo(constant) == 0 || total.compareTo(constant) == 1;
				break;
			case LESS_THAN:
				achievementGranted = total.compareTo(constant) == -1;
				break;
			case LESS_THAN_OR_EQUAL:
				achievementGranted = total.compareTo(constant) == 0 || total.compareTo(constant) == -1;
				break;
			case NOT_EQUAL:
				achievementGranted = !total.equals(constant);
				break;
			}
		}
		return achievementGranted;
	}

	/**
	 * Checks to see whether or not this class was built correctly when it was instantiated.
	 * 
	 * @return boolean
	 */
	private boolean isWellFormed() {

		boolean isWellFormed = true;

		// The numbersList cannot be empty and the others cannot be null
		if (methodNameMap.isEmpty() || comparator == null || constant == null) {
			isWellFormed = false;
		}

		// If numbersList has x elements, operatorsList must have x-1 elements.
		int numbersListMinusOne = methodNameMap.size() - 1;
		if (operatorsList.size() != numbersListMinusOne) {
			isWellFormed = false;
		}
		return isWellFormed;
	}
	
	public final void setupRuleHandler(final List<Rule> rulesList) {

		clearRuleHandler();
		int mapIndex = 0;
		for (Rule rule : rulesList) {
			String ruleType = rule.getType();
			String ruleValue = rule.getValue();
			String ruleMethod = rule.getMethod();

			if (Rule.TYPE_PLAYER.equals(ruleType) || Rule.TYPE_GAMEPLAYER.equals(ruleType)) {

				// Add to numbersList
				this.methodNameMap.put(ruleType + mapIndex, ruleMethod);
			}
			if (Rule.TYPE_MATH.equals(ruleType)) {

				// Add to operatorsList
				this.operatorsList.add(Operators.valueOf(ruleMethod));
			}
			if (Rule.TYPE_EVAL.equals(ruleType)) {

				// This is the comparator
				this.comparator = Comparators.valueOf(ruleMethod);
			}
			if (Rule.TYPE_CONSTANT.equals(ruleType)) {

				// Add as constant
				this.constant = new BigDecimal(ruleValue);
			}
			mapIndex++;
		}
		if (!isWellFormed()) {
			throw new IllegalArgumentException("This rulesList was not well formed. Please ensure that the Rules are defined correctly.");
		}
	}
	
	private void clearRuleHandler() {
		
		//Resets the RuleHandler so that it can process another list of rules
		methodNameMap = new HashMap<String, String>();
		operatorsList = new ArrayList<Operators>();
		Comparators comparator = null;
		BigDecimal constant = null;
	}

	/**
	 * @return the operatorsList
	 */
	public final List<Operators> getOperatorsList() {
		return operatorsList;
	}

	/**
	 * @param operatorsList
	 *            the operatorsList to set
	 */
	public final void setOperatorsList(List<Operators> operatorsList) {
		this.operatorsList = operatorsList;
	}

	/**
	 * @return the comparator
	 */
	public final Comparators getComparator() {
		return comparator;
	}

	/**
	 * @param comparator
	 *            the comparator to set
	 */
	public final void setComparator(Comparators comparator) {
		this.comparator = comparator;
	}

	/**
	 * @return the constant
	 */
	public final BigDecimal getConstant() {
		return constant;
	}

	/**
	 * @param constant
	 *            the constant to set
	 */
	public final void setConstant(BigDecimal constant) {
		this.constant = constant;
	}

	/**
	 * @return the methodNameMap
	 */
	public final Map<String, String> getMethodNameMap() {
		return methodNameMap;
	}

	/**
	 * @param methodNameMap the methodNameMap to set
	 */
	public final void setMethodNameMap(Map<String, String> methodNameMap) {
		this.methodNameMap = methodNameMap;
	}
}
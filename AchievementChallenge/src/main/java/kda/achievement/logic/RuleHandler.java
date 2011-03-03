package kda.achievement.logic;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.math.fraction.Fraction;
import org.apache.commons.math.util.MathUtils;

import kda.achievement.domain.GamePlayer;
import kda.achievement.domain.Player;
import kda.achievement.domain.Rule;
import kda.achievement.enumeration.Comparators;
import kda.achievement.enumeration.Operators;

public class RuleHandler {

	private List<String> numbersList = new ArrayList<String>();
	private List<Operators> operatorsList = new ArrayList<Operators>();
	private Comparators comparator;
	private BigDecimal constant;

	/**
	 * Creates a new RuleHandler using the provided Rules
	 * 
	 * @param rulesList
	 */
	public RuleHandler(List<Rule> rulesList) {
		
		for(Rule rule : rulesList) {
			String ruleType = rule.getType();
			String ruleValue = rule.getValue();
			String ruleMethod = rule.getMethod();
			
			if(Rule.TYPE_PLAYER.equals(ruleType) || Rule.TYPE_GAMEPLAYER.equals(ruleType)) {
				
				//Add to numbersList
				this.numbersList.add(ruleMethod);
			}
			if(Rule.TYPE_MATH.equals(ruleType)) {
				
				//Add to operatorsList
				this.operatorsList.add(Operators.valueOf(ruleMethod));
			}
			if(Rule.TYPE_EVAL.equals(ruleType)) {
				
				//This is the comparator
				this.comparator = Comparators.valueOf(ruleMethod);
			}
			if(Rule.TYPE_CONSTANT.equals(ruleType)) {
				
				//Add as constant
				this.constant = new BigDecimal(ruleValue);
			}
		}
		if(!isWellFormed()) {
			throw new IllegalArgumentException("This rulesList was not well formed. Please ensure that the Rules are defined correctly.");
		}
	}
	
	private boolean isWellFormed() {
		
		boolean isWellFormed = true;

		//None of the list members can be empty and the others cannot be null
		if(numbersList.isEmpty() || operatorsList.isEmpty() || comparator == null || constant == null) {
			isWellFormed = false;
		}
		
		//If numbersList has x elements, operatorsList must have x-1 elements.
		int numbersListMinusOne = numbersList.size() - 1;
		if(operatorsList.size() != numbersListMinusOne) {
			isWellFormed = false;
		}
		return isWellFormed;
	}

	/**
	 * @return the numbersList
	 */
	public final List<String> getNumbersList() {
		return numbersList;
	}

	/**
	 * @param numbersList the numbersList to set
	 */
	public final void setNumbersList(List<String> numbersList) {
		this.numbersList = numbersList;
	}

	/**
	 * @return the operatorsList
	 */
	public final List<Operators> getOperatorsList() {
		return operatorsList;
	}

	/**
	 * @param operatorsList the operatorsList to set
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
	 * @param comparator the comparator to set
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
	 * @param constant the constant to set
	 */
	public final void setConstant(BigDecimal constant) {
		this.constant = constant;
	}

	public List<Integer> processNumbersList(Player player, GamePlayer gamePlayer) {
		
		List<Integer> processedValues = new ArrayList<Integer>();
		if(player != null) {
			
		}
		if(gamePlayer != null) {
			Class gamePlayerClass = GamePlayer.class;
			
			for(String methodName : numbersList) {
				
				try {
					String completeMethodName = "get" + methodName.substring(0, 1).toUpperCase() + methodName.substring(1);
					Method methodToInvoke = gamePlayerClass.getMethod(completeMethodName);
					Integer value = (Integer)methodToInvoke.invoke(gamePlayer, new Object[0]);
					processedValues.add(value);
				} 
				catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}	
			}
			
		}
		return processedValues;
	}

	public boolean processRuleEvaluation(List<Integer> achievementValues) {

		//achievementValues are the values from the object being evaluated
		//get the first, in case it is the only entry.
		BigDecimal total = new BigDecimal(achievementValues.get(0));
		for(Integer value : achievementValues.subList(1, achievementValues.size())) {
			
			//for the rest of the list, get an operator and do the math.
			Operators operator = operatorsList.get(0);
			BigDecimal valueBigDecimal = new BigDecimal(value);
			switch(operator) {
				case ADD:
					total = total.add(valueBigDecimal);
					break;
				case DIVIDE:
					total = total.divide(valueBigDecimal, 2, RoundingMode.HALF_UP);
					break;
				case MULTIPLY:
					total = total.multiply(valueBigDecimal);
					break;
				case SUBTRACT:
					total = total.subtract(valueBigDecimal);
					break;
			}
		}
		
		//Depending on the comparator, do a different evaluation to the constant
		boolean achievementGranted = false;
		switch(comparator) {
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
		return achievementGranted;
	}
}
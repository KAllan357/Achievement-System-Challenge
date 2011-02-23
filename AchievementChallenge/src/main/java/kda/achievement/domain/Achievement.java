package kda.achievement.domain;

import java.util.LinkedList;
import java.util.List;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("Achievement")
public class Achievement {

	@XStreamAlias("Name")
	private String name;
	
	@XStreamAlias("type")
	@XStreamAsAttribute
	private String type; 
	
	//Shows up when this achievement is earned.
	@XStreamAlias("ShortDescription")
	private String shortDescription;
	
	//A longer, more robust description.
	@XStreamAlias("LongDescription")
	private String longDescription;
	
	@XStreamAlias("Rules")
	private LinkedList<Rule> rules;
	
	public Achievement() {
		
	}
	
	/**
	 * @return the name
	 */
	public final String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public final void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the shortDescription
	 */
	public final String getShortDescription() {
		return shortDescription;
	}

	/**
	 * @param shortDescription the shortDescription to set
	 */
	public final void setShortDescription(String shortDescription) {
		this.shortDescription = shortDescription;
	}

	/**
	 * @return the longDescription
	 */
	public final String getLongDescription() {
		return longDescription;
	}

	/**
	 * @param longDescription the longDescription to set
	 */
	public final void setLongDescription(String longDescription) {
		this.longDescription = longDescription;
	}

	/**
	 * @return the type
	 */
	public final String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public final void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the rules
	 */
	public final List<Rule> getRules() {
		return rules;
	}

	/**
	 * @param rules the rules to set
	 */
	public final void setRules(LinkedList<Rule> rules) {
		this.rules = rules;
	}
}

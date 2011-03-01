package kda.achievement.domain;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamAsAttribute;

@XStreamAlias("Rule")
public class Rule {
	
	public static final String TYPE_GAMEPLAYER = "GAMEPLAYER";
	public static final String TYPE_PLAYER = "PLAYER";
	public static final String TYPE_MATH = "MATH";
	public static final String TYPE_EVAL = "EVAL";
	public static final String TYPE_CONSTANT = "CONSTANT";
	
	@XStreamAlias("type")
	@XStreamAsAttribute
	private String type;
	
	@XStreamAlias("method")
	@XStreamAsAttribute
	private String method;
	
	@XStreamAlias("value")
	@XStreamAsAttribute
	private String value;

	public Rule() {
		
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
	 * @return the method
	 */
	public final String getMethod() {
		return method;
	}

	/**
	 * @param method the method to set
	 */
	public final void setMethod(String method) {
		this.method = method;
	}

	/**
	 * @return the value
	 */
	public final String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public final void setValue(String value) {
		this.value = value;
	}
}
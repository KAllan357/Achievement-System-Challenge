package kda.achievement.logic.tests;

import java.io.InputStream;
import java.util.LinkedList;

import org.junit.Assert;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import junit.framework.TestCase;
import kda.achievement.domain.Achievement;
import kda.achievement.domain.Rule;

public class AchievementTest extends TestCase {

	private XStream xstream;
	
	@Override
	protected void setUp() throws Exception {
		xstream = new XStream(new DomDriver());
		xstream.processAnnotations(Achievement.class);
		super.setUp();
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * Resources on the classpath should be readable as streams
	 */
	public void testResourcesAvailable() {
		
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("xml/sharpshooterAchievement.xml");
		Assert.assertNotNull("The resource cannot be found.", inputStream);
	}
	
	/**
	 * Creates an Achievement object and marshalls it into a String of xml
	 */
	public void testAchievementXMLMarshall() {
		
		Achievement achievement = new Achievement();
		achievement.setName("Roy Halladay Achievement");
		achievement.setShortDescription("You pitched a perfect game!");
		achievement.setLongDescription("This achievement is awarded when you pitch a perfect recording 0 hits and 0 earned runs.");
		
		LinkedList<Rule> rules = new LinkedList<Rule>();
		Rule rule = new Rule();
		rule.setType("MATH");
		rule.setMethod("DIVIDE");
		rules.add(rule);
		achievement.setRules(rules);
		String xml = xstream.toXML(achievement);
		Assert.assertNotNull(xml);
		Assert.assertTrue(xml.startsWith("<Achievement>"));
	}
	
	/**
	 * Loads a resource from the classpath and unmarshalls it into an Achievement object
	 */
	public void testAchievementXMLUnmarshall() {

		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("xml/sharpshooterAchievement.xml");
		Assert.assertNotNull("The resource cannot be found.", inputStream);
		
		//Create an Achievement instance out of my xml
		Achievement achievement = (Achievement)xstream.fromXML(inputStream);
		Assert.assertNotNull(achievement);
		Assert.assertEquals("Sharpshooter", achievement.getName());
		Assert.assertNotNull(achievement.getRules());
		Assert.assertFalse(achievement.getRules().isEmpty());
	}
}

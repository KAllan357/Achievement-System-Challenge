package kda.achievement.logic.tests;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

import junit.framework.TestCase;
import kda.achievement.domain.Achievement;
import kda.achievement.domain.Rule;

public class AchievementTest extends TestCase {

	@Override
	protected void setUp() throws Exception {
		super.setUp();
	}
	
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}
	
	public void testResourcesAvailable() {
		
		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("sharpshooterAchievement.xml");
		Assert.assertNotNull("The resource cannot be found.", inputStream);
	}
	
	public void testAchievementXMLUnmarshall() {

		InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("sharpshooterAchievement.xml");
		Assert.assertNotNull("The resource cannot be found.", inputStream);
		XStream xstream = new XStream(new DomDriver());
		xstream.autodetectAnnotations(true);
		
		Achievement foo = new Achievement();
		foo.setName("Kyle");
		foo.setShortDescription("test");
		foo.setLongDescription("This is an interesting test!");
		
		List<Rule> rules = new ArrayList<Rule>();
		Rule rule = new Rule();
		rule.setType("MATH");
		rule.setMethod("DIVIDE");
		rules.add(rule);
		foo.setRules(rules);
		System.out.println(xstream.toXML(foo));

		//Create an Achievement instance out of my xml
		Achievement achievement = (Achievement)xstream.fromXML(inputStream);
		Assert.assertNotNull(achievement);
		Assert.assertEquals("Sharpshooter", achievement.getName());
		Assert.assertNotNull(achievement.getRules());
		Assert.assertFalse(achievement.getRules().isEmpty());
	}
}

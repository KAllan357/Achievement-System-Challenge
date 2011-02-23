package kda.achievement.logic.tests;

import org.apache.commons.math.fraction.Fraction;
import org.apache.commons.math.util.MathUtils;
import org.junit.Assert;

import junit.framework.TestCase;

public class ApacheMathTest extends TestCase {

	@Override
	protected void setUp() throws Exception {
		// TODO Auto-generated method stub
		super.setUp();
	}
	
	@Override
	protected void tearDown() throws Exception {
		// TODO Auto-generated method stub
		super.tearDown();
	}
	
	public void testCommonsFractions() {
		
		int numerator = 1;
		int denominator = 2;
		Assert.assertEquals(.5, Fraction.getReducedFraction(numerator, denominator).doubleValue());
		
		numerator = 2;
		denominator = 3;
		Assert.assertEquals(0.6666666666666666, Fraction.getReducedFraction(numerator, denominator).doubleValue());
		
		numerator = 5;
		denominator = 0;
		try {
			Fraction.getReducedFraction(numerator, denominator);
		}
		catch(ArithmeticException e) {
			//This should throw an exception, so we pass
		}
	}
	
	public void testCommonsAdd() {
		
		int x = 1;
		int y = 2;
		Assert.assertEquals(3,MathUtils.addAndCheck(x, y));
		
		x = 50;
		y = 22;
		Assert.assertEquals(72,MathUtils.addAndCheck(x, y));
		
		x = -2;
		y = 2;
		Assert.assertEquals(0,MathUtils.addAndCheck(x, y));
		
		x = 20;
		y = 0;
		Assert.assertEquals(20,MathUtils.addAndCheck(x, y));
	}
	
	public void testCommonsSubtract() {
		
		int x = 2;
		int y = 1;
		Assert.assertEquals(1,MathUtils.subAndCheck(x, y));
		
		x = 5;
		y = 10;
		Assert.assertEquals(-5,MathUtils.subAndCheck(x, y));
		
		x = 100;
		y = 10;
		Assert.assertEquals(90,MathUtils.subAndCheck(x, y));
	}
	
	public void testCommonsMultiply() {
		
		int x = 2;
		int y = 4;
		Assert.assertEquals(8, MathUtils.mulAndCheck(x, y));
		
		x = 10;
		y = 10;
		Assert.assertEquals(100, MathUtils.mulAndCheck(x, y));
		
		x = 10;
		y = 0;
		Assert.assertEquals(0, MathUtils.mulAndCheck(x, y));
		
		x = 10;
		y = 1;
		Assert.assertEquals(10, MathUtils.mulAndCheck(x, y));
		
		x = 10;
		y = -2;
		Assert.assertEquals(-20, MathUtils.mulAndCheck(x, y));
	}
}

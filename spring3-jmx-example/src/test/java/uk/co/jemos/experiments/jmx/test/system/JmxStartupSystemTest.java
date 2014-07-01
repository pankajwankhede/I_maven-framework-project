package uk.co.jemos.experiments.jmx.test.system;

import javax.management.MBeanServerConnection;
import javax.management.ObjectName;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

import uk.co.jemos.experiments.jmx.mbeans.LoggerConfigurator;

/**
 *
 * @author mtedone
 * 
 */
@ContextConfiguration(locations = { "classpath:jemos-jmx-test-appCtxImpl.xml" })
public class JmxStartupSystemTest extends AbstractJUnit4SpringContextTests {

	// ------------------->> Constants

	// ------------------->> Instance / Static variables

	@Autowired
	private MBeanServerConnection mbeanClient;

	// ------------------->> Constructors

	// ------------------->> Public methods

	@Before
	public void init() {
		Assert.assertNotNull("The MBean Server client cannot be null!");
		Assert.assertNotNull("The Memory MX Bean cannot be null!");
	}

	@Test
	public void helloWorld() throws Exception {

		ObjectName objectName = new ObjectName(LoggerConfigurator.MBEAN_NAME);
		mbeanClient.invoke(objectName, "setLoggerLevel", new Object[] {
				"uk.co.jemos.experiments", "TRACE" }, new String[] {
				String.class.getName(), String.class.getName() });

	}
	// ------------------->> Getters / Setters

	// ------------------->> Private methods

	// ------------------->> equals() / hashcode() / toString()

	// ------------------->> Inner classes

}

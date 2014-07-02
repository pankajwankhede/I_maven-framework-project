package net.aimeizi.spring.example;

import net.aimeizi.spring.rest.example.WelcomeController;
import org.junit.Test;

public class WelcomeControllerTests {
	
	private WelcomeController controller = new WelcomeController();
	
	@Test
	public void welcome() {
		controller.welcome();
	}
	
}

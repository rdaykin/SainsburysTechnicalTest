import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import controller.AppController;

public class SainsburysTechnicalTest {

	public static void main(String[] args){

		ApplicationContext context = new ClassPathXmlApplicationContext(
				"techtest-spring.xml");
		AppController controller = (AppController) context.getBean("appController");
		controller.generateJson();

	}
}

package polymorphism.coupling;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class TvUserContainer {

	public static void main(String[] args) {

		AbstractApplicationContext  factory =
				new GenericXmlApplicationContext("applicationContext.xml");
		TV tv = (TV)factory.getBean("tv"); //DI(Dependency Injection) 의존성 주입
		
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();

	}
}

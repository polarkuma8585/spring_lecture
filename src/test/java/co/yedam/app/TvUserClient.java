package co.yedam.app;

import javax.annotation.Resource;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import polymorphism.coupling.TV;

@RunWith(SpringRunner.class)
@ContextConfiguration({"/applicationContext.xml"})
public class TvUserClient {
	
//	@Autowired @Qualifier("stv") TV tv; // (TV)factory.getBean("tv")와 동일 DI;
	@Resource(name = "stv") TV tv;
	
	@Test
	public void tvTest() {
		tv.powerOn();
		tv.volumeUp();
	}
	@Ignore
	public void tvTest2() {
		
	}
}

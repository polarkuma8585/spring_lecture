package polymorphism.coupling;

import org.springframework.stereotype.Component;

@Component("ltv")
public class LgTV implements TV{

	public void powerOn() {
		System.out.println("LgTV powerOn");
	}
	public void powerOff() {
		System.out.println("LgTV powerDown");
	}
	public void volumeUp() {
		System.out.println("LgTV soundUp");
	}
	public void volumeDown() {
		System.out.println("LgTV soundDown");
	}	
}

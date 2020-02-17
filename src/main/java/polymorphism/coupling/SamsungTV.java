package polymorphism.coupling;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("stv")
public class SamsungTV implements TV {
	@Autowired Speaker speaker;
	
//	public SamsungTV() {
//		
//	}
//	public SamsungTV(Speaker speaker) {
//		this.speaker = speaker;
//	}
//	public void setSpeaker(Speaker speaker) { // Speaker 초기화. set method 이용 
//		this.speaker = speaker;
//	}
	public void powerOn() {
		System.out.println("SamsungTV powerOn");
	}
	public void powerOff() {
		System.out.println("SamsungTV powerDown");
	}
	public void volumeUp() {
		speaker.volumeUp();
	}
	public void volumeDown() {
		speaker.volumeDown();
	}	
}

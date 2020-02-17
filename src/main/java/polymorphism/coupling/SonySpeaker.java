package polymorphism.coupling;

import org.springframework.stereotype.Component;

//@Component("speaker")
public class SonySpeaker implements Speaker {
	public SonySpeaker(){
		System.out.println("SonySpeaker 생성");
	}
	@Override
	public void volumeUp(){
		System.out.println("SonySpeaker 소리 올림");
	}
	@Override
	public void volumeDown(){
		System.out.println("SonySpeaker 소리 내림");
	}	
}

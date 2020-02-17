package polymorphism.coupling;

import org.springframework.stereotype.Component;

@Component("speaker")
public class AppleSpeaker implements Speaker {
	public AppleSpeaker(){
		System.out.println("AppleSpeaker 생성");
	}
	@Override
	public void volumeUp(){
		System.out.println("AppleSpeaker 소리 올림");
	}
	@Override
	public void volumeDown(){
		System.out.println("AppleSpeaker 소리 내림");
	}	
}

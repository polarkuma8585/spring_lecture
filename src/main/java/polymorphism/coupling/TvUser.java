package polymorphism.coupling;

public class TvUser {

	public static void main(String[] args) {
		//TV tv = new SamsungTV();
		BeanFactory factory = new BeanFactory();
		TV tv = (TV)factory.getBean(args[0]);
		tv.powerOn();
		tv.volumeUp();
		tv.volumeDown();
		tv.powerOff();

	}
}

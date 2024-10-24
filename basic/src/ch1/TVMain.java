package ch1;

public class TVMain {
    public static void main(String[] args) {
        // LgTV 사용
        // 객체 생성
        // 다른 거(스피커)를 부르려면 무조건 new 다른거를 써야
        // LgTV lgTV = new LgTV(); // 티비안에 스피커가 있으니 부르는 개념 => 티비가서 쓸 수 있음

        // lgTV.setBritzSpeaker(new BritzSpeaker());

        // lgTV.powerOn();
        // lgTV.volumeUp();
        // lgTV.volumeDown();
        // lgTV.powerOff();

        // SamsungTV samsungTV = new SamsungTV();
        // samsungTV.setBritzSpeaker(new BritzSpeaker());
        // samsungTV.powerOn();
        // samsungTV.volumeUp();
        // samsungTV.volumeDown();
        // samsungTV.powerOff();

        // 이름만 바꾸면 바뀜 - 인터페이스를 쓰는 이유
        TV tv = new SamsungTV();
        // ((SamsungTV)tv).setBritzSpeaker(new SonySpeaker());

        tv = new LgTV();
        ((LgTV) tv).setBritzSpeaker(new SonySpeaker());

        tv.powerOn();
        tv.volumeUp();
        tv.volumeDown();
        tv.powerOff();

    }
}

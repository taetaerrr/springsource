package ch1;
/* 변수
 * 1) 멤버 변수 : 클래스 안에 선언된 변수
 *                객체인 경우 null 로 초기화 됨
 *                기본 타입 변수(float,double,int,char,boolean)인 경우 0 or '' or 0.0. false 초기화 됨
 * 2) 지역 변수 : 메소드나 {} 안에 선언된 변수
 * 3) 매개 변수 : powerOn(int x) => x 를 부를 때
 */

public class Speaker implements TV {

    // 멤버변수(==인스턴스 변수,필드,특성,속성)
    // 초기화 == null
    // new brizSpeaker 기능
    private BritzSpeaker britzSpeaker;

    // private int age; 기본 타입 변수

    public void setBritzSpeaker(BritzSpeaker britzSpeaker) {
        this.britzSpeaker = britzSpeaker;
    }

    @Override
    public void powerOn() {
        System.out.println("SamsungTV - 전원 On");
    }

    @Override
    public void powerOff() {
        System.out.println("SamsungTV - 전원 Off");
    }

    @Override
    public void volumeUp() {
        // System.out.println("SamsungTV - 볼륨 Up");
        // britz스피커로 볼륨 조절할거
        britzSpeaker.volumeUp();
    }

    @Override
    public void volumeDown() {
        // System.out.println("SamsungTV - 볼륨 Down");
        britzSpeaker.volumeDown();
    }

}

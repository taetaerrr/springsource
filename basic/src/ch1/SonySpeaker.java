package ch1;

public class SonySpeaker implements Speaker {

    @Override
    public void volumeUp() {
        System.out.println("BritzSpeaker volumeUp");
    }

    @Override
    public void volumeDown() {
        System.out.println("BritzSpeaker volumeDown");
    }

}

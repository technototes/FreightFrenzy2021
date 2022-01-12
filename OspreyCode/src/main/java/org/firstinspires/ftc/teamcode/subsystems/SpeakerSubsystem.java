package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.subsystems.SpeakerSubsystem.SpeakerConstants.*;

import com.acmerobotics.dashboard.config.Config;
import com.technototes.library.hardware.Speaker;
import com.technototes.library.subsystem.Subsystem;

import java.util.function.Supplier;

public class SpeakerSubsystem implements Subsystem, Supplier<String> {
    @com.acmerobotics.dashboard.config.Config
    public static class SpeakerConstants{
        public static String AMOGUS = "among4";
        public static String JEOPARDY = "";
        public static String TERRARIA = "";
        public static String COUNTDOWN = "";
    }
    public Speaker speaker;

    public SpeakerSubsystem(Speaker s){
        speaker = s.addSongs(AMOGUS);
    }

    public void playAmogus(){

        //speaker.start(AMOGUS);
    }

    public void playJeopardy(){
        //TODO speaker.start(JEOPARDY);
    }

    public void playCountdown(){
        //TODO speaker.start(COUNTDOWN);
    }

    public void playTerraria(){
        //TODO speaker.start(TERRARIA);
    }

    @Override
    public String get() {
        return speaker.getCurrentSong();
    }

}

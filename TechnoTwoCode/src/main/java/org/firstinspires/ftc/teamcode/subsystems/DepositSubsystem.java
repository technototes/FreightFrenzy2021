package org.firstinspires.ftc.teamcode.subsystems;

import com.acmerobotics.dashboard.config.Config;
import com.technototes.library.subsystem.Subsystem;

public class DepositSubsystem implements Subsystem {
    public Servo dumpservo;
    public Servo armservo;
    public DepositSubsystem(Servo arm, Servo dump) {
        dumpservo = dump;
        armservo = arm;
    }
    @Config
    public static class DepositConstants {
        public static double DUMP = 1, CARRY = 0.5, COLLECT = 0;
        public static double IN = 1, OUT = 0;
    }
    public void dump() {
        dumpservo.setPosition(DUMP);
    }
    public void carry() {
        dumpservo.setPosition(CARRY);
    }
    public void collect() {
        dumpservo.setPosition(COLLECT);
    }
    public void setDump(double v) {
        dumpservo.setPosition(Range.clip(v, CARRY, DUMP));
    }

    public void  fullyIn() {

        armservo.setPosition(IN);

    }

    public void fullyOut() {
        armservo.setPosition(OUT);
    }
    public void setExtension(double v) {
        armservo.setPosition(Range.clip(v, IN, OUT));
    }
    public void translateExtension(double v) {
        armservo.incrementPosition(v);
    }
    @Override
    public String get() {
        return "Extension: " + armoservo.getPosition() + ", Dump: " + dumpservo.getPosition();
    }
}

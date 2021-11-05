package org.firstinspires.ftc.teamcode.subsystems;

import static org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem.DepositConstants.CARRY;
import static org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem.DepositConstants.COLLECT;
import static org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem.DepositConstants.DUMP;
import static org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem.DepositConstants.IN;
import static org.firstinspires.ftc.teamcode.subsystems.DepositSubsystem.DepositConstants.OUT;

import com.acmerobotics.dashboard.config.Config;
import com.qualcomm.robotcore.util.Range;
import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.subsystem.Subsystem;

import java.util.function.Supplier;

public class DepositSubsystem implements Subsystem, Supplier<String> {
    public Servo dumpservo;
    //public Servo armservo;
    public DepositSubsystem(Servo dump) {
        dumpservo = dump;
        //armservo = arm;
    }

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

    public void fullyIn() {

        //armservo.setPosition(IN);

    }

    public void fullyOut() {
//        armservo.setPosition(OUT);
    }

    public void setExtension(double v) {
       // armservo.setPosition(Range.clip(v, IN, OUT));
    }
    public void translateExtension(double v) {
        //armservo.incrementPosition(v);
    }

    @Override
    public String get() {
        return "Dump: " + dumpservo.getPosition();
    }

}

package org.firstinspires.ftc.teamcode.subsystems;

import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.subsystem.servo.ServoSubsystem;
import com.technototes.logger.Stated;

public class WobbleSubsystem extends ServoSubsystem implements Stated<String> {


    public enum ArmPosition{
        RAISED(0), LOWERED(1);
        public double position;
        ArmPosition(double pos) {
            position = pos;
        }
        public double getPosition(){
            return position;
        }
    }

    public enum ClawPosition{
        OPEN(0), CLOSED(1);
        public double position;
        ClawPosition(double pos) {
            position = pos;
        }
        public double getPosition(){
            return position;
        }
    }
    //hardware devices
    public Servo armServo;
    public Servo clawServo;

    public Servo turretServo;

    //variables for wobble states
    public ArmPosition armPosition;
    public ClawPosition clawPosition;

    public WobbleSubsystem(Servo arm, Servo claw, Servo turret){
        super(arm, claw, turret);
        armServo = arm;
        clawServo = claw;
        turretServo = turret;
        armPosition = ArmPosition.LOWERED;
        clawPosition = ClawPosition.CLOSED;
    }
    //set claw position to either OPEN(1) or CLOSED(0.5)
    public void setClawPosition(ClawPosition pos){
        clawServo.setPosition(pos.getPosition());
        clawPosition = pos;

    }
    //set arm position to either RAISED(0.5) or LOWERED(0)
    public void setArmPosition(ArmPosition pos){
        armServo.setPosition(pos.getPosition());
        armPosition = pos;
    }

    public void setTurretPosition(double val){
        turretServo.setPosition(val);
    }

    public double getTurretPosition(){
        return turretServo.getPosition();
    }

    @Override
    public String getState() {
        return "CLAW: "+clawPosition+". ARM: "+armPosition;
    }

}

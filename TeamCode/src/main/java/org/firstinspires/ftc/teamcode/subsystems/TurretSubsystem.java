package org.firstinspires.ftc.teamcode.subsystems;

import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.subsystem.servo.ServoSubsystem;
import com.technototes.logger.Stated;

public class TurretSubsystem extends ServoSubsystem implements Stated<String> {
    Servo turretServo;


    public TurretSubsystem(Servo turret){
        super(turret);
        turretServo = turret;
    }

    public void setTurretPosition(double val){
        turretServo.setPosition(val);
    }

    public double getTurretPosition(){
        return turretServo.getPosition();
    }

    public void changeBy(double v){
        setTurretPosition(getTurretPosition()+v);
    }

    @Override
    public String getState() {
        System.out.println(turretServo.getPosition());
        return "TURRET: " + turretServo.getPosition();
    }
}

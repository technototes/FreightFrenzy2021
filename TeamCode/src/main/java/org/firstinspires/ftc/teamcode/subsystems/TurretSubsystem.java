package org.firstinspires.ftc.teamcode.subsystems;

import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.subsystem.servo.ServoSubsystem;
import com.technototes.logger.Stated;

public class TurretSubsystem extends ServoSubsystem implements Stated<String> {
    public Servo turretServo, raiseServo;


    public TurretSubsystem(Servo turret, Servo raise){
        super(turret, raise);
        turretServo = turret;
        raiseServo = raise;
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

    public void raise(){
        raiseServo.setPosition(0.87);
    }
    @Override
    public String getState() {
        System.out.println(turretServo.getPosition());
        return "TURRET: " + turretServo.getPosition();
    }
}

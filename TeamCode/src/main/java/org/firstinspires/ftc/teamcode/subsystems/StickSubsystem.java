package org.firstinspires.ftc.teamcode.subsystems;

import com.technototes.library.hardware.servo.Servo;
import com.technototes.library.subsystem.servo.ServoSubsystem;
import com.technototes.logger.Stated;

public class StickSubsystem extends ServoSubsystem implements Stated<StickSubsystem.StickState> {
   public Servo servo;

   public enum StickState{
       UP(1), DOWN(0);
       public double val;
       StickState(double v){
           val = v;
       }
       public double getVal(){
           return val;
       }
   }
   public StickState state;
   public StickSubsystem(Servo s){
       super (s);
       servo = s;
       state = StickState.UP;
   }

    public void raise(){
       servo.setPosition(StickState.UP.getVal());
       state = StickState.UP;
    }
    public void lower(){
       servo.setPosition(StickState.DOWN.getVal());
       state = StickState.DOWN;
    }


    @Override
    public StickState getState() {
        return state;
    }

}

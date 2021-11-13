package org.firstinspires.ftc.teamcode.commands.bucket;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;

import java.util.function.DoubleSupplier;

public class BucketCommand implements Command {
    /**
     * for some reason it must be a DoubleSupplier
     */
    DumpSubsystem bucketSys;
    DoubleSupplier doubleSupplier_motor;
    DoubleSupplier doubleSupplier_servo;

    public BucketCommand(DumpSubsystem bs, DoubleSupplier ds_m, DoubleSupplier ds_s){
        this.bucketSys = bs;
        this.doubleSupplier_motor = ds_m;
        this.doubleSupplier_servo = ds_s;
        addRequirements(bs);
    }
    public BucketCommand(DumpSubsystem bs, double d_m, double d_s){
        this(bs, ()->d_m, ()-> d_s);
    }
    public BucketCommand(DumpSubsystem bs, double[] combo){
        this(bs, combo[0], combo[1]);
    }

    @Override
    public void init() {
        bucketSys.setPositionCombination(doubleSupplier_motor.getAsDouble(),doubleSupplier_servo.getAsDouble());
    }

    @Override
    public void execute() {}

    @Override
    public boolean isFinished() {
        return bucketSys.isAtTarget();
    }
}

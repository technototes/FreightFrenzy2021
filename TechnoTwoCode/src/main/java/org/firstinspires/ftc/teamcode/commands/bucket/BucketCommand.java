package org.firstinspires.ftc.teamcode.commands.bucket;

import com.technototes.library.command.Command;

import org.firstinspires.ftc.teamcode.subsystems.DumpSubsystem;

import java.util.function.DoubleSupplier;

public class BucketCommand implements Command {
    /**
     * for some reason it must be a DoubleSupplier
     */
    DumpSubsystem bucketSys;
    DoubleSupplier doubleSupplier_servo;

    public BucketCommand(DumpSubsystem bs, DoubleSupplier ds_s){
        this.bucketSys = bs;
        this.doubleSupplier_servo = ds_s;
        addRequirements(bs);
    }
    public BucketCommand(DumpSubsystem bs, double d_s){
        this(bs, ()-> d_s);
    }

    @Override
    public void init() {
        bucketSys.setServoPosition(doubleSupplier_servo.getAsDouble());
    }

    @Override
    public void execute() {}

    @Override
    public boolean isFinished() {
        return true;
    }
}

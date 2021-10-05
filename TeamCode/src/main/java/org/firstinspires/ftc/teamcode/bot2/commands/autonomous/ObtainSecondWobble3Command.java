package org.firstinspires.ftc.teamcode.bot2.commands.autonomous;

import com.technototes.library.command.SequentialCommandGroup;

import org.firstinspires.ftc.teamcode.bot2.commands.StrafeCommand;
import org.firstinspires.ftc.teamcode.bot2.commands.wobble.WobbleCloseCommand;
import org.firstinspires.ftc.teamcode.bot2.commands.wobble.WobbleLowerCommand;
import org.firstinspires.ftc.teamcode.bot2.commands.wobble.WobbleRotateCommand;
import org.firstinspires.ftc.teamcode.bot2.subsystems.DrivebaseSubsystem;
import org.firstinspires.ftc.teamcode.bot2.subsystems.WobbleSubsystem;

public class ObtainSecondWobble3Command extends SequentialCommandGroup {
    public ObtainSecondWobble3Command(DrivebaseSubsystem d, WobbleSubsystem w, AutoState s){
        super(
                new WobbleRotateCommand(w, 1),
                new WobbleLowerCommand(w).alongWith(new StrafeCommand(d, s.correctedSecondWobbleGrabPos())),

                new WobbleCloseCommand(w));
    }
}

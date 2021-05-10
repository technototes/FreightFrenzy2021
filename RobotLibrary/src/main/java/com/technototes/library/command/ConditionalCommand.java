package com.technototes.library.command;

import java.util.function.BooleanSupplier;

/** Simple class for commands that require a certain condition to be true to run
 * @author Alex Stedman
 */
public class ConditionalCommand extends Command {
    private BooleanSupplier supplier;
    private Command trueCommand, falseCommand;

    public ConditionalCommand(BooleanSupplier condition){
        supplier = condition;
        trueCommand = new Command();
        falseCommand = new Command();
    }


    /** Make a conditional command
     *
     * @param condition The condition
     * @param command The command to run when the condition is true.
     */
    public ConditionalCommand(BooleanSupplier condition, Command command) {
        supplier = condition;
        CommandScheduler.getInstance().scheduleWithOther(this, command, condition);
        trueCommand = command;
        falseCommand = new Command();
    }

    /** Make a conditional command
     *
     * @param condition The condition
     * @param trueC The command to run when the condition is true
     * @param falseC The command to run when the condition is false
     */
    public ConditionalCommand(BooleanSupplier condition, Command trueC, Command falseC) {
        supplier = condition;
        CommandScheduler.getInstance().scheduleWithOther(this, trueC, condition);
        CommandScheduler.getInstance().scheduleWithOther(this, falseC, ()->!condition.getAsBoolean());
        trueCommand = trueC;
        falseCommand = falseC;
    }

    @Override
    public boolean isFinished() {
        return trueCommand.justFinished() || falseCommand.justFinished();
    }
}

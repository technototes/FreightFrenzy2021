package com.technototes.library.command;


import com.qualcomm.robotcore.util.ElapsedTime;
import com.technototes.library.subsystem.Subsystem;


import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

/** The root Command class
 * @author Alex Stedman
 */
public class Command implements Runnable {

    protected ElapsedTime commandRuntime;
    protected CommandState commandState;
    protected Set<Subsystem> requirements;

    /** Make a Command
     *
     */
    public Command() {
        commandState = CommandState.RESET;
        commandRuntime = new ElapsedTime();
        requirements = new HashSet<>();
        commandRuntime.reset();
    }

    /** Add requirement subsystems to command
     *
     * @param requirements The subsystems
     * @return this
     */
    public final Command addRequirements(Subsystem... requirements) {
        this.requirements.addAll(Arrays.asList(requirements));
        return this;
    }

    /** Init the command
     *
     */
    public void init() {

    }

    /** Execute the command
     *
     */
    public void execute() {

    }

    /** Return if the command is finished
     *
     * @return Is command finished
     */
    public boolean isFinished() {
        return true;
    }

    /** End the command
     *
     * @param cancel If the command was cancelled or ended naturally
     */
    public void end(boolean cancel) {

    }

    //run a command after
    public SequentialCommandGroup then(Command c){
        return new SequentialCommandGroup(this, c);
    }

    //wait a time
    public SequentialCommandGroup sleep(double sec){
        return then(new WaitCommand(sec));
    }
    public SequentialCommandGroup sleep(DoubleSupplier sup){
        return then(new WaitCommand(sup));
    }



    //await a condition
    public SequentialCommandGroup until(BooleanSupplier condition) {
        return then(new ConditionalCommand(condition));
    }

    //run a command in parallel
    public ParallelCommandGroup with(Command c){
        return new ParallelCommandGroup(this, c);
    }

    /** Run the commmand
     *
     */
    @Override
    public void run() {
        switch (commandState) {
            case RESET:
                commandRuntime.reset();
                commandState = CommandState.INITILAIZING;
                return;
            case INITILAIZING:
                init();
                commandState = CommandState.EXECUTING;
                //THERE IS NO RETURN HERE SO IT FALLS THROUGH TO POST-INITIALIZATION
            case EXECUTING:
                execute();
                if(isFinished()) commandState = CommandState.FINISHED;
                //allow one cycle to run so other dependent commands can schedule
                return;
            case CANCELLED:
            case FINISHED:
                end(commandState == CommandState.CANCELLED);
                commandState = CommandState.RESET;
        }
    }

    /** The command state enum
     *
     */
    public enum CommandState {
        RESET, INITILAIZING, EXECUTING, FINISHED, CANCELLED
    }


    /** Return the command runtime
     *
     * @return The runtime as an {@link ElapsedTime}
     */
    public ElapsedTime getRuntime() {
        return commandRuntime;
    }

    /** Return the command state
     *
     * @return The state as an {@link CommandState}
     */
    public CommandState getState() {
        return commandState;
    }

    /** Return the subsystem requirements for this command
     *
     * @return The {@link Subsystem} requirements
     */
    public Set<Subsystem> getRequirements() {
        return requirements;
    }

    /** Creates a conditional command out of this
     *
     * @param condition The condition to run the command under
     * @return the conditional command
     */
    public ConditionalCommand asConditional(BooleanSupplier condition){
        return new ConditionalCommand(condition, this);
    }


    public final boolean justFinished(){
        return commandState == CommandState.FINISHED;
    }
    public final boolean justStarted() {
        return commandState == CommandState.INITILAIZING;
    }

    public final boolean isRunning(){
        return commandState != CommandState.RESET;
    }

    public final void cancel(){
        if(isRunning()) commandState = CommandState.CANCELLED;
    }

}

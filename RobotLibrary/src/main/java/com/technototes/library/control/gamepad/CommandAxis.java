package com.technototes.library.control.gamepad;

import com.technototes.library.command.Command;

import java.util.function.Consumer;
import java.util.function.DoubleSupplier;
import java.util.function.Function;

/** Class for command axis for the gamepad
 * @author Alex Stedman
 */
public class CommandAxis extends GamepadAxis implements GamepadInput<CommandAxis> {
    /** Make a command axis
     *
     * @param supplier The axis supplier
     */
    public CommandAxis(DoubleSupplier supplier){
        super(supplier);
    }

    /** Make a command axis
     *
     * @param supplier The axis supplier
     * @param threshold The threshold to trigger to make the axis behave as a button
     */
    public CommandAxis(DoubleSupplier supplier, double threshold){
        super(supplier, threshold);
    }

    @Override
    public CommandAxis getInstance() {
        return this;
    }

    @Override
    public CommandAxis setTriggerThreshold(double threshold) {
        super.setTriggerThreshold(threshold);
        schedule(this::e);
        return this;
    }

    public CommandAxis schedule(Function<Double, Command> f){
        return schedule(f.apply(this.getAsDouble()));
    }
    public CommandAxis schedule(Consumer<Double> f){
        return schedule(()->f.accept(this.getAsDouble()));
    }
    public void e(double v){
    }
}

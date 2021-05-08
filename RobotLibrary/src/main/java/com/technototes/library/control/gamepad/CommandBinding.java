package com.technototes.library.control.gamepad;

import com.technototes.control.gamepad.Binding;
import com.technototes.control.gamepad.GamepadButton;

import java.util.function.BooleanSupplier;

/** Command implementation of {@link com.technototes.control.gamepad.Binding}
 * @author Alex Stedman
 */
//TODO make this less jank to use
public class CommandBinding extends CommandButton implements Binding<CommandButton> {
    private CommandButton[] buttons;
    private Type defaultType;

    public CommandBinding(CommandButton... b){
        this(Type.ALL_ACTIVE, b);
    }

    public CommandBinding(Type type, CommandButton... b){
        buttons = b;
        defaultType = type;
        setSupplier(this::get);
    }

    @Override
    public CommandButton[] getSuppliers() {
        return buttons;
    }

    @Override
    public Type getDefaultType() {
        return defaultType;
    }
}

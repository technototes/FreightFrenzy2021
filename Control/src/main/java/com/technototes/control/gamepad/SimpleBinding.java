package com.technototes.control.gamepad;

import java.util.function.BooleanSupplier;

/** Simple implementation of {@link Binding}
 * @author Alex Stedman
 */
public final class SimpleBinding extends GamepadButton implements Binding<GamepadButton> {
    private GamepadButton[] buttons;
    private Type defaultType;
    /** Create binding
     *
     * @param b The buttons for the binding
     */
    public SimpleBinding(GamepadButton... b){
       this(Type.ALL_ACTIVE, b);
    }
    /** Create binding
     *
     * @param b The buttons for the binding
     * @param type The binding type
     */
    public SimpleBinding(Type type, GamepadButton... b){
        defaultType = type;
        buttons = b;
        setSupplier(this::get);
    }

    @Override
    public GamepadButton[] getSuppliers() {
        return buttons;
    }

    @Override
    public Type getDefaultType() {
        return defaultType;
    }

}

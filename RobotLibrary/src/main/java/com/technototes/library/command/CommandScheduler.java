package com.technototes.library.command;

import androidx.annotation.Nullable;

import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.subsystem.Subsystem;
import com.technototes.library.general.Periodic;

import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BooleanSupplier;

public final class CommandScheduler {

    private final Map<Command, BooleanSupplier> commandMap;
    private final Map<Subsystem, Set<Command>> requirementMap;
    private final Map<Subsystem, Command> defaultMap;

    private final Set<Periodic> registered;

    private CommandOpMode opMode;

    public CommandScheduler setOpMode(CommandOpMode c) {
        opMode = c;
        return this;
    }

    public CommandScheduler terminateOpMode() {
        opMode.terminate();
        return this;
    }
    public double getOpModeRuntime() {
        return opMode.getOpModeRuntime();
    }

    private static CommandScheduler instance;

    public static synchronized CommandScheduler getInstance() {
        if (instance == null) {
            instance = new CommandScheduler();
        }
        return instance;
    }

    //be careful with this
    public static synchronized CommandScheduler resetScheduler() {
        instance = null;
        Command.clear();
        return getInstance();
    }

    private CommandScheduler() {
        commandMap = new HashMap<>();
        requirementMap = new HashMap<>();
        defaultMap = new HashMap<>();
        registered = new LinkedHashSet<>();
    }

    public CommandScheduler schedule(Command command) {
        return schedule(command, () -> true);
    }

    public CommandScheduler scheduleOnce(Command command) {
        return schedule(command);
    }
    public CommandScheduler scheduleOnceForState(Command command, CommandOpMode.OpModeState state) {
        return scheduleForState(command, state);
    }
    public CommandScheduler scheduleInit(Command command, BooleanSupplier supplier) {
        return scheduleForState(command, supplier, CommandOpMode.OpModeState.INIT);
    }

    public CommandScheduler scheduleInit(Command command) {
        return scheduleForState(command, () -> true, CommandOpMode.OpModeState.INIT);
    }

    public CommandScheduler scheduleJoystick(Command command, BooleanSupplier supplier) {
        return scheduleForState(command, supplier, CommandOpMode.OpModeState.RUN, CommandOpMode.OpModeState.END);
    }
    public CommandScheduler scheduleJoystick(Command command) {
        return scheduleForState(command, CommandOpMode.OpModeState.RUN, CommandOpMode.OpModeState.END);
    }


    public CommandScheduler scheduleForState(Command command, BooleanSupplier supplier, CommandOpMode.OpModeState... states) {
        return schedule(command.cancelUpon(() -> !opMode.getOpModeState().isState(states)), () -> supplier.getAsBoolean() && opMode.getOpModeState().isState(states));
    }

    public CommandScheduler scheduleForState(Command command, CommandOpMode.OpModeState... states) {
        return schedule(command.cancelUpon(() -> !opMode.getOpModeState().isState(states)), () -> opMode.getOpModeState().isState(states));
    }


    public CommandScheduler scheduleAfterOther(Command dependency, Command other) {
        return schedule(other, dependency::justFinishedNoCancel);
    }

    public CommandScheduler scheduleWithOther(Command dependency, Command other) {
        return schedule(other, dependency::justStarted);
    }

    public CommandScheduler scheduleAfterOther(Command dependency, Command other, BooleanSupplier additionalCondition) {
        return schedule(other, () -> dependency.justFinishedNoCancel() && additionalCondition.getAsBoolean());
    }

    public CommandScheduler scheduleWithOther(Command dependency, Command other, BooleanSupplier additionalCondition) {
        return schedule(other, () -> dependency.justStarted() && additionalCondition.getAsBoolean());
    }

    public CommandScheduler scheduleDefault(Command command, Subsystem subsystem) {
        if (command.getRequirements().contains(subsystem)) {
            defaultMap.put(subsystem, command);
            schedule(command, () -> getCurrent(subsystem) == command);
        } else {
            System.err.println("default commands must require their subsystem: " + command.getClass().toString());
        }
        return this;
    }

    public CommandScheduler register(Periodic p){
        registered.add(p);
        return this;
    }


    @Nullable
    public Command getDefault(Subsystem s) {
        return opMode.getOpModeState() == CommandOpMode.OpModeState.RUN ? defaultMap.get(s) : null;
    }

    @Nullable
    public Command getCurrent(Subsystem s) {
        if(requirementMap.get(s) == null) return null;
        for(Command c : requirementMap.get(s)){
            if(c.isRunning()) return c;
        }
        return getDefault(s);
    }

    public CommandScheduler schedule(Command command, BooleanSupplier supplier) {
        commandMap.put(command, supplier);
        for (Subsystem s : command.getRequirements()) {
            requirementMap.putIfAbsent(s, new LinkedHashSet<>());
            requirementMap.get(s).add(command);
            register(s);
        }
        return this;
    }
    public void run() {
        //better way to do this soontm

        commandMap.forEach((c1, b) -> {
            if (c1.justStarted()) {
                for (Subsystem s : c1.getRequirements()) {
                    for (Command c2 : requirementMap.get(s)) {
                        if (c1 != c2) c2.cancel();
                    }
                }
            }
        });
        commandMap.forEach((c1, b)->{
            if(b.getAsBoolean() || c1.isRunning()) c1.run();
        });
        registered.forEach(Periodic::periodic);
    }
}

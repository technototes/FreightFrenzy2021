package com.technototes.library.command;

import androidx.annotation.Nullable;

import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.subsystem.Subsystem;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BooleanSupplier;

public class CommandScheduler {

    private Map<Command, BooleanSupplier> commandMap;
    private Map<Subsystem, Set<Command>> requirementMap;
    private Map<Subsystem, Command> defaultMap;


    public CommandOpMode opMode;
    public CommandScheduler setOpMode(CommandOpMode c){
        opMode = c;
        return this;
    }

    private static CommandScheduler instance;
    public static synchronized CommandScheduler getInstance(){
        if(instance == null){
            instance = new CommandScheduler();
        }
        return instance;
    }
    //be careful with this
    public static synchronized CommandScheduler resetScheduler(){
        instance = null;
        Command.clear();
        return getInstance();
    }

    private CommandScheduler(){
        commandMap = new HashMap<>();
        requirementMap = new HashMap<>();
        defaultMap = new HashMap<>();
    }

    public CommandScheduler schedule(Command command){
        return schedule(command, ()->true);
    }
    public CommandScheduler scheduleOnce(Command command){
        return schedule(command);
    }
    public CommandScheduler scheduleInit(Command command, BooleanSupplier supplier){
        return scheduleForState(command, supplier, CommandOpMode.OpModeState.INIT);
    }
    public CommandScheduler scheduleInit(Command command){
        return scheduleForState(command, ()->true, CommandOpMode.OpModeState.INIT);
    }
    public CommandScheduler scheduleJoystick(Command command, BooleanSupplier supplier){
        return scheduleForState(command, supplier, CommandOpMode.OpModeState.RUN, CommandOpMode.OpModeState.END);
    }
    public CommandScheduler scheduleForState(Command command, BooleanSupplier supplier, CommandOpMode.OpModeState... states){
        return schedule(command, ()->supplier.getAsBoolean() && opMode.getOpModeState().isState(states));
    }

    public CommandScheduler scheduleAfterOther(Command dependency, Command other){
        return schedule(other, dependency::justFinished);
    }
    public CommandScheduler scheduleWithOther(Command dependency, Command other){
        return schedule(other, dependency::justStarted);
    }
    public CommandScheduler scheduleAfterOther(Command dependency, Command other, BooleanSupplier additionalCondition){
        return schedule(other, ()->dependency.justFinished()&&additionalCondition.getAsBoolean());
    }
    public CommandScheduler scheduleWithOther(Command dependency, Command other, BooleanSupplier additionalCondition){
        return schedule(other, ()->dependency.justStarted() && additionalCondition.getAsBoolean());
    }

    public CommandScheduler scheduleDefault(Command command, Subsystem subsystem){
        if (command.getRequirements().contains(subsystem)) {
            defaultMap.put(subsystem, command);
            schedule(command, ()-> getCurrent(subsystem)==command);
        } else {
            System.err.println("default commands must require their subsystem: "+command.getClass().toString());
        }
        return this;
    }
    @Nullable
    public Command getDefault(Subsystem s){
        return defaultMap.get(s);
    }
    @Nullable
    public Command getCurrent(Subsystem s){
        return requirementMap.getOrDefault(s, new HashSet<>()).stream()
                .filter(Command::isRunning).findAny().orElse(getDefault(s));
    }

    public CommandScheduler schedule(Command command, BooleanSupplier supplier) {
        commandMap.put(command, supplier);
        for(Subsystem s : command.getRequirements()){
            requirementMap.putIfAbsent(s, new HashSet<>());
            requirementMap.get(s).add(command);
        }
        return this;
    }

    public void run() {

            for(Command c1 : commandMap.keySet()){
                if(c1.justStarted()) {
                    for (Subsystem s : c1.getRequirements()) {
                        for(Command c2 : requirementMap.get(s)){
                            if(c1 != c2) c2.cancel();
                        }
                    }
                }
            }
            commandMap.forEach(this::run);
            requirementMap.keySet().forEach(Subsystem::periodic);

    }
    public void run(Command command, BooleanSupplier supplier){
        if(supplier.getAsBoolean() || command.isRunning()) command.run();
    }



}

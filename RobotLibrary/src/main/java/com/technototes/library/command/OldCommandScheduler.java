package com.technototes.library.command;

import com.technototes.library.structure.CommandOpMode;
import com.technototes.library.subsystem.Subsystem;

import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.BooleanSupplier;

public class OldCommandScheduler {

    private Map<Subsystem<?>, Map<Command, BooleanSupplier>> requirementCommands;
    private Map<Subsystem<?>, Command> runningRequirementCommands;
    private Map<Command, BooleanSupplier> commandsWithoutRequirements;

    public CommandOpMode opMode;
    public OldCommandScheduler setOpMode(CommandOpMode c){
        opMode = c;
        return this;
    }

    private static OldCommandScheduler instance;
    public static synchronized OldCommandScheduler getInstance(){
        if(instance == null){
            instance = new OldCommandScheduler();
        }
        return instance;
    }
    //be careful with this
    public static synchronized OldCommandScheduler resetScheduler(){
        instance = null;
        return getInstance();
    }

    private OldCommandScheduler(){
        commandsWithoutRequirements = new LinkedHashMap<>();
        requirementCommands = new LinkedHashMap<>();
        runningRequirementCommands = new LinkedHashMap<>();
    }

    public OldCommandScheduler schedule(Command command){
        return schedule(command, ()->true);
    }
    public OldCommandScheduler scheduleInit(Command command, BooleanSupplier supplier){
        return scheduleForState(command, supplier, CommandOpMode.OpModeState.INIT);
    }
    public OldCommandScheduler scheduleInit(Command command){
        return scheduleForState(command, ()->true, CommandOpMode.OpModeState.INIT);
    }
    public OldCommandScheduler scheduleJoystick(Command command, BooleanSupplier supplier){
        return scheduleForState(command, supplier, CommandOpMode.OpModeState.RUN, CommandOpMode.OpModeState.END);
    }
    public OldCommandScheduler scheduleForState(Command command, BooleanSupplier supplier, CommandOpMode.OpModeState... states){
        return schedule(command, ()->supplier.getAsBoolean() && opMode.getOpModeState().isState(states));
    }

    public OldCommandScheduler scheduleAfterOther(Command dependency, Command other){
        return schedule(other, dependency::justFinished);
    }
    public OldCommandScheduler scheduleWithOther(Command dependency, Command other){
        return schedule(other, dependency::justStarted);
    }
    public OldCommandScheduler scheduleAfterOther(Command dependency, Command other, BooleanSupplier additionalCondition){
        return schedule(other, ()->dependency.justFinished()&&additionalCondition.getAsBoolean());
    }
    public OldCommandScheduler scheduleWithOther(Command dependency, Command other, BooleanSupplier additionalCondition){
        return schedule(other, ()->dependency.justStarted() && additionalCondition.getAsBoolean());
    }


    public OldCommandScheduler schedule(Command command, BooleanSupplier supplier){
        if(command.getRequirements().isEmpty()){
            commandsWithoutRequirements.put(command, supplier);
        }else{
            command.getRequirements().forEach((subsystem -> {
                if(!requirementCommands.containsKey(subsystem)){
                    requirementCommands.put(subsystem, new LinkedHashMap<>());
                }
                if(subsystem.getDefaultCommand() == command){
                    runningRequirementCommands.put(subsystem, command);
                }
                requirementCommands.get(subsystem).put(command, supplier);
            }));
        }
        return this;
    }
    private Set<Command> cancelledCommands;

    public void run() {
            /*
            Map<Subsystem, Map<Command, BooleanSupplier>> commandMap;

            changing command state ops
                cancel command if command is running and command with same requirement just started

                run commands if condition is true or command state is not reset

            */



            cancelledCommands = new LinkedHashSet<>();
            requirementCommands.forEach(((subsystem, commandMap) -> {
                Command c = runningRequirementCommands.get(subsystem);
                commandMap.entrySet().stream().filter((entry) -> {
                            return entry.getKey().getState() == Command.CommandState.RESET
                                    && entry.getValue().getAsBoolean()
                                    && entry.getKey() != c;
                        }
                ).findFirst().ifPresent(m -> {
                    if (c != null) {
                        c.cancel();
                        cancelledCommands.add(c);
                    }
                    runningRequirementCommands.put(subsystem, m.getKey());
                });
            }));

        runningRequirementCommands.forEach(((subsystem, command) -> run(command, requirementCommands.get(subsystem).get(command))));
        commandsWithoutRequirements.forEach(this::run);
        requirementCommands.keySet().forEach(Subsystem::periodic);
        cancelledCommands.forEach(Command::run);
    }
    public void run(Command command, BooleanSupplier supplier){
        if(supplier.getAsBoolean() || command.isRunning()){
            //System.out.println("run(): " + command.toString() + ", " + command.getClass().toString() + ", " + command.getRuntime  ().toString());
            command.run();
        }
    }

}

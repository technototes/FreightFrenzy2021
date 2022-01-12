package org.firstinspires.ftc.teamcode;

import com.acmerobotics.dashboard.config.Config;

public class RobotState {

    private static AllianceHubStrategy allianceHubStrategy;
    private static SharedHubStrategy sharedHubStrategy;

    public static AllianceHubStrategy getAllianceStrategy() {
        return allianceHubStrategy;
    }

    public static SharedHubStrategy getSharedStrategy() {
        return sharedHubStrategy;
    }

    public static void strategy1(){
        setStrategy(AllianceHubStrategy.HIGH, SharedHubStrategy.OWN);
    }
    public static void strategy2(){
        setStrategy(AllianceHubStrategy.MID, SharedHubStrategy.STEAL);
    }

    public static void setStrategy(AllianceHubStrategy allianceHubStrategy, SharedHubStrategy sharedHubStrategy) {
        RobotState.allianceHubStrategy = allianceHubStrategy;
        RobotState.sharedHubStrategy = sharedHubStrategy;
    }

    public enum AllianceHubStrategy {
        HIGH, MID
    }

    public enum SharedHubStrategy {
        OWN, STEAL
    }
    private static boolean depositTarget = true;
    public static void startDeposit(){
        depositTarget = true;
    }
    public static void stopDeposit(){
        depositTarget = true;
    }
    public static boolean isDepositing(){
        return depositTarget;
    }

    public enum Freight {
        CUBE, BALL, DUCK, NONE
    }

    private static Freight freight = Freight.NONE;

    public static void setFreight(Freight f){
        freight = f;
    }

    public static Freight getFreight(){
        return freight;
    }

    public static boolean hasFreight(){
        return freight!=Freight.NONE;
    }


}

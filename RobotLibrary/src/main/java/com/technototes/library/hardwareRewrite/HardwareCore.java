package com.technototes.library.hardwareRewrite;

import com.qualcomm.robotcore.hardware.HardwareDevice;
import com.qualcomm.robotcore.hardware.HardwareMap;

public class HardwareCore {
    private static HardwareMap hardwareMap = null;
    public static HardwareMap getMap(){
        return hardwareMap;
    }
    public static void initMap(HardwareMap h){
        if(hardwareMap != null) return;
        hardwareMap = h;
    }


    @SuppressWarnings("unchecked cast")
    public static <T> T create(String s){
        if(hardwareMap == null) return null;
        return hardwareMap.get((Class<T>) Object.class, s);
    }
    //TODO
    public static <T> T create(int p){
        if(hardwareMap == null) return null;
        return null;
    }
}

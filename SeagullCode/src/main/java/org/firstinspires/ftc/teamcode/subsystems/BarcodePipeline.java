package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.DuckOrDepot;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.function.Supplier;
import com.acmerobotics.dashboard.config.Config;

import static org.firstinspires.ftc.teamcode.subsystems.BarcodePipeline.BarcodeConstants.*;

import com.technototes.library.logger.LogConfig;
import com.technototes.library.logger.Loggable;
import com.technototes.library.logger.Log;
import com.technototes.library.util.Alliance;

public class BarcodePipeline extends OpenCvPipeline implements Supplier<Integer>, Loggable{

    @Config
    public static class BarcodeConstants {
//        public static boolean DISPLAY = true;
//        public static Scalar DISPLAY_COLOR = new Scalar(200, 0, 0);
//        public static Scalar LOWER_LIMIT = new Scalar(100.0, 0.0, 0.0, 0.0);
//        public static Scalar UPPER_LIMIT = new Scalar(255.0, 80.0, 80.0, 255.0);
//        public static int BORDER_LEFT_X = 0;   //amount of pixels from the left side of the cam to skip
//        public static int BORDER_RIGHT_X = 0;   //amount of pixels from the right of the cam to skip
//        public static int BORDER_TOP_Y = 0;   //amount of pixels from the top of the cam to skip
//        public static int BORDER_BOTTOM_Y = 0;   //amount of pixels from the bottom of the cam to skip
//
//        //y is fot the outpiut
//        public static Point LEFT = new Point(50, 120);
//        public static Point CENTER = new Point(160, 120);
//        public static Point RIGHT = new Point(270, 120);
//
//        public static int VARIANCE = 50;
//        public static double MIN_AREA = 500;

        public enum ArmPosition{
            HIGH,
            MEDIUM,
            LOW,
        }

        public static ArmPosition position;

        /**
         * the boundaries of each region
         * the up and down might be flipped
         */

        static final Scalar BLUE = new Scalar(0, 0, 255);
        static final Scalar RED = new Scalar(255, 0, 0);
        static final Scalar YELLOW = new Scalar(255, 255, 0);
        public static class CameraConfig {
            final Alliance alliance;
            final DuckOrDepot side;
            final Rect region1;
            final Rect region2;
            final Rect region3;
            CameraConfig(Alliance alliance, DuckOrDepot side,
                         Rect topRect, Rect midRect, Rect botRect) {
                this.alliance = alliance;
                this.side = side;
                this.region1 = topRect;
                this.region2 = midRect;
                this.region3 = botRect;
            }
        }
        //for each location, want to know which rectangle corresponds to which spot and want to know if theres a spot
        //we do not see
        //left rect is 0, middle + right is 1, 2 is not visible


        private static final CameraConfig cameraConfigs[] = {
                // blue depot-
                // right rect = top level
                // center rect = middle level
                // left rect = nothing, cannot see barcode for bottom level
                new CameraConfig(Alliance.BLUE, DuckOrDepot.DEPOT,
                        new Rect(200, 0, 100, 200),
                        new Rect(50, 0, 100, 200),
                        null),
                // blue duck
                // cannot see barcode for top level
                // center rect = middle level
                // right rect = bottom level
                new CameraConfig(Alliance.BLUE, DuckOrDepot.DUCK,
                        null,
                        new Rect(50,   0, 100, 200),
                        new Rect(200, 0, 100, 200)),
                // red depot
                // center rect = top level
                // right rect = middle level
                // left rect = nothing, cannot see barcode
                new CameraConfig(Alliance.RED,  DuckOrDepot.DEPOT,
                        new Rect(50, 0, 100, 200),
                        new Rect(200, 0, 100, 200),
                  null),
                // red duck
                // right rect = top level
                // center rect = middle level
                // lowest level = cannot see
                new CameraConfig(Alliance.RED,  DuckOrDepot.DUCK,
                        new Rect(200, 0, 100, 200),
                        new Rect(50, 0, 100, 200),
                        null)
        };

//        public final static Point REGION_1_TOPLEFT_ANCHOR_POINT = new Point(REGION_1_LEFT, REGION_1_UP);
//        public final static Point REGION_2_TOPLEFT_ANCHOR_POINT = new Point(REGION_2_LEFT, REGION_2_UP);
//        public final static Point REGION_3_TOPLEFT_ANCHOR_POINT = new Point(REGION_3_LEFT, REGION_3_UP);
//        public final static Point REGION_4_TOPLEFT_ANCHOR_POINT = new Point(REGION_4_LEFT, REGION_4_UP);
//        public final static Point REGION_5_TOPLEFT_ANCHOR_POINT = new Point(REGION_5_LEFT, REGION_5_UP);
    }

    public Exception debug;

    private CameraConfig currentConfig = null;
    public Mat region_1_Cr, region_2_Cr, region_3_Cr;
    public Mat customColorSpace = new Mat();
    public Mat Cr = new Mat();

    @LogConfig.Run(duringRun = false, duringInit = true)
    @Log.Boolean (name="top")
    public volatile boolean topDetected = false;
    @LogConfig.Run(duringRun = false, duringInit = true)
    @Log.Boolean (name="mid")
    public volatile boolean middleDetected = true;
    @LogConfig.Run(duringRun = false, duringInit = true)
    @Log.Boolean (name="bot")
    public volatile boolean bottomDetected = false;

    @LogConfig.Run(duringInit = true, duringRun = false)
    @Log.Number (name="red")
    public volatile int redThreshhold = 0;

    private final Mat mat = new Mat();
    private final Mat processed = new Mat();

    private Rect maxRect = new Rect();

    public Telemetry telemetry;

    public BarcodePipeline(Telemetry t){
        telemetry = t;
    }
    public BarcodePipeline(){
    }

    public void inputToCr(Mat input){
        Imgproc.cvtColor(input, customColorSpace, Imgproc.COLOR_RGB2HSV);
        Mat red1 = new Mat();
        Scalar bottom = new Scalar(0, 70, 50);
        Scalar bottomEdge = new Scalar(10, 255, 255);
        Core.inRange(customColorSpace, bottom, bottomEdge, red1);
        Mat red2 = new Mat();
        Scalar top = new Scalar(170, 70, 50);
        Scalar topEdge = new Scalar(180, 255, 255);
        Core.inRange(customColorSpace, top, topEdge, red2);
        Core.bitwise_or(red1, red2, Cr);
        // Make everything that's the color we're looking for turn white
        Core.bitwise_or(Cr, input, input);
        /*
        // flip the pixels that we're seeing as "red" to yellow!
        // also: Draw pixels by drawing a 1x1 rectangle is *masterful* code!
        // TODO: This should use bitwise and/bitwise or to do this really...
        Rect r = new Rect(new Point(0,0), new Point(1,1));
        for (int i = 0; i < Cr.width(); i++) {
            for (int j = 0; j < Cr.height(); j++) {
                if (Cr.get(j, i)[0] > 0) {
                    r.x = i;
                    r.y = j;
                    r.width = 1;
                    r.height = 1;
                    Imgproc.rectangle(input, r, YELLOW);
                }
            }
        }
        */
    }

    public void init(Mat firstFrame){

        inputToCr(firstFrame);
        region_1_Cr = currentConfig.region1 == null ? null  : Cr.submat(currentConfig.region1);
        region_2_Cr = currentConfig.region2 == null ? null : Cr.submat(currentConfig.region2);
        region_3_Cr = currentConfig.region3 == null ? null : Cr.submat(currentConfig.region3);

    }
    public void setStartingPosition(Alliance alliance, DuckOrDepot side){
        for (CameraConfig config : BarcodeConstants.cameraConfigs){
            if (alliance.equals(config.alliance) && side.equals(config.side)){
                currentConfig = config;
                break;
            }
        }

    }

    /**
     *
     *ryans doing this up to this point; there are some member variables he
     * made as well
     */
    @Override
    public Mat processFrame(Mat input)
    {
        inputToCr(input);

        // First, get the average red level of the region (or -1 if no region)
        int red0 = region_1_Cr == null ? -1 : (int) Core.mean(region_1_Cr).val[0];
        int red1 = region_2_Cr == null ? -1 : (int) Core.mean(region_2_Cr).val[0];
        int red2 = region_3_Cr == null ? -1 : (int) Core.mean(region_3_Cr).val[0];

        // Figure out how much is the most amount of red
        int max_red = Math.max(Math.max(red0, red1), red2);
        // If we have more than 20 as an average, we see a brick: set the 'Detected' bits
        if (max_red > 20) {
            topDetected = max_red == red0;
            middleDetected = max_red == red1;
            bottomDetected = max_red == red2;
        } else {
            // We didn't see enough red, so pick a region to defaul to
            topDetected = currentConfig.region1 == null;
            middleDetected = currentConfig.region2 == null;
            bottomDetected = currentConfig.region3 == null;
        }
        // If we didn't detect any block at all, default to the top just in case
        if (!topDetected && !middleDetected && !bottomDetected) {
            topDetected = true;
        }
        // Draw rectangles to show what we're seeing
        if (currentConfig.region1 != null){
            Imgproc.rectangle(input, currentConfig.region1, (topDetected ? RED : BLUE), 2);
        } else {
            Imgproc.rectangle(input, new Rect(50, 220, 15, 15), (topDetected ? RED : YELLOW), 2);
        }
        if (currentConfig.region2 != null){
            Imgproc.rectangle(input, currentConfig.region2, (middleDetected ? RED : BLUE), 2);
        } else {
            Imgproc.rectangle(input, new Rect(150, 220, 15, 15), (middleDetected ? RED : YELLOW), 2);
        }
        if (currentConfig.region3 != null){
            Imgproc.rectangle(input, currentConfig.region3, (bottomDetected ? RED : BLUE), 2);
        } else {
            Imgproc.rectangle(input, new Rect(250, 220, 15, 15), (bottomDetected ? RED : YELLOW), 2);
        }
        return input;
    }
    public int getRectHeight(){return maxRect.height;}
    public int getRectWidth(){ return maxRect.width; }
    public int getRectX(){ return maxRect.x; }
    public int getRectY(){ return maxRect.y; }
    public double getRectMidpointX(){ return getRectX() + (getRectWidth()/2.0); }
    public double getRectMidpointY(){ return getRectY() + (getRectHeight()/2.0); }
    public Point getRectMidpointXY(){ return new Point(getRectMidpointX(), getRectMidpointY());}
    public double getRectArea(){ return maxRect.area(); }

    /**
     * just a placeholder for new
     */
    @Override
    public Integer get() {
        return null;
    }

//    private int last = -1;
//    @Override
//    public Integer get() {
//        if(getRectArea()>MIN_AREA) {
//            Point p = getRectMidpointXY();
//            if (Math.abs(p.x - LEFT.x) < VARIANCE && Math.abs(p.y - LEFT.y) < VARIANCE) last = 0;
//            if (Math.abs(p.x - CENTER.x) < VARIANCE && Math.abs(p.y - CENTER.y) < VARIANCE) last = 1;
//            if (Math.abs(p.x - RIGHT.x) < VARIANCE && Math.abs(p.y - RIGHT.y) < VARIANCE) last = 2;
//        }
//        return last;
//    }
//
//    public boolean none(){
//        return get() == -1;
//    }
    public boolean top(){
        return topDetected;
    }
    public boolean middle(){
        return middleDetected;
    }
    public boolean bottom(){
        return bottomDetected;
    }

}

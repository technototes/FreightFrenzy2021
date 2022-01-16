package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.commands.autonomous.AutonomousConstants;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.MatOfPoint;
import org.opencv.core.MatOfPoint2f;
import org.opencv.core.Point;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.core.Size;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Supplier;
import com.acmerobotics.dashboard.config.Config;

import static org.firstinspires.ftc.teamcode.subsystems.BarcodePipeline.BarcodeConstants.*;

import com.technototes.library.logger.LogConfig;
import com.technototes.library.logger.Loggable;
import com.technototes.library.logger.Log;

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
        public static int REGION_1_LEFT = 0;
        public static int REGION_1_RIGHT = 100;
        public static int REGION_1_DOWN = 200;
        public static int REGION_1_UP = 0;

        public static int REGION_2_LEFT = 100;
        public static int REGION_2_RIGHT = 200;
        public static int REGION_2_DOWN = 200;
        public static int REGION_2_UP = 0;

        public static int REGION_3_LEFT = 200;
        public static int REGION_3_RIGHT = 300;
        public static int REGION_3_DOWN = 200;
        public static int REGION_3_UP = 0;

//        public final static Point REGION_1_TOPLEFT_ANCHOR_POINT = new Point(REGION_1_LEFT, REGION_1_UP);
//        public final static Point REGION_2_TOPLEFT_ANCHOR_POINT = new Point(REGION_2_LEFT, REGION_2_UP);
//        public final static Point REGION_3_TOPLEFT_ANCHOR_POINT = new Point(REGION_3_LEFT, REGION_3_UP);
//        public final static Point REGION_4_TOPLEFT_ANCHOR_POINT = new Point(REGION_4_LEFT, REGION_4_UP);
//        public final static Point REGION_5_TOPLEFT_ANCHOR_POINT = new Point(REGION_5_LEFT, REGION_5_UP);
    }

    public Exception debug;

    public Point region_1_pointA = new Point(REGION_1_LEFT, REGION_1_UP);
    public Point region_1_pointB = new Point(REGION_1_RIGHT, REGION_1_DOWN);
    public Point region_2_pointA = new Point(REGION_2_LEFT, REGION_2_UP);
    public Point region_2_pointB = new Point(REGION_2_RIGHT, REGION_2_DOWN);
    public Point region_3_pointA = new Point(REGION_3_LEFT, REGION_3_UP);
    public Point region_3_pointB = new Point(REGION_3_RIGHT, REGION_3_DOWN);




    public Mat region_1_Cr, region_2_Cr, region_3_Cr;
    public Mat YCrCb = new Mat();
    public Mat Cr = new Mat();

    @LogConfig.Run(duringRun = false, duringInit = true)
    @Log.Boolean (name="sq1")
    public volatile boolean on_square_1 = false;
    @LogConfig.Run(duringRun = false, duringInit = true)
    @Log.Boolean (name="sq2")
    public volatile boolean on_square_2 = true;
    @LogConfig.Run(duringRun = false, duringInit = true)
    @Log.Boolean (name="sq3")
    public volatile boolean on_square_3 = false;

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
        Imgproc.cvtColor(input, YCrCb, Imgproc.COLOR_RGB2YCrCb);
        Core.extractChannel(YCrCb, Cr, 1);
    }

    public void init(Mat firstFrame){

        inputToCr(firstFrame);

        region_1_Cr = Cr.submat(new Rect(region_1_pointA, region_1_pointB));
        region_2_Cr = Cr.submat(new Rect(region_2_pointA, region_2_pointB));
        region_3_Cr = Cr.submat(new Rect(region_3_pointA, region_3_pointB));

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

        int [] red_avg = new int [3];



        red_avg[0] = (int) Core.mean(region_1_Cr).val[0];
        red_avg[1] = (int) Core.mean(region_2_Cr).val[0];
        red_avg[2] = (int) Core.mean(region_3_Cr).val[0];

        int max = 0;
        if (red_avg[0] > red_avg[1]){
            max = 0;
        }else{
            max = 1;
        }
        if (red_avg[max] < red_avg[2]){
            max = 2;
        }
        Imgproc.rectangle(input, region_1_pointA, region_1_pointB, ((max == 0) ? RED : BLUE), 2);
        Imgproc.rectangle(input, region_2_pointA, region_2_pointB, ((max == 1) ? RED : BLUE), 2);
        Imgproc.rectangle(input, region_3_pointA, region_3_pointB, ((max == 2) ? RED : BLUE), 2);
//        System.out.printf("ASDF Read Max Value: %d\n", max);
        on_square_1 = max == 0;
        on_square_2 = max == 1;
        on_square_3 = max == 2;



        /*
        Mat output = input.clone();
        try
        {

        } catch (Exception e) {
            debug = e;
            boolean error = true;
        }
        if(telemetry != null) {
            telemetry.addLine(get().toString());
            telemetry.update();
        }
*/
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
    public boolean zero(){
        return on_square_1;
    }
    public boolean one(){
        return on_square_2;
    }
    public boolean two(){
        return on_square_3;
    }

}

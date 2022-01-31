package org.firstinspires.ftc.teamcode.subsystems;

import org.firstinspires.ftc.robotcore.external.Telemetry;
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
public class BarcodePipeline extends OpenCvPipeline implements Supplier<Integer> {
    @Config
    public static class BarcodeConstants {
        public static boolean DISPLAY = true;
        public static Scalar DISPLAY_COLOR = new Scalar(200, 0, 0);
        public static Scalar LOWER_LIMIT = new Scalar(80.0, 0.0, 0.0, 0.0);
        public static Scalar UPPER_LIMIT = new Scalar(255.0, 80.0, 80.0, 255.0);
        public static int BORDER_LEFT_X = 0;   //amount of pixels from the left side of the cam to skip
        public static int BORDER_RIGHT_X = 0;   //amount of pixels from the right of the cam to skip
        public static int BORDER_TOP_Y = 90;   //amount of pixels from the top of the cam to skip
        public static int BORDER_BOTTOM_Y = 50;   //amount of pixels from the bottom of the cam to skip

        //y is fot the outpiut
        public static Point LEFT = new Point(50,  140);
        public static Point CENTER = new Point(160, 140);
        public static Point RIGHT = new Point(270, 140);

        public static int VARIANCE = 50;
        public static double MIN_AREA = 1000;


    }

    public Exception debug;


    private int loopcounter = 0;
    private int ploopcounter = 0;

    private final Mat mat = new Mat();
    private final Mat processed = new Mat();

    private Rect maxRect = new Rect();

    private double maxArea = 0;
    private boolean first = false;

    public Telemetry telemetry;

    public BarcodePipeline(Telemetry t){
        telemetry = t;
    }
    public BarcodePipeline(){
    }

    @Override
    public Mat processFrame(Mat input)
    {
        try
        {
            // Process Image
            Imgproc.cvtColor(input, mat, Imgproc.COLOR_RGB2RGBA);
            Core.inRange(mat, LOWER_LIMIT, UPPER_LIMIT, processed);
            // Core.bitwise_and(input, input, output, processed);

            // Remove Noise
            Imgproc.morphologyEx(processed, processed, Imgproc.MORPH_OPEN, new Mat());
            Imgproc.morphologyEx(processed, processed, Imgproc.MORPH_CLOSE, new Mat());
            // GaussianBlur
            Imgproc.GaussianBlur(processed, processed, new Size(5.0, 15.0), 0.00);
            // Find Contours
            List<MatOfPoint> contours = new ArrayList<>();
            Imgproc.findContours(processed, contours, new Mat(), Imgproc.RETR_LIST, Imgproc.CHAIN_APPROX_SIMPLE);

            // Draw Contours
            if(DISPLAY) Imgproc.drawContours(input, contours, -1, DISPLAY_COLOR);

            // Loop Through Contours
            for (MatOfPoint contour : contours)
            {
                Point[] contourArray = contour.toArray();

                // Bound Rectangle if Contour is Large Enough
                if (contourArray.length >= 15)
                {
                    MatOfPoint2f areaPoints = new MatOfPoint2f(contourArray);
                    Rect rect = Imgproc.boundingRect(areaPoints);

                    // if rectangle is larger than previous cycle or if rectangle is not larger than previous 6 cycles > then replace
                    if (rect.area() > maxArea
                            && rect.x > BORDER_LEFT_X && rect.x + rect.width < input.width() - BORDER_RIGHT_X
                            && rect.y > BORDER_TOP_Y && rect.y + rect.height < input.height() - BORDER_BOTTOM_Y
                            || loopcounter - ploopcounter > 6)
                    {
                        maxArea = rect.area();
                        maxRect = rect;
                        ploopcounter++;
                        loopcounter = ploopcounter;
                        first = true;
                    }
                    areaPoints.release();
                }
                contour.release();
            }
            mat.release();
            processed.release();
            if (contours.isEmpty())
            {
                maxRect = new Rect();
            }
            if (first && maxRect.area() > MIN_AREA)
            {
                if(DISPLAY) Imgproc.rectangle(input, maxRect, DISPLAY_COLOR, 2);
            }
            // Draw Borders
            if(DISPLAY) {
                Imgproc.rectangle(input, new Rect(BORDER_LEFT_X, BORDER_TOP_Y, input.width() - BORDER_RIGHT_X - BORDER_LEFT_X, input.height() - BORDER_BOTTOM_Y - BORDER_TOP_Y), DISPLAY_COLOR, 2);
                Imgproc.circle(input, LEFT, VARIANCE, DISPLAY_COLOR);
                Imgproc.circle(input, CENTER, VARIANCE, DISPLAY_COLOR);
                Imgproc.circle(input, RIGHT, VARIANCE, DISPLAY_COLOR);

                // Display Data

                Imgproc.putText(input, "Area: " + getRectArea() + " Midpoint: " + getRectMidpointXY().x + " , " + getRectMidpointXY().y+ " Selection: "+get(), new Point(20, input.height() - 20), Imgproc.FONT_HERSHEY_PLAIN, 0.6, DISPLAY_COLOR, 1);
            }
            loopcounter++;
        } catch (Exception e) {
            debug = e;
            boolean error = true;
        }
        if(telemetry != null){
            telemetry.addLine(get().toString());
            telemetry.update();
        }

        try {
            Thread.sleep(100);
        } catch (InterruptedException ignored) {
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

    private int last = -1;
    @Override
    public synchronized Integer get() {
        if(getRectArea()>MIN_AREA) {
            Point p = getRectMidpointXY();
            if (Math.abs(p.x - LEFT.x) < VARIANCE && Math.abs(p.y - LEFT.y) < VARIANCE) last = 0;
            if (Math.abs(p.x - CENTER.x) < VARIANCE && Math.abs(p.y - CENTER.y) < VARIANCE) last = 1;
            if (Math.abs(p.x - RIGHT.x) < VARIANCE && Math.abs(p.y - RIGHT.y) < VARIANCE) last = 2;
        }
        return last;
    }

    public boolean none(){
        return get() == -1;
    }
    public boolean zero(){
        return get() == 0;
    }
    public boolean one(){
        return get() == 1;
    }
    public boolean two(){
        return get() == 2;
    }

    public boolean twoOrDefault(){
        return none() || two();
    }


}

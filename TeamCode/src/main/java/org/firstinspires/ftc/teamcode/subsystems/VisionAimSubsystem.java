package org.firstinspires.ftc.teamcode.subsystems;

import com.technototes.logger.Stated;

import org.firstinspires.ftc.teamcode.commands.autonomous.AutoState;
import org.opencv.core.Core;
import org.opencv.core.Mat;
import org.opencv.core.Rect;
import org.opencv.core.Scalar;
import org.opencv.imgproc.Imgproc;
import org.openftc.easyopencv.OpenCvCamera;
import org.openftc.easyopencv.OpenCvCameraRotation;
import org.openftc.easyopencv.OpenCvPipeline;

import java.util.ArrayList;
import java.util.List;

public class VisionAimSubsystem extends OpenCvPipeline implements Stated<Integer> {

    private double upRectHeight = 0.5;
    private double upRectWidth = 0.02;
    private Mat matYCrCb = new Mat();

    public List<Integer> goal;
    public int mean;

    private OpenCvCamera webcam;

    public VisionAimSubsystem(OpenCvCamera w) {
        webcam = w;
        webcam.setPipeline(this);
        webcam.openCameraDeviceAsync(() -> webcam.startStreaming(320,240, OpenCvCameraRotation.UPRIGHT));
    }

    private Mat m1, m2, m3;
    private Rect r;
    private Scalar mean1, mean2, mean3;
    public double avg;
    @Override
    public Mat processFrame(Mat input) {
        /**
         *input which is in RGB is the frame the camera gives
         *We convert the input frame to the color space matYCrCb
         *Then we store this converted color space in the mat matYCrCb
         *For all the color spaces go to
         *https://docs.opencv.org/3.4/d8/d01/group__imgproc__color__conversions.html
         */
        Imgproc.cvtColor(input, matYCrCb, Imgproc.COLOR_RGB2RGBA);

        //The points needed for the rectangles are calculated here
        goal = new ArrayList<>();
        for(double d = 0; d<1; d+= upRectWidth){
            r = new Rect((int) (matYCrCb.width() * d), 0, (int) (matYCrCb.width()* upRectWidth), (int) (matYCrCb.height()* upRectHeight));
            m1 = new Mat();
            m2 = new Mat();
            m3 = new Mat();

            Core.extractChannel(matYCrCb.submat(r), m1, 0);
            Core.extractChannel(matYCrCb.submat(r), m2, 1);
            Core.extractChannel(matYCrCb.submat(r), m3, 2);

            mean1 = Core.mean(m1);
            mean2 = Core.mean(m2);
            mean3 = Core.mean(m3);

            //avg = mean1.val[0]*2-mean2.val[0]-mean3.val[0]+100;
            avg = mean1.val[0]-(mean1.val[0]+mean2.val[0]+mean3.val[0])/3;
            //mats.add(m);

            if(avg>15) {
                goal.add((int) Math.round(d/ upRectWidth));
                drawRectOnToMat(input, r, new Scalar(0, 255, 0));

            }else{
                drawRectOnToMat(input, r, new Scalar(255, 0, 0));
            }


        }

        mean = 0;
        for(int i : goal){
            mean+=i;
        }
        mean = (goal.size() != 0 ? mean/goal.size() : 0);
        m1.release();
        m2.release();
        m3.release();


        //return the mat to be shown onto the screen
        return input;
    }

    /**
     * Draw the rectangle onto the desired mat
     *
     * @param mat   The mat that the rectangle should be drawn on
     * @param rect  The rectangle
     * @param color The color the rectangle will be
     */
    private void drawRectOnToMat(Mat mat, Rect rect, Scalar color) {
        Imgproc.rectangle(mat, rect, color, 1);
    }


    public double getAvg(){
        return mean;
    }

    @Override
    public Integer getState() {
        return (int) avg;
    }
}
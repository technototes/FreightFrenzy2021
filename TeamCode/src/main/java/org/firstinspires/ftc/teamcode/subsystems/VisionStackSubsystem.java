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

public class VisionStackSubsystem extends OpenCvPipeline implements Stated<Integer> {

    //We declare the mats ontop so we can reuse them later to avoid memory leaks
    private Mat matYCrCb = new Mat();
    private Mat matCbBottom = new Mat();
    private Mat matCbTop = new Mat();
    private Mat topBlock = new Mat();
    private Mat bottomBlock = new Mat();

    //Where the average CB value of the rectangles are stored
    private double topAverage;
    private double bottomAverage;

    //The max difference allowed inside the rectangles
    private int minThreshold = 10;
    private int maxThreshold = 50;


    //The position related to the screen
    private double topRectWidthPercentage = 0;
    private double topRectHeightPercentage = 0.89;
    private double bottomRectWidthPercentage = 0;
    private double bottomRectHeightPercentage = 0.96;

    //The width and height of the rectangles in terms of pixels
    private int rectangleWidth = 17;
    private int rectangleHeight = 10;

    private OpenCvCamera webcam;

    public VisionStackSubsystem(OpenCvCamera w) {
        webcam = w;
        webcam.setPipeline(this);

        // See https://github.com/OpenFTC/EasyOpenCV/issues/12
        // Hack to give Android some extra time to make the webcam accessible, so it's less likely
        // the robot crashes on startup
        final boolean useWebcamWorkaround = true;
        if (useWebcamWorkaround) {
            new Thread(() -> {
                try {
                    webcam.openCameraDevice();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                webcam.openCameraDeviceAsync(() -> webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT));
            }).start();
        } else {
            webcam.openCameraDeviceAsync(() -> webcam.startStreaming(320, 240, OpenCvCameraRotation.UPRIGHT));
        }
    }

    Rect topRect, bottomRect;
    Scalar topMean, bottomMean;


    @Override
    public Mat processFrame(Mat input) {
        /**
         *input which is in RGB is the frame the camera gives
         *We convert the input frame to the color space matYCrCb
         *Then we store this converted color space in the mat matYCrCb
         *For all the color spaces go to
         *https://docs.opencv.org/3.4/d8/d01/group__imgproc__color__conversions.html
         */
        Imgproc.cvtColor(input, matYCrCb, Imgproc.COLOR_RGB2HSV);

        //The points needed for the rectangles are calculated here
        topRect = new Rect(
                (int) (matYCrCb.width() * topRectWidthPercentage),
                (int) (matYCrCb.height() * topRectHeightPercentage),
                rectangleWidth,
                rectangleHeight
        );

        bottomRect = new Rect(
                (int) (matYCrCb.width() * bottomRectWidthPercentage),
                (int) (matYCrCb.height() * bottomRectHeightPercentage),
                rectangleWidth,
                rectangleHeight
        );

        //The rectangle is drawn into the mat
        drawRectOnToMat(input, topRect, new Scalar(255, 0, 0));
        drawRectOnToMat(input, bottomRect, new Scalar(0, 255, 0));

        //We crop the image so it is only everything inside the rectangles and find the cb value inside of them
        topBlock = matYCrCb.submat(topRect);
        bottomBlock = matYCrCb.submat(bottomRect);
        Core.extractChannel(bottomBlock, matCbBottom, 0);
        Core.extractChannel(topBlock, matCbTop, 0);

        //We take the averaoge
        bottomMean = Core.mean(matCbBottom);
        topMean = Core.mean(matCbTop);

        bottomAverage = bottomMean.val[0];
        topAverage = topMean.val[0];

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

    public int getStackSize(){
        return (minThreshold < getTopAverage() && maxThreshold > getTopAverage()) ?
                4 : (minThreshold < getBottomAverage() && maxThreshold > getBottomAverage()) ? 1 : 0;
    }

    public AutoState.StackSize getStackSizeEnum(){
        return getStackSize() == 4 ? AutoState.StackSize.FOUR : getStackSize() == 1 ? AutoState.StackSize.ONE : AutoState.StackSize.ZERO;
    }

    public double getTopAverage() {
        return topAverage;
    }

    public double getBottomAverage() {
        return bottomAverage;
    }

    @Override
    public Integer getState() {
        return getStackSize();
    }
}
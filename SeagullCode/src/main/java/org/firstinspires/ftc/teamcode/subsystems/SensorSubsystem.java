package org.firstinspires.ftc.teamcode.subsystems;

import static java.lang.System.nanoTime;

import com.qualcomm.hardware.stmicroelectronics.VL53L0X;
import com.qualcomm.robotcore.hardware.I2cDeviceSynchImplOnSimple;
import com.technototes.library.command.Command;
import com.technototes.library.hardware.sensor.Rev2MDistanceSensor;
import com.technototes.library.logger.Log;
import com.technototes.library.logger.Loggable;
import com.technototes.library.subsystem.Subsystem;

import java.util.concurrent.atomic.AtomicBoolean;

public class SensorSubsystem implements Subsystem, Loggable {
    static class SequentialRead implements Runnable {
        private final Rev2MDistanceSensor front_range;
        private final Rev2MDistanceSensor left_range;
        private final Rev2MDistanceSensor right_range;
        private final Rev2MDistanceSensor bucket_range;

        private double front_dist;
        private double left_dist;
        private double right_dist;
        private double bucket_dist;

        private long read_interval;
        private long read_duration;
        private long last_read_time = 0;
        private final AtomicBoolean stop_read_thread = new AtomicBoolean(false);
        private final Thread thread = new Thread(this, "SequentialRangeRead");

        public SequentialRead(Rev2MDistanceSensor front, Rev2MDistanceSensor left, Rev2MDistanceSensor right,
                              Rev2MDistanceSensor bucket) {
            this.front_range = front;
            this.left_range = left;
            this.right_range = right;
            this.bucket_range = bucket;
        }

        public void start() {
            last_read_time = nanoTime();
            stop_read_thread.set(false);
            thread.start();
        }

        public void stop() {
            stop_read_thread.set(true);
            try {
                thread.join();
            } catch (InterruptedException ignored) {}
        }

        public void updateValues(SensorSubsystem sensorSubsystem) {
            sensorSubsystem.frontDist = front_dist;
            sensorSubsystem.leftDist = left_dist;
            sensorSubsystem.rightDist = right_dist;
            sensorSubsystem.bucketDist = bucket_dist;
            sensorSubsystem.readDurationMs = Double.toString(read_duration / 1000000.0);
            sensorSubsystem.readIntervalMs = Double.toString(read_interval / 1000000.0);
        }

        @Override
        public void run() {
            while (!stop_read_thread.get()) {
                final long startTime = nanoTime();
                front_dist = front_range.getDistance();
                left_dist = left_range.getDistance();
                right_dist = right_range.getDistance();
                bucket_dist = bucket_range.getDistance();
                final long stopTime = nanoTime();
                read_duration = stopTime - startTime;
                read_interval = startTime - last_read_time;
                last_read_time = startTime;
            }
        }
    }

    static class ParallelRead {
        enum SensorType {
            FRONT(0),
            LEFT(1),
            RIGHT(2),
            BUCKET(3),
            COUNT(BUCKET.getIndex() + 1);

            private final int index;

            SensorType(int index) {
                this.index = index;
            }

            public int getIndex() {
                return index;
            }
        }

        static class SensorUpdateData {
            public double distance;
            public long readInterval;
            public long readDuration;
        }

        static class ParallelReadThread implements Runnable {
            // Synchronized by 'this'
            private double distance;
            private long read_interval;
            private long read_duration;

            private long last_read_time = 0;
            private final Rev2MDistanceSensor sensor;
            private final AtomicBoolean stop_read_thread = new AtomicBoolean(false);
            private final Thread thread = new Thread(this, "ParallelRangeRead");

            ParallelReadThread(Rev2MDistanceSensor sensor) {
                this.sensor = sensor;
            }

            @Override
            public void run() {
                while (!stop_read_thread.get()) {
                    final long startTime = nanoTime();
                    final double localDistance = sensor.getDistance();
                    final long stopTime = nanoTime();

                    synchronized (this) {
                        distance = localDistance;
                        read_interval = startTime - last_read_time;
                        read_duration = stopTime - startTime;
                    }

                    last_read_time = startTime;
                }
            }

            public void start() {
                last_read_time = nanoTime();
                stop_read_thread.set(false);
                thread.start();
            }

            public void stop() {
                stop_read_thread.set(true);
                try {
                    thread.join();
                } catch (InterruptedException ignored) {}
            }

            public SensorUpdateData getSensorUpdateData()
            {
                SensorUpdateData sensorUpdateData = new SensorUpdateData();
                synchronized (this) {
                    sensorUpdateData.distance = distance;
                    sensorUpdateData.readDuration = read_duration;
                    sensorUpdateData.readInterval = read_interval;
                }
                return sensorUpdateData;
            }
        }

        private final ParallelReadThread[] read_threads = new ParallelReadThread[SensorType.COUNT.getIndex()];

        public ParallelRead(Rev2MDistanceSensor front, Rev2MDistanceSensor left, Rev2MDistanceSensor right,
                            Rev2MDistanceSensor bucket) {
            read_threads[SensorType.FRONT.getIndex()] = new ParallelReadThread(front);
            read_threads[SensorType.LEFT.getIndex()] = new ParallelReadThread(left);
            read_threads[SensorType.RIGHT.getIndex()] = new ParallelReadThread(right);
            read_threads[SensorType.BUCKET.getIndex()] = new ParallelReadThread(bucket);
        }

        public void start() {
            for (ParallelReadThread thread : read_threads) {
                thread.start();
            }
        }

        public void stop() {
            for (ParallelReadThread thread : read_threads) {
                thread.stop();
            }
        }

        public void updateValues(SensorSubsystem sensorSubsystem) {

            SensorUpdateData[] sensorUpdateDataArray = new SensorUpdateData[SensorType.COUNT.getIndex()];
            sensorUpdateDataArray[SensorType.FRONT.getIndex()] = read_threads[SensorType.FRONT.getIndex()].getSensorUpdateData();
            sensorUpdateDataArray[SensorType.LEFT.getIndex()] = read_threads[SensorType.LEFT.getIndex()].getSensorUpdateData();
            sensorUpdateDataArray[SensorType.RIGHT.getIndex()] = read_threads[SensorType.RIGHT.getIndex()].getSensorUpdateData();
            sensorUpdateDataArray[SensorType.BUCKET.getIndex()] = read_threads[SensorType.BUCKET.getIndex()].getSensorUpdateData();

            sensorSubsystem.frontDist = sensorUpdateDataArray[SensorType.FRONT.getIndex()].distance;
            sensorSubsystem.leftDist = sensorUpdateDataArray[SensorType.LEFT.getIndex()].distance;
            sensorSubsystem.rightDist = sensorUpdateDataArray[SensorType.RIGHT.getIndex()].distance;
            sensorSubsystem.bucketDist = sensorUpdateDataArray[SensorType.BUCKET.getIndex()].distance;

            sensorSubsystem.readDurationMs = "";
            sensorSubsystem.readIntervalMs = "";
            long totalDuration = 0;
            long totalInterval = 0;
            for (SensorUpdateData sensorUpdateData : sensorUpdateDataArray) {
                totalDuration += sensorUpdateData.readDuration;
                sensorSubsystem.readDurationMs += sensorUpdateData.readDuration/1000000.0 + ",";
                totalInterval += sensorUpdateData.readInterval;
                sensorSubsystem.readIntervalMs += sensorUpdateData.readInterval/1000000.0 + ",";
            }

            //sensorSubsystem.readIntervalMs = "Sensor Read Interval (ms): " + totalInterval / (sensorUpdateDataArray.length * 1000000.0);
            //sensorSubsystem.readDurationMs = "Sensor Read Duration (ms): " + totalDuration / (sensorUpdateDataArray.length * 1000000.0);
        }
    }

    public static class TestCommand implements Command {
        private final SensorSubsystem sensorSubsystem;

        public TestCommand(SensorSubsystem s) {
            sensorSubsystem = s;
            addRequirements(sensorSubsystem);
        }

        @Override
        public void execute() {
        }

        @Override
        public boolean isFinished() {
            return false;
        }

        @Override
        public void end(boolean cancel) {
            sensorSubsystem.destroy();
        }
    }

    @Log.Number (name = "Front Range Sensor")
    public double frontDist;
    @Log.Number (name = "Left Range Sensor")
    public double leftDist;
    @Log.Number (name = "Right Range Sensor")
    public double rightDist;
    @Log.Number (name = "Bucket Range Sensor")
    public double bucketDist;
    @Log.Number (name = "Loop Time (ms)")
    public double loopTimeMs;
    @Log(name = "Sensor Read Interval (ms)")
    public String readIntervalMs = "";
    @Log(name = "Sensor Read Duration (ms)")
    public String readDurationMs = "";
    @Log(name = "Clients")
    public String clientInfo;

    public enum ReadType {
        SEQUENTIAL_INLINE,
        SEQUENTIAL_TEST_TIMING,
        SEQUENTIAL_THREAD,
        PARALLEL_THREAD
    }

    final Rev2MDistanceSensor front_range;
    final Rev2MDistanceSensor left_range;
    final Rev2MDistanceSensor right_range;
    final Rev2MDistanceSensor bucket_range;
    final ReadType read_type;
    private long last_loop_time = 0;
    private final SequentialRead sequentialRead;
    private final ParallelRead parallelRead;
    public SensorSubsystem(Rev2MDistanceSensor front, Rev2MDistanceSensor left, Rev2MDistanceSensor right,
                    Rev2MDistanceSensor bucket, ReadType readType) {
        this.front_range = front;
        this.left_range = left;
        this.right_range = right;
        this.bucket_range = bucket;
        this.read_type = readType;
        this.last_loop_time = nanoTime();

        switch(this.read_type) {
            case SEQUENTIAL_THREAD:
                sequentialRead = new SequentialRead(front, left, right, bucket);
                sequentialRead.start();
                parallelRead = null;
                break;
            case PARALLEL_THREAD:
                sequentialRead = null;
                parallelRead = new ParallelRead(front, left, right, bucket);
                parallelRead.start();
                break;
            case SEQUENTIAL_TEST_TIMING:
            case SEQUENTIAL_INLINE:
                sequentialRead = null;
                parallelRead = null;
                break;
            default:
                throw new IllegalArgumentException("readType");
        }

        VL53L0X vl53L0X = (VL53L0X)this.front_range.getDevice();
        I2cDeviceSynchImplOnSimple i2cDeviceSynch= (I2cDeviceSynchImplOnSimple)vl53L0X.getDeviceClient();
        int frontClient = i2cDeviceSynch.hashCode();

        vl53L0X = (VL53L0X)this.left_range.getDevice();
        i2cDeviceSynch= (I2cDeviceSynchImplOnSimple)vl53L0X.getDeviceClient();
        int leftClient = i2cDeviceSynch.hashCode();

        vl53L0X = (VL53L0X)this.right_range.getDevice();
        i2cDeviceSynch= (I2cDeviceSynchImplOnSimple)vl53L0X.getDeviceClient();
        int rightClient = i2cDeviceSynch.hashCode();

        vl53L0X = (VL53L0X)this.bucket_range.getDevice();
        i2cDeviceSynch= (I2cDeviceSynchImplOnSimple)vl53L0X.getDeviceClient();
        int bucketClient = i2cDeviceSynch.hashCode();

        clientInfo = frontClient + " " + leftClient + " " + rightClient + " " + bucketClient;
    }

    public void destroy() {
        if (sequentialRead != null) {
            sequentialRead.stop();
        }

        if (parallelRead != null) {
            parallelRead.stop();
        }
    }

    @Override
    public void periodic() {
        final long startTime = nanoTime();
        switch(this.read_type) {
            case SEQUENTIAL_THREAD:
                sequentialRead.updateValues(this);
                break;
            case PARALLEL_THREAD:
                parallelRead.updateValues(this);
                break;
            case SEQUENTIAL_INLINE:
                frontDist = front_range.getDistance();
                leftDist = left_range.getDistance();
                rightDist = right_range.getDistance();
                bucketDist = bucket_range.getDistance();
                final long stopTime = nanoTime();
                readDurationMs = Double.toString((stopTime - startTime) / 1000000.0);
                readIntervalMs = Double.toString((startTime - last_loop_time) / 1000000.0);
                break;
            case SEQUENTIAL_TEST_TIMING: {
                long start1 = nanoTime();
                frontDist = front_range.getDistance();
                long start2 = nanoTime();
                frontDist = front_range.getDistance();
                long start3 = nanoTime();
                frontDist = front_range.getDistance();
                long start4 = nanoTime();
                frontDist = front_range.getDistance();
                long stop = nanoTime();
                android.util.Log.i("SensorSub", "Front: " + start1 + "," + start2 + "," + start3 + "," + start4 + "," + stop);

                start1 = nanoTime();
                frontDist = left_range.getDistance();
                start2 = nanoTime();
                frontDist = left_range.getDistance();
                start3 = nanoTime();
                frontDist = left_range.getDistance();
                start4 = nanoTime();
                frontDist = left_range.getDistance();
                stop = nanoTime();
                android.util.Log.i("SensorSub", "Left: " + start1 + "," + start2 + "," + start3 + "," + start4 + "," + stop);

                start1 = nanoTime();
                frontDist = right_range.getDistance();
                start2 = nanoTime();
                frontDist = right_range.getDistance();
                start3 = nanoTime();
                frontDist = right_range.getDistance();
                start4 = nanoTime();
                frontDist = right_range.getDistance();
                stop = nanoTime();
                android.util.Log.i("SensorSub", "Right: " + start1 + "," + start2 + "," + start3 + "," + start4 + "," + stop);

                start1 = nanoTime();
                frontDist = bucket_range.getDistance();
                start2 = nanoTime();
                frontDist = bucket_range.getDistance();
                start3 = nanoTime();
                frontDist = bucket_range.getDistance();
                start4 = nanoTime();
                frontDist = bucket_range.getDistance();
                stop = nanoTime();
                android.util.Log.i("SensorSub", "Bucket: " + start1 + "," + start2 + "," + start3 + "," + start4 + "," + stop);
                break;
            }
            default:
                throw new IllegalArgumentException("read_type");
        }

        loopTimeMs = (startTime - last_loop_time) / 1000000.0;
        last_loop_time = startTime;
    }
}

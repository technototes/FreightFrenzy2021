package org.firstinspires.ftc.teamcode;

import androidx.annotation.Nullable;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.Telemetry;

public class MockLine implements Telemetry.Line {

    @Override
    public Telemetry.Item addData(String caption, String format, Object... args) {
        return new MockItem().addData(caption, format, args);
    }

    @Override
    public Telemetry.Item addData(String caption, Object value) {
        return new MockItem().addData(caption, value);
    }

    @Override
    public <T> Telemetry.Item addData(String caption, Func<T> valueProducer) {
        return new MockItem().addData(caption, valueProducer);
    }

    @Override
    public <T> Telemetry.Item addData(String caption, String format, Func<T> valueProducer) {
        return new MockItem().addData(caption, format, valueProducer);
    }
}

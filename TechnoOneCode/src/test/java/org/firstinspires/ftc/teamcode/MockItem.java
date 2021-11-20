package org.firstinspires.ftc.teamcode;

import androidx.annotation.Nullable;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.mockito.Mock;

public class MockItem implements Telemetry.Item {

    public String cap;
    public Object val;
    @Override
    public String getCaption() {
        return cap;
    }

    @Override
    public Telemetry.Item setCaption(String caption) {
        cap = caption;
        return this;
    }

    @Override
    public Telemetry.Item setValue(String format, Object... args) {
        val = String.format(format, args);
        return this;
    }

    @Override
    public Telemetry.Item setValue(Object value) {
        val = value;
        return this;
    }

    @Override
    public <T> Telemetry.Item setValue(Func<T> valueProducer) {
        val = valueProducer.value();
        return this;
    }

    @Override
    public <T> Telemetry.Item setValue(String format, Func<T> valueProducer) {
        val = String.format(format, valueProducer.value());
        return this;
    }

    @Override
    public Telemetry.Item setRetained(@Nullable Boolean retained) {
        return this;
    }

    @Override
    public boolean isRetained() {
        return false;
    }

    @Override
    public Telemetry.Item addData(String caption, String format, Object... args) {
        cap = caption;
        val = String.format(format, args);
        return this;
    }

    @Override
    public Telemetry.Item addData(String caption, Object value) {
        cap = caption;
        val = value;
        return this;
    }

    @Override
    public <T> Telemetry.Item addData(String caption, Func<T> valueProducer) {
        cap = caption;
        val = valueProducer.value();
        return this;
    }

    @Override
    public <T> Telemetry.Item addData(String caption, String format, Func<T> valueProducer) {
        cap = caption;
        val = String.format(format, valueProducer.value());
        return this;
    }
}

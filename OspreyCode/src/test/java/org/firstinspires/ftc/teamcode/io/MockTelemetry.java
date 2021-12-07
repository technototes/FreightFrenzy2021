package org.firstinspires.ftc.teamcode.io;

import org.firstinspires.ftc.robotcore.external.Func;
import org.firstinspires.ftc.robotcore.external.Telemetry;
import org.firstinspires.ftc.teamcode.MockRobot;

import java.util.ArrayList;
import java.util.List;

public class MockTelemetry implements Telemetry {
    List<Item> items = new ArrayList<>();

    @Override
    public Item addData(String caption, String format, Object... args) {
        Item i = new MockItem().addData(caption, format, args);
        items.add(i);
        return i;
    }

    @Override
    public Item addData(String caption, Object value) {
        Item i = new MockItem().addData(caption, value);
        items.add(i);
        return i;
    }

    @Override
    public <T> Item addData(String caption, Func<T> valueProducer) {
        Item i = new MockItem().addData(caption, valueProducer);
        items.add(i);
        return i;
    }

    @Override
    public <T> Item addData(String caption, String format, Func<T> valueProducer) {
        Item i = new MockItem().addData(caption, format, valueProducer);
        items.add(i);
        return i;
    }

    @Override
    public boolean removeItem(Item item) {
        return items.remove(item);
    }

    @Override
    public void clear() {
        items.clear();
    }

    @Override
    public void clearAll() {
        items.clear();
    }

    @Override
    public Object addAction(Runnable action) {
        return null;
    }

    @Override
    public boolean removeAction(Object token) {
        return false;
    }

    @Override
    public void speak(String text) {

    }

    @Override
    public void speak(String text, String languageCode, String countryCode) {

    }

    @Override
    public boolean update() {
        for (Item i : items) {
           if(MockRobot.TELEMETRY) System.out.println(i.getCaption());
        }
        clearAll();
        return true;
    }

    @Override
    public Line addLine() {
        Line i = new MockLine();
        items.add(new MockItem().addData("", ""));
        return i;
    }

    @Override
    public Line addLine(String lineCaption) {
        Line i = new MockLine();
        items.add(i.addData(lineCaption, ""));
        return i;
    }

    @Override
    public boolean removeLine(Line line) {
        return false;
    }

    @Override
    public boolean isAutoClear() {
        return false;
    }

    @Override
    public void setAutoClear(boolean autoClear) {

    }
    int d;
    @Override
    public int getMsTransmissionInterval() {
        return d;
    }

    @Override
    public void setMsTransmissionInterval(int msTransmissionInterval) {
        d = msTransmissionInterval;
    }

    @Override
    public String getItemSeparator() {
        return ":";
    }

    @Override
    public void setItemSeparator(String itemSeparator) {

    }

    @Override
    public String getCaptionValueSeparator() {
        return null;
    }

    @Override
    public void setCaptionValueSeparator(String captionValueSeparator) {

    }

    @Override
    public void setDisplayFormat(DisplayFormat displayFormat) {

    }

    @Override
    public Log log() {
        return null;
    }

}

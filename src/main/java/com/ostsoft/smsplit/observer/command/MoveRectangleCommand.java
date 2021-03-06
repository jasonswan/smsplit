package com.ostsoft.smsplit.observer.command;

import com.ostsoft.smsplit.AutoData;
import com.ostsoft.smsplit.observer.EventType;
import com.ostsoft.smsplit.xml.config.RectangleXML;

public class MoveRectangleCommand extends Command {

    private final AutoData autoData;
    private final RectangleXML rectangleXML;
    private final double newX, newY;
    private final double oldX, oldY;

    public MoveRectangleCommand(AutoData autoData, RectangleXML rectangleXML, double newX, double newY) {
        this.autoData = autoData;
        this.rectangleXML = rectangleXML;
        this.newX = newX;
        this.newY = newY;
        this.oldX = rectangleXML.x;
        this.oldY = rectangleXML.y;
    }

    @Override
    public void execute() {
        rectangleXML.x = newX;
        rectangleXML.y = newY;
        autoData.fireEvent(EventType.ITEMBOX_RECTANGLE_MOVED);

        autoData.setConfigChanged(true);
        autoData.fireEvent(EventType.CONFIG_UPDATE);
    }

    @Override
    public void undo() {
        rectangleXML.x = oldX;
        rectangleXML.y = oldY;
        autoData.fireEvent(EventType.ITEMBOX_RECTANGLE_MOVED);

        autoData.setConfigChanged(true);
        autoData.fireEvent(EventType.CONFIG_UPDATE);
    }

    @Override
    String getDescription() {
        return "Move rectangle from (" + oldX + ", " + oldY + ") to (" + newX + ", " + newY + ")";
    }

}

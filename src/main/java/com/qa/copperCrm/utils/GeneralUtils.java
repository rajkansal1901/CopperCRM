package com.qa.copperCrm.utils;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class GeneralUtils {
    private static LocalDateTime myDateObj;
    protected static DateTimeFormatter myFormatObj;

    public static String getUniqueName(String name) {
        myDateObj = LocalDateTime.now();
        myFormatObj = DateTimeFormatter.ofPattern("ddMMss");
        String formattedDate = myDateObj.format(myFormatObj);
        return name + formattedDate;
    }
    public static String getLocalTime() {
        myDateObj = LocalDateTime.now();
        myFormatObj = DateTimeFormatter.ofPattern("k:mm a");
        String currentTime = myDateObj.format(myFormatObj);
        return currentTime;
    }

    public static String getCurrentDate() {
        myDateObj = LocalDateTime.now();
        myFormatObj = DateTimeFormatter.ofPattern("MM" + "/" + "dd" + "/" + "YYYY");
        String formattedDate = myDateObj.format(myFormatObj);
        return formattedDate;
    }

    public static String getCurrentDateAndTime() {
        myDateObj = LocalDateTime.now();
        myFormatObj = DateTimeFormatter.ofPattern("MM" + "/" + "dd" + "/" + "YYYY" + "(" + "hh:mm" + ")");
        String formattedDate = myDateObj.format(myFormatObj);
        return formattedDate;
    }

    public static void robotUploadFile(String filePath) {

        Robot robot;
        try {
            robot = new Robot();
            StringSelection stringSelection = new StringSelection(filePath);
            Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
            clipboard.setContents(stringSelection, null);
            robot.delay(500);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);

            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyRelease(KeyEvent.VK_V);

            robot.delay(500);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
        } catch (AWTException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void main(String[] args) {
        System.out.println(getLocalTime());
    }
}

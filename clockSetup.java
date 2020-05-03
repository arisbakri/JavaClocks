package assignment_sysSoft;

import java.awt.*;
import javax.swing.*;
import java.util.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import javax.swing.JPanel;
import javax.swing.Timer;

public class clockSetup extends JPanel implements Runnable{

    private int second;
    private int minute;
    private int hour;
    private boolean secondHandVisible = true;
    private boolean minuteHandVisible = true;
    private boolean hourHandVisible = true;

    private SimpleDateFormat formatting = new SimpleDateFormat("s", Locale.getDefault());
	private Date latestDate;
        
    public clockSetup() {

        setCurrentTime();

    }
    public clockSetup(int hour, int minute, int second) {

        this.hour = hour;
        this.minute = minute;
        this.second = second;

    }
    // seconds param
    public int getSecond() {

        return second;

    }
    public void setSecond(int second) {

        this.second = second;

        repaint();

    }
    // minutes param
    public int getMinute() {

        return minute;

    }
    public void setMinute(int minute) {

        this.minute = minute;

        repaint();

    }
    // hour param
    public int getHour() {

        return hour;

    }
    public void setHour(int hour) {

        this.hour = hour;

        repaint();

    }
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics = (Graphics2D) g;
		graphics.setRenderingHint(
				RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
                
                latestDate = new Date();
		formatting.applyPattern("s");
		second = Integer.parseInt(formatting.format(latestDate));
		formatting.applyPattern("m");
		minute = Integer.parseInt(formatting.format(latestDate));
		formatting.applyPattern("h");
		hour = Integer.parseInt(formatting.format(latestDate));

        // Initialize clock parameters
        int radiusClock
                = (int) (Math.min(getWidth(), getHeight()) * 0.8 * 0.5);

        int xCenter = getWidth() / 2;
        int yCenter = getHeight() / 2;

        // Draw circle
        g.setColor(Color.black);
        g.setFont(new Font("Times", Font.ITALIC, 16));
        

        g.drawOval(xCenter - radiusClock, yCenter - radiusClock,
                2 * radiusClock, 2 * radiusClock);

        g.drawString("12", xCenter - 5, yCenter - radiusClock + 12);
        g.drawString("3", xCenter + radiusClock - 10, yCenter + 3);
        g.drawString("6", xCenter - 3, yCenter + radiusClock - 3);
        g.drawString("9", xCenter - radiusClock + 3, yCenter + 5);

        // Draw second hand of the clock
        
        if (secondHandVisible) {
        int sLength = (int) (radiusClock * 0.8);

        int xSecond = (int) (xCenter + sLength
                * Math.sin(second * (2 * Math.PI / 60)));

        int ySecond = (int) (yCenter - sLength
                * Math.cos(second * (2 * Math.PI / 60)));

        g.setColor(Color.gray);
        g.drawLine(xCenter, yCenter, xSecond, ySecond);
        
        }
        
        if (minuteHandVisible) {
        // Draw minute hand of the clock
        int mLength = (int) (radiusClock * 0.65);

        int xMinute = (int) (xCenter + mLength
                * Math.sin(minute * (2 * Math.PI / 60)));

        int yMinute = (int) (yCenter - mLength
                * Math.cos(minute * (2 * Math.PI / 60)));

        g.setColor(Color.red);g.drawLine(xCenter, yCenter, xMinute, yMinute);
        
        }
        
        if (hourHandVisible) {
        // Draw hour hand of the clock
        int hLength = (int) (radiusClock * 0.5);

        int xHour = (int) (xCenter + hLength
                * Math.sin((hour % 12 + minute / 60.0) * (2 * Math.PI / 12)));

        int yHour = (int) (yCenter - hLength
                * Math.cos((hour % 12 + minute / 60.0) * (2 * Math.PI / 12)));

        g.setColor(Color.cyan);
        g.drawLine(xCenter, yCenter, xHour, yHour);
        }

    }

    public void setCurrentTime() {

        // Set time
        Calendar calendar = new GregorianCalendar();
        this.hour = calendar.get(Calendar.HOUR_OF_DAY);
        this.minute = calendar.get(Calendar.MINUTE);
        this.second = calendar.get(Calendar.SECOND);

    }

    @Override
    public Dimension getPreferredSize() {

        return new Dimension(200, 200);

    }
    
    public boolean isSecondHandVisisble() {
        return secondHandVisible;
    }
    
    public boolean isMinuteHandVisible() {
        return minuteHandVisible;
    }
    
    public boolean isHourHandVisible() {
        return hourHandVisible;
    }
    
    public void setSecondHandVisible(boolean secondHandVisible) {
        this.secondHandVisible = secondHandVisible;
    }
    
    public void setMinuteHandVisible(boolean minuteHandVisible) {
        this.minuteHandVisible = minuteHandVisible;
    }
    
    public void setHourHandVisible(boolean hourHandVisible) {
        this.hourHandVisible = hourHandVisible;
    }
    
	public void run() {
		// TODO Auto-generated method stub
		
	}

}
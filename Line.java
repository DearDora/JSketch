import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.*;
import java.awt.BasicStroke;

public class Line implements Shape{
    int startX;
    int startY;
    int currentX;
    int currentY;
    int width;
    public Color color;
    boolean state = false;
    BasicStroke s;
    float dash[] = { 10.0f };

    public Line(int startX,int startY,int currentX, int currentY,Color color,int width){
        this.startX = startX;
        this.startY = startY;
        this.currentX = currentX;
        this.currentY = currentY;
        this.width = width;
        this.color = color;
        s = new BasicStroke(width);
    }
    public void sketch(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;

        g2.setStroke(s);
        g2.setColor(color);
        g2.drawLine(startX, startY, currentX, currentY);
    }
    public boolean clicked(int x, int y){
        if (currentX < startX && currentY < startY) {
            return(x >= currentX && x <= startX && y >= currentY && y <= startY);
        }else if(currentX < startX && currentY >= startY){
            return(x >= currentX && x <= startX && y >= startY && y <= currentY);
        }else if(currentX >= startX && currentY < startY){
            return(x <= currentX && x >= startX && y >= currentY && y <= startY);
        }else{
            return(x <= currentX && x >= startX && y >= startY && y <= currentY);
        }
    }
    public void changeColor(Color c){
        color = c;
    }
    public void changeBorderColor(Color c){
        color = c;
    }
    public boolean getState(){
        return state;
    }
    public void setState(boolean b){ state = b; }
    public void changeBorder(){
        s = new BasicStroke(10,BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_BEVEL, 0.0f, dash, 0);
    }
    public void changeBorderBack(){
        s =  new BasicStroke(width);
    }
    public void changePosition(int x, int y){
        startX += x;
        startY += y;
        currentX += x;
        currentY +=y;
    }
    public String getType(){
        return "line";
    }
    public int getThickness(){
        return width;
    }
    public Color getColor(){
        return color;
    }
    public Color getInnerColor(){return color;}
    public int getX1(){
        return startX;
    }
    public int getX2(){
        return currentX;
    }
    public int getY1(){
        return startY;
    }
    public int getY2(){
        return currentY;
    }
}
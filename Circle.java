import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.*;

public class Circle implements Shape{
    int startX;
    int startY;
    int r;
    int width;
    Color bcolor;
    Color icolor;
    boolean state = false;
    BasicStroke s;
    float dash[] = { 10.0f };

    public Circle (int startX,int startY,int r,Color bcolor, Color icolor,int width){
        this.startX = startX;
        this.startY = startY;
        this.r = r;
        this.width = width;
        this.bcolor = bcolor;
        this.icolor = icolor;
        s = new BasicStroke(width);
    }
    public void sketch(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(s);
        g2.setColor(bcolor);
        g2.drawArc(startX-r/2, startY-r/2, r, r, 0, 360);
        g2.setColor(icolor);
        g2.fillArc(startX-r/2, startY-r/2, r, r, 0, 360);
    }
    public boolean clicked(int x, int y){
        int r_ = (int) java.lang.Math.sqrt((x-startX)*(x-startX)+(startY-y)*(startY-y));
        return (r_ <= r/2);
    }
    public void changeColor(Color c){
        icolor = c;
    }
    public void changeBorderColor(Color c){
        bcolor = c;
    }
    public boolean getState(){
        return state;
    }
    public void setState(boolean b){ state = b; }
    public void changeBorder(){
        s =  new BasicStroke(10,BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_BEVEL, 0.0f, dash, 0);
    }
    public void changeBorderBack(){
        s =  new BasicStroke(width);
    }
    public void changePosition(int x, int y){
        startX += x;
        startY += y;
    }
    public String getType(){
        return "circle";
    }
    public int getThickness(){
        return width;
    }
    public Color getColor(){
        return bcolor;
    }
    public Color getInnerColor(){
        return icolor;
    }
    public int getX1(){
        return startX;
    }
    public int getX2(){
        return r;
    }
    public int getY1(){
        return startY;
    }
    public int getY2(){
        return r;
    }
}
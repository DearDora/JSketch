import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.*;

public class Rectangle implements Shape{
    int x_;
    int y_;
    int w;
    int l;
    int width;
    Color bcolor;
    Color icolor;
    boolean state = false;
    BasicStroke s;
    float dash[] = { 10.0f };

    public Rectangle (int x,int y,int w,int l,Color bcolor,Color icolor,int width){
        this.x_ = x;
        this.y_ = y;
        this.w = w;
        this.l = l;
        this.width = width;
        this.bcolor = bcolor;
        this.icolor = Color.WHITE;
        s = new BasicStroke(width);
    }
    public void sketch(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.setStroke(s);
        g2.setColor(bcolor);
        g.drawRect(x_, y_, w, l);
        g2.setColor(icolor);
        g.fillRect(x_, y_, w, l);
    }
    public boolean clicked(int x, int y){
        return (x >= x_ && y >= y_ && x <= (x_+w) && y <= (y_+l));
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
        s = new BasicStroke(10,BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_BEVEL, 0.0f, dash, 0);
    }
    public void changeBorderBack(){
        s =  new BasicStroke(width);
    }
    public void changePosition(int x, int y){
        x_ += x;
        y_ += y;
    }
    public String getType(){
        return "rectangle";
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
        return x_;
    }
    public int getX2(){
        return w;
    }
    public int getY1(){
        return y_;
    }
    public int getY2(){
        return l;
    }
}
import java.awt.Graphics;
import java.awt.Color;
import java.awt.BasicStroke;
public interface Shape {
    public void sketch(Graphics g);
    public boolean clicked(int x, int y);
    public void changeColor(Color c);
    public boolean getState();
    public void setState(boolean b);
    public void changeBorder();
    public void changeBorderBack();
    public void changePosition(int x, int y);
    public void changeBorderColor(Color c);
    public String getType();
    public int getThickness();
    public Color getColor();
    public Color getInnerColor();
    public int getX1();
    public int getX2();
    public int getY1();
    public int getY2();
}
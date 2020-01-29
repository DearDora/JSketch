import javax.swing.*;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.geom.*;
import java.awt.*;
import java.awt.Graphics;
import java.awt.event.*;
import java.util.*;
import java.awt.event.MouseEvent;

class SketchView extends Canvas implements Observer{
    int startX = 0;
    int startY = 0;
    int lastX = -1;
    int lastY = -1;
    int currentX = 0;
    int currentY = 0;
    boolean pressed = false;
    float dash[] = { 10.0f };
    // the model that this view is showing
    private Model model;
    BasicStroke dashStroke;

    SketchView(Model model) {
        // create UI
        setBackground(Color.WHITE);

        // set the model
        this.model = model;

        // anonymous class acts as model listener
        this.model.addObserver(new Observer() {
            public void update(Object observable) {
                repaint();
            }
        });

        // setup the event to go to the "controller"
        // (this anonymous class is essentially the controller)
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                switch(model.currentCommand) {
                    case Command.SELECTION:
                        for (int i = (model.shapes.size() - 1); i >= 0; --i) {
                            if (model.shapes.get(i).clicked(e.getX(), e.getY())) {
                                if(model.selected != -1) {
                                    model.shapes.get(model.selected).changeBorderBack();
                                }
                                model.shapes.get(i).changeBorder();
                                model.selected = i;
                                model.lineThickness = model.shapes.get(model.selected).getThickness();
                                model.lineColor = model.shapes.get(model.selected).getColor();
                                model.last.setBorder(BorderFactory.createRaisedBevelBorder());
                                model.last.repaint();
                                model.notifyObservers();
                                break;
                            }
                        }
                        repaint();
                        break;
                    case Command.ERASE:
                        for (int i = (model.shapes.size() - 1); i >= 0; --i) {
                            if (model.shapes.get(i).clicked(e.getX(), e.getY())) {
                                model.shapes.remove(i);
                                break;
                            } else {
                                continue;
                            }
                        }
                        break;
                    case Command.PAINT:
                        for (int i = (model.shapes.size() - 1); i >= 0; --i) {
                            if (model.shapes.get(i).clicked(e.getX(), e.getY())) {
                                model.shapes.get(i).changeColor(model.lineColor);
                                break;
                            } else {
                                continue;
                            }
                        }
                        break;
                }
            }
        });
        addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                startX = e.getX();
                startY = e.getY();
                pressed = true;
            }
        });
        addKeyListener(new KeyAdapter() {
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == 27) {
                    if(model.selected != -1){
                        model.shapes.get(model.selected).changeBorderBack();
                        model.selected = -1;
                        repaint();
                    }
                }
            }
        });
        addMouseListener(new MouseAdapter() {
            public void mouseReleased(MouseEvent e) {
                currentX = e.getX();
                currentY = e.getY();
                pressed = false;

                switch(model.currentCommand){
                    case Command.SELECTION:
                        lastX = -1;
                        lastY = -1;
                        if(model.selected != -1) model.shapes.get(model.selected).changeBorderBack();
                        model.selected = -1;
                        repaint();
                        break;
                    case Command.LINE:
                        Line l = new Line(startX, startY, currentX, currentY, model.lineColor,model.lineThickness);
                        model.shapes.add(l);
                        break;
                    case Command.CIRCLE:
                        int r = (int) java.lang.Math.sqrt((currentX - startX)*(currentX-startX)
                                + (startY - currentY)*(startY - currentY));
                        Circle c = new Circle (startX, startY,r,model.lineColor, Color.WHITE,model.lineThickness);
                        model.shapes.add(c);
                        break;
                    case Command.RECTANGLE:
                        Rectangle re;
                        if (currentX < startX && currentY < startY) {
                            re = new Rectangle(currentX, currentY, startX - currentX, startY-currentY,
                                    model.lineColor,Color.WHITE,model.lineThickness);
                        }else if(currentX < startX && currentY >= startY){
                            re = new Rectangle(currentX, startY, startX - currentX, currentY-startY,
                                    model.lineColor, Color.WHITE,model.lineThickness);
                        }else if(currentX >= startX && currentY < startY){
                            re = new Rectangle(startX, currentY, currentX-startX, startY-currentY,
                                    model.lineColor, Color.WHITE,model.lineThickness);
                        }else{
                            re = new Rectangle(startX, startY, currentX-startX, currentY-startY,
                                    model.lineColor, Color.WHITE,model.lineThickness);
                        }
                        model.shapes.add(re);
                        break;
                }
                repaint();
            }
        });
        addMouseMotionListener(new MouseAdapter() {
            public void mouseDragged(MouseEvent e) {
                currentX = e.getX();
                currentY = e.getY();
                repaint();
            }
        });
    }
    public void update(Graphics g){ paint(g);}

    public void paint(Graphics g){
        Shape shape = null;
        Graphics2D g2 = (Graphics2D)g;
        g2.setColor(Color.white);
        g2.fillRect(0, 0, this.getWidth(), this.getHeight());
        dashStroke = new BasicStroke(model.lineThickness,BasicStroke.CAP_BUTT,
                BasicStroke.JOIN_BEVEL, 0.0f, dash, 0);

        Iterator<Shape> itr = model.shapes.iterator();
        while(itr.hasNext()){
            itr.next().sketch(g);
        }

        if(pressed){
            g2.setStroke(new BasicStroke(model.lineThickness));
            switch(model.currentCommand) {
                case Command.SELECTION:
                    if(model.selected != -1){
                        if(!model.shapes.get(model.selected).clicked(currentX, currentY)){
                            break;
                        }
                        if(lastX == -1){
                            lastX = startX;
                            lastY = startY;
                        }
                        model.shapes.get(model.selected).changePosition(currentX-lastX,currentY-lastY);
                        lastX = currentX;
                        lastY = currentY;
                    }
                    break;
                case Command.ERASE:
                    break;
                case Command.LINE:
                    g2.setColor(model.lineColor);
                    g2.setStroke(dashStroke);
                    g2.drawLine(startX, startY, currentX, currentY);
                    break;
                case Command.CIRCLE:
                    g2.setColor(model.lineColor);
                    g2.setStroke(dashStroke);
                    int r = (int) java.lang.Math.sqrt((currentX - startX)*(currentX-startX)
                            + (startY - currentY)*(startY - currentY));
                    g2.drawArc(startX-r/2, startY-r/2, r, r, 0, 360);
                    g2.setColor(Color.WHITE);
                    g2.fillArc(startX-r/2, startY-r/2, r, r, 0, 360);
                    break;
                case Command.RECTANGLE:
                    g2.setColor(model.lineColor);
                    g2.setStroke(dashStroke);
                    if (currentX < startX && currentY < startY) {
                        g2.drawRect(currentX, currentY, startX - currentX, startY-currentY);
                        g2.setColor(Color.WHITE);
                        g2.fillRect(currentX, currentY, startX - currentX, startY-currentY);
                    }else if(currentX < startX && currentY >= startY){
                        g2.drawRect(currentX, startY, startX - currentX, currentY-startY);
                        g2.setColor(Color.WHITE);
                        g2.fillRect(currentX, startY, startX - currentX, currentY-startY);
                    }else if(currentX >= startX && currentY < startY){
                        g2.drawRect(startX, currentY, currentX-startX, startY-currentY);
                        g2.setColor(Color.WHITE);
                        g2.fillRect(startX, currentY, currentX-startX, startY-currentY);
                    }else{
                        g2.drawRect(startX, startY, currentX-startX, currentY-startY);
                        g2.setColor(Color.WHITE);
                        g2.fillRect(startX, startY, currentX-startX, currentY-startY);
                    }
                    break;
                case Command.PAINT:
                    break;
            }
        }
    }
    public void update(Object observable) {

    }
}
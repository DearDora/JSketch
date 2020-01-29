import javax.swing.*;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.*;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Dimension;
import java.util.*;
import java.awt.Graphics;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


class ColorBlock extends JComponent{
    public Color color;
    ColorBlock(Color c){
        this.color = c;
        this.setBorder(BorderFactory.createRaisedBevelBorder());
    }
    public Color getBlockColor(){
        return color;
    }
    protected void paintComponent(Graphics g){
        g.setColor(color);
        g.fillRect(0,0,30,30);
    }
}

class ColorPalette extends JPanel {

    private Model model;
    ColorPalette(Model model) {
        setLayout(null);
        final JButton chooser = new JButton("Chooser");
        chooser.setBounds(0, 100, 100, 50);
        this.add(chooser);

        model.red.setBounds(3,3,30,30);
        model.orange.setBounds(35,3,30,30);
        model.yellow.setBounds(67,3,30,30);
        model.green.setBounds(3,35,30,30);
        model.cyan.setBounds(35,35,30,30);
        model.blue.setBounds(67,35,30,30);
        model.lightgray.setBounds(3,67,30,30);
        model.darkgray.setBounds(35,67,30,30);
        model.black.setBounds(67,67,30,30);

        this.add(model.red);
        this.add(model.orange);
        this.add(model.yellow);
        this.add(model.green);
        this.add(model.cyan);
        this.add(model.blue);
        this.add(model.lightgray);
        this.add(model.darkgray);
        this.add(model.black);

        // set the model
        this.model = model;
        // anonymous class acts as model listener
        this.model.addObserver(new Observer() {
            public void update(Object observable) {

            }
        });

        chooser.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                final JColorChooser colorChooser = new JColorChooser(Color.RED);
                ActionListener okActionListener = new ActionListener() {
                    public void actionPerformed(ActionEvent actionEvent) {
                        model.lineColor = colorChooser.getColor();
                        model.last.setBorder(BorderFactory.createRaisedBevelBorder());
                        model.last.repaint();
                        model.last = model.black;
                    }
                };

                // For cancel selection, change button background to red
                ActionListener cancelActionListener = new ActionListener() {
                    public void actionPerformed(ActionEvent actionEvent) {
                        System.out.println("canclled");
                    }
                };

                final JDialog dialog = JColorChooser.createDialog(null, "Choose a Color", true,
                        colorChooser, okActionListener, cancelActionListener);

                dialog.setVisible(true);

            }
        });


        // setup the event to go to the "controller"
        addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int x = e.getX();
                int y = e.getY();
                if (SwingUtilities.isLeftMouseButton(e)) {
                    if (x > 3 && x < 33 && y > 3 && y < 33) {
                        if(model.selected != -1){
                            model.shapes.get(model.selected).changeBorderColor(model.red.color);
                            model.notifyObservers();
                        }
                        model.lineColor = model.red.color;
                        model.last.setBorder(BorderFactory.createRaisedBevelBorder());
                        model.red.setBorder(BorderFactory.createLoweredBevelBorder());
                        model.last.repaint();
                        model.red.repaint();
                        model.last = model.red;
                    } else if (x > 35 && x < 65 && y > 3 && y < 33) {
                        if(model.selected != -1){
                            model.shapes.get(model.selected).changeBorderColor(model.orange.color);
                            model.notifyObservers();
                        }
                        model.lineColor = model.orange.color;
                        model.last.setBorder(BorderFactory.createRaisedBevelBorder());
                        model.orange.setBorder(BorderFactory.createLoweredBevelBorder());
                        model.last.repaint();
                        model.orange.repaint();
                        model.last = model.orange;
                    } else if (x > 67 && x < 97 && y > 3 && y < 33) {
                        if(model.selected != -1){
                            model.shapes.get(model.selected).changeBorderColor(model.yellow.color);
                            model.notifyObservers();
                        }
                        model.lineColor = model.yellow.color;
                        model.last.setBorder(BorderFactory.createRaisedBevelBorder());
                        model.yellow.setBorder(BorderFactory.createLoweredBevelBorder());
                        model.last.repaint();
                        model.yellow.repaint();
                        model.last = model.yellow;
                    } else if (x > 3 && x < 33 && y > 35 && y < 65) {
                        if(model.selected != -1){
                            model.shapes.get(model.selected).changeBorderColor(model.green.color);
                            model.notifyObservers();
                        }
                        model.lineColor = model.green.color;
                        model.last.setBorder(BorderFactory.createRaisedBevelBorder());
                        model.green.setBorder(BorderFactory.createLoweredBevelBorder());
                        model.last.repaint();
                        model.green.repaint();
                        model.last = model.green;
                    } else if (x > 35 && x < 65 && y > 35 && y < 65) {
                        if(model.selected != -1){
                            model.shapes.get(model.selected).changeBorderColor(model.cyan.color);
                            model.notifyObservers();
                        }
                        model.lineColor = model.cyan.color;
                        model.last.setBorder(BorderFactory.createRaisedBevelBorder());
                        model.cyan.setBorder(BorderFactory.createLoweredBevelBorder());
                        model.last.repaint();
                        model.cyan.repaint();
                        model.last = model.cyan;
                    } else if (x > 67 && x < 97 && y > 35 && y < 65) {
                        if(model.selected != -1){
                            model.shapes.get(model.selected).changeBorderColor(model.blue.color);
                            model.notifyObservers();
                        }
                        model.lineColor = model.blue.color;
                        model.last.setBorder(BorderFactory.createRaisedBevelBorder());
                        model.blue.setBorder(BorderFactory.createLoweredBevelBorder());
                        model.last.repaint();
                        model.blue.repaint();
                        model.last = model.blue;
                    } else if (x > 3 && x < 33 && y > 67 && y < 97) {
                        if(model.selected != -1){
                            model.shapes.get(model.selected).changeBorderColor(model.lightgray.color);
                            model.notifyObservers();
                        }
                        model.lineColor = model.lightgray.color;
                        model.last.setBorder(BorderFactory.createRaisedBevelBorder());
                        model.lightgray.setBorder(BorderFactory.createLoweredBevelBorder());
                        model.last.repaint();
                        model.lightgray.repaint();
                        model.last = model.lightgray;
                    } else if (x > 35 && x < 65 && y > 67 && y < 97) {
                        if(model.selected != -1){
                            model.shapes.get(model.selected).changeBorderColor(model.darkgray.color);
                            model.notifyObservers();
                        }
                        model.lineColor = model.darkgray.color;
                        model.last.setBorder(BorderFactory.createRaisedBevelBorder());
                        model.darkgray.setBorder(BorderFactory.createLoweredBevelBorder());
                        model.last.repaint();
                        model.darkgray.repaint();
                        model.last = model.darkgray;
                    } else if (x > 67 && x < 97 && y > 67 && y < 97) {
                        if(model.selected != -1){
                            model.shapes.get(model.selected).changeBorderColor(model.black.color);
                            model.notifyObservers();
                        }
                        model.lineColor = model.black.color;
                        model.last.setBorder(BorderFactory.createRaisedBevelBorder());
                        model.black.setBorder(BorderFactory.createLoweredBevelBorder());
                        model.last.repaint();
                        model.black.repaint();
                        model.last = model.black;
                    }
                }else if(SwingUtilities.isRightMouseButton(e)) {
                    final JColorChooser colorChooser = new JColorChooser(Color.RED);
                    model.temp = colorChooser.getColor();

                    ActionListener okActionListener = new ActionListener() {
                        public void actionPerformed(ActionEvent actionEvent) {
                            model.temp = colorChooser.getColor();
                        }
                    };

                    // For cancel selection, change button background to red
                    ActionListener cancelActionListener = new ActionListener() {
                        public void actionPerformed(ActionEvent actionEvent) {
                            System.out.println("canclled");
                        }
                    };

                    final JDialog dialog = JColorChooser.createDialog(null, "Choose a Color", true,
                            colorChooser, okActionListener, cancelActionListener);

                    dialog.setVisible(true);
                    if (x > 3 && x < 33 && y > 3 && y < 33) {
                        model.red.color = model.temp;
                        model.red.repaint();
                    } else if (x > 35 && x < 65 && y > 3 && y < 33) {
                        model.orange.color = model.temp;
                        model.orange.repaint();
                    } else if (x > 67 && x < 97 && y > 3 && y < 33) {
                        model.yellow.color = model.temp;
                        model.yellow.repaint();
                    } else if (x > 3 && x < 33 && y > 35 && y < 65) {
                        model.green.color = model.temp;
                        model.green.repaint();
                    } else if (x > 35 && x < 65 && y > 35 && y < 65) {
                        model.cyan.color = model.temp;
                        model.cyan.repaint();
                    } else if (x > 67 && x < 97 && y > 35 && y < 65) {
                        model.blue.color = model.temp;
                        model.blue.repaint();
                    } else if (x > 3 && x < 33 && y > 67 && y < 97) {
                        model.lightgray.color = model.temp;
                        model.lightgray.repaint();
                    } else if (x > 35 && x < 65 && y > 67 && y < 97) {
                        model.darkgray.color = model.temp;
                        model.darkgray.repaint();
                    } else if (x > 67 && x < 97 && y > 67 && y < 97) {
                        model.black.color = model.temp;
                        model.black.repaint();
                    }
                }
            }
        });
    }
}
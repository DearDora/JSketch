import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.UIManager;
import java.awt.event.*;

class ToolPalette extends JPanel {

    private Model model;

    public ToolPalette (Model model) {

        ImageIcon toolIcon = new ImageIcon("icons/selectiontool.png");
        ImageIcon eraseIcon = new ImageIcon("icons/erasetool.png");
        ImageIcon lineIcon = new ImageIcon("icons/linetool.png");
        ImageIcon circleIcon = new ImageIcon("icons/circletool.png");
        ImageIcon rectangleIcon = new ImageIcon("icons/rectangletool.png");
        ImageIcon fillIcon = new ImageIcon("icons/filltool.png");

        this.setLayout(new GridLayout(3, 2));
        JButton selectionTool = new JButton();
        selectionTool.setIcon(resizeIcon(toolIcon,40,40));
        JButton eraseTool = new JButton();
        eraseTool.setIcon(resizeIcon(eraseIcon,40,40));
        JButton lineTool = new JButton();
        lineTool.setIcon(resizeIcon(lineIcon,40,40));
        JButton circleTool = new JButton();
        circleTool.setIcon(resizeIcon(circleIcon,40,40));
        JButton rectangleTool = new JButton();
        rectangleTool.setIcon(resizeIcon(rectangleIcon,40,40));
        JButton fillTool = new JButton();
        fillTool.setIcon(resizeIcon(fillIcon,40,40));

        this.add(selectionTool);
        this.add(eraseTool);
        this.add(lineTool);
        this.add(circleTool);
        this.add(rectangleTool);
        this.add(fillTool);

        validate();
        // set the model
        this.model = model;

        // anonymous class acts as model listener
        this.model.addObserver(new Observer() {
            public void update(Object observable) {
            }
        });

        selectionTool.setBorder(UIManager.getBorder("Button.borderPressed"));
        eraseTool.setBorder(UIManager.getBorder("Button.border"));
        lineTool.setBorder(UIManager.getBorder("Button.border"));
        circleTool.setBorder(UIManager.getBorder("Button.border"));
        rectangleTool.setBorder(UIManager.getBorder("Button.border"));
        fillTool.setBorder(UIManager.getBorder("Button.border"));

        selectionTool.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                selectionTool.setBorder(UIManager.getBorder("Button.borderPressed"));
                eraseTool.setBorder(UIManager.getBorder("Button.border"));
                lineTool.setBorder(UIManager.getBorder("Button.border"));
                circleTool.setBorder(UIManager.getBorder("Button.border"));
                rectangleTool.setBorder(UIManager.getBorder("Button.border"));
                fillTool.setBorder(UIManager.getBorder("Button.border"));
                model.currentCommand = Command.SELECTION;
            }
        });
        eraseTool.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(model.selected != -1) {
                    model.shapes.get(model.selected).changeBorderBack();
                    model.selected = -1;
                    model.notifyObservers();
                }
                eraseTool.setBorder(UIManager.getBorder("Button.borderPressed"));
                selectionTool.setBorder(UIManager.getBorder("Button.border"));
                lineTool.setBorder(UIManager.getBorder("Button.border"));
                circleTool.setBorder(UIManager.getBorder("Button.border"));
                rectangleTool.setBorder(UIManager.getBorder("Button.border"));
                fillTool.setBorder(UIManager.getBorder("Button.border"));
                model.currentCommand = Command.ERASE;
            }
        });
        lineTool.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(model.selected != -1) {
                    model.shapes.get(model.selected).changeBorderBack();
                    model.selected = -1;
                    model.notifyObservers();
                }
                lineTool.setBorder(UIManager.getBorder("Button.borderPressed"));
                eraseTool.setBorder(UIManager.getBorder("Button.border"));
                selectionTool.setBorder(UIManager.getBorder("Button.border"));
                circleTool.setBorder(UIManager.getBorder("Button.border"));
                rectangleTool.setBorder(UIManager.getBorder("Button.border"));
                fillTool.setBorder(UIManager.getBorder("Button.border"));
                model.currentCommand = Command.LINE;
            }
        });
        circleTool.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(model.selected != -1) {
                    model.shapes.get(model.selected).changeBorderBack();
                    model.selected = -1;
                    model.notifyObservers();
                }
                circleTool.setBorder(UIManager.getBorder("Button.borderPressed"));
                eraseTool.setBorder(UIManager.getBorder("Button.border"));
                lineTool.setBorder(UIManager.getBorder("Button.border"));
                selectionTool.setBorder(UIManager.getBorder("Button.border"));
                rectangleTool.setBorder(UIManager.getBorder("Button.border"));
                fillTool.setBorder(UIManager.getBorder("Button.border"));
                model.currentCommand = Command.CIRCLE;
            }
        });
        rectangleTool.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(model.selected != -1) {
                    model.shapes.get(model.selected).changeBorderBack();
                    model.selected = -1;
                    model.notifyObservers();
                }
                rectangleTool.setBorder(UIManager.getBorder("Button.borderPressed"));;
                eraseTool.setBorder(UIManager.getBorder("Button.border"));
                lineTool.setBorder(UIManager.getBorder("Button.border"));
                circleTool.setBorder(UIManager.getBorder("Button.border"));
                selectionTool.setBorder(UIManager.getBorder("Button.border"));
                fillTool.setBorder(UIManager.getBorder("Button.border"));
                model.currentCommand = Command.RECTANGLE;
            }
        });
        fillTool.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if(model.selected != -1) {
                    model.shapes.get(model.selected).changeBorderBack();
                    model.selected = -1;
                    model.notifyObservers();
                }
                fillTool.setBorder(UIManager.getBorder("Button.borderPressed"));;
                eraseTool.setBorder(UIManager.getBorder("Button.border"));
                lineTool.setBorder(UIManager.getBorder("Button.border"));
                circleTool.setBorder(UIManager.getBorder("Button.border"));
                rectangleTool.setBorder(UIManager.getBorder("Button.border"));
                selectionTool.setBorder(UIManager.getBorder("Button.border"));
                model.currentCommand = Command.PAINT;
            }
        });
    }
    private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}

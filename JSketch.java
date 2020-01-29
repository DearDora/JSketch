import javax.swing.JMenuBar;
import javax.swing.*;
import java.awt.Color;
import java.util.*;
import java.awt.Dimension;
import java.awt.event.*;
import java.awt.*;
import java.awt.BorderLayout;

public class JSketch {

    Model model;

    public static void main(String[] args) { new JSketch(); }

    public JSketch(){

        JFrame frame = new JFrame("Assignment 2: JSketch");

        model = new Model();

        JPanel mainPanel = new JPanel();
        mainPanel.setBackground(Color.DARK_GRAY);

        ToolView tools = new ToolView(model);
        SketchView canvas = new SketchView(model);

        SpringLayout layout = new SpringLayout();
        mainPanel.setLayout(layout);

        MainMenuView menuView = new MainMenuView(model);

        frame.getContentPane().add(mainPanel);
        mainPanel.add(tools);
        mainPanel.add(canvas);
        tools.setPreferredSize(new Dimension(120,480));

        layout.putConstraint(SpringLayout.WEST, tools, 0,
                SpringLayout.WEST, mainPanel);

        layout.putConstraint(SpringLayout.NORTH, tools, 0,
                SpringLayout.NORTH, mainPanel);

        canvas.setMaximumSize(new Dimension(Integer.MAX_VALUE,
                Integer.MAX_VALUE));

        layout.putConstraint(SpringLayout.WEST, canvas, 0,
                SpringLayout.EAST, tools);
        layout.putConstraint(SpringLayout.NORTH, canvas, 10,
                SpringLayout.NORTH, mainPanel);
        layout.putConstraint(SpringLayout.SOUTH, canvas, -10,
                SpringLayout.SOUTH, mainPanel);
        layout.putConstraint(SpringLayout.EAST, canvas, -10,
                SpringLayout.EAST, mainPanel);


        frame.setJMenuBar(menuView);
        frame.setMinimumSize(new Dimension(640, 480));
        frame.setSize(800, 600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
        model.notifyObservers();
    }
}

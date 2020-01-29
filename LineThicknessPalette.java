import javax.swing.*;
import java.awt.Color;
import javax.swing.ImageIcon;
import java.awt.Image;
import java.awt.event.*;
import java.util.*;
import java.awt.GridLayout;
import javax.swing.border.LineBorder;

class LineThicknessPalette extends JPanel {

    // the model that this view is showing
    private Model model;
    private JLabel label = new JLabel();

    LineThicknessPalette(Model model) {
        ImageIcon smallIcon = new ImageIcon("icons/smallLine.png");
        ImageIcon mediumIcon = new ImageIcon("icons/mediumLine.png");
        ImageIcon largeIcon = new ImageIcon("icons/largeLine.png");
        // create the view UI
        this.setLayout(new GridLayout(3, 1));
        JButton small = new JButton();
        small.setIcon(resizeIcon(smallIcon,90,30));
        JButton medium = new JButton();
        medium.setIcon(resizeIcon(mediumIcon,90,30));
        JButton large = new JButton();
        large.setIcon(resizeIcon(largeIcon,90,30));

        this.add(small);
        this.add(medium);
        this.add(large);
        validate();
        // set the model
        this.model = model;

        // anonymous class acts as model listener
        this.model.addObserver(new Observer() {
            public void update(Object observable) {
                if(model.lineThickness == 2){
                    small.setBorder(new LineBorder(Color.RED));
                    medium.setBorder(UIManager.getBorder("Button.border"));
                    large.setBorder(UIManager.getBorder("Button.border"));
                }else if(model.lineThickness == 5){
                    medium.setBorder(new LineBorder(Color.RED));
                    small.setBorder(UIManager.getBorder("Button.border"));
                    large.setBorder(UIManager.getBorder("Button.border"));
                }else if(model.lineThickness == 8){
                    large.setBorder(new LineBorder(Color.RED));
                    small.setBorder(UIManager.getBorder("Button.border"));
                    medium.setBorder(UIManager.getBorder("Button.border"));
                }
            }
        });

        // setup the event to go to the "controller"
        // (this anonymous class is essentially the controller)
        small.setBorder(new LineBorder(Color.RED));
        medium.setBorder(UIManager.getBorder("Button.border"));
        large.setBorder(UIManager.getBorder("Button.border"));

        small.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                small.setBorder(new LineBorder(Color.RED));
                medium.setBorder(UIManager.getBorder("Button.border"));
                large.setBorder(UIManager.getBorder("Button.border"));
                model.lineThickness = 2;
            }
        });
        medium.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                medium.setBorder(new LineBorder(Color.RED));
                small.setBorder(UIManager.getBorder("Button.border"));
                large.setBorder(UIManager.getBorder("Button.border"));
                model.lineThickness = 5;
            }
        });
        large.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                large.setBorder(new LineBorder(Color.RED));
                small.setBorder(UIManager.getBorder("Button.border"));
                medium.setBorder(UIManager.getBorder("Button.border"));
                model.lineThickness = 8;
            }
        });
    }
    private static Icon resizeIcon(ImageIcon icon, int resizedWidth, int resizedHeight) {
        Image img = icon.getImage();
        Image resizedImage = img.getScaledInstance(resizedWidth, resizedHeight,  java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImage);
    }
}
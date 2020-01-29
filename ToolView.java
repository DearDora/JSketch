import java.io.*;
import java.util.*;
import java.awt.*;
import javax.swing.*;

public class ToolView extends JPanel implements Observer {

    private Model model;
    // Used In-Class sample code from Layout file
    public ToolView (Model model) {
        this.model = model;
        model.addObserver(this);

        ToolPalette toolPalette = new ToolPalette(model);
        ColorPalette colorPalette = new ColorPalette(model);
        LineThicknessPalette lineThicknessPalette = new LineThicknessPalette(model);
        this.setBackground(Color.DARK_GRAY);

        SpringLayout layout = new SpringLayout();
        this.setLayout(layout);

        this.add(toolPalette);
        this.add(colorPalette);
        this.add(lineThicknessPalette);

        // Set Palette size
        toolPalette.setPreferredSize(new Dimension(100,150));
        colorPalette.setPreferredSize(new Dimension(100,150));
        lineThicknessPalette.setSize(new Dimension(100,60));

        // Constrain toolPalette
        layout.putConstraint(SpringLayout.WEST, toolPalette, 10,
                SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, toolPalette, 10,
                SpringLayout.NORTH, this);

        // Constrain colorPalette
        layout.putConstraint(SpringLayout.WEST, colorPalette, 10,
                SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, colorPalette, 170,
                SpringLayout.NORTH, this);

        // constrain linePalette
        layout.putConstraint(SpringLayout.WEST, lineThicknessPalette, 10,
                SpringLayout.WEST, this);
        layout.putConstraint(SpringLayout.NORTH, lineThicknessPalette, 330,
                SpringLayout.NORTH, this);

        layout.putConstraint(SpringLayout.EAST, colorPalette, 0,
                SpringLayout.EAST, toolPalette);
        layout.putConstraint(SpringLayout.EAST, lineThicknessPalette, 0,
                SpringLayout.EAST, toolPalette);

        // Add constraints to sketchPalette
        lineThicknessPalette.setMaximumSize(new Dimension(Integer.MAX_VALUE,
                Integer.MAX_VALUE));

        this.model.addObserver(new Observer() {
            public void update(Object observable) {
            }
        });
    }

    /**
     * Update with data from the model.
     */
    public void update(Object observable) {

    }

}

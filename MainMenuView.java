import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.Observable;
import java.util.Observer;
import java.awt.*;
import java.awt.Shape;
import java.io.*;
import java.util.*;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.KeyStroke;


// a main menu view
public class MainMenuView extends JMenuBar{

    private JMenuItem menuNew;
    private JMenuItem menuLoad;
    private JMenuItem menuSave;
    private JMenuItem menuExit;
    private JMenuItem menuFitToWindow;
    private JMenuItem menuFullSize;

    int x1;
    int y1;
    int x2;
    int y2;
    int r;
    Color bcolor;
    Color icolor;
    int width;

    private Model model;

    public MainMenuView(Model model_) {

        model = model_;


        JMenu fileIcon = new JMenu("File");
        fileIcon.setMnemonic(KeyEvent.VK_F);
        JMenu viewIcon = new JMenu("View");
        viewIcon.setMnemonic(KeyEvent.VK_F);

        menuNew = new JMenuItem("New");
        menuNew.setMnemonic(KeyEvent.VK_E);
        menuNew.setToolTipText("Create a new file");
        menuNew.addActionListener((ActionEvent event) -> {
            model.shapes.clear();
            model.notifyObservers();
        });

        menuLoad = new JMenuItem("Load");
        menuLoad.setMnemonic(KeyEvent.VK_E);
        menuLoad.setToolTipText("Load file");
        menuLoad.addActionListener((ActionEvent event) -> {
            model.shapes.clear();
            try {
                FileInputStream fstream = new FileInputStream("save.txt");
                Reader re = new InputStreamReader(fstream, StandardCharsets.UTF_8);
                BufferedReader br = new BufferedReader(re);
                String line;
                while ((line = br.readLine()) != null) {
                    if(line.length() == 4){
                        line = br.readLine();
                        x1 = Integer.valueOf(line);
                        line = br.readLine();
                        y1 = Integer.valueOf(line);
                        line = br.readLine();
                        x2 = Integer.valueOf(line);
                        line = br.readLine();
                        y2 = Integer.valueOf(line);
                        line = br.readLine();
                        bcolor = new Color(Integer.parseInt(line));
                        line = br.readLine();
                        width = Integer.valueOf(line);
                        Line l = new Line(x1, y1, x2, y2, bcolor,width);
                        model.shapes.add(l);
                    }else if(line.length() == 6){
                        line = br.readLine();
                        x1 = Integer.valueOf(line);
                        line = br.readLine();
                        y1 = Integer.valueOf(line);
                        line = br.readLine();
                        r = Integer.valueOf(line);
                        line = br.readLine();
                        bcolor = new Color(Integer.parseInt(line));
                        line = br.readLine();
                        icolor = new Color(Integer.parseInt(line));
                        line = br.readLine();
                        width = Integer.valueOf(line);
                        Circle c = new Circle (x1, y1, r,bcolor, icolor,width);
                        model.shapes.add(c);
                    }else if(line.length() == 9){
                        line = br.readLine();
                        x1 = Integer.valueOf(line);
                        line = br.readLine();
                        y1 = Integer.valueOf(line);
                        line = br.readLine();
                        x2 = Integer.valueOf(line);
                        line = br.readLine();
                        y2 = Integer.valueOf(line);
                        line = br.readLine();
                        bcolor = new Color(Integer.parseInt(line));
                        line = br.readLine();
                        icolor = new Color(Integer.parseInt(line));
                        line = br.readLine();
                        width = Integer.valueOf(line);
                        Rectangle rect = new Rectangle(x1, y1, x2, y2,
                                bcolor,icolor,width);
                        model.shapes.add(rect);
                    }
                }
                br.close();
            }
            catch (IOException ex) {
                System.out.println("Load failed");
            }
            model.notifyObservers();
        });

        menuSave = new JMenuItem("Save");
        menuSave.setMnemonic(KeyEvent.VK_E);
        menuSave.setToolTipText("Save current file");
        menuSave.addActionListener((ActionEvent event) -> {
            try (Writer writer = new BufferedWriter(new OutputStreamWriter
                    (new FileOutputStream("save.txt"), StandardCharsets.UTF_8))) {
                for(int i=0; i<model.shapes.size(); ++i){
                    if (model.shapes.get(i).getType() == "line") {
                        writer.write("line\n");
                        writer.write(String.valueOf(model.shapes.get(i).getX1()));
                        writer.write("\n");
                        writer.write(String.valueOf(model.shapes.get(i).getY1()));
                        writer.write("\n");
                        writer.write(String.valueOf(model.shapes.get(i).getX2()));
                        writer.write("\n");
                        writer.write(String.valueOf(model.shapes.get(i).getY2()));
                        writer.write("\n");
                        writer.write(Integer.toString(model.shapes.get(i).getColor().getRGB()));
                        writer.write("\n");
                        writer.write(String.valueOf(model.shapes.get(i).getThickness()));
                        writer.write("\n");
                    }else if (model.shapes.get(i).getType() == "circle"){
                        writer.write("circle\n");
                        writer.write(String.valueOf(model.shapes.get(i).getX1()));
                        writer.write("\n");
                        writer.write(String.valueOf(model.shapes.get(i).getY1()));
                        writer.write("\n");
                        writer.write(String.valueOf(model.shapes.get(i).getX2()));
                        writer.write("\n");
                        writer.write(Integer.toString(model.shapes.get(i).getColor().getRGB()));
                        writer.write("\n");
                        writer.write(Integer.toString(model.shapes.get(i).getInnerColor().getRGB()));
                        writer.write("\n");
                        writer.write(String.valueOf(model.shapes.get(i).getThickness()));
                        writer.write("\n");
                    }else if (model.shapes.get(i).getType() == "rectangle"){
                        writer.write("rectangle\n");
                        writer.write(String.valueOf(model.shapes.get(i).getX1()));
                        writer.write("\n");
                        writer.write(String.valueOf(model.shapes.get(i).getY1()));
                        writer.write("\n");
                        writer.write(String.valueOf(model.shapes.get(i).getX2()));
                        writer.write("\n");
                        writer.write(String.valueOf(model.shapes.get(i).getY2()));
                        writer.write("\n");
                        writer.write(Integer.toString(model.shapes.get(i).getColor().getRGB()));
                        writer.write("\n");
                        writer.write(Integer.toString(model.shapes.get(i).getInnerColor().getRGB()));
                        writer.write("\n");
                        writer.write(String.valueOf(model.shapes.get(i).getThickness()));
                        writer.write("\n");
                    }
                }
            }
            catch (IOException ex) {
                System.out.println("Save failed");
            }
        });

        menuExit = new JMenuItem("Exit");
        menuExit.setMnemonic(KeyEvent.VK_E);
        menuExit.setToolTipText("Exit application");
        menuExit.addActionListener((ActionEvent event) -> {
            System.exit(0);
        });

        menuFitToWindow = new JMenuItem("Fit to Window");
        menuFitToWindow.setMnemonic(KeyEvent.VK_E);
        menuFitToWindow.setToolTipText("Image resizes to fit the window");
        menuFitToWindow.addActionListener((ActionEvent event) -> {

        });

        menuFullSize = new JMenuItem("Full Size");
        menuFullSize.setMnemonic(KeyEvent.VK_E);
        menuFullSize.setToolTipText("Get full size of image");
        menuFullSize.addActionListener((ActionEvent event) -> {
        });

        fileIcon.add(menuNew);
        fileIcon.add(menuLoad);
        fileIcon.add(menuSave);
        fileIcon.add(menuExit);
        viewIcon.add(menuFullSize);
        viewIcon.add(menuFitToWindow);

        this.add(fileIcon);
        this.add(viewIcon);
    }
}
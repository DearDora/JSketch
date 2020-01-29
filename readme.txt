INSTRUCTION:
The main area of the drawing program is a canvas that supports drawing shapes 
from the tool palette. Users can draw by selecting a shape from the list on the 
side, then clicking and dragging the mouse on the canvas to draw that shape. A 
shape "preview" is shown as the mouse is dragged, and the shape is complete when
the mouse button is released (i.e. the first click sets the starting position, 
and the drag operation sets the width of the circle, or creates a line, 
depending on the shape being drawn, and the shape is complete when the mouse 
button is released).

- Menu Bar
  -File:
    -New: create a new drawing.
    -Load: load a drawing from save.txt
    -Save: save the current drawing
    -Exit: exit the program without saving
  -View:
    -Full Size: if this option is selected, the canvas and its contents remain 
                the same size as you resize the window, and scrollbars appear to
                let you scroll the image 
    -Fit to Window: if this option is selected, the canvas and its contents 
                    resize to fit the window as the window is resized

- Tool Palette
  - A selection tool:this allows the user to select a shape that has been drawn.
                     There would be a specail pressed border which button is 
                     selected. 
                     To select a shape, the user should click this tool, then 
                     DOUBLE click on an existing shape. 
                     Pressing ESC on the keyboard will clear shape selection. 
                     Selecting a shape will cause the color palette and line 
                     thickness palette to update their state to reflect the 
                     currently selected shape.
                     Changing color or line thickness when a shape is selected 
                     will update the shape properties to the new values.
  - An erase tool:click on this tool and then click on a shape to delete.
  - A line drawing tool:  select this to draw a line.
  - A circle drawing tool: select this to draw an unfilled circle at the point 
                          indicated
  - A rectangle tool: select this to draw an unfilled rectangle
  - A fill tool: select this tool, and DOUBLE click on a shape to fill that 
                shape with the currently selected color
- Color Palette
  - a graphical display of 8 colors, user can select it by DOUBLE click it
  - the user can click on the "Chooser" button to bring up a color chooser 
    dialog to select a color
  - Choosing a color will set the current drawing color, which will be used for 
    any new shapes that are drawn.
  - If a shape is already selected when a color is chosen, the shape's border 
    will change to that new color
- Line Thickness Palette
  - a graphical display of 3 line widths that the user can select.
  - Selecting a line width will set the border thickness for any new shapes.
  - Selecting a shape will change the border thickness to reflect the line 
    thickness of that shape.

- Support:
  - The user are able to move a selected shape around the screen by selecting it
    , and dragging it with the mouse.
  - The interface is clearly indicate which tool, color and line thickness are 
    selected at any time. It is also be obvious which shape is selected.
  - The interface would enable/disable controls if appropriate.
  - The application supports dynamic resizing of the application window, so that
    the application adapts to different window sizes and resolutions. The tool 
    palettes should expand and contract based upon available space; how the 
    layout changes is a visual design decision left to you. The canvas won't 
    change size, but will display scrollbars if the user resizes the window 
    smaller than the canvas (otherwise the scrollbars should remain hidden).

- Technical Achievements:
  - The assignment is programmed in Java 10.0.2, using Swing components.
  - The starting appication window is no larger than 1600x1200. And it supports 
    a wide enough range of window sizes to demonstrate your dynamic layout.
  - The layout is dynamic and designed appropriately to support scaling the 
    application, as described above.
  - MVC architecture is used for the application. 
    There is one model, and two views (ToolView and SketchView).
  - Appropriate widgets are selected and used for the interface. 
    Options are appropriately enabled/disabled in the UI.
  - Copyright allowed images source: 
    - selectiontool.png: 
      https://thenounproject.com/term/path-selection-tool/765268/
    - erasetool.png:
      https://pngtree.com/free-icon/eraser_1320087
    - filltool.png: 
      https://icons8.com/icon/11493/fill-color
  - Provided a comment in the code indicating the source of the code in class.
  
- Additional Features:
  - Scale shapes: the ability to change the scale/size of any shape by selecting
                  it, then grabbing a corner of the shape, and dragging to 
                  increase/decrease it's size 
  - Customizable color palette: you can wholly or partially customize color 
                  buttons in the palette (right-click a button and choose a new
                  color for that button from a color-chooser dialog).

- Bonus Fetures: (as described above)
    
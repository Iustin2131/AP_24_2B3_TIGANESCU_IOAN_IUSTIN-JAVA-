  Graphical User Interfaces (Swing, JavaFX)
Consider a positional game played on a grid-shaped board. A grid is a two-dimensional structure of intersecting lines, the lines are evenly spaced, intersecting at right angles.
  At the beginning of the game, there will be randomly placed small line-shaped tokens (sticks) that connect two adjacent intersections of the grid, along a line of the grid.
An intersection that is adjacent with at least one stick, is called a node.
  The first player selects any node of the grid and places a circular-shaped token (stone) on it. Next, the players must alternatively choose a new unselected node that is adjacent (is connected by a stick) to the previously selected one and place a stone on it. They use stones of different colors. The player who cannot choose another node, loses the game.
  In order to create a graphical user interface for the game, you may use either Swing or JavaFX. 

   Homework:
  Create the object oriented model.
  Initialize the game by generating random sticks and place them on the board. Implement either direct or retained mode for drawing the game board.
   Implement the logic of the game. When the player execute a mouse pressed operation, a stone must be drawn at the mouse location: red or blue depending on whose turn it is. Validate the move, according to the game rules. Determine the winner of the game.
   Export the current image of the game board into a PNG file.
   Use object serialization in order to save and restore the current status of the game. 

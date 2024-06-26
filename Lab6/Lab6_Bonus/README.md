# Graphical User Interfaces (Swing, JavaFX)
Consider a positional game played on a grid-shaped board. A grid is a two-dimensional structure of intersecting lines, the lines are evenly spaced, intersecting at right angles.
  At the beginning of the game, there will be randomly placed small line-shaped tokens (sticks) that connect two adjacent intersections of the grid, along a line of the grid.
An intersection that is adjacent with at least one stick, is called a node.
  The first player selects any node of the grid and places a circular-shaped token (stone) on it. Next, the players must alternatively choose a new unselected node that is adjacent (is connected by a stick) to the previously selected one and place a stone on it. They use stones of different colors. The player who cannot choose another node, loses the game.
  In order to create a graphical user interface for the game, you may use either Swing or JavaFX. 

 #  Bonus :
 #### Prove that the player who starts the game has always a winning strategy if and only if the corresponding graph does not have a perfect matching.
 #### Based on the above observation, implement an AI for the game. 
 
![screenshot1](Screenshot%202024-04-11%20120740.png)
![screenshot2](Screenshot%202024-04-11%20120821.png)
![screenshot3](Screenshot%202024-04-11%20120842.png)
![screenshot4](Screenshot%202024-04-11%20120924.png)


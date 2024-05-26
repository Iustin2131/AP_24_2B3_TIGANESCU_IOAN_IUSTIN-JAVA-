#  Networking
Create an implementation of the Battleship game, that allows remote players to start or join games. The application will contain two parts (create a project for each one):
    The server is responsible with the game management and mediating the players.
    The client will communicate with the server, sending it commands such as:
        create or join a game,
        submit a move, etc. 
# Homework 
  ####  Create the OOP model and implement functionalities of the game.
  ####  The clients will send to the server commands such as: create game, join game, submit move, etc.
  ####  The server is responsible with the game management and mediating the players.
  ####  The games will be played under time control (blitz) with each player having the same amount of time at the beginning of the game. If a player's time runs out, the game is lost. 

![screenshot1](1.png)

  ###   A player can create a new game by specifying an ID (create <gameId>).
  ###   A player can join an existing game with an ID and a name (join <gameId> <player>).
  ###   Upon joining, the player receives an initialized game grid.
  ###   A player can leave the game at any time (leave <gameId> <player>), which may end the game.
  ###   The player can make moves to hit targets on the opponent's grid (submit <gameId> <player> <target>).
  ###   Each player has a limited time; if the time runs out, the game ends and the player loses.
  # Game Instructions

## Overview
This game allows players to engage in a turn-based grid game with time control. Each player has a limited amount of time to make their moves. If a player's time runs out, the game is lost.

## Commands
Here are the commands that players can use to interact with the game:

1. **Create a Game**
   - Command: `create <gameId>`
   - Description: A player can create a new game by specifying a unique game ID.

2. **Join a Game**
   - Command: `join <gameId> <player>`
   - Description: A player can join an existing game using the game ID and their player name. Upon joining, the player receives an initialized game grid.

3. **Leave a Game**
   - Command: `leave <gameId> <player>`
   - Description: A player can leave the game at any time using the game ID and their player name. If the game is in progress, leaving may end the game and declare the opponent as the winner.

4. **Submit a Move**
   - Command: `submit <gameId> <player> <target>`
   - Description: The player can make moves to hit targets on the opponent's grid by specifying the game ID, their player name, and the target coordinates.

5. **Stop the Game**
   - Command: `stop`
   - Description: A player can stop their client connection.

6. **Stop the Server**
   - Command: `stopServer`
   - Description: A command to stop the server. Only available to the server administrator.

## Time Control
- Each player has a limited amount of time to play (e.g., 5 minutes).
- If a player's time runs out, the game automatically ends, and the player loses.
- The remaining time is reset or adjusted after each move to ensure fair play.


  #  Networking
Create an implementation of the Battleship game, that allows remote players to start or join games. The application will contain two parts (create a project for each one):
    The server is responsible with the game management and mediating the players.
    The client will communicate with the server, sending it commands such as:
        create or join a game,
        submit a move, etc. 

# Bonus 

  ####   Implement a feature for organizing tournaments.
  #### Assuming there are n players registered on the server, create a schedule such that:

  ####  each player will play with every other player exactly once (the first player to move is chosen randomly);
  ##### a player can not have more than p games in a day;
  ##### the tournament must finish in at most d days. 

  ####  Once the schedule is created, generate random outcomes for all games (there are no draws).
  ##### Find the sequence of players p1,p2,...,pn such that pi beats pi+1, for all i=1,n-1. 

![screenshot1](1.png)

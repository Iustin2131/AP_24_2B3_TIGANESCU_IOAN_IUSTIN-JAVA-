
  # Concurrency
Write a program that simulates the following game between a given number of players.
At the beginning of the game there is a bag containing a number of tokens, each token being a distinct pair of numbers between 1 and n.
Each player extracts tokens successively from the bag and must create with them closed sequences of the form t1=(i1,i2), t2=(i2,i3),...,tk=(ik,i1), where ti are tokens and i1,i2,i3,...,ik are distinct numbers.
The value of a sequence is given by the number of its tokens.
The game ends when all tokens have been removed from the bag or when a player makes a sequence of length n. Each player receives a number of points equal to the value of its largest sequence.
The players might take turns (or not...) and a time limit might be imposed (or not...).

 # Bonus (2p)

  ####  Implement a "smart" player. This should try to create the optimum sequence using its tiles, in some special cases.
  ####  Verify if the graph satisfies the Ore's condition and implement, using Graph4J API, a polynomial time algorithm for determining a hamiltonian cycle in graphs that meet Ore's condition.
  ##  You may want to read The Hidden Algorithm of Ore’s Theorem on Hamiltonian Cycles. 

![screenshot_1](1.png)

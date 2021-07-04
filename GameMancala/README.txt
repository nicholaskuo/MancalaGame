=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=
CIS 120 Game Project README
PennKey: nickkuo
=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=:=

===================
=: Core Concepts :=
===================

- List the four core concepts, the features they implement, and why each feature
  is an appropriate use of the concept. Incorporate the feedback you got after
  submitting your proposal.

  1. Modeling state using 2-D arrays: The Mancala board's main game state is stored in a
  2-D array. Specifically, the 2 rows of 6 marble pits are represented by the 2 by 6
  2-D array, where each index is an integer representing the number of marbles in
  each respective pit. It was easy when using the 2D array to know when to increment
  the player scores, because when the array traversal arrived at the end of its row,
  then the respective player's score would be increased sicne it would mark that a 
  marble had entered the score pit. Since in Mancala every pit has a specific number of 
  marbles, the MancalaBoard class contains this 2-D array along with methods such as 
  playTurn and checkWinner, which manipulates the 2-D array by changing the integers
  of each counterclockwise index and increments player 2 score if row 1 is fully
  traversed and increments player 1 score if row 2 is done being traversed. The 2D
  array allows for each access to specific index values to retrieve marble amounts,
  as well for checking for the free turns and capture aspect of Mancala. Checking for
  marbles between different columns was easy due to the structure of a 2D array. The 
  grid-like features of the Mancala board, as well as the integer value each pit stores, makes
  a 2D array the most efficient concept to use. Also, the Mancala board size does not
  change, so a set 2D array was okay to use. 

  2. Modeling state using collections: There are a total of three classes (ScorePit,
  ScorePitBottom, and MarblePit) that each extend JPanel and contain a LinkedList of 
  Marble objects. The Marble object extends GameObj, whether the Marble contains 
  a color, a size, and uses the super call in order to specify its x,y coordinates 
  within each JPanel. The reason for the use of LinkedList is the GUI Mancala board
  consists of 14 JPanels, 12 of which are marble pits and 4 of which represent the side
  score pits. Whenever someone presses a panel to distribute marbles, that specific pit
  must visibly remove all its marbles (that are basically ovals painted through repaint) 
  and distribute the marbles into other panel's location. For every marble stored in
  the LinkedList would basically repaint itself in the same location as before, and
  for every added marble to a marble pit panel, the marble pit class would randomly
  generate an x and y coordinate in the panel and then add a new Marble object to that
  panel's LinkedList. By using this LinkedList of marbles, I would dynamically add/remove
  marbles from the list in order to give the effect that marbles were being distributed
  into new marble pits while old marbles stayed in its original positions. I think 
  LinkedLists are needed in order to keep track of all the marbles' locations within
  each panel, especially because whenever I had to remove a marble, it was easy to
  simply use the .remove command to remove the most "old" marble within the marble
  pit. The LinkedList provides flexibility in terms of changing its size while also
  simulating the idea that the oldest marble is distributed first. 

  3. File I/O: My Mancala game has a save button that writes out the current game state
  of the Mancala board out to a text file. This includes the current player turn, each
  player's score, and the number of marbles in each of the 12 marble pits. Then, I also
  have a load button that restores the most recently saved game. File I/O is appropriate
  for the saving and loading of the game because the saving between games requires the
  data of the game state to be stored somewhere and then later read from that same place.
  By writing each data point to a text file, even after exiting the game, the data is
  still saved. Then, when pressing load previous game, the data can easily be read from
  the text file, updating the game state and GUI to the previously saved spot. 

  4. JUnit Testing: There are three main components to the design of my Mancala game:
  the Game class which helps to organize the basic layout of the GUI, the GameCourt
  which updates the GUI to reflect the actual numerical state of the game, and the
  MancalaBoard where the 2D array is located and all the underlying math and numerical
  changes between turns occur. Using this design of having a separate MancalaBoard class,
  I aimed to create an encapsulated model class that functions independently of the GUI.
  There are many methods in the MancalaBoard that manipulates the 2D array, which
  makes my design JUnit testable. By simply creating tests for the MancalaBoard class,
  along with some tests with the Score pit classes, I can test all the underlying
  calculations that the GUI simply reflects onto the screen. Therefore, testing is
  appropriate in order to check that these underlying methods are implemented correctly.

=========================
=: Your Implementation :=
=========================

- Provide an overview of each of the classes in your code, and what their
  function is in the overall game.
  
  The Game class simply creates a GameCourt object, JLabels, redo/instructions buttons,
  and other components that will be displayed in the GUI for the user to interact with.
  The GameCourt class is where the MancalaBoard class and the Game class interact.
  The GameCourt creates an instance of the MancalaBoard class and contains methods
  for mouse pressing of JPanels, as well as methods to update the status of JLabels
  and Marble pit classes (explained later). The purpose of the GameCourt, other than
  to display the image of the wooden table, was to call methods in the MancalaBoard
  class and then re-analyze the changes in the MancalaBoard's 2D array in order to
  reflect the state in the GUI. The MancalaBoard's function was to model the changes
  in integer values of marbles in each pit. This class would store the 2D array. The
  MarblePit, ScorePit, and ScorePitBotton classes are quite similar, where the first
  class is for the 12 JPanel marble pits that dynamically change their display of
  marbles. The ScorePit and ScorePitBottom are for storing the marbles for each
  player's score, and then they display them to the screen appropriately. Each of these
  three classes incorporate a List of Marble objects. The Marble class extends the 
  GameObj class, where the Marble class contains information about color and the 
  GameObj stores the location of the x-y coordinates of a marble. The Marble class
  was used to physically draw the oval-blue marbles onto the GUI during each repaint.


- Were there any significant stumbling blocks while you were implementing your
  game (related to your design, or otherwise)?
  
  Yes, some of the stumbling blocks included how I would create a design that
  properly registered the player's mouse clicks and then changed all the marble
  pits GUI displays accordingly. I had to take time to understand how to properly
  encapsulate my model so that the MancalaBoard class functions separately than my
  Game and GameCourt. The planning behind this game took a lot of time, especially
  since it was difficult to manipulate a game state and then have the GUI properly
  reflect the marbles without any bugs (like marbles generating new coordinates
  each round or being painted out of bounds). A specific roadblock I encountered
  was the fact that the marbles would randomly generate itself in a new location each
  round in the GUI. I soon recognized that storing the marbles' location in a LinkedList
  could solve my design problem. 

- Evaluate your design. Is there a good separation of functionality? How well is
  private state encapsulated? What would you refactor, if given the chance?
  
  I believe there is a good separation of functionality. I made sure specifically
  for my 2D array in my MancalaBoard to have it declared as private and not pass the
  array to other classes. Rather, I created public methods to interact between classes
  in order to ensure that the 2D array modeling the state of the game could not be directly
  changed. Similarly, I used the same idea for the encapsulation of my collection LinkedList of
  marbles. While the GameCourt is able to use a method in the MarblePit class to set the 
  number of marbles (which is a private variable in MarblePit), there is no way to directly
  access the LinkedList. I feel the main components of my game state and code to display
  the marbles are properly encapsulated. By breaking my design into three main components as
  described above, my private state functions separately compared to the other classes. If
  given the chance, I would probably refactor the many times I individually set each JLabel
  through hard code. Perhaps there was an opportunity to use a List of marble pits in order
  to make the updating hard code a bit more short. Likewise, I think maybe some longer pieces
  of code in my game state MancalaBoard class could have been broken a little bit more into methods 
  for readability purposes. But overall, I did try to refactor as much as I could to separate
  most of the functionality into separate methods.


========================
=: External Resources :=
========================

- Cite any external resources (libraries, images, tutorials, etc.) that you may
  have used while implementing your game.
  
  Inspiration for game (plus screen-shotted pits for my game)
  https://mancala.playdrift.com/
  
  Image for wood table:
  https://www.thirteenchefs.com/collections/villa-acacia/products/copy-of-villa-acacia-wood-bath-stool

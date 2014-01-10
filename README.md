CHESS
=====

		     _
		    / \
		    | |
		    | |
		   /   \
		  |     |
		  .......
		  
This game of chess is simply a classic game of chess.
It is programmed in java core in less than 1000 lines of source code.

List of java packages used-->
-----------------------------
	1) java.awt
	2) java.awt.image
	3) java.io
	4) javax.imageio
	5) javax.swing
	6) java.util
	7) java.util.concurrent

Class Hierarchy:-
-----------------
								     std.ankur.game(package)
								    /          |  	    \
								   /	       |	     \
								  /	       |	      \	
								 /	       |	       \
	  				----std.ankur.game.gui(package)	   Chess(Class)	       std.ankur.game.movements(package)
	  				|							| 													
			       	        |--> ChessGUI_Main					|--> MovementListener
					|		
					|--> StartGame
					|		
					|--> VarientLabel
					|		
					|--> BoxPanels

Screenshots
-----------

"""""""""""""""""  
Start Game
"""""""""""""""""  

.. figure:: http://i.imgur.com/VXULVrO.png
    :align: center
    
    
""""""""""""""""""""    
Movement of a pawn
""""""""""""""""""""

.. figure:: http://i.imgur.com/8pnUvWr.png
    :align: center
   

"""""""""""""""""""
Kill by Knight
"""""""""""""""""""

.. figure:: http://i.imgur.com/25Vmxfo.png
    :align: center

	
"""""""""""""""""""""""""
Possible moves of Queen
"""""""""""""""""""""""""

.. figure:: http://i.imgur.com/tJQFkC7.png
    :align: center

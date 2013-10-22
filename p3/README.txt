Running the .jar:
navigate terminal to the containing folder
run the command: 
java -jar TicTacToe.jar
this will run thoughtful vs naive

if you want to run a different test just put two arguments at the end of the command
put t for the thoughtful agent
put n for the naive agent
put h for a human player
the order matters, the first one is player one and the second is player two

ex: running naive vs thoughtful
java -jar TicTacToe.jar n t

ex2: running human vs thoughtful
java -jar TicTacToe.jar h t

you can also compile and run the code without the jar by using the following commands
javac *.java
java TicTacToe

it takes the same style of command line arguments as the .jar

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

To ask the agents questions you just type in your answer to the questions:

Which player do you want to ask a question?
to answer this put either 1 or 2 for player 1 or player 2 

Which turn are you interested in?
to answer this put the number of the turn you are interested in
the turn number is listed after the player number at the beginning of each turn

What type of question do you want to ask?
to answer this put one of: wrong, which, why
wrong: "What did you do wrong this turn?"
which: "Which position did you select?"
why: "Why did you select the move you did?"

Would you like to ask another question?
to answer this put either yes or no













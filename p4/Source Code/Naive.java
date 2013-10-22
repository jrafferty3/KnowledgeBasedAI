import java.io.*;
import java.util.Scanner;  
import java.util.Random;
import java.util.ArrayList;

public class Naive implements Player{
    private int playerNumber;
    private int otherNumber;
    private boolean testing;
    private Strategy strat;
    public Naive(int number){
	playerNumber = number;
	if(number == 1){
	    otherNumber = 2;
	}else{
	    otherNumber = 1;
	}
	testing = false;
	strat = new Strategy(playerNumber,otherNumber);
    }
	
    public Naive(int number, String s){
	playerNumber = number;
	if(number == 1){
	    otherNumber = 2;
	}else{
	    otherNumber = 1;
	}
	testing = true;
    }
	
    public int getMove(int[] board, ArrayList<String> trace) throws IOException{
	Random win_rand = new Random();
	Random block_rand = new Random();
	Random strat_rand = new Random();
	int wr = win_rand.nextInt(4);
	int br = block_rand.nextInt(2);
	int sr = strat_rand.nextInt(3);
	return searchMove(board,strat.applyStrategy(board,wr,br,sr, trace));
    }
	
    public int searchMove(int[] board, String type) throws IOException{
	if(type == "r"){
	    return getRandMove(board);
	}
	File infile = new File("p2_thoughtful_db.txt");
	Scanner scan = new Scanner(infile);
	int db_move = 0;
	int[] db = new int[9];
	while(scan.hasNext()){
	    String db_type = scan.next();
	    String in_board = scan.next();
	    String move = scan.next();
	    if(!db_type.matches(type)){
		continue;
	    }
	    int index = 0;
	    for(char c : in_board.toCharArray()){
		if(c=='0'){
		    db[index] = 0;
		}else if(c=='!'){
		    db[index] = otherNumber;
		}else{
		    db[index] = playerNumber;
		}
		index++;
	    }
	    db_move = Integer.parseInt(move);
	    int rot_count = 0;
	    while(!equal(db,board) && rot_count < 3){
		db = rotate_b(db);
		rot_count++;
	    }
	    if(equal(db,board)){
		return unRotate(db_move,rot_count);
	    }
	}
	//if run out of db entries with no solution
	//return random
	//if testing ask if good move
	Random rand = new Random();
	db_move = rand.nextInt(9);
	while(board[db_move] != 0){
	    db_move = rand.nextInt(9);
	}
	if(testing){
	    BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	    System.out.println("What is a good move?");
	    try {
		db_move = Integer.parseInt(br.readLine());
	    } catch (IOException ioe) {
		System.out.println("IO error trying to read your name!");
		System.exit(1);
	    }
	    //save board and best move in db
	    PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(infile, true)));
	    String old_board = toString(board);
	    String move = ""+db_move;
	    out.println(type);
	    out.println(old_board);
	    out.print(move);
	    out.println();
	    out.close();
	}
	return db_move;
    }
	
    public int[] rotate_b(int[] b){
	int[] ret = {b[6],b[3],b[0],b[7],b[4],b[1],b[8],b[5],b[2]};
	return ret;
    }
	
    public int unRotate(int index, int num_rot){
	int[] zero = new int[9];
	zero[index] = 1;
	for(int i=0;i<num_rot;i++)
	    zero = rotate_b(zero);
	int ret = -1;
	for(int i=0;i<zero.length;i++){
	    if(zero[i] == 1){
		ret = i;
		break;
	    }
	}
	return ret;
    }
	
    public boolean equal(int[] x, int[] y){
	for(int i=0;i<x.length;i++){
	    if(x[i] != y[i]){
		return false;
	    }
	}
	return true;
    }
	
    public String toString(int[] b){
	String ret = "";
	for(int i=0;i<b.length;i++){
	    if(b[i] == 0){
		ret+="0";
	    }else if(b[i] == otherNumber){
		ret+="!";
	    }else{
		ret+="~";
	    }
	}
	return ret;
    }

    public int getRandMove(int[] board){
	Random rand = new Random();
	int r_move = rand.nextInt(9);
	while(board[r_move] != 0){
	    r_move = rand.nextInt(9);
	}
	return r_move;
    }
	
	public ArrayList<Trace> getBacktrack(){
		return null;
	}
}

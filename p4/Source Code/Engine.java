import java.io.*;
import java.util.ArrayList;

public class Engine{
    ArrayList<Node> board = new ArrayList<Node>();
    int[] aval = {0,0,0,0,0,0,0,0,0};
    private boolean game = true;
    private boolean suppress;
    private boolean human;
    private int turn = 1;
    private int winner = -1;
    private boolean tie = false;
    private ArrayList<String> trace = new ArrayList<String>();
    private ArrayList<Trace> p1Trace;
    private ArrayList<Trace> p2Trace;
    private int p1Turn;
    private int p2Turn;
	
    public Engine(){
	for(int i=0;i<9;i++){
	    Node n = new Node(i);
	    board.add(n);
	}
	p1Turn = 0;
	p2Turn = 0;
    }
	
    public void runGame(Player p1, Player p2, boolean suppress, boolean human) throws IOException{
	this.suppress = suppress;
	this.human = human;
	while(game){
	    printBoard();
	    if(turn%2!=0){
		p1Turn++;
		trace.add("Player 1 turn: "+p1Turn);
		int p1_move = p1.getMove(aval, trace);
		board.get(p1_move).markX();
		aval[p1_move] = 1;
		turn++;
	    }else{
		p2Turn++;
		trace.add("Player 2 turn: "+p2Turn);
		int p2_move = p2.getMove(aval, trace);
		board.get(p2_move).markO();
		aval[p2_move] = 2;
		turn++;
	    }
	    checkWin();
	}
	if(tie){
	    trace.add("///////////////////////////////");
	    trace.add("Tie game!");
	}else{
	    trace.add("///////////////////////////////");
	    trace.add("Player "+winner+" wins!");
	}
	printBoard();
	trace.add("///////////////////////////////");
	for(int i=0;i<trace.size();i++){
	    System.out.println(trace.get(i));
	}
	p1Trace = p1.getBacktrack();
	p2Trace = p2.getBacktrack();
	ArrayList<Trace> curr = p1Trace;
	Trace tr = null;
	BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	boolean question = true;
	while(question){
	
	    System.out.println("Which player do you want to ask a question?");
	    try{
		String p = br.readLine();
		if(p.equals("1")){
		    curr = p1Trace;
		}else{
		    curr = p2Trace;
		}
	    }catch(Exception e){
		continue;
	    }
	    System.out.println("Which turn are you interested in?");
	    try{
		int t = Integer.parseInt(br.readLine());
		for(int i=0;i<curr.size();i++){
		    if(curr.get(i).getTurn() == t){
			tr = curr.get(i);
		    }
		}
	    }catch(Exception e){
		continue;
	    }
	    System.out.println("What type of question do you want to ask?");
	    try{
		String ty = br.readLine();
		if(ty.equals("why")){
		    System.out.println();
		    tr.why();
		}else if(ty.equals("wrong")){
		    System.out.println();
		    tr.wrong();
		}else if(ty.equals("which")){
		    System.out.println();
		    tr.which();
		}else{
		    continue;
		}
	    }catch(Exception e){
		continue;
	    }
	    System.out.println();
	    System.out.println("Would you like to ask another question?");
	    try{
		String dec = br.readLine();
		if(dec.equals("yes")){
		    continue;
		}else{
		    question = false;
		}
	    }catch(Exception e){
		continue;
	    }
	}
    }
	
    public void printBoard(){
	if(suppress) return;
	if(human){
	    // 0 | 1 | 2
	    //-----------
	    // 3 | 4 | 5 
	    //-----------
	    // 6 | 7 | 8 
	    trace.add("");
	    trace.add(" "+board.get(0)+" | "+board.get(1)+" | "+board.get(2));
	    trace.add("-----------");
	    trace.add(" "+board.get(3)+" | "+board.get(4)+" | "+board.get(5));
	    trace.add("-----------");
	    trace.add(" "+board.get(6)+" | "+board.get(7)+" | "+board.get(8));
	    trace.add("");
	}else{
	    trace.add("");
	    trace.add(" "+(board.get(0).toString().matches("[0-9]")? " ":board.get(0))+" | "
		      +(board.get(1).toString().matches("[0-9]")? " ":board.get(1))+" | "
		      +(board.get(2).toString().matches("[0-9]")? " ":board.get(2)));
	    trace.add("-----------");
	    trace.add(" "+(board.get(3).toString().matches("[0-9]")? " ":board.get(3))+" | "
		      +(board.get(4).toString().matches("[0-9]")? " ":board.get(4))+" | "
		      +(board.get(5).toString().matches("[0-9]")? " ":board.get(5)));
	    trace.add("-----------");
	    trace.add(" "+(board.get(6).toString().matches("[0-9]")? " ":board.get(6))+" | "
		      +(board.get(7).toString().matches("[0-9]")? " ":board.get(7))+" | "
		      +(board.get(8).toString().matches("[0-9]")? " ":board.get(8)));
	    trace.add("");
	}
    }
	
    public void checkWin(){
	if(checkX()){
	    game = false;
	    winner = 1;
	}else if(checkO()){
	    game = false;
	    winner = 2;
	}else{
	    boolean t = true;
	    for(int i : aval){
		if(i == 0)
		    t = false;
	    }
	    if(t){
		game = false;
		tie = true;
	    }
	}
    }
	
    public boolean checkX(){
	ArrayList<Integer> nodes = new ArrayList<Integer>();
	for(int i=0;i<aval.length;i++){
	    if(aval[i] == 1)
		nodes.add(i);
	}
	//check all 8 win cases
	//horizontal
	if(nodes.contains(0) && nodes.contains(1) && nodes.contains(2)) return true;
	if(nodes.contains(3) && nodes.contains(4) && nodes.contains(5)) return true;
	if(nodes.contains(6) && nodes.contains(7) && nodes.contains(8)) return true;
	//vertical
	if(nodes.contains(0) && nodes.contains(3) && nodes.contains(6)) return true;
	if(nodes.contains(1) && nodes.contains(4) && nodes.contains(7)) return true;
	if(nodes.contains(2) && nodes.contains(5) && nodes.contains(8)) return true;
	//diagonal
	if(nodes.contains(0) && nodes.contains(4) && nodes.contains(8)) return true;
	if(nodes.contains(2) && nodes.contains(4) && nodes.contains(6)) return true;
	return false;
    }
	
    public boolean checkO(){
	ArrayList<Integer> nodes = new ArrayList<Integer>();
	for(int i=0;i<aval.length;i++){
	    if(aval[i] == 2)
		nodes.add(i);
	}
	//check all 8 win cases
	//horizontal
	if(nodes.contains(0) && nodes.contains(1) && nodes.contains(2)) return true;
	if(nodes.contains(3) && nodes.contains(4) && nodes.contains(5)) return true;
	if(nodes.contains(6) && nodes.contains(7) && nodes.contains(8)) return true;
	//vertical
	if(nodes.contains(0) && nodes.contains(3) && nodes.contains(6)) return true;
	if(nodes.contains(1) && nodes.contains(4) && nodes.contains(7)) return true;
	if(nodes.contains(2) && nodes.contains(5) && nodes.contains(8)) return true;
	//diagonal
	if(nodes.contains(0) && nodes.contains(4) && nodes.contains(8)) return true;
	if(nodes.contains(2) && nodes.contains(4) && nodes.contains(6)) return true;
	return false;
    }
	
}

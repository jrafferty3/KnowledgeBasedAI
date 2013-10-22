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
	public Engine(){
		for(int i=0;i<9;i++){
			Node n = new Node(i);
			board.add(n);
		}
	}
	
	public void runGame(Player p1, Player p2, boolean suppress, boolean human) throws IOException{
	  this.suppress = suppress;
	  this.human = human;
	  while(game){
		printBoard();
		if(turn%2!=0){
			int p1_move = p1.getMove(aval);
			board.get(p1_move).markX();
			aval[p1_move] = 1;
			turn++;
		}else{
			int p2_move = p2.getMove(aval);
			board.get(p2_move).markO();
			aval[p2_move] = 2;
			turn++;
		}
		checkWin();
	  }
	  if(tie){
		System.out.println("///////////////////////////////");
		System.out.println("Tie game!");
	  }else{
		System.out.println("///////////////////////////////");
		System.out.println("Player "+winner+" wins!");
	  }
	  printBoard();
	  System.out.println("///////////////////////////////");
	}
	
	public void printBoard(){
		if(suppress) return;
		if(human){
			// 0 | 1 | 2
			//-----------
			// 3 | 4 | 5 
			//-----------
			// 6 | 7 | 8 
			System.out.println();
			System.out.println(" "+board.get(0)+" | "+board.get(1)+" | "+board.get(2));
			System.out.println("-----------");
			System.out.println(" "+board.get(3)+" | "+board.get(4)+" | "+board.get(5));
			System.out.println("-----------");
			System.out.println(" "+board.get(6)+" | "+board.get(7)+" | "+board.get(8));
			System.out.println();
		}else{
			System.out.println();
			System.out.println(" "+(board.get(0).toString().matches("[0-9]")? " ":board.get(0))+" | "
					+(board.get(1).toString().matches("[0-9]")? " ":board.get(1))+" | "
					+(board.get(2).toString().matches("[0-9]")? " ":board.get(2)));
			System.out.println("-----------");
			System.out.println(" "+(board.get(3).toString().matches("[0-9]")? " ":board.get(3))+" | "
					+(board.get(4).toString().matches("[0-9]")? " ":board.get(4))+" | "
					+(board.get(5).toString().matches("[0-9]")? " ":board.get(5)));
			System.out.println("-----------");
			System.out.println(" "+(board.get(6).toString().matches("[0-9]")? " ":board.get(6))+" | "
					+(board.get(7).toString().matches("[0-9]")? " ":board.get(7))+" | "
					+(board.get(8).toString().matches("[0-9]")? " ":board.get(8)));
			System.out.println();
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

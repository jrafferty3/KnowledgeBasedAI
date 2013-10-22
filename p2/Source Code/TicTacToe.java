import java.io.*;

public class TicTacToe{
	private static boolean human = false;
	public TicTacToe(){}
	
	public static void main(String[] args) throws IOException{
		TicTacToe ttt = new TicTacToe();
		Engine e = new Engine();
		Player p1;
		Player p2;
		String s1;
		String s2;
		boolean suppress = false;
		if(args.length == 0){
			s1 = "t";
			s2 = "n";
		}else{
			s1 = args[0];
			s2 = args[1];
		}
		if(args.length == 3)
			suppress = true;
		ttt.check(s1);
		ttt.check(s2);
		if(s1.equals("test")){
			//human = true;
			p1= new Thoughtful(1,"test");
			p2 = new Naive(2);
		}else{
			if(s1.equals("h")){
				human = true;
				p1 = new Human(1);
			}else if(s1.equals("n")){
				p1 = new Naive(1);
			}else{
				p1 = new Thoughtful(1);
			}
			if(s2.equals("h")){
				human = true;
				p2 = new Human(2);
			}else if(s2.equals("n")){
				p2 = new Naive(2);
			}else{
				p2 = new Thoughtful(2);
			}
		}
		e.runGame(p1,p2,suppress,human);
	}
	
	public void check(String s){
		if(!("h".equals(s)) && !("n".equals(s)) && !("t".equals(s)) && !("test".equals(s))){
			System.out.println(s+" is not a valid input to this program.");
			System.exit(1);
		}
	}
}

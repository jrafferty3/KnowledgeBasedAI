import java.io.*;
import java.util.Scanner;  

public class ReDo{
	public ReDo(){
	
	}
	
	public static void main (String[] args) throws Exception{
		ReDo r = new ReDo();
		File infile = new File("thoughtful_db.txt");
		File outfile = new File("p2_thoughtful_db.txt");
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		Scanner scan = new Scanner(infile);
		int count = 0;
		String[] db = new String[9];
		while(scan.hasNext()){
			String in_board = scan.next();
			String move = scan.next();
			String db_type = "";
			int index = 0;
			for(char c : in_board.toCharArray()){
				if(c=='0'){
					db[index] = " ";
				}else if(c=='!'){
					db[index] = "O";
				}else{
					db[index] = "X";
				}
				index++;
			}
			r.printBoard(db);
			System.out.println(move);
			System.out.println("Block(b)? Win(w)? Strat(s)?");
			while(true){
				try {
					db_type = br.readLine();
					break;
				} catch (IOException ioe) {
					System.out.println("IO error trying to read your name!");
					System.exit(1);
				} catch (Exception e){
					System.out.print("That is not a number, try again. ");
					continue;
				}
			}
			PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(outfile, true)));
			out.println(db_type);
			out.println(in_board);
			out.print(move);
			out.println();
			out.close();
			count++;
		}
	}
	
	public void printBoard(String[] board){
		System.out.println();
			System.out.println(" "+board[0]+" | "+board[1]+" | "+board[2]);
			System.out.println("-----------");
			System.out.println(" "+board[3]+" | "+board[4]+" | "+board[5]);
			System.out.println("-----------");
			System.out.println(" "+board[6]+" | "+board[7]+" | "+board[8]);
			System.out.println();
	}
	
}
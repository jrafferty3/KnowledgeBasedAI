import java.io.*;
import java.util.Scanner;  

public class RotationTest{
	private static int pnum = 1;
	private static int onum = 2;
	public void RotationTest(){}
	
	public static void main(String[] args) throws IOException {
		RotationTest rt = new RotationTest();
		File infile = new File("thoughtful_db.txt");
		Scanner scan = new Scanner(infile);
		int[] db_board = new int[9];
		while(scan.hasNext()){
			String s = scan.next();
			String move = scan.next();
			
			int index = 0;
			for(char c : s.toCharArray()){
				if(c=='0'){
					db_board[index] = 0;
				}else if(c=='!'){
					db_board[index] = pnum;
				}else{
					db_board[index] = onum;
				}
				index++;
			}
			rt.print_b(db_board);
			System.out.println(move);
		}
		/*
		
		int[] board = {0,1,0,0,0,0,0,0,0};
		rt.print_b(board);
		
		int[] rot1 = rt.rotate_b(board);
		rt.print_b(rot1);
		
		int[] rot2 = rt.rotate_b(rot1);
		rt.print_b(rot2);
		
		int[] rot3 = rt.rotate_b(rot2);
		rt.print_b(rot3);
		
		int ind = -1;
		for(int i=0;i<9;i++){
			if(rot2[i] == 1){
				ind = i;
				break;
			}
		}
		System.out.println(ind);
		*/
	}
	
	public void print_b(int[] b){
		System.out.println();
		System.out.println(" "+b[0]+" | "+b[1]+" | "+b[2]);
		System.out.println("-----------");
		System.out.println(" "+b[3]+" | "+b[4]+" | "+b[5]);
		System.out.println("-----------");
		System.out.println(" "+b[6]+" | "+b[7]+" | "+b[8]);
		System.out.println();
	}
	
	public int[] rotate_b(int[] b){
		int[] ret = {b[6],b[3],b[0],b[7],b[4],b[1],b[8],b[5],b[2]};
		return ret;
	}
}
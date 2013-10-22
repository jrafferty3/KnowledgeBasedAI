
public class Node{
	int index;
	String display;
	public Node(int index){
		this.index = index;
		this.display = ""+index;
	}	
	
	public int getIndex(){
		return index;
	}
	
	public void markX(){
		display = "X";
	}
	
	public void markO(){
		display = "O";
	}
	
	public String toString(){
		return display;
	}
}

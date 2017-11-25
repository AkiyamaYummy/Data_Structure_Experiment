import java.util.Scanner;
import java.util.Vector;

public class dijTest {
	public static Scanner sc = new Scanner(System.in);
	public static void main(String[] args) {
		//services.txt
		Graph G = new Graph("services.txt");
		String messageIn = null;
		Vector<Integer> resvec = new Vector<Integer>();
		while(!(messageIn=newMessage()).equals("quit")){
			String[] twoCity = messageIn.split(" ");
			resvec.clear();
			Pair resp = Dij.dijkstra(twoCity[0],twoCity[1],G,resvec);
			sprint("The cheapest route");
			sprintln("from "+twoCity[0]+" to "+twoCity[1]);
			sprintln("costs "+resp.first+" euros and spans "+resp.second+" kilometers");
			for(int i=resvec.size()-1;i>=0;i--){
				sprint(G.id_name.get(resvec.get(i)));
				if(i != 0)sprint(" to ");
			}
			System.out.println();
		}
	}
	public static String newMessage(){
		System.out.println("Enter a start and destination city: ('quit' to exit)");
		return sc.nextLine();
	}
	public static void sprint(String str){
		System.out.print(str);
	}
	public static void sprintln(String str){
		sprint(str+"\r\n");
	}
}

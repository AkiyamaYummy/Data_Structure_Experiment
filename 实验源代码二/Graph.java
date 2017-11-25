import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Vector;

class Edge{
	public int to,next,len,cost;
	public Edge(){}
	public Edge(int to,int next,int len,int cost){
		this.to = to;
		this.next = next;
		this.len = len;
		this.cost = cost;
	}
}
public class Graph {
	public Edge[] edges;
	public int[] head;
	public int maxe,maxn,eid;
	HashMap<String,Integer> name_id = new HashMap<String,Integer>();
	Vector<String> id_name = new Vector<String>();
	public Graph(int maxe,int maxn){
		this.maxe = maxe;
		this.maxn = maxn;
		edges = new Edge[maxe];
		reInit();
	}
	public Graph(String fileName){
		String strin = null;
		String[] messagein = null;
		String city1=null,city2=null;
		Vector<Integer> messageVec = new Vector<Integer>();
		int cost,length,id1,id2,edgeCon = 0;
		try {
			BufferedReader reader = new BufferedReader(
								new FileReader(fileName));
			while((strin = reader.readLine()) != null){
				//System.out.println(strin);
				messagein = strin.split(" ");
				//System.out.println(messagein[0]);
				city1 = messagein[0]; city2 = messagein[1];
				id1 = addName(city1); id2 = addName(city2);
				cost = Integer.parseInt(messagein[2]);
				length = Integer.parseInt(messagein[3]);
				edgeCon ++;
				messageVec.add(id1);messageVec.add(id2);
				messageVec.add(cost);messageVec.add(length);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		this.maxe = edgeCon; this.maxn = id_name.size();
		edges = new Edge[maxe];
		reInit();
		for(int i=0;i<edgeCon;i++)
			add(messageVec.get(i*4),messageVec.get(i*4+1),messageVec.get(i*4+2),messageVec.get(i*4+3));
	}
	public void reInit(){
		head = new int[maxn];
		for(int i=0;i<maxn;i++)
			head[i] = -1;
		eid = 0;
	}
	public void add(int host,int to,int cost,int len){
		edges[eid] = new Edge(to,head[host],len,cost);
		head[host] = eid++;
	}
	public int addName(String name){
		if(name_id.containsKey(name))
			return ((Integer)name_id.get(name)).intValue();
		id_name.add(name);
		name_id.put(name,id_name.size()-1);
		return id_name.size()-1;
	}
}

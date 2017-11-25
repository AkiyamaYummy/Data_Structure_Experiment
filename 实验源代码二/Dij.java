import java.util.PriorityQueue;
import java.util.Vector;

public class Dij {
	static public int INI = 1000000000;
	static public Pair dijkstra(String start,String end,Graph G,Vector<Integer> path){
		return dijkstra(G.name_id.get(start),G.name_id.get(end),G,path);
	}
	static public Pair dijkstra(int start,int end,Graph G,Vector<Integer> path){
		int[] d = new int[G.maxn],c = new int[G.maxn],p = new int[G.maxn];
		for(int i=0;i<G.maxn;i++){
			d[i] = INI; c[i] = 0;
		}
		Edge[] edges = G.edges;
		int[] head = G.head;
		PriorityQueue<Pair> que = new PriorityQueue<Pair>();
		que.offer(new Pair(0,0,start));
		d[start] = 0;
		while(que.size() > 0){
			int first = que.peek().first,second = que.peek().second,third = que.poll().third;
			if(second == end){
				getPath(start,end,p,path);
				return new Pair(first,second,0);
			}
			if(first > d[third])continue;
			for(int i=head[third];i!=-1;i=edges[i].next){
				int to = edges[i].to,len = edges[i].cost,cost = edges[i].len;
				if(d[to] > d[third]+len){
					p[to] = third;
					d[to] = d[third]+len;
					c[to] = c[third]+cost;
					que.offer(new Pair(d[to],c[to],to));
				}
			}
		}
		getPath(start,end,p,path);
		return new Pair(d[end],c[end],0);
	}
	static void getPath(int start,int end,int[] p,Vector<Integer> path){
		for(int c=end;c!=start;c=p[c])path.add(c);
		path.add(start);
	}
}

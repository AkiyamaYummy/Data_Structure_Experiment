public class Pair implements Comparable<Pair>{
	int first,second,third;
	public Pair(int f,int s,int t){
		first = f; second = s; third = t;
	}
	public int compareTo(Pair that) {
		if(this.first < that.first)return -1;
		else if(this.first > that.first)return 1;
		else if(this.second < that.second)return -1;
		else if(this.second < that.second)return 1;
		else return 0;
	}
}

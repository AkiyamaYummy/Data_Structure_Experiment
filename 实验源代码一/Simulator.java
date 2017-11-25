public class Simulator {
	private int seconds_per_page;
	MyQueue<Event> workload;
	public Simulator(int s){
		seconds_per_page = s;
		workload = new MyQueue<Event>();
	}
	public int getSec(){
		return seconds_per_page;
	}
	public MyQueue<Event> getQueue(){
		return workload;
	}
}

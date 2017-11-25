public class Event {
	private Job j;
	private int arrival_time;
	public Event(Job _j,int _time){
		j = _j; arrival_time = _time;
	}
	public void setJob(Job _j){
		j = _j;
	}
	public void setTime(int _time){
		arrival_time = _time;
	}
	public Job getJob(){
		return j;
	}
	public int getTime(){
		return arrival_time;
	}
	public String toString(){
		return new String(arrival_time+" "+j.toString());
	}
}

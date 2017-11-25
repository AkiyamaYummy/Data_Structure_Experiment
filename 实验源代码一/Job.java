public class Job {
	private String user;
	private int number_of_pages;
	public Job(String u,int p){
		user = u;
		number_of_pages = p;
	}
	public String getUser(){
		return user;
	}
	public int getNumOfPages(){
		return number_of_pages;
	}
	public void setUser(String _user){
		user = _user;
	}
	public void setNumOfPages(int nop){
		number_of_pages = nop;
	}
	public String toString(){
		return new String(user+" "+number_of_pages);
	}
}

import java.io.*;
import java.text.DecimalFormat;

public class Fifo {
	public static String splitStr = " ";
	private MyQueue<Event> orderLine;
	private MyQueue<Event> workLine;
	private MyQueue<Integer> timeLine;
	private Simulator sim;
	private String fileOut,fileIn; 
	public Fifo(String fileIn,String fileOut,int sec){
		orderLine = new MyQueue<Event>();
		timeLine = new MyQueue<Integer>();
		sim = new Simulator(sec);
		workLine = sim.getQueue();
		this.fileOut = fileOut;
		this.fileIn = fileIn;
	}
	public void readMessage(){
		String strin = null;
		String[] messagein = null;
		String user; int pages,time;
		try {
			BufferedReader reader = new BufferedReader(
								new FileReader(fileIn));
			while((strin = reader.readLine()) != null){
				//System.out.println(strin);
				messagein = strin.split(splitStr);
				//System.out.println(messagein[0]);
				time = Integer.parseInt(messagein[0]);
				pages = Integer.parseInt(messagein[1]);
				user = messagein[2];
				Event eventin = new Event(new Job(user,pages),time); 
				orderLine.push(eventin);
			}
			reader.close();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public void wirteMessage(String message){
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(
					new FileWriter(fileOut));
			writer.write(message);
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public String run(){
		DecimalFormat df = new DecimalFormat("#.0000");  
		StringBuffer sb = new StringBuffer();
		int timeToUse = -1,proCon = 0,timeCon = 0;
		for(int time = 0;!(orderLine.empty()&&workLine.empty());time++){
			if(!workLine.empty()){
				if(timeToUse == 0){
					//sb.append("Time : "+time+"\r\n");
					//sb.append("Complete event : "+workLine.front()+"\r\n");
					workLine.pop();
					timeToUse = -1;
				}
			}
			while(!orderLine.empty()&&time==orderLine.front().getTime()){
				//sb.append("Time : "+time+"\r\n");
				//sb.append("Get event : "+orderLine.front().toString()+"\r\n");
				sb.append(arriveStr(orderLine.front().getJob(),time));
				workLine.push(orderLine.front()); timeLine.push(time);
				proCon ++;
				orderLine.pop();
			}
			if(!workLine.empty()){
				if(timeToUse == -1){
					while(!workLine.empty()){
						timeToUse = workLine.front().getJob().getNumOfPages()*sim.getSec();
						if(timeToUse == 0){
							//sb.append("Time : "+time+"\r\n");
							//sb.append("Get an empty event : "+workLine.front()+"\r\n");
							sb.append(serviceStr(workLine.front().getJob(),time));
							timeCon += (time-timeLine.front()); timeLine.pop();
							workLine.pop();
						}else {
							//sb.append("Time : "+time+"\r\n");
							//sb.append("Start event : "+workLine.front()+"\r\n");
							sb.append(serviceStr(workLine.front().getJob(),time));
							timeCon += (time-timeLine.front()); timeLine.pop();
							break;
						}
					}
				}
				timeToUse = workLine.empty()?-1:timeToUse-1;
			}
		}
		//System.out.print(sb.toString());
		sb.append("\r\n");
		sb.append("\tTotal jobs: "+proCon+"\r\n");
		sb.append("\tAggregate latency: "+timeCon+" seconds\r\n"); 
		sb.append("\tMean latency: "+df.format(1.0*timeCon/proCon)+" seconds\r\n"); 
		return sb.toString();
	}
	public String arriveStr(Job j,int t){
		String pageStr = j.getNumOfPages()<=1?" page from ":" pages from ";
		return "\tArriving: "+j.getNumOfPages()+pageStr+j.getUser()+" at "+t+" seconds\r\n";
	}
	public String serviceStr(Job j,int t){
		String pageStr = j.getNumOfPages()<=1?" page from ":" pages from ";
		return "\tServicing: "+j.getNumOfPages()+pageStr+j.getUser()+" at "+t+" seconds\r\n";
	}
}

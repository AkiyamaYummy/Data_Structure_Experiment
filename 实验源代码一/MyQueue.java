class MyListNode<Type>{
	private Type value;
	private MyListNode<Type> prev,next;
	public MyListNode(Type v,MyListNode<Type> p,MyListNode<Type> n){
		value = v;
		prev = p;
		next = n;
	}
	public Type getValue(){
		return value;
	}
	public MyListNode<Type> getPrev(){
		return prev;
	}
	public MyListNode<Type> getNext(){
		return next;
	}
	public void setPrev(MyListNode<Type> p){
		prev = p;
	}
	public void setNext(MyListNode<Type> n){
		next = n;
	}
}
class MyList<Type>{
	private MyListNode<Type> head,tail;
	public MyList(){
		head = new MyListNode<Type>(null,null,null);
		tail = new MyListNode<Type>(null,null,null);
		head.setNext(tail);
		tail.setPrev(head);
	}
	public boolean empty(){
		return head.getNext() == tail;
	}
	public MyListNode<Type> begin(){
		return head.getNext();
	}
	public MyListNode<Type> end(){
		return tail; 
	}
	public void pushBack(Type v){
		MyListNode<Type> back = tail.getPrev();
		MyListNode<Type> newBack = new MyListNode<Type>(v,back,tail);
		back.setNext(newBack);
		tail.setPrev(newBack);
	}
	public void pushBegin(Type v){
		MyListNode<Type> begin = head.getNext();
		MyListNode<Type> newBegin = new MyListNode<Type>(v,head,begin);
		head.setNext(newBegin);
		begin.setPrev(newBegin);
	}
	public void popBack(){
		MyListNode<Type> newBack = tail.getPrev().getPrev();
		newBack.setNext(tail);
		tail.setPrev(newBack);
	}
	public void popBegin(){
		MyListNode<Type> newBegin = head.getNext().getNext();
		head.setNext(newBegin);
		newBegin.setPrev(head);
	}
}
public class MyQueue<Type> extends MyList<Type>{
	public Type front(){
		return this.begin().getValue();
	}
	public void pop(){
		this.popBegin();
	}
	public void push(Type value){
		this.pushBack(value);
	}
}
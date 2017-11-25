public class FifoTest {

	public static void main(String[] args) {
		Fifo fifo = new Fifo("arbitrary.run","arbitrary.out",2);
		fifo.readMessage();
		fifo.wirteMessage(fifo.run());
	}
}

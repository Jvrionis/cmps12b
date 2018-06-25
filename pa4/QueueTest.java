//------------------------------------------------------------------------------------
// Pa4
// Program: QueueTest.java
// Author:  James Michael Vrionis
// ID    :  JVrionis
// Date  :  11-22-16
//------------------------------------------------------------------------------------
public class QueueTest {
	public static void main (String[] args) {
		Queue A = new Queue();

		System.out.println(A.isEmpty());
		A.enqueue((int)2);
		A.enqueue((int)4);
		A.enqueue((int)6);
		A.enqueue((int)8);

		System.out.println(A.isEmpty());
		System.out.println("Length: " + A.length());
		System.out.println(A); 

		A.dequeue();
		A.dequeue();

		System.out.println(A.isEmpty());
		System.out.println("Length: " + A.length());
		System.out.println("Front: " + A.peek());

		A.dequeueAll();
		System.out.println(A.isEmpty());
		System.out.println("Length: " + A.length());
		try {
			System.out.println(A.peek());
		} catch(QueueEmptyException e) {
			System.out.println("Caught Exception " + e);
			System.out.println("Continuing without interuption");
		}
	}
}


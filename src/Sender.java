import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**This class implements the sending of objects to receivers 
 * via multicasting.
 * 
 * @author Sagnik
 * @version 1.0
 * @see ObjectCustom 
 * 
 *
 */
public class Sender {

	//Declaring the Address and Ports
	final static String INET_ADDR = "239.0.0.3";
	final static int port = 8890;
	/**
	 * Main method to send objects via multicasting
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		System.out.println("Create socket on address " + INET_ADDR + " on port = " + port);
		
		// Get the group address that we are going to connect to
		InetAddress addr = InetAddress.getByName(INET_ADDR);
		
		// Loop to send 10 objects
		for (int i = 0; i < 10; i++) {
			// Open a new MulticastSocket, which will be used to send data.
			try (
				MulticastSocket s = new MulticastSocket(port);
				ByteArrayOutputStream baos = new ByteArrayOutputStream();
				ObjectOutputStream oos = new ObjectOutputStream(baos);
			) {
				// Create an ObjectCustom object, set length
				// and message, and display it alongwith
				// its datatypes and current time
				ObjectCustom obj = new ObjectCustom();
				obj.setLength();
				obj.setContent();
				System.out.println("Current Message is :");
				obj.display();
				System.out.println("Current Time is:" + System.currentTimeMillis());
				System.out.println();
				// Write obj to the ObjectOutputStream and send as byte array
				oos.writeObject(obj);
				byte data[] = baos.toByteArray();
				
				// Send the datagram packet
				s.send(new DatagramPacket(data, data.length, addr,port));
				
				// try-catch block to pause the current thread 
				// for 10 secs
				try {
					Thread.sleep(10000);
				}
				catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
				
				
				
			}
			catch (Exception e) {
				System.out.println("exception = " + e);
				e.printStackTrace();
			}
		}
		

	}

}

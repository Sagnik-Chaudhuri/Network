import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.DatagramPacket;
import java.net.InetAddress;
import java.net.MulticastSocket;

/**
 * This class implements the receiving of objects,
 * Receiver being present in the Multicast Group
 * @author Sagnik
 * @version 1.0
 * @see ObjectCustom
 */
public class Receiver {

	// Set the Group Address and Port number
	final static String INET_ADDR = "239.0.0.3";
	final static int port = 8890;
	/**
	 * Main method to receive multicast objects sent to particular
	 * group
	 * @param args
	 * @throws IOException
	 */
	public static void main(String[] args) throws IOException {
		
		// Set size of buffer
		final int bufferSize = 10000;
		System.out.println("Create Socket on address " + INET_ADDR + " and port = " + port);
		
		// Get the group address that we connect to
		InetAddress addr = InetAddress.getByName(INET_ADDR);
		
		// Create a buffer of bytes, which will be used to store
		// the incoming bytes containing the information from sender
		byte buffer[] = new byte[bufferSize];
		
		// loop to receive 10 objects
		for (int i = 0; i < 10; i++) {
			try (
					 MulticastSocket s = new MulticastSocket(port);
				     ByteArrayInputStream bais = new ByteArrayInputStream(buffer);
			) {
				
				// Join the multicast group to receive data
				s.joinGroup(addr);
				System.out.println("Waiting for datagram to be received");
				
				// Receive the datagram packet from socket
				s.receive(new DatagramPacket(buffer, bufferSize));
				System.out.println("Datagram Received!");
				
				// Create ObjectInputStream, Deserialize data
				ObjectInputStream ois = new ObjectInputStream(bais);
				try {
					Object readObject = ois.readObject();
					// Check if instance matches with ObjectCustom
					if(readObject instanceof ObjectCustom) {
						ObjectCustom obj = (ObjectCustom)readObject;
						// Display details of obj
						obj.display();
						System.out.println("Current Time is " + System.currentTimeMillis());
						System.out.println();
						
					}
					else System.out.println("Received object isn't type ObjectCustom");
					
				}
				catch (Exception e) {
					System.out.println("exception " + e);
					e.printStackTrace();
				}
				
			}
			catch (Exception e) {
				System.out.println("exception " + e);
				e.printStackTrace();
			}
		}
		

	}

}

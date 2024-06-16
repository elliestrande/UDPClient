import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
*  UDP Client Program
*  Connects to a UDP Server
*  Receives a line of input from the keyboard and sends it to the server
*  Receives a response from the server and displays it.
*  Continues this process in a while loop until the user enters "Goodbye"
*
*  @author: Ellie Strande & Cal Hegstrom
*     email: strande@chapman.edu & hegstrom@chapman.edu
*     date: 2/20/2023
*  @version: 3.1
*/

class UdpServer {
  public static void main(String[] args) throws Exception {
    DatagramSocket serverSocket = null;

    try {
      serverSocket = new DatagramSocket(9876);
    } catch (Exception e) {
      System.out.println("Failed to open UDP socket");
      System.exit(0);
    }
    byte[] receiveData = null;
    byte[] sendData  = null;

    while (true) {
      System.out.println("Waiting for client to connect");
      receiveData = new byte[1024];
      sendData  = new byte[1024];
      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
      serverSocket.receive(receivePacket);
      String sentence = new String(receivePacket.getData());
      System.out.println(sentence);

      InetAddress ipAddress = receivePacket.getAddress();

      int port = receivePacket.getPort();

      String capitalizedSentence = sentence.toUpperCase();

      sendData = capitalizedSentence.getBytes();

      DatagramPacket sendPacket =
          new DatagramPacket(sendData, sendData.length, ipAddress, port);

      serverSocket.send(sendPacket);
    }
  }
}

import java.io.BufferedReader;
import java.io.InputStreamReader;
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
*
*/

class MyUdpClient {

  public static void main(String[] args) throws Exception {

    BufferedReader inFromUser = new BufferedReader(new InputStreamReader(System.in));

    DatagramSocket clientSocket = new DatagramSocket();

    InetAddress ipAddress = InetAddress.getByName("localhost");

    String sentence = " ";

    while (sentence.equals("Goodbye") != true) {

      System.out.println("Type a Sentence");
      sentence = inFromUser.readLine();

      byte[] sendData = new byte[1024];
      byte[] receiveData = new byte[1024];
      
      sendData = sentence.getBytes();
      DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, ipAddress, 9876);

      clientSocket.send(sendPacket);

      DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);

      clientSocket.receive(receivePacket);

      String modifiedSentence = new String(receivePacket.getData());

      System.out.println("FROM SERVER: " + modifiedSentence);
      
    }
    clientSocket.close();
  }
}
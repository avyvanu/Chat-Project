package relationship.socket;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.io.*;
import java.net.Socket;
import relationship.dao.DAOFactory;
import relationship.dao.ChatDAO;
import relationship.persistence.ChatMessagePersistence;



public class UdpClient {

	
	
	
	/*public String clientCall(String outstr){
		String output = "";
		try{
			DatagramSocket socket = new DatagramSocket();
			byte outblock[] = outstr.getBytes();
			InetAddress address = InetAddress.getLocalHost();
			
			DatagramPacket outpack = new DatagramPacket(outblock, outblock.length, address,8795);
			socket.send(outpack);
			System.out.println(" Client Sent : "+outstr);

			
			byte inblock[] = new byte[256];	
			DatagramPacket inpacket = new DatagramPacket(inblock,inblock.length);
			socket.receive(inpacket);
			String instr = new String(inpacket.getData(),0,inpacket.getLength());
			System.out.println(" Client Got = "+instr);
			socket.close();
			output = instr;
			
			
			
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		return output;
	}
	
	public String tcpClientCall(String outstr){
		
		String result = "";
		try{
		
			Socket socket = new Socket("localhost",9867);
			 
			InputStream inFromServer = socket.getInputStream();
			DataInputStream in = new DataInputStream(inFromServer);
	        System.out.println("Server says " + in.readUTF());
			//result = in.readUTF();
			
			  // When done, just close the connection and exit     
			in.close();      socket.close();			
		}
		catch(Exception e)
		{
			System.out.println("Client Exception  ");
			e.printStackTrace();
		}
		   return result;
	}
	*/
	
	public synchronized String udpClientCall(String clientMessage,String chatBy,int publicId){
		
		String outstr = "";
		
		try{
			DatagramSocket socket = new DatagramSocket();
			byte outblock[] = clientMessage.getBytes();
			InetAddress address = InetAddress.getLocalHost();
			
			DatagramPacket outpack = new DatagramPacket(outblock, outblock.length, address,8795);
			socket.send(outpack);
						
			byte inblock[] = new byte[256];	
			DatagramPacket inpacket = new DatagramPacket(inblock,inblock.length);
			socket.receive(inpacket);
			String instr = new String(inpacket.getData(),0,inpacket.getLength());
			System.out.println(" Client Got = "+instr);
			socket.close();			
			outstr = instr;
			
			
			java.sql.Time  timeIn = new java.sql.Time(new java.util.Date().getTime());
			java.sql.Date toDate = new java.sql.Date(new java.util.Date().getTime());
			ChatDAO chatDAO = new DAOFactory().getChatDAO();			
			ChatMessagePersistence chatMessage = new ChatMessagePersistence(0, clientMessage,chatBy,toDate,timeIn,publicId);			
			chatDAO.saveChatMessage(chatMessage);			
		}
		catch(Exception e){
			e.printStackTrace();			
		}
		return   outstr;
	}
	
}

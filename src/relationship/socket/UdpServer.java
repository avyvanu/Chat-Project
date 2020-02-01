//package relationship.socket;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.net.Socket;
import java.util.Date;
import java.io.OutputStream;
import java.net.ServerSocket;


public class UdpServer
{
	public static void main(String args[])
	{
		System.out.println(" Server is Ready For Receiving Request ");
		
		DatagramSocket socket = null;
		int count = 0;
		try{
			socket =new DatagramSocket(8795);			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		
		while(true){
			try{
				byte block[] = new byte[256];
				DatagramPacket inpaPacket = new DatagramPacket(block, block.length);
				socket.receive(inpaPacket);
				
				int length = inpaPacket.getLength();
				
				//System.out.println(" Length of data received = "+length);
				
				byte inblock[] = inpaPacket.getData();
				String inmsg = new String(inblock,0,length);
				//System.out.println(" Server Got  = "+inmsg);
				
				count++;
				String outmsg ;
				if(inmsg.equals("date")){
					Date date = new Date();
					outmsg = date.toString();
				}
				else if(inmsg.equals("count")){
					outmsg = "Number of Messages :"+count; 
				}
				else if(inmsg.equals("halt")){
					socket.close();
					return;
				}
				else 
					outmsg = "what is this message";				
				
				byte outblock[] = outmsg.getBytes();
				InetAddress returnAddress = inpaPacket.getAddress();
				int returnport = inpaPacket.getPort();
				DatagramPacket outpacket =  new DatagramPacket(outblock, outblock.length,returnAddress,returnport);
				
				socket.send(outpacket);
			}
			catch(Exception e){
				e.printStackTrace();
			}
			
		}
	}
	
	
}
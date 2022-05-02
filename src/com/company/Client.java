package com.company;

import javax.xml.crypto.Data;
import java.io.IOException;
import java.net.*;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

public class Client {

    public static void main(String []args) throws IOException {
        String host = "localhost";
        int port = 4445;
        InetAddress address = InetAddress.getByName(host);
        DatagramSocket socket = new DatagramSocket();
        byte[] buffer;
      while(true)  {


            System.out.println("Client Output:\n" +
                    "Please enter the list of numbers to be arranged or 'close' to close.");
            Scanner scan = new Scanner(System.in);
            String number = scan.next();
            if(number.equals("close"))
            {
                System.out.println("Client terminated the connection ");
                break;

            }
            buffer = number.getBytes();
            DatagramPacket request = new DatagramPacket(buffer, buffer.length, address, port);
            socket.send(request);
            System.out.println("""
                    Please choose:
                    1. Ascending order.
                    2. Descending order.""");
            String choice = scan.next();
            buffer = choice.getBytes();
            DatagramPacket request2 = new DatagramPacket(buffer, buffer.length, address, port);
            socket.send(request2);
            byte[] replyBuffer = new byte[256];
            DatagramPacket reply = new DatagramPacket(replyBuffer, replyBuffer.length, address, port);
            socket.receive(reply);
            String reply2 = new String(reply.getData(),0,reply.getLength());
            System.out.println(reply2);
        }

    }
}

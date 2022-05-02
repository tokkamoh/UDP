package com.company;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.ArrayList;

public class Multiple implements Runnable {
    public  DatagramSocket socket;
    public  byte [] buf = new byte[256];
Multiple(DatagramSocket socket)
{
    this.socket= socket;

}
    @Override
    public void run() {

        while(true)
        {
            DatagramPacket packet = new DatagramPacket( buf , buf.length);
            try {
                socket.receive(packet);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String recivied = new String (packet.getData(), 0,packet.getLength()); // the first packet which was the array or close
            String[] ArrayOfString = recivied.split(",");
            ArrayList<Integer> NumbersList = new ArrayList<>();
            for (String number : ArrayOfString) {
                NumbersList.add(Integer.parseInt(number));
            }
            buf = new byte[256];
            DatagramPacket packet2 = new DatagramPacket( buf , buf.length); /// the second packet which was 1 or 2 (the choice)
            try {
                socket.receive(packet2);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String Choice=new String (packet2.getData(),0,packet2.getLength());
            //System.out.println(Choice);
            int choice=Integer.parseInt(Choice);
            if(choice==1)
            {

                String respond=Server.sortAscending(NumbersList);
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                byte [] replyBytes= respond.getBytes();
                packet = new DatagramPacket(replyBytes , replyBytes.length , address,port);
                try {
                    socket.send(packet);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            else if (choice==2) {

                String respond=Server.sortDescending(NumbersList);
                InetAddress address = packet.getAddress();
                int port = packet.getPort();
                byte [] replyBytes= respond.getBytes();
                packet = new DatagramPacket(replyBytes , replyBytes.length , address,port);
                try {
                    socket.send(packet);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

//            if (recivied.equals("close")){
//                breakl
        }


    }
}

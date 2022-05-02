package com.company;

import java.io.IOException;
import java.net.*;
import java.util.ArrayList;
import java.util.Collections;

public class Server {
    public static DatagramSocket socket;

    public static String sortAscending(ArrayList<Integer> example)
    {
        StringBuilder respond=new StringBuilder();
        Collections.sort(example);
        for(Integer x:example)
        {
            respond.append(x).append(",");
        }
        return respond.toString();
    }
    public static String sortDescending(ArrayList<Integer> example)
    {
        StringBuilder respond=new StringBuilder();
        example.sort(Collections.reverseOrder());
        for(Integer x:example)
        {
            respond.append(x).append(",");
        }
        return respond.toString();
    }
    public static void main(String[] args) throws IOException {
        try {
            socket = new DatagramSocket(4445);
        } catch (SocketException e) {
            e.printStackTrace();
        }
        Multiple M= new Multiple(socket);
        new Thread(M).start();
        // write your code here

        }
    }


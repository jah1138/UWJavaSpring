//package cp125.week8;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 * Author: Alex
 * Version: 5/31/2014.
 */
public class ChatST {

    public static void main (String[]args) {

        int port = 0;
        String host = null;
        Boolean listener = false;

        String usage = "Usage: \nTo listen: " + ChatST.class.getName() + " port \n" +
                        "To connect: " + ChatST.class.getName() + " host port";

        /* Check parameters */
        switch (args.length) {
            case 0:
                System.out.println(usage);
                System.exit(1);
            case 1:
                if (checkValidPort(args[0])) {
                    port = Integer.parseInt(args[0]);
                    listener = true;
                    break;
                } else {
                    System.out.println(args[0] + " is not a valid port number");
                    System.exit(1);
                }
            case 2:
                if (checkValidPort(args[1])) {
                    port = Integer.parseInt(args[1]);
                    host = args[0];
                    break;
                } else {
                    System.out.println(args[0] + " is not a valid port number");
                    System.exit(1);
                }
            default:
                System.out.println(usage);
                System.exit(1);
        }

        /* Create sockets and call chat */
        try {
            if (listener) {
                ServerSocket ss = new ServerSocket(port);
                System.out.println("Listening on : " + ss);
                Socket s = ss.accept();
                System.out.println("Connected to : " + s);
                chatListener(s);
            } else {
                Socket s = new Socket(host, port);
                chatConnector(s);
            }
        } catch (UnknownHostException uhe) {
            System.out.println("Host " + host + " cannot be resolved");
            System.exit(1);
        } catch (IOException ioe) {
            System.out.println("IO exception attempting connection to " + host + " port " + port);
            System.exit(1);
        }
    }

    // In the listener program, read from peer THEN user
    static void chatListener( Socket s ) throws IOException {
        InputStream is = s.getInputStream();
        OutputStream os = s.getOutputStream();

        boolean autoFlush = true;

        // objects for reading,writing text over the socket
        BufferedReader brPeer = new BufferedReader
                (new InputStreamReader(is));
        PrintWriter pwPeer = new PrintWriter
                (new OutputStreamWriter(os), autoFlush);

        // objects for reading,writing text at the user console
        BufferedReader brUser = new BufferedReader
                (new InputStreamReader(System.in));
        PrintWriter pwUser = new PrintWriter(System.out, autoFlush);

        while (true) {
            String line = brPeer.readLine();
            pwUser.println(line);
            line = brUser.readLine();
            pwPeer.println(line);
        }
    }


    // In the connector program, read from user THEN peer
    static void chatConnector( Socket s ) throws IOException {
        InputStream is = s.getInputStream();
        OutputStream os = s.getOutputStream();

        boolean autoFlush = true;

        // objects for reading,writing text over the socket
        BufferedReader brPeer = new BufferedReader
                ( new InputStreamReader( is ) );
        PrintWriter pwPeer = new PrintWriter
                ( new OutputStreamWriter( os ), autoFlush );

        // objects for reading,writing text at the user console
        BufferedReader brUser = new BufferedReader
                ( new InputStreamReader( System.in ) );
        PrintWriter pwUser = new PrintWriter( System.out, autoFlush );

        while( true ) {
            String line = brUser.readLine();
            pwPeer.println( line );
            line = brPeer.readLine();
            pwUser.println( line );
        }
    }


    private static Boolean checkValidPort( String s) {
        int num = -1;
        try {
            num = Integer.parseInt(s);
        }
        catch(NumberFormatException nfe) {
            return false;
        }
        return ((num >= 0 && num <= 65534));
    }

}

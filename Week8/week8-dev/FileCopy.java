import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.RandomAccessFile;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author Stuart Maclean
 *
 * Show how to copy a file from one host to another.  We include both
 * the server logic, which accepts data pushed to it, and the client
 * logic, which does the pushing, here in this one source file. We
 * could have also coded separate 'FileCopySender' and
 * 'FileCopyReceiver' classes (like ftp/ftpd).

 * We thus need two distinct VMs to run this application.  We switch
 * on whether to run in server vs client 'mode' via cmd line arg
 * count:
 *
 * FileCopy port == run as the server
 *
 * FileCopy host port fileName == run as the client
 *
 * There is a mini 'protocol' involved.  The client sends
 *
 * NameLength - 4 bytes
 * Name - NameLength bytes
 * ContentLength - 4 bytes
 * Content - ContentLength bytes
 *
 * The server accepts the data and creates Name with supplied Content,
 * unless Name exists locally.  Either way, the client is NOT informed of
 * the server's actions.
 *
 * This is obviously not as fully fledged an application as say ftp or
 * scp, since it only supports file copy in one direction and there is
 * no 'feedback'.. But it does show how to move binary data over
 * sockets, and has plenty of file I/O too.
 *
 * Note the sender informs the receiver of lengths, both file name
 * length and content name length.  Another way to achieve this
 * 'knowledge transfer' is for a special character to be added at the
 * end of the data parts.  But then what if some data blob actually
 * INCLUDES such a special char.  Better to say 'prepare to read N
 * bytes' and then send those N bytes.
 */
public class FileCopy {

	static public void main( String[] args ) {

		String usage = "Usage: " + FileCopy.class.getName() +
			" port | (host port fileName)";
		if( args.length == 1 ) {
			try {
				server( args );
			} catch( Exception e ) {
				System.err.println( e );
				System.err.println( usage );
			}
		} else if( args.length == 3 ) {
			try {
				client( args );
			} catch( Exception e ) {
				System.err.println( e );
				System.err.println( usage );
			}
		} else {
			System.err.println( usage );
			System.exit(1);
		}
	}

	/**
	 * The server side.  Listen on the supplied port (args[0]) for a
	 * connecting FileCopy client.  Then read nameLength,name,contentLength
	 * and content.  Check target file name exists, if yes bail.  Else write
	 * the supplied content to the newly created file
	 *
	 * Remember that reading data off InputStreams is not as simple as
	 * it is writing to OutputStreams.  With input, you never know how
	 * many bytes any individual call to InputStream.read (or one of
	 * its subclasses) is going to return.  So we must wrap the reads in a loop
	 * where we DO know the desired byte count.  We grab the two desired counts
	 * (name length and content length) via DataInputStream.readInt(), which
	 * always reads 4 bytes in (using network byte order remember), even if
	 * it takes 2+ read calls to consume those 4 bytes.
	 */
	static void server( String[] args ) throws IOException {
		int port = Integer.parseInt( args[0] );
		ServerSocket ss = new ServerSocket( port );
		System.out.println( "Listening on " + ss );
		Socket s = ss.accept();
		InputStream is = s.getInputStream();
		// need a DataInputStream in order to read ints
		DataInputStream dis = new DataInputStream( is );
		int nameLength = dis.readInt();
		byte[] nameBytes = new byte[nameLength];
		readN( dis, nameBytes );
		int contentLength = dis.readInt();
		byte[] content = new byte[contentLength];
		// can pass DataInputStream to readN, since it is an InputStream
		readN( dis, content );
		dis.close();

		// name supplied as byte[], convert to string (uses Charsets)
		String nameStr = new String( nameBytes );
		File f = new File( nameStr );
		if( f.exists() ) {
			System.err.println( "Skipping, existing file: " + f );
			return;
		}

		System.out.println( "Writing " + f + ", " + contentLength + " bytes" );
		FileOutputStream fos = new FileOutputStream( f );
		fos.write( content );
		fos.close();
	}

	static int readN( InputStream is, byte[] b ) throws IOException {
		return readN( is, b, 0, b.length );
	}

	/**
	 * How to put InputStream.read in a loop so we always get all of
	 * 'len' bytes, since recall that any single call to
	 * InputStream.read can return 1<=n<=len bytes.  This is
	 * particularly true for network-facing InputStreams, where
	 * Internet data can be fragmented and take arbitrary routes from
	 * source to destination.
	 *
	 * Note how we 'walk along' the supplied array b, adjusting both
	 * where to next store data (offset+total) and how much we are
	 * willing to read in the next read call (len-total)
	 */
	static int readN( InputStream is,
					  byte[] b, int offset, int len ) throws IOException {
		int total = 0;
		while( total < len ) {
			int nin = is.read( b, offset+total, len-total );
			if( nin == -1 )
				break;
			total += nin;
		}
		return total;
	}
	
	static void client( String[] args ) throws IOException {
		String host = args[0];
		int port = Integer.parseInt( args[1] );
		String fileName = args[2];
		File f = new File( fileName );
		if( !f.canRead() ) {
			System.err.println( "Unreadable/missing: " + f );
			return;
		}
		System.out.println( "Writing " + f + ", " + f.length() + " bytes" );
		Socket s = new Socket( host, port );
		OutputStream os = s.getOutputStream();
		DataOutputStream dos = new DataOutputStream( os );
		byte[] bs = fileName.getBytes();
		// first advertise the file name length, followed by the name
		dos.writeInt( bs.length );
		dos.write( bs );
		RandomAccessFile raf = new RandomAccessFile( f , "r" );
		byte[] content = new byte[(int)f.length()];
		raf.readFully( content );
		raf.close();
		// now send the content length, followed by the content
		dos.writeInt( (int)f.length() );
		dos.write( content );
		dos.close();
		
	}
	
}

// eof

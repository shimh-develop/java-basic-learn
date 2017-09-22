package nio;

import java.io.FileInputStream;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
/**
 * *****************************************************************
 * Created on 2017年9月18日 下午2:38:57
 * @author shimh
 * 功能说明： ------ FileChannel 读写文件 ------
 *
 ******************************************************************
 */
public class FileChannelDemo {
	
	private static void buffer(){
		//Buffer
	    //ByteBuffer
	    //MappedByteBuffer
	    //CharBuffer
	    //DoubleBuffer
	    //FloatBuffer
	    //IntBuffer
	    //LongBuffer
	    //ShortBuffer
	}
	private static void channel(){
		//Channel
	    //FileChannel
	    //DatagramChannel
	    //SocketChannel
	    //ServerSocketChannel
	}
	private static void readFile() throws Exception{
		FileChannel channel = new FileInputStream("D:BugReport.txt").getChannel();
		ByteBuffer buffer = ByteBuffer.allocate(256);
		
		int read = channel.read(buffer);
		
		while (read != -1) {
			buffer.flip();
			while(buffer.hasRemaining()){
				System.out.print((char) buffer.get());
			}
			buffer.clear();
			read = channel.read(buffer);
		}
		channel.close();
	}
	private static void writeFile() throws Exception{
		FileChannel fc = new RandomAccessFile("D:BugReport.txt","rw").getChannel();       
	    fc.position(fc.size()); //定位到文件末尾  
	    fc.write(ByteBuffer.wrap("new data!".getBytes()));  
	    fc.close(); 
	}
	
	private static void copyFile() throws Exception{
		RandomAccessFile fromFile = new RandomAccessFile("D:BugReport.txt", "rw");
		FileChannel      fromChannel = fromFile.getChannel();

		RandomAccessFile toFile = new RandomAccessFile("D:BugReport2.txt", "rw");
		FileChannel      toChannel = toFile.getChannel();

		long position = 0;
		long count = fromChannel.size();

		fromChannel.transferTo(position, count, toChannel);
		fromFile.close();
		toFile.close();
	}
	public static void main(String[] args) throws Exception {
		//writeFile();
		//readFile();
		copyFile();
	}
}

package nio;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

public class ScatterAndGather {
	
	
	public static void main(String[] args) throws Exception {
		scatter();
		gather();
	}
	
	/**
	 * 
	 * 功能说明：分散（scatter） 数据从一个channel读取到多个buffer中
	 * read()方法按照buffer在数组中的顺序将从channel中读取的数据写入到buffer，
	 * 当一个buffer被写满后，channel紧接着向另一个buffer中写。
	 * Scattering Reads在移动下一个buffer前，必须填满当前的buffer，这也意味着它不适用于动态消息(译者注：消息大小不固定)。
	 * 换句话说，如果存在消息头和消息体，消息头必须完成填充（例如 128byte），Scattering Reads才能正常工作。
	 * @throws Exception 
	 *
	 *
	 */
	public static void scatter() throws Exception{
		
		//1.txt: aa
		FileChannel channel = new FileInputStream("D:1.txt").getChannel();
		
		ByteBuffer header = ByteBuffer.allocate(1);
		ByteBuffer body   = ByteBuffer.allocate(4);
		 
		ByteBuffer[] bufferArray = { header, body };
		 
		channel.read(bufferArray);
		channel.close();
		
		header.flip();
		body.flip();
		while (header.hasRemaining()) {
			System.out.println((char)header.get());
		}
		System.out.println("-----");
		while (body.hasRemaining()) {
			System.out.println((char)body.get());
		}
		
	}
	/**
	 * 
	 * 功能说明：Gathering 是指数据从多个buffer写入到同一个channel
	 *
	 * @throws Exception
	 *
	 */
	public static void gather() throws Exception{
		
		FileChannel channel = new FileOutputStream("D:1.txt").getChannel();
		
		ByteBuffer header = ByteBuffer.wrap("header".getBytes());
		ByteBuffer body   = ByteBuffer.wrap("body".getBytes());
		 
		ByteBuffer[] bufferArray = { header, body };
		
		channel.write(bufferArray);
		channel.close();
		
	}
	
	
}

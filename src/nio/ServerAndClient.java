package nio;

import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.concurrent.TimeUnit;


public class ServerAndClient {
	public static void main(String[] args) throws Exception {
		servetSocket();
		//TimeUnit.SECONDS.sleep( 5 );
		socket();
		
	}
	
	private static void socket() throws Exception{
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				try{
					SocketChannel socketChannel = SocketChannel.open();
					socketChannel.connect(new InetSocketAddress("localhost", 8070));
					
					String newData = "New String to write to file...";
	
					ByteBuffer buf = ByteBuffer.allocate(48);
					buf.clear();
					buf.put(newData.getBytes());
	
					buf.flip();
	
					while(buf.hasRemaining()) {
						socketChannel.write(buf);
					}
					socketChannel.close();
				}catch(Exception e){
					
				}
			}
		}).start();
		
	}
	
	private static void servetSocket() throws Exception{
		new Thread(new Runnable() {
			@Override
			public void run() {
				try{
					ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
			
					serverSocketChannel.socket().bind(new InetSocketAddress(8070));
					serverSocketChannel.configureBlocking(false);
					while(true){
					    SocketChannel socketChannel =
					            serverSocketChannel.accept();
					    if(socketChannel != null){
					    	ByteBuffer buf = ByteBuffer.allocate(48);
						    ByteBuffer buf2 = ByteBuffer.wrap("123456".getBytes());
						    int read = socketChannel.read(buf);
						    System.out.println("服务端数据-----");
						    while (read != -1) {
						    	buf.flip();
						    	while (buf.hasRemaining()) {
									System.out.print((char)buf.get());
									socketChannel.write(buf);
								}
								buf.clear();
								read = socketChannel.read(buf);
								
							}
						   
					    }
					    
					}
				}catch(Exception e){
					
				}
			}
		}).start();
		
	}
}

package nio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;
/**
 * *****************************************************************
 * Created on 2017骞�9鏈�18鏃� 涓嬪崍2:38:57
 * @author shimh
 * 鍔熻兘璇存槑锛� ------ FileChannel 璇诲啓鏂囦欢 ------
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
	    fc.position(fc.size()); //瀹氫綅鍒版枃浠舵湯灏�  
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
	
	
	public static File writeString2File(String text, String fileName){
		FileOutputStream out = null;  
       File file = null;  
 		FileChannel fc = null;
         try {  
             
             file = new File(fileName);  
             out = new FileOutputStream(file);  
             fc = out.getChannel();  
             fc.write(ByteBuffer.wrap(text.getBytes())); 
         } catch (Exception e) {  
             e.printStackTrace();  
         } finally {  
             if (fc != null) {  
                 try {  
                     fc.close();  
                 } catch (IOException e1) {  
                     e1.printStackTrace(); 
                 }
             if (out != null) {  
                 try {  
                    out.close();  
                 } catch (IOException e) {  
                     e.printStackTrace();  
                 }  
             } 
             }
         } 
         return file;
	}

	public static byte[] readFile2Bytes(String filename) throws IOException {  
  
        File f = new File(filename);  
        if (!f.exists()) {  
            throw new FileNotFoundException(filename);  
        }  
  
        FileChannel channel = null;  
        FileInputStream fs = null;  
        try {  
            fs = new FileInputStream(f);  
            channel = fs.getChannel();  
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());  
            while ((channel.read(byteBuffer)) > 0) {  
                // do nothing  

                // System.out.println("reading");  

            }  
            return byteBuffer.array();  
        } catch (IOException e) {  
            e.printStackTrace();  
            throw e;  
        } finally {  
            try {  
                channel.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
            try {  
                fs.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }  

	    /** 

     * Mapped File way MappedByteBuffer 可以在处理大文件时，提升性能 

     *  

     * @param filename 

     * @return 

     * @throws IOException 

     */  
    public static byte[] toByteArray3(String filename) throws IOException {  
  
        FileChannel fc = null;  
        try {  
            fc = new RandomAccessFile(filename, "r").getChannel();  
            MappedByteBuffer byteBuffer = fc.map(MapMode.READ_ONLY, 0,  
                    fc.size()).load();  
            System.out.println(byteBuffer.isLoaded());  
            byte[] result = new byte[(int) fc.size()];  
            if (byteBuffer.remaining() > 0) {  
                // System.out.println("remain");  

                byteBuffer.get(result, 0, byteBuffer.remaining());  
            }  
            return result;  
        } catch (IOException e) {  
            e.printStackTrace();  
            throw e;  
        } finally {  
            try {  
                fc.close();  
            } catch (IOException e) {  
                e.printStackTrace();  
            }  
        }  
    }
	public static void main(String[] args) throws Exception {
		//writeFile();
		//readFile();
		copyFile();
	}
}

package io;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;

public class FileUtil {
	
	public static boolean createFile(String destFileName) {  
        File file = new File(destFileName);  
        if(file.exists()) {  
            System.out.println("���������ļ�" + destFileName + "ʧ�ܣ�Ŀ���ļ��Ѵ��ڣ�");  
            return false;  
        }  
        if (destFileName.endsWith(File.separator)) {  
            System.out.println("���������ļ�" + destFileName + "ʧ�ܣ�Ŀ���ļ�����ΪĿ¼��");  
            return false;  
        }  
        //�ж�Ŀ���ļ����ڵ�Ŀ¼�Ƿ����  
        if(!file.getParentFile().exists()) {  
            //���Ŀ���ļ����ڵ�Ŀ¼�����ڣ��򴴽���Ŀ¼  
            System.out.println("Ŀ���ļ�����Ŀ¼�����ڣ�׼����������");  
            if(!file.getParentFile().mkdirs()) {  
                System.out.println("����Ŀ���ļ�����Ŀ¼ʧ�ܣ�");  
                return false;  
            }  
        }  
        //����Ŀ���ļ�  
        try {  
            if (file.createNewFile()) {  
                System.out.println("���������ļ�" + destFileName + "�ɹ���");  
                return true;  
            } else {  
                System.out.println("���������ļ�" + destFileName + "ʧ�ܣ�");  
                return false;  
            }  
        } catch (IOException e) {  
            e.printStackTrace();  
            System.out.println("���������ļ�" + destFileName + "ʧ�ܣ�" + e.getMessage());  
            return false;  
        }  
    }  
     
     
    public static boolean createDir(String destDirName) {  
        File dir = new File(destDirName);  
        if (dir.exists()) {  
            System.out.println("����Ŀ¼" + destDirName + "ʧ�ܣ�Ŀ��Ŀ¼�Ѿ�����");  
            return false;  
        }  
        if (!destDirName.endsWith(File.separator)) {  
            destDirName = destDirName + File.separator;  
        }  
        //����Ŀ¼  
        if (dir.mkdirs()) {  
            System.out.println("����Ŀ¼" + destDirName + "�ɹ���");  
            return true;  
        } else {  
            System.out.println("����Ŀ¼" + destDirName + "ʧ�ܣ�");  
            return false;  
        }  
    }  
     
     
    public static String createTempFile(String prefix, String suffix, String dirName) {  
        File tempFile = null;  
        if (dirName == null) {  
            try{  
                //��Ĭ���ļ����´�����ʱ�ļ�  
                tempFile = File.createTempFile(prefix, suffix);  
                //������ʱ�ļ���·��  
                return tempFile.getCanonicalPath();  
            } catch (IOException e) {  
                e.printStackTrace();  
                System.out.println("������ʱ�ļ�ʧ�ܣ�" + e.getMessage());  
                return null;  
            }  
        } else {  
            File dir = new File(dirName);  
            //�����ʱ�ļ�����Ŀ¼�����ڣ����ȴ���  
            if (!dir.exists()) {  
                if (!FileUtil.createDir(dirName)) {  
                    System.out.println("������ʱ�ļ�ʧ�ܣ����ܴ�����ʱ�ļ����ڵ�Ŀ¼��");  
                    return null;  
                }  
            }  
            try {  
                //��ָ��Ŀ¼�´�����ʱ�ļ�  
                tempFile = File.createTempFile(prefix, suffix, dir);  
                return tempFile.getCanonicalPath();  
            } catch (IOException e) {  
                e.printStackTrace();  
                System.out.println("������ʱ�ļ�ʧ�ܣ�" + e.getMessage());  
                return null;  
            }  
        }  
    }  
	
    /**
     * ɾ��Ŀ¼ ���µ����е��ļ�
     * 
     * @param file  Ŀ¼
     */
	public static void deleteDir(File file){
		File[] files = file.listFiles();
		for(File f:files){
			
			if(f.isDirectory()){
				deleteDir(f);
			}else{
				f.delete();
			}
			
		}
		
		file.delete();
	}
	
	/** 
	 * 	�����ļ�    �������ļ�  д���ļ�
	 * 
	 * @param src
	 * @param dest
	 * @throws Exception 
	 */
	public static void copyFile(String src, String dest) throws Exception{
		FileInputStream in = new FileInputStream(src);
		
		FileOutputStream out = new FileOutputStream(dest);
		
		byte[] buf = new byte[1024];
		
		int read;
		
		while((read = in.read(buf)) != -1){
			out.write(buf, 0, read);
		}
		
		in.close();
		out.close();
	}
	
	public static void main(String[] args) throws Exception {
		  RandomAccessFile file = new RandomAccessFile("E:\\shi.txt", "rw");
		  // 以下向file文件中写数据
		  file.writeInt(20);// 占4个字节
		  file.writeDouble(8.236598);// 占8个字节
		  file.writeUTF("这是一个UTF字符串");// 这个长度写在当前文件指针的前两个字节处，可用readShort()读取
		  file.writeBoolean(true);// 占1个字节
		  file.writeShort(395);// 占2个字节
		  file.writeLong(2325451l);// 占8个字节
		  file.writeUTF("又是一个UTF字符串");
		  file.writeFloat(35.5f);// 占4个字节
		  file.writeChar('a');// 占2个字节

		  file.seek(0);// 把文件指针位置设置到文件起始处

		  // 以下从file文件中读数据，要注意文件指针的位置
		  System.out.println("——————从file文件指定位置读数据——————");
		  System.out.println(file.readInt());
		  System.out.println(file.readDouble());
		  System.out.println(file.readUTF());

		  file.skipBytes(3);// 将文件指针跳过3个字节，本例中即跳过了一个boolean值和short值。
		  System.out.println(file.readLong());

		  file.skipBytes(file.readShort()); // 跳过文件中“又是一个UTF字符串”所占字节，注意readShort()方法会移动文件指针，所以不用加2。
		  System.out.println(file.readFloat());
	}
	
}

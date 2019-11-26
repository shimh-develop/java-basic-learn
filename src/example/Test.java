package example;

import java.io.*;
import java.nio.ByteBuffer;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author: shimh
 * @create: 2019年09月
 **/
public class Test {

    public static void main(String[] args) throws Exception{
//        System.out.println(10 << 1);
//
//        CharSequence charSequence = "shimh";
//        String s = "shimh";
//        System.out.println(charSequence);
//        System.out.println(s);
//
//        StringBuilder sb = new StringBuilder("12345");
//        sb.setLength(3);
//        sb.append("678");
//        System.out.println(sb);
//        System.out.println(sb.capacity());
//        System.out.println(sb.length());
//
//        System.out.println(Byte.TYPE );
//        System.out.println(Byte.valueOf((byte)10) == Byte.valueOf((byte)10));
//        System.out.println(Byte.parseByte("10") == Byte.parseByte("10"));
//        System.out.println(Byte.decode("0x10"));
//        System.out.println(Byte.toUnsignedInt((byte)10000));
        System.out.println(Integer.parseInt("21", 10));
        System.out.println("21".charAt(0));
        byte b = (byte) 131;
        System.out.println(b);
        System.out.println(-131 + 127);
        print(Integer.parseInt("10", 10));
        print(Integer.parseInt("10", 2));
        print(Integer.parseInt("123", 8));
        String binary="11000000101010000000000100000001";
        int ipnum = Integer.parseUnsignedInt(binary,2);
        System.out.println(ipnum);
        print(Integer.decode("-2147483648"));
        print(Integer.bitCount(214748364));
        Short aa = 3;

        List<String> l1 = new ArrayList<>();
        l1.add("1");
        l1.add("2");
        l1.add("3");
        l1.add("4");
        ListIterator<String> i1 = l1.listIterator(1);
        String a = i1.next();
        print(a);
        //i1.set("D");
//        while (i1.hasNext()){
//            String a = i1.previous();
//            i1.add("D");
//            //i1.set("A");
//            print(a);
//            //i1.remove();
////            if (a.equals("2"))
////                break;
//        }
        print(l1);
        i1.forEachRemaining(obj -> print(obj + " ```"));

        List<String> sub = l1.subList(1, 3);
        print(sub);
        print( Integer.MAX_VALUE + 1 );
        l1.remove(1);
        l1.add("5");
        print(l1);
        String[] ss = {"a", "b", "c", "d"};
        int numMoved = 4 - 1 - 1;
        if (numMoved > 0)
            System.arraycopy(ss, 1+1, ss, 1,
                    numMoved);
        //ss[3] = null;
        print(Arrays.toString(ss));
        print(ss.length);
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("1");
        print(linkedList.getFirst());
        print(linkedList.getLast());
        Map<String, String> map = new HashMap<>();
        map.put("1", "a");
        map.put("2", null);
        print(map.getOrDefault("1", "b"));
        print(map.getOrDefault("2", "b"));
        print(map.getOrDefault("3", "b"));
        map.forEach((x, y) -> print(x + " : " + y));
        //map.replaceAll((x, y) -> x + y);
        map.putIfAbsent("2", "C");
        print(map);
        print(tableSizeFor(15));
        int cc = 3;
        if(cc > 1) {
            print("1");
        }else if(cc > 2) {
            print("2");
        }
        print(1^1);
        print(11>>2);

        Map<String, String> m = new HashMap<>();
        m.put("1", null);
        print(m.containsKey("1"));
        print(calculateSize(16));
        print(tableSizeFor(16));

        byte b1 = (byte)170;
        print(b1);
//        10101010
//        11010101
//        11010110

       int x;
       print(Integer.parseInt("01111111", 2));
       Class s = Boolean.TYPE;

//        FileOutputStream fos = new FileOutputStream("/Users/handu/test.txt");
//
//
//        fos.write(new byte[] { 0x61, 0x62, 0x63, 0x64 });
//
//        fos.close();


        FileInputStream fis = new FileInputStream("/Users/handu/test.txt");
        FileOutputStream fos = new FileOutputStream("/Users/handu/test2.txt");

        byte[] bytes = new byte[1024];
        int len = 0;
        while (( len = fis.read(bytes)) != -1) {
            fos.write(bytes, 0, len);
        }
        fis.close();
        fos.close();


        // InputStream
        // OutputStream
        //ByteArrayInputStream
        //ByteArrayOutputStream
        //PipedInputStream
        //PipedOutputStream
        //FilterInputStream
        //BufferedInputStream
        //BufferedOutputStream
        //DataInputStream
        //DataOutputStream
        //FileInputStream
        //FileOutputStream
        //Reader
        //Writer
        //CharArrayReader
        //CharArrayWriter
        //BufferedReader
        //BufferedWriter
        //InputStreamReader
        //OutputStreamWriter
        //FileReader
        Thread thread;
        //ConcurrentHashMap
        //ConcurrentLinkedQueue

        String[] threads = {"0", "1", "2", "3", "4"};
        int nthreads = 5;
        for (int i = 0 ; i < nthreads ; i++) {
            if (threads[i].equals("2")) {
                System.arraycopy(threads, i + 1, threads, i, --nthreads - i);
                print(Arrays.toString(threads));
                // Zap dangling reference to the dead thread so that
                // the garbage collector will collect it.
                threads[nthreads] = null;
                break;
            }
        }
        print(Arrays.toString(threads));
        //Lock
        //ReentrantLock
        // LockSupport

    }
    private static int calculateSize(int numElements) {
        int initialCapacity = 8;
        // Find the best power of two to hold elements.
        // Tests "<=" because arrays aren't kept full.
        if (numElements >= initialCapacity) {
            initialCapacity = numElements;
            initialCapacity |= (initialCapacity >>>  1);
            initialCapacity |= (initialCapacity >>>  2);
            initialCapacity |= (initialCapacity >>>  4);
            initialCapacity |= (initialCapacity >>>  8);
            initialCapacity |= (initialCapacity >>> 16);
            initialCapacity++;

            if (initialCapacity < 0)   // Too many elements, must back off
                initialCapacity >>>= 1;// Good luck allocating 2 ^ 30 elements
        }
        return initialCapacity;
    }
    static final int tableSizeFor(int cap) {
        int n = cap - 1;
        n |= n >>> 1;
        n |= n >>> 2;
        n |= n >>> 4;
        n |= n >>> 8;
        n |= n >>> 16;
        return (n < 0) ? 1 :  n + 1;
    }
    public static void print(Object obj) {
        System.out.println(obj);
    }
}

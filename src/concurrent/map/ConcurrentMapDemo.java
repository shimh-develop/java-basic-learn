package concurrent.map;

import java.util.concurrent.ConcurrentHashMap;

public class ConcurrentMapDemo {
	public static void main(String[] args) {
		//ConcurrentMap
		//ConcurrentHashMap  HashMap
		
		concurrentHashMap();
	}
	/**
	 * 
	 * 功能说明：
	 * ConcurrentHashMap 和 java.util.HashTable 类很相似，但 ConcurrentHashMap 能够提供比 HashTable 更好的并发性能。
	 * 在你从中读取对象的时候 ConcurrentHashMap 并不会把整个 Map 锁住。
	 * 此外，在你向其中写入对象的时候，ConcurrentHashMap 也不会锁住整个 Map。
	 * 它的内部只是把 Map 中正在被写入的部分进行锁定。
	 * 另外一个不同点是，在被遍历的时候，即使是 ConcurrentHashMap 被改动，它也不会抛 ConcurrentModificationException。
	 * 尽管 Iterator 的设计不是为多个线程的同时使用。
	 *
	 */
	private static void concurrentHashMap(){
		ConcurrentHashMap<String,String> map = new ConcurrentHashMap<String,String>();
		
		/**
		 * 如果不存在key对应的值，则将value以key加入Map，否则返回key对应的旧值
		 * As if (!map.containsKey(key))
   		 *		return map.put(key, value);
		 *	  else
   		 *		return map.get(key);
		 */
		System.out.println(map.putIfAbsent("1", "a")); //null
		System.out.println(map.putIfAbsent("1", "b")); //a
		System.out.println(map); //{1=a}
		
		/**
		 * if (map.containsKey(key) && map.get(key).equals(value)) {
		 *	   map.remove(key);
		 *	   return true;
		 *	}
		 *	return false;
		 */
		System.out.println(map.remove("1", "b")); //false
		System.out.println(map); //{1=a}
		/**
		 * if (map.containsKey(key) && map.get(key).equals(oldValue)) {
		 *	   map.put(key, newValue);
		 *	   return true;
		 *	}
		 *	return false;
		 */
		System.out.println(map.replace("1", "a", "b")); //true
		System.out.println(map); //{1=b}
		
	}
}

package concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class TaskService implements Runnable {
	
	private CountDownLatch cdl;
	private String name;
	private boolean taskEnd;
	
	TaskService(CountDownLatch cdl,String name){
		this.cdl = cdl;
		this.name = name;
		this.taskEnd = false;
	}
	
	@Override
	public void run() {
		//执行任务
		System.out.println(getName() + "执行了。。。。");
		try {
			TimeUnit.MILLISECONDS.sleep(2000);;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		setTaskEnd(true);
		System.out.println(getName() + "=" + isTaskEnd());
		cdl.countDown();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isTaskEnd() {
		return taskEnd;
	}

	public void setTaskEnd(boolean taskEnd) {
		this.taskEnd = taskEnd;
	}

}

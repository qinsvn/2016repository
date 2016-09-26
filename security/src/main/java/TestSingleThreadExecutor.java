import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TestSingleThreadExecutor {
	public static void main(String[] args) {
		ExecutorService executorService = Executors.newSingleThreadExecutor();
		Thread t1 = new MyThread("t1");
		Thread t2 = new MyThread("t2");
		Thread t3 = new MyThread("t3");
		Thread t4 = new MyThread("t4");
		executorService.execute(t1);
		executorService.execute(t2);
		executorService.execute(t3);
		executorService.execute(t4);
		executorService.shutdown();
	}
}
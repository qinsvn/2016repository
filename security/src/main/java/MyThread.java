 public class MyThread extends Thread {
private String name;


public MyThread(String name) {
this.name = name;
}


@Override
public void run() {
// TODO Auto-generated method stub
try {
Thread.sleep(1000);
} catch (InterruptedException e) {
// TODO Auto-generated catch block
e.printStackTrace();
}
System.out.println("线程"+Thread.currentThread().getName()+  "正在进行任务==》"+name);
}
}


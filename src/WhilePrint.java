import java.util.concurrent.Semaphore;

public class WhilePrint {
    private static Semaphore s1 = new Semaphore(1);
    private static Semaphore s2 = new Semaphore(0);

    public static void main(String[] args) throws Exception {
        Thread t1 = new Thread(() -> {
            while(true){
                try{
                    s1.acquire();
                    System.out.print("1\n");
                    s2.release();
                }catch(Exception ex){ex.printStackTrace();}
            }
        });

        Thread t2 = new Thread(() -> {
            while (true){
                try{
                    s2.acquire();
                    System.out.print("2 \n");
                    s1.release();
                }catch(Exception ex){ex.printStackTrace();}
            }
        });

        t1.start(); t2.start();
        t1.join(); t2.join();
    }
}

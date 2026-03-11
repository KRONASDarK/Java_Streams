import java.util.concurrent.TimeUnit;
import java.util.Random;

public class Livelock {
    public static void main(String[] args) {
        Random rand = new Random();
        Thread thread1 = new Thread(() -> {
            while(true) {
                if(rand.nextBoolean()) {
                    System.out.println("Иван: Подожду Сергея.");
                    sleepRandomTime();
                } else {
                    System.out.println("Иван: Пусть Сергей проходит первым!");
                    sleepRandomTime();
                }
            }
        });

        Thread thread2 = new Thread(() -> {
            while(true) {
                if(rand.nextBoolean()) {
                    System.out.println("Сергей: Подожду Ивана.");
                    sleepRandomTime();
                } else {
                    System.out.println("Сергей: Пусть Иван проходит первым!");
                    sleepRandomTime();
                }
            }
        });

        thread1.start();
        thread2.start();
    }

    private static void sleepRandomTime() {
        try {
            TimeUnit.SECONDS.sleep((long)(Math.random()*3));
        } catch(InterruptedException ignored){}
    }
}

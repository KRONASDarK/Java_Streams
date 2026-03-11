public class Deadlock {
    private static final Object RESOURCE_A = new Object();
    private static final Object RESOURCE_B = new Object();

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            synchronized (RESOURCE_A) {
                System.out.println("Поток 1 захватил ресурс A");
                try {
                    Thread.sleep(100); // Пауза перед попыткой захвата другого ресурса
                } catch (InterruptedException e) {}
                synchronized (RESOURCE_B) {
                    System.out.println("Поток 1 захватил ресурс B");
                }
            }
        });

        Thread t2 = new Thread(() -> {
            synchronized (RESOURCE_B) {
                System.out.println("Поток 2 захватил ресурс B");
                try {
                    Thread.sleep(100); // Пауза перед попыткой захвата другого ресурса
                } catch (InterruptedException e) {}
                synchronized (RESOURCE_A) {
                    System.out.println("Поток 2 захватил ресурс A");
                }
            }
        });

        t1.start(); t2.start();
        t1.join(); t2.join();
    }
}

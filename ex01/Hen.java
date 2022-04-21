public class Hen extends Thread {
    private int numberOfAnswers;
    private Egg egg;


    public Egg getEgg() {
        return egg;
    }

    public void setEgg(Egg egg) {
        this.egg = egg;
    }

    public Hen(int numberOfAnswers) {
        this.numberOfAnswers = numberOfAnswers;
    }

    public void run() {
       synchronized (egg) {
           try {
               for (int i = 0; i < numberOfAnswers; i++) {
                   egg.wait();
                   System.out.println("Hen");
                   egg.notify();
               }
           } catch (Exception e) {
               System.out.println(e);
           }
       }
    }
}
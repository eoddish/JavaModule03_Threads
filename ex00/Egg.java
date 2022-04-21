public class Egg extends Thread {

    private Hen hen;
    private int numberOfAnswers;

    public Egg(int numberOfAnswers) {
        this.numberOfAnswers = numberOfAnswers;
    }

    public Hen getHen() {
        return hen;
    }

    public void setHen(Hen hen) {
        this.hen = hen;
    }

    public void run () {

                for (int i = 0; i < numberOfAnswers; i++) {
                    System.out.println("Egg");
                }
    }
}
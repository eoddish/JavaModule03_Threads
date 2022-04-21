public class Program {
    public static void main(String[] args) {
        if (args.length < 1 || args[0].length() < "--count=".length() || !args[0].substring(0, "--count=".length()).equals("--count=")) {
            System.out.println("Error: Wrong arguments!");
            System.exit(-1);
        }
        Integer numberOfAnswers = Integer.parseInt(args[0].substring("--count=".length(), args[0].length()));

        Egg egg = new Egg(numberOfAnswers);
        Hen hen = new Hen(numberOfAnswers);

        egg.setHen(hen);
        hen.setEgg(egg);

        hen.start();
        egg.start();

        try {
            hen.join();
            egg.join();
        } catch (Exception e) {
            System.out.println(e);
        }




            for (int i = 0; i < numberOfAnswers; i++) {
                System.out.println("Human");
            }

    }
}
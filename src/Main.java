import island.Island;
import simulation.Simulation;

public class Main {
    public static void main(String[] args) {
        Island island = new Island(100, 20);
        Simulation simulation = new Simulation(island);


        simulation.start();

        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        simulation.stop();
    }
}

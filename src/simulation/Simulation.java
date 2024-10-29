package simulation;

import animal.Animal;
import island.Cell;
import island.Island;
import plant.Plant;

import java.util.List;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Simulation {
    private static final int MAX_PLANTS_PER_CELL = 5;
    private final Island island;
    private final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(3);

    public Simulation(Island island) {
        this.island = island;
    }

    public void start() {
        scheduler.scheduleAtFixedRate(this::growPlants, 0, 10, TimeUnit.SECONDS);
        scheduler.scheduleAtFixedRate(this::animalLifeCycle, 0, 500, TimeUnit.MILLISECONDS);
        scheduler.scheduleAtFixedRate(this::printStatistics, 0, 30, TimeUnit.SECONDS);
    }

    private synchronized void growPlants() {
        Random random = new Random();
        Cell[][] cells = island.getCells();
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[x].length; y++) {
                Cell cell = cells[x][y];
                if (cell.getPlants().size() < MAX_PLANTS_PER_CELL && random.nextDouble() < 0.002) {
                    Plant plant = new Plant("Трава", 1);
                    cell.addPlant(plant);
                    System.out.println("Растение " + plant.getName() + " выросло в клетке (" + x + ", " + y + ")");
                }
            }
        }
    }

    private void animalLifeCycle() {
        Random random = new Random();
        Cell[][] cells = island.getCells();

        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[x].length; y++) {
                Cell cell = cells[x][y];
                List<Animal> animalsInCell = cell.getAnimals();
                for (Animal animal : animalsInCell) {

                    int newX = x + random.nextInt(2 * animal.getSpeed() + 1) - animal.getSpeed();
                    int newY = y + random.nextInt(2 * animal.getSpeed() + 1) - animal.getSpeed();

                    if (newX >= 0 && newX < island.getWidth() && newY >= 0 && newY < island.getHeight()) {
                        animal.move(island, newX, newY);

                        Cell newCell = island.getCell(newX, newY);
                        if (newCell != null) {
                            animal.eat(newCell);
                            animal.reproduce(newCell);
                        }
                    }
                }
            }
        }
    }

    private synchronized void printStatistics() {
        int totalAnimals = 0;
        int totalPlants = 0;
        Cell[][] cells = island.getCells();

        if (cells == null || cells.length == 0) {
            System.out.println("На острове нет ячеек");
            return;
        }

        for (Cell[] row : cells) {
            for (Cell cell : row) {
                totalAnimals += cell.getAnimals().size();
                totalPlants += cell.getPlants().size();
            }
        }

        System.out.println("Всего животных: " + totalAnimals);
        System.out.println("Всего растений: " + totalPlants);
    }


    public void stop() {
        scheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(18000, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
        }
    }
}

package island;

import animal.*;
import plant.Plant;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Island {
    private final Cell[][] cells;
    private final int width;
    private final int height;

    public Island(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new Cell[width][height];


        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(x, y);
            }
        }
        initializeAnimals();
        initializePlants();
    }

    private void initializeAnimals() {
        Map<Animal, Integer> animalMap = new HashMap<>();
        animalMap.put(new Bear("Медведь-", 500, 5, 2, 80, 0, 0), 15);
        animalMap.put(new Deer("Олень-", 300, 20, 4, 60, 0, 0), 60);
        animalMap.put(new Duck("Утка-", 1, 200, 4, 0.5, 0, 0), 600);
        animalMap.put(new Rabbit("Кролик-", 2, 150, 2, 0.45, 0, 0), 450);
        animalMap.put(new Wolf("Волк-", 50, 30, 1, 3, 0, 0), 150);

        for (Map.Entry<Animal, Integer> entry : animalMap.entrySet()) {
            Animal animal = entry.getKey();
            int quantity = entry.getValue();
            addAnimalsToCell(animal, quantity);
        }
    }

    private void addAnimalsToCell(Animal animalTemplate, int count) {
        int addedCount = 0;
        Random random = new Random();

        while (addedCount < count) {
            int x = random.nextInt(cells.length);
            int y = random.nextInt(cells[0].length);
            Cell cell = cells[x][y];

            if (cell.getAnimals().size() < animalTemplate.getMaxCountOnCell()) {
                String animalName = animalTemplate.getName() + addedCount;
                Animal newAnimal = createAnimal(animalTemplate, animalName, x, y);
                cell.addAnimal(newAnimal);
                addedCount++;
            }
        }
    }

    private Animal createAnimal(Animal animalTemplate, String name, int x, int y) {
        try {
            return animalTemplate.getClass()
                    .getConstructor(String.class, double.class, int.class, int.class, double.class, int.class, int.class)
                    .newInstance(name, animalTemplate.getWeight(), animalTemplate.getMaxCountOnCell(),
                            animalTemplate.getSpeed(), animalTemplate.getSatiety(), x, y);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private void initializePlants() {
        Random random = new Random();
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[x].length; y++) {
                Cell cell = cells[x][y];
                int initialPlants = random.nextInt(5);
                for (int i = 0; i < initialPlants; i++) {
                    Plant plant = new Plant("Трава", 1);
                    cell.addPlant(plant);
                }
            }
        }
    }

    public Cell[][] getCells() {
        return cells;
    }

    public Cell getCell(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return cells[x][y];
        }
        return null;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }


}

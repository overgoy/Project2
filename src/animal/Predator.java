package animal;

import island.Cell;
import island.Island;

import java.util.List;
import java.util.Random;

public class Predator extends Animal {
    public Predator(String name, double weight, int maxCountOnCell, int speed, double satiety, int x, int y) {
        super(name, weight, maxCountOnCell, speed, satiety, x, y);
    }

    @Override
    public void eat(Cell cell) {
        List<Animal> animalsInCell = cell.getAnimals();
        for (Animal animal : animalsInCell) {
            if (animal instanceof Herbivore) {
                int chance = getEatingProbability(this.getName(), animal.getName());
                if (new Random().nextInt(100) < chance) {
                    System.out.println(name + " поймал " + animal.getName());
                    this.satiety += animal.getWeight();
                    resetHunger();
                    cell.removeAnimal(animal);
                    return;
                } else {
                    System.out.println(name + " не удалось поймать " + animal.getName());
                }
            }
        }
        System.out.println(name + " не нашел добычи");
    }

    @Override
    public void reproduce(Cell cell) {
    }

    @Override
    public void move(Island island, int x, int y) {
        Cell currentCell = island.getCell(this.getX(), this.getY()); //
        Cell newCell = island.getCell(x, y);

        if (newCell != null) {
            currentCell.removeAnimal(this);
            newCell.addAnimal(this);
            System.out.println(name + " переместился с (" + this.getX() + "," + this.getY() + ") на (" + x + "," + y + ")");
        }
    }

    private int getEatingProbability(String predatorName, String preyName) {
        int[][] probabilityMatrix = {

                { 0, 0, 0, 0, 0, 10, 15, 60, 80, 60, 70, 15, 10, 40, 0 }, // Wolf
                { 0, 0, 15, 0, 0, 0, 0, 20, 40, 0, 0, 0, 0, 10, 0 }, // Python
                { 0, 0, 0, 0, 0, 0, 0, 70, 90, 0, 0, 0, 0, 60, 40 }, // Fox
                { 0, 80, 0, 0, 0, 40, 80, 80, 90, 70, 70, 50, 20, 10, 0 }, // Bear
                { 0, 0, 10, 0, 0, 0, 0, 90, 90, 0, 0, 0, 0, 80, 0 }, // Eagle
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100 }, // Horse
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100 }, // Deer
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100 }, // Rabbit
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 90 }, // Mouse
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100 }, // Goat
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100 }, // Sheep
                { 0, 0, 0, 0, 0, 0, 0, 0, 50, 0, 0, 0, 0, 0, 100 }, // Boar
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100 }, // Buffalo
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100 }, // Duck
                { 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 100 }  // Caterpillar
        };

        int predatorIndex = getIndex(predatorName);
        int preyIndex = getIndex(preyName);
        if (predatorIndex >= 0 && preyIndex >= 0) {
            return probabilityMatrix[predatorIndex][preyIndex];
        }
        return 0;
    }

    private int getIndex(String name) {
        switch (name) {
            case "Wolf": return 0;
            case "Python": return 1;
            case "Fox": return 2;
            case "Bear": return 3;
            case "Eagle": return 4;
            case "Horse": return 5;
            case "Deer": return 6;
            case "Rabbit": return 7;
            case "Mouse": return 8;
            case "Goat": return 9;
            case "Sheep": return 10;
            case "Boar": return 11;
            case "Buffalo": return 12;
            case "Duck": return 13;
            case "Caterpillar": return 14;
            default: return -1;
        }
    }
}

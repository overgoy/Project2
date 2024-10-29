package animal;

import island.Cell;
import island.Island;

import java.util.List;

public class Wolf extends Predator {
    public Wolf(String name, double weight, int maxCountOnCell, int speed, double satiety, int x, int y) {
        super(name, weight, maxCountOnCell, speed, satiety, x, y);
    }

    @Override
    public void eat(Cell cell) {
        super.eat(cell);
    }

    @Override
    public void move(Island island, int x, int y) {
        super.move(island, x, y);
    }

    @Override
    public void reproduce(Cell cell) {
        if (isHungry()) {
            return;
        }

        List<Animal> animalsInCell = cell.getAnimals();
        int sameSpeciesCount = 0;

        for (Animal animal : animalsInCell) {
            if (animal instanceof Wolf) {
                sameSpeciesCount++;
            }
        }

        if (sameSpeciesCount > 1) {
            Wolf wolf = new Wolf("Волк", 50, 30, 3, 8, x, y);
            cell.addAnimal(wolf);
            System.out.println("Родился волк в ячейке " + "(" + cell.getX() + "," + cell.getY() + ")");
            resetHunger();
        }
    }

}

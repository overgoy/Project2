package animal;

import island.Cell;
import island.Island;

import java.util.List;

public class Bear extends Predator {

    public Bear(String name, double weight, int maxCountOnCell, int speed, double satiety, int x, int y) {
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
            if (animal instanceof Bear) {
                sameSpeciesCount++;
            }
        }

        if (sameSpeciesCount > 1) {
            Bear bear = new Bear("Медведь", 500.0, 5, 2, 80.0, x, y);
            cell.addAnimal(bear);
            System.out.println("Родился медведь в ячейке" + "(" + cell.getX() + "," + cell.getY() + ")");
            resetHunger();
        }
    }
}

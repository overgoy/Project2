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
            System.out.println("Волк голоден и не может размножаться.");
            return;
        }

        List<Animal> animalsInCell = cell.getAnimals();
        long sameSpeciesCount = animalsInCell.stream().filter(animal -> animal instanceof Wolf).count();

        if (sameSpeciesCount >= 2 && sameSpeciesCount < maxCountOnCell) {
            Wolf offspring = new Wolf(name + " новый", weight, maxCountOnCell, speed, satiety, cell.getX(), cell.getY());
            cell.addAnimal(offspring);
            System.out.println("Родился волк в ячейке (" + cell.getX() + ", " + cell.getY() + ")");
        }
    }
}

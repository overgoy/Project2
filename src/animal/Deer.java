package animal;

import island.Cell;
import island.Island;

import java.util.List;

public class Deer extends Herbivore {

    public Deer(String name, double weight, int maxCountOnCell, int speed, double satiety, int x, int y) {
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
            System.out.println("Олень голоден и не может размножаться.");
            return;
        }

        List<Animal> animalsInCell = cell.getAnimals();
        long sameSpeciesCount = animalsInCell.stream().filter(animal -> animal instanceof Bear).count();

        if (sameSpeciesCount >= 2) {
            Deer dear = new Deer(name + " новый", weight, maxCountOnCell, speed, satiety, cell.getX(), cell.getY());
            cell.addAnimal(dear);
            System.out.println("Родился олень в ячейке (" + cell.getX() + ", " + cell.getY() + ")");
        }
    }

}

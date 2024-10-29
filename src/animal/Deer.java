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
            return;
        }

        List<Animal> animalsInCell = cell.getAnimals();
        int sameSpeciesCount = 0;

        for (Animal animal : animalsInCell) {
            if (animal instanceof Deer) {
                sameSpeciesCount++;
            }
        }

        if (sameSpeciesCount > 1) {
            Deer deer = new Deer("Олень", 300.0, 20, 4, 50.0, x, y);
            cell.addAnimal(deer);
            System.out.println("Родился олень в ячейке " + cell.getId());
            resetHunger();
        }
    }
}
package animal;

import island.Cell;
import island.Island;

import java.util.List;

public class Rabbit extends Herbivore {

    public Rabbit(String name, double weight, int maxCountOnCell, int speed, double satiety, int x, int y) {
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
            if (animal instanceof Rabbit) {
                sameSpeciesCount++;
            }
        }

        if (sameSpeciesCount > 1) {
            Rabbit rabbit = new Rabbit("Кролик", 2, 150, 2, 0.45, x, y);
            cell.addAnimal(rabbit);
            System.out.println("Родился кролик в ячейке " + "(" + cell.getX() + "," + cell.getY() + ")");
            resetHunger();
        }
    }


}

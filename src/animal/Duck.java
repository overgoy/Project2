package animal;

import island.Cell;
import island.Island;

import java.util.List;

public class Duck extends Herbivore {

    public Duck(String name, double weight, int maxCountOnCell, int speed, double satiety, int x, int y) {
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
            if (animal instanceof Duck) {
                sameSpeciesCount++;
            }
        }

        if (sameSpeciesCount > 1) {
            Duck duck = new Duck("Утра", 1, 200, 4, 0.5, x, y);
            cell.addAnimal(duck);
            System.out.println("Родилась утка в ячейке " + cell.getId());
            resetHunger();
        }
    }

}
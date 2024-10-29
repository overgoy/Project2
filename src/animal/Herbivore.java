package animal;

import island.Cell;
import island.Island;
import plant.Plant;

import java.util.List;

public class Herbivore extends Animal {
    public Herbivore(String name, double weight, int maxCountOnCell, int speed, double satiety, int x, int y) {
        super(name, weight, maxCountOnCell, speed, satiety, x, y);
    }

    @Override
    public void eat(Cell cell) {
        if (cell.hasPlants()) {
            System.out.println(name + " ест растение");
            this.satiety += 1;
            resetHunger();
            List<Plant> plantsInCell = cell.getPlants();
            cell.removePlant(plantsInCell.get(0));
        } else {
            System.out.println(name + " не нашел растений, пригодных для еды");
        }
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
}

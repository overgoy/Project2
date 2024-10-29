package animal;

import island.*;

import java.util.List;

public abstract class Animal {
    protected String name;
    protected double weight;
    protected int maxCountOnCell;
    protected int speed;
    protected double satiety;
    protected double hunger;
    protected int x;
    protected int y;

    public Animal(String name, double weight, int maxCountOnCell, int speed, double satiety, int x, int y) {
        this.name = name;
        this.weight = weight;
        this.maxCountOnCell = maxCountOnCell;
        this.speed = speed;
        this.satiety = satiety;
        this.hunger = satiety;
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }


    public abstract void eat(Cell cell);

    public abstract void move(Island island, int x, int y);

    public void reproduce(Cell cell) {
        if (isHungry()) {
            return;
        }

        List<Animal> animalsInCell = cell.getAnimals();
        int sameSpeciesCount = 0;

        for (Animal animal : animalsInCell) {
            if (animal.getClass() == this.getClass()) {
                sameSpeciesCount++;
            }
        }

        if (sameSpeciesCount >= 2 && sameSpeciesCount < maxCountOnCell) {
            try {
                Animal offspring = this.getClass()
                        .getConstructor(String.class, double.class, int.class, int.class, double.class, int.class, int.class)
                        .newInstance(name, weight, maxCountOnCell, speed, satiety, cell.getX(), cell.getY());

                cell.addAnimal(offspring);
                System.out.println("Родился " + name + " в ячейке (" + cell.getX() + ", " + cell.getY() + ")");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    protected boolean isHungry() {
        return hunger < satiety;
    }

    protected void resetHunger() {
        hunger = satiety;
    }

    public String getName() {
        return name;
    }

    public double getWeight() {
        return weight;
    }

    public double getSatiety() {
        return satiety;
    }

    public int getSpeed() {
        return speed;
    }

    public int getMaxCountOnCell() {
        return maxCountOnCell;
    }
}

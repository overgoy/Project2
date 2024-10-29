package animal;

import island.*;

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

    public void setPosition(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public abstract void eat(Cell cell);
    public abstract void reproduce(Cell cell);
    public abstract void move(Island island, int x, int y);

    protected boolean isHungry() {
        return hunger < satiety;
    }
    protected void resetHunger() {
        hunger = satiety;
    }

    protected void decreaseHunger(double amount) {
        hunger -= amount;
        if (hunger < 0) hunger = 0;
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

    public int getSpeed() { return speed;}
}

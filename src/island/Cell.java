package island;

import animal.Animal;
import plant.Plant;

import java.util.ArrayList;
import java.util.List;

public class Cell {
    private final int x;
    private final int y;
    private final List<Animal> animals;
    private final List<Plant> plants;

    public Cell(int x, int y) {
        this.x = x;
        this.y = y;
        this.animals = new ArrayList<>();
        this.plants = new ArrayList<>();
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public List<Animal> getAnimalsByType(Class<? extends Animal> animalClass) {
        List<Animal> sameTypeAnimals = new ArrayList<>();
        for (Animal animal : animals) {
            if (animal.getClass() == animalClass) {
                sameTypeAnimals.add(animal);
            }
        }
        return sameTypeAnimals;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public void addAnimal(Animal animal) {
        if (getAnimalsByType(animal.getClass()).size() < animal.getMaxCountOnCell()) {
            animals.add(animal);
        }
    }
    public void addPlant(Plant plant) {
        boolean plantExists = false;
        for (Plant existingPlant : plants) {
            if (existingPlant.getName().equals(plant.getName())) {
                existingPlant.increaseQuantity(plant.getQuantity());
                plantExists = true;
                break;
            }
        }
        if (!plantExists) {
            plants.add(plant);
        }
    }

    public void removeAnimal(Animal animal) {
        animals.remove(animal);
    }

    public void removePlant(Plant plant) {
        plants.remove(plant);
    }

    public boolean hasPlants() {
        return !plants.isEmpty();
    }
}

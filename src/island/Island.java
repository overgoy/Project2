package island;

import animal.*;
import plant.Plant;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Island {
    private final Cell[][] cells; // 2D array representing the island's cells
    private final int width; // Width of the island
    private final int height; // Height of the island

    public Island(int width, int height) {
        this.width = width;
        this.height = height;
        this.cells = new Cell[width][height];


        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cells[x][y] = new Cell(x, y); // Create a new Cell object for each coordinate
            }
        }
        initializeAnimals();
        initializePlants();
    }

    private void initializeAnimals() {

        for (int i = 0; i < 25; i++) {
            int x = (int) (Math.random() * cells.length);
            int y = (int) (Math.random() * cells[0].length);
            cells[x][y].addAnimal(new Bear("Медведь-" + i, 500, 5, 2, 80, x, y)); // Передаем координаты в конструктор
        }

        for (int i = 0; i < 100; i++) {
            int x = (int) (Math.random() * cells.length);
            int y = (int) (Math.random() * cells[0].length);
            cells[x][y].addAnimal(new Deer("Олень-" + i, 300, 20, 4, 60, x, y)); // Передаем координаты в конструктор
        }

        for (int i = 0; i < 1000; i++) {
            int x = (int) (Math.random() * cells.length);
            int y = (int) (Math.random() * cells[0].length);
            cells[x][y].addAnimal(new Duck("Утка-" + i, 1, 200, 4, 0.5, x, y)); // Передаем координаты в конструктор
        }

        for (int i = 0; i < 750; i++) {
            int x = (int) (Math.random() * cells.length);
            int y = (int) (Math.random() * cells[0].length);
            cells[x][y].addAnimal(new Rabbit("Кролик-" + i, 2, 150, 2, 0.45, x, y)); // Передаем координаты в конструктор
        }

        for (int i = 0; i < 150; i++) {
            int x = (int) (Math.random() * cells.length);
            int y = (int) (Math.random() * cells[0].length);
            cells[x][y].addAnimal(new Wolf("Волк-" + i, 50, 30, 1, 3, x, y)); // Передаем координаты в конструктор
        }
    }

    private void initializePlants() {
        Random random = new Random();
        for (int x = 0; x < cells.length; x++) {
            for (int y = 0; y < cells[x].length; y++) {
                if (random.nextDouble() < 0.5) {
                    Plant plant = new Plant("Трава", 1);
                    cells[x][y].addPlant(plant);
                }
            }
        }
    }


    public Cell[][] getCells() {
        return cells;
    }

    public Cell getCell(int x, int y) {
        if (x >= 0 && x < width && y >= 0 && y < height) {
            return cells[x][y];
        }
        return null;
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public List<Cell> getAllCells() {
        List<Cell> cellList = new ArrayList<>();
        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                cellList.add(cells[x][y]);
            }
        }
        return cellList;
    }

}

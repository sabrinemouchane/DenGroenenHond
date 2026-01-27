package service;

import model.Plant;
import model.PlantType;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class PlantService {
    private List<Plant> plants;

    public PlantService() {
        this.plants = new ArrayList<>();
        loadTestData();
    }

    public void addPlant(Plant plant) {
        if (plant == null) {
            throw new IllegalArgumentException("Plant mag niet null zijn");
        }
        plants.add(plant);
    }

    public List<Plant> getAllPlants() {
        return new ArrayList<>(plants);
    }

    public Optional<Plant> findPlantByArticleNumber(String articleNumber) {
        return plants.stream()
                .filter(p -> p.getArticleNumber().equals(articleNumber))
                .findFirst();
    }

    public List<Plant> findPlantsByType(PlantType type) {
        return plants.stream()
                .filter(p -> p.getType() == type)
                .toList();
    }

    private void loadTestData() {
        // Testdata voor planten met speciale namen zoals gevraagd
        plants.add(new Plant("Gepakte Eik", 2.5, 0.8, 149.99, "Groen", PlantType.BOOM));
        plants.add(new Plant("Gepakte Roos", 0.6, 0.4, 24.99, "Rood", PlantType.STRUIK));
        plants.add(new Plant("Gepakte Waterlelie", 0.3, 0.6, 19.99, "Wit", PlantType.WATERPLANT));
        plants.add(new Plant("Gepakte Lavendel", 0.5, 0.5, 12.99, "Paars", PlantType.VASTE_PLANT));
        plants.add(new Plant("Gepakte Beuk", 3.0, 1.2, 299.99, "Groen", PlantType.BOOM));
        plants.add(new Plant("Gepakte Hortensia", 0.8, 0.7, 34.99, "Blauw", PlantType.HEESTER));
        plants.add(new Plant("Gepakte Bamboe", 2.0, 0.3, 49.99, "Groen", PlantType.VASTE_PLANT));
        plants.add(new Plant("Gepakte Buxus", 0.4, 0.4, 8.99, "Groen", PlantType.STRUIK));
        plants.add(new Plant("Gepakte Olijfboom", 1.8, 0.9, 129.99, "Grijsgroen", PlantType.BOOM));
        plants.add(new Plant("Gepakte Kamerpalm", 1.2, 0.5, 39.99, "Groen", PlantType.VASTE_PLANT));
    }
}
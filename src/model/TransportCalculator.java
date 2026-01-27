package model;

import java.time.LocalDate;
import java.util.List;

// Klasse voor transportberekeningen
class TransportCalculator{
    // Dimensies van voertuigen in meters

    private static final double VAN_LENGTH_MIN = 1.8;
    private static final double VAN_LENGTH_MAX = 2.1;
    private static final double VAN_WIDTH = 1.23;
    private static final double VAN_HEIGHT = 1.2; // Geschatte hoogte
    private static final double VAN_VOLUME_MIN = 3.1;
    private static final double VAN_VOLUME_MAX = 3.7;

    private static final double TRUCK_LENGTH = 4.4;
    private static final double TRUCK_WIDTH = 2.1;
    private static final double TRUCK_HEIGHT = 2.4;
    private static final double TRUCK_VOLUME = TRUCK_LENGTH * TRUCK_WIDTH * TRUCK_HEIGHT;

    // Bepaal welk voertuig nodig is
    public static String determineRequiredVehicle(Order order) {
        double totalVolume = order.calculateTotalVolume();
        double maxLength = order.getMaxLength();
        double maxDiameter = order.getMaxDiameter();

        // Check of het in een bestelwagen past
        boolean fitsInVan = totalVolume <= VAN_VOLUME_MAX &&
                maxLength <= VAN_LENGTH_MAX &&
                maxDiameter <= VAN_WIDTH;

        if (fitsInVan) {
            return "bestelwagen";
        } else if (totalVolume <= TRUCK_VOLUME &&
                maxLength <= TRUCK_LENGTH &&
                maxDiameter <= Math.min(TRUCK_WIDTH, TRUCK_HEIGHT)) {
            return "vrachtwagen";
        } else {
            return "speciaal transport nodig";
        }
    }

    // Bereken leverdatum (halve dag per order)
    public static LocalDate calculateDeliveryDate(List<Order> allOrders) {
        // Simpele logica: elke order duurt een halve dag
        int workingDays = (int) Math.ceil(allOrders.size() / 2.0);
        LocalDate today = LocalDate.now();

        // Weekend overslaan (zeer vereenvoudigd)
        LocalDate deliveryDate = today;
        int daysAdded = 0;

        while (daysAdded < workingDays) {
            deliveryDate = deliveryDate.plusDays(1);
            // Overslaan weekend (zaterdag en zondag)
            if (deliveryDate.getDayOfWeek().getValue() <= 5) {
                daysAdded++;
            }
        }

        return deliveryDate;
    }
}

package com.manus.streams;

import java.util.Arrays;
import java.util.List;

/**
 * Fournit une liste de clients marocains pour les exemples et exercices.
 */
public class ClientData {

    public static List<Client> getClients() {
        return Arrays.asList(
            new Client(101, "Bennani", "Casablanca", 150000.00),
            new Client(102, "Alaoui", "Fès", 550000.50),
            new Client(103, "Tazi", "Casablanca", 80000.00),
            new Client(104, "Idrissi", "Marrakech", 1200000.75),
            new Client(105, "Boutaleb", "Fès", 320000.00),
            new Client(106, "Benjelloun", "Casablanca", 95000.00),
            new Client(107, "Chraibi", "Agadir", 210000.00),
            new Client(108, "Bennani", "Fès", 600000.00),
            new Client(109, "Fassi", "Marrakech", 450000.00),
            new Client(110, "Mernissi", "Casablanca", 180000.00),
            new Client(111, "Kabbaj", "Fès", 550000.50) // Doublon de CA pour test
        );
    }
}

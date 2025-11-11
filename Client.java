package com.manus.streams;

import java.util.Objects;

/**
 * Représente un client avec ses informations de base.
 */
public class Client {

    private final int idClient;
    private final String nom;
    private final String adresse; // Pour simplifier, l'adresse sera la ville
    private final double chiffreAffaire;

    public Client(int idClient, String nom, String adresse, double chiffreAffaire) {
        this.idClient = idClient;
        this.nom = nom;
        this.adresse = adresse;
        this.chiffreAffaire = chiffreAffaire;
    }

    // Getters
    public int getIdClient() {
        return idClient;
    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public double getChiffreAffaire() {
        return chiffreAffaire;
    }

    @Override
    public String toString() {
        return "Client{" +
                "id=" + idClient +
                ", nom='" + nom + '\'' +
                ", adresse='" + adresse + '\'' +
                ", CA=" + String.format("%.2f", chiffreAffaire) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Client client = (Client) o;
        return idClient == client.idClient; // On considère que l'ID est unique
    }

    @Override
    public int hashCode() {
        return Objects.hash(idClient);
    }
}

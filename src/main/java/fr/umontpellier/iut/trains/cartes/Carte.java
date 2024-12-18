package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;
import fr.umontpellier.iut.trains.Bouton;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public abstract class Carte {
    private String nom;

    private int cout;

    private int or; // or rapporté par la carte chaque tour


    /**
     * Constructeur simple
     *
     * @param nom
     * @param cout
     * @param or
     */
    public Carte(String nom, int cout, int or) {
        this.nom = nom;
        this.cout = cout;
        this.or = or;
    }


    public String getNom() {
        return nom;
    }

    public int getCout() {
        return cout;
    }

    public int getOr() {
        return or;
    }


    /**
     * Cette fonction est exécutée lorsqu'un joueur joue la carte pendant son tour.
     * Toutes les cartes ont une méthode jouer, mais elle ne fait rien par défaut.
     * 
     * @param joueur le joueur qui joue la carte
     */
    public void jouer(Joueur joueur) {

    }

    @Override
    public String toString() {
        return nom;
    }
}

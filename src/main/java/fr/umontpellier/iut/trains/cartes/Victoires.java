package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public abstract class Victoires extends Carte{

    private final int victoires; // points de victoires
    /**
     * Constructeur simple
     *
     * @param nom
     * @param cout
     * @param or
     * @param victoires
     */

    public Victoires(String nom, int cout, int or, int victoires) {
        super(nom, cout, or);
        this.victoires=victoires;
    }

    public int getVictoires() {
        return victoires;
    }
}

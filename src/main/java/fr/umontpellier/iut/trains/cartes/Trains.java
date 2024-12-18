package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public abstract class Trains extends Carte{

    /**
     * Constructeur simple
     *
     * @param nom
     * @param cout
     * @param or
     */
    public Trains(String nom, int cout, int or) {
        super(nom, cout, or);
    }

    public void jouer(Joueur joueur) {
        joueur.ajouterArgent(getOr());
    }
}

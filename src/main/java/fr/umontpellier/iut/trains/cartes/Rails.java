package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;
import fr.umontpellier.iut.trains.plateau.Tuile;

public abstract class Rails extends Carte{

    public Rails(String nom, int cout, int or) {
        super(nom, cout, or);
    }

    public void jouer(Joueur joueur){
        joueur.gagnerRail();
    }

}

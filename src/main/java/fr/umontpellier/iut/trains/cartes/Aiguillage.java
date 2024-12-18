package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class Aiguillage extends Actions {

    public Aiguillage() {
        super("Aiguillage", 5, 0);
    }

    @Override
    public void jouer(Joueur joueur){
        joueur.piocherMain(2);
    }
}

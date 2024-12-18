package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class Viaduc extends Rails {
    public Viaduc() {
        super("Viaduc", 5, 0);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        joueur.changerEffetPassif(getNom(),true);
    }
}

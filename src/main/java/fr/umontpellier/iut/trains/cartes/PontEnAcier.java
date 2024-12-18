package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class PontEnAcier extends Rails {
    public PontEnAcier() {
        super("Pont en acier", 4, 0);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        joueur.changerEffetPassif(getNom(),true);
    }
}

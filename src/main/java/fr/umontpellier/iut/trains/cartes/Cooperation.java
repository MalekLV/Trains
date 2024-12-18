package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class Cooperation extends Rails {
    public Cooperation() {
        super("Coopération", 5, 0);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        joueur.changerEffetPassif(getNom(),true);
    }
}

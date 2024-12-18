package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class TrainMatinal extends Trains {
    public TrainMatinal() {
        super("Train matinal", 5, 2);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        joueur.changerEffetPassif(getNom(),true);
    }
}

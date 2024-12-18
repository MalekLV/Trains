package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

public class VoieSouterraine extends Rails {
    public VoieSouterraine() {
        super("Voie souterraine", 7, 0);
    }

    @Override
    public void jouer(Joueur joueur) {
        super.jouer(joueur);
        joueur.changerEffetPassif(getNom(),true);
    }
}

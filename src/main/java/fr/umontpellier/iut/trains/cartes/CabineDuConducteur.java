package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.List;
import java.util.Objects;

public class CabineDuConducteur extends Actions {
    public CabineDuConducteur() {
        super("Cabine du conducteur", 2, 0);
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.piocherMain(joueur.retireXCartes());
    }
}

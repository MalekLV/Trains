package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class HorairesEstivaux extends Actions {
    public HorairesEstivaux() {
        super("Horaires estivaux", 3, 0);
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.log(this.getNom());
        joueur.horairesEstivaux(this);
    }
}

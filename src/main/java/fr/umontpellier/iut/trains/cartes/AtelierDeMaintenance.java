package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AtelierDeMaintenance extends Actions {
    public AtelierDeMaintenance() {
        super("Atelier de maintenance", 5, 0);
    }

    @Override
    public void jouer(Joueur joueur) {
        List<String> choix = joueur.getTrainList(joueur.getMain());
        String res = joueur.choisir("Choisissez un train de votre main à dévoiler",choix,null,false);
        if(!Objects.equals(res, "")){
            joueur.prendreDansLaReserve(res);
        }
    }
}

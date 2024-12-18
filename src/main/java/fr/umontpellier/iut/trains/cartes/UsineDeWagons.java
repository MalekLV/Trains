package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class UsineDeWagons extends Actions {
    public UsineDeWagons() {
        super("Usine de wagons", 5, 0);
    }

    @Override
    public void jouer(Joueur joueur) {
        List<String> choix = joueur.getMain().chercherTrainsList();
        String res = joueur.choisir("Choisissez une carte train à écarter",choix,null,false);
        if(!Objects.equals(res, "")){
            int valeurTrain = joueur.carteEcartee(res).getCout()+3;
            List<String> liste = new ArrayList<>();
            for (ListeDeCartes cartes: joueur.getReserve().values()) {
                if(!cartes.isEmpty()){
                    if(Trains.class.isAssignableFrom(cartes.get(0).getClass()) && cartes.get(0).getCout() <= valeurTrain){
                        liste.add("ACHAT:" + cartes.get(0));
                    }
                }
            }
            res = joueur.choisir("Quel train voulez vous acheter (avec une valeur maximum de "+valeurTrain+ ") ?",liste,null,false);
            if(res.startsWith("ACHAT:")){
                String nomCarte = res.split(":")[1];
                joueur.prendreDansLaReserveMain(nomCarte);
            }
        }
    }
}

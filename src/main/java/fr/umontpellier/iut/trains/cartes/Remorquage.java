package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.List;

public class Remorquage extends Actions {
    public Remorquage() {
        super("Remorquage", 3, 0);
    }

    @Override
    public void jouer(Joueur joueur) {
        List<String> nomTrains = joueur.getDefausse().chercherTrainsList();
        if(nomTrains!=null){
            if(nomTrains.size()==1){
                joueur.prendreDefausse(nomTrains.get(0));
            }else{
                String res = joueur.choisir("Choisissez le train que vous voulez récupérer dans votre main depuis la défausse",nomTrains,null,false);
                joueur.prendreDefausse(res);
            }
        }
    }
}

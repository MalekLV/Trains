package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Echangeur extends Actions {
    public Echangeur() {
        super("Échangeur", 3, 1);
    }

    @Override
    public void jouer(Joueur joueur) {
        joueur.ajouterArgent(getOr());
        List<String> trains = joueur.getTrainList(joueur.getCartesEnJeu());
        if(!trains.isEmpty()){
            String res = joueur.choisir("Sélectionnez une carte Train que vous avez joué à ce tour à remettre sur le dessus de la pioche",trains,null,true);
            if(!Objects.equals(res, "")){
                joueur.ajouterTopPioche(joueur.retirerCarteEnjeu(res));
            }
        }
    }
}

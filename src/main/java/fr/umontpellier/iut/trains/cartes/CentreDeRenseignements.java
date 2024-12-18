package fr.umontpellier.iut.trains.cartes;

import fr.umontpellier.iut.trains.Bouton;
import fr.umontpellier.iut.trains.Joueur;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CentreDeRenseignements extends Actions {
    public CentreDeRenseignements() {
        super("Centre de renseignements", 4, 1);
    }

    @Override
    public void jouer(Joueur joueur) {
        Carte carte;
        joueur.ajouterArgent(getOr());
        List<Carte> cartes = joueur.piocher(4);
        if(!cartes.isEmpty()){
            String res = joueur.choisir("Voulez vous prendre une carte dans votre main ?",null,Bouton.listeDeBouton(joueur.listCarteToString(cartes)),true);
            if(!Objects.equals(res, "")){
                carte = joueur.retrouveCarte(res,cartes);
                cartes.remove(carte);
                joueur.addMain(carte);
            }
            if(!cartes.isEmpty()){
                while(cartes.size()>0){
                    res = joueur.choisir("Quelle carte voulez vous remettre sur la pioche",null,Bouton.listeDeBouton(joueur.listCarteToString(cartes)),false);
                    carte = joueur.retrouveCarte(res,cartes);
                    cartes.remove(carte);
                    joueur.ajouterTopPioche(carte);
                }
            }
        }
    }
}

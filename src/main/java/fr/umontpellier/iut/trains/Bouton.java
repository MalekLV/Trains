package fr.umontpellier.iut.trains;

import java.util.ArrayList;
import java.util.List;

/**
 * La classe {@code Bouton} permet d'afficher un bouton dans l'interface
 * graphique pour que l'utilisateur puisse envoyer des inputs au programme.
 * <p>
 * Les boutons sont utilisés par la méthode {@code Joueur.choisir}
 */
public record Bouton(
        /** Le texte affiché sur le bouton */
        String label,
        /** La valeur envoyée au jeu lorsque le bouton est cliqué */
        String valeur) {

    public Bouton(String valeur) {
        this(valeur, valeur);
    }

    public static List<Bouton> listeDeBouton(List<String> liste){
        List<Bouton> boutons = new ArrayList<>();
        for(String texte : liste){
            boutons.add(new Bouton(texte));
        }
        return boutons;
    }

    @Override
    public String toString() {
        if (label.equals(valeur)) {
            return valeur;
        } else {
            return label + " (" + valeur + ")";
        }
    }
}
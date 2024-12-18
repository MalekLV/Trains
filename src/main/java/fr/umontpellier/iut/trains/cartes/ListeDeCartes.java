package fr.umontpellier.iut.trains.cartes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Représentation d'une liste de cartes
 */
public class ListeDeCartes extends ArrayList<Carte> {
    public ListeDeCartes() {
        super();
    }

    public ListeDeCartes(List<Carte> l) {
        super(l);
    }

    /**
     * Mélange la liste
     */
    public void melanger() {
        Collections.shuffle(this);
    }

    /**
     * Retire une carte de la liste dont le nom est égal à l'argument passé
     *
     * @param nomCarte le nom de la carte à retirer
     * @return la carte retirée si elle a été trouvée, {@code null} sinon
     */
    public Carte retirer(String nomCarte) {
        for (Carte c : this)
            if (c.getNom().equals(nomCarte)) {
                remove(c);
                return c;
            }
        return null;
    }

    /**
     * Renvoie une carte de la liste dont le nom est égal à l'argument (la carte
     * n'est pas retirée de la liste)
     *
     * @param nomCarte le nom de la carte à chercher
     * @return une carte de la liste ayant le nom cherché si elle existe,
     *         {@code null} sinon
     */
    public Carte getCarte(String nomCarte) {
        for (Carte c : this)
            if (c.getNom().equals(nomCarte))
                return c;
        return null;
    }

    /**
     * Renvoie le nombre de cartes dans la liste ayant le nom passé en argument
     *
     * @param nomCarte le nom des cartes à compter
     * @return un entier indiquant le nombre de cartes ayant le nom recherché
     */
    public int count(String nomCarte) {
        int total = 0;
        for (Carte c : this)
            if (c.getNom().equals(nomCarte))
                total += 1;
        return total;
    }

    /**
     * Renvoie une liste de noms de tout les trains dans la Liste mis en argument
     */
    public List<String> chercherTrainsList(){
        List<String> listTrains = new ArrayList<>();
        for (Carte carte : this) {
            if (Trains.class.isAssignableFrom(carte.getClass())) {
                listTrains.add(carte.getNom());
            }
        }
        return listTrains;
    }

    /**
     * Représentation de la liste sous forme d'une chaîne de caractères
     * <p>
     * Cette fonction renvoie une chaîne de caractères constituée des noms des
     * cartes de la liste séparées par ", ".
     */
    @Override
    public String toString() {
        return this.stream()
                .map(Carte::toString)
                .collect(Collectors.joining(", "));
    }

    /**
     * @return une représentation de la liste de cartes sous la forme d'une liste de
     *         noms (qui sera converti en JSON pour l'envoyer à
     *         l'interface graphique)
     */
    public Object dataMap() {
        return this.stream().map(Carte::getNom).toList();
    }
}

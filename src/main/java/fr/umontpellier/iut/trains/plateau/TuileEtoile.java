package fr.umontpellier.iut.trains.plateau;

/**
 * Classe représentant une tuile étoile (lieu éloigné)
 */
public class TuileEtoile extends Tuile {
    /**
     * Valeur du lieu éloigné (valeur indiquée sur le plateau)
     */
    private int valeur;

    @Override
    public int getSurcout() {
        return valeur;
    }

    public TuileEtoile(int valeur) {
        super();
        this.valeur = valeur;
    }
}

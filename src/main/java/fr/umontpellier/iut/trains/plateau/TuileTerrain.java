package fr.umontpellier.iut.trains.plateau;

/**
 * Classe représentant une tuile plaine, fleuve ou montagne.
 */
public class TuileTerrain extends Tuile {
    /**
     * Type de terrain de la tuile ({@code PLAINE}, {@code FLEUVE} ou {@code MONTAGNE})
     */
    private TypeTerrain type;

    public TuileTerrain(TypeTerrain type) {
        super();
        this.type = type;
    }

    public TypeTerrain getType() {
        return type;
    }

    @Override
    public int getSurcout() {
        if(type.equals(TypeTerrain.MONTAGNE)){
            return 2;
        } else if(type.equals(TypeTerrain.FLEUVE)){
            return 1;
        }else{
            return 0;
        }
    }
}

package bataille;

import java.util.Arrays;

public class Joueur{

    private Carte[] tableau;
    private int point;

    public Joueur() {
        tableau = new Carte[0];
        point = 0;
    }


    public void setTableau(Carte[] tableau) {
        this.tableau = tableau;
    }
    public Carte[] getTableau() {
        return tableau;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }


    //Supprimer une carte d'un paquet
    public void tireCarte(int indice){
        // Créez un nouveau tableau avec une taille réduite d'une unité
        Carte[] nouveauTableau = new Carte[tableau.length - 1];
        int newIndex = 0;

        // Copiez les éléments du tableau original dans le nouveau tableau
        for (int i = 0; i < tableau.length; i++) {
            if (i != indice) {
                nouveauTableau[newIndex] = tableau[i];
                newIndex++;
            }
        }

        // Mettez à jour le tableau de cartes avec le nouveau tableau
        tableau = nouveauTableau;
    }


    //ajouter une nouvelle carte au paquet
    public void addCart(Carte[] carte, Carte indiceCarte){
            int nouvelleTaille = carte.length + 1;
            Carte[] nouveauTableau = new Carte[nouvelleTaille];

            // Copiez les éléments du tableau d'origine dans le nouveau tableau.
            for(int i = 0; i < carte.length; i++){
                nouveauTableau[i] = carte[i];
            }
            // Ajoutez le nouvel élément à la fin du nouveau tableau.
            nouveauTableau[nouvelleTaille - 1] = indiceCarte;
            this.tableau = nouveauTableau;
    }


    @Override
    public String toString() {
        return "Joueur{" +
                "tableau=" + Arrays.toString(tableau) +
                ", point=" + point +
                '}';
    }


}

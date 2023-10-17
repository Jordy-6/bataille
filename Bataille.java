package bataille;

import java.awt.*;
import java.util.Arrays;
import java.util.Calendar;

public class Bataille {
    public static void main(String[] args) {

        Carte[] paquet = new Carte[52];
        String[] c = Carte.getColor_tab();
        int[] v = Carte.getValue_tab();

        int index = 0;

        for(int i = 0; i < c.length;i++){
            for(int j = 0; j < v.length;j++){
                paquet[index] = new Carte(c[i],v[j]);
                index++;
            }
        }

        for (int i = 0; i < paquet.length; i++) {
            int randomIndex = (int) (Math.random() * paquet.length);
            // Échangez les cartes aux indices i et randomIndex
            Carte temp = paquet[i];
            paquet[i] = paquet[randomIndex];
            paquet[randomIndex] = temp;
        }

        Joueur j1 = new Joueur();
        Joueur j2 = new Joueur();
        Joueur pile = new Joueur();

        j1.setTableau(Arrays.copyOfRange(paquet,0,26));
        j2.setTableau(Arrays.copyOfRange(paquet,26,52));

        //tant que le paquet de l'un des 2 joueurs n'est pas vide
        while(j1.getTableau().length != 0 || j2.getTableau().length != 0){
            if(j1.getTableau().length == 0){
                System.out.println("Le joueur 2 a gagné !");
                break;
            }
            else if (j2.getTableau().length == 0){
                System.out.println("Le joueur 1 a gagné ! ");
                break;
            }
            else{
                System.out.println("Carte du joueur 1: " + j1.getTableau()[0] +
                        " vs Carte du joueur 2: " + j2.getTableau()[0]);





                if(Carte.comparer(j1.getTableau()[0],j2.getTableau()[0]) == 1){
                    if(pile.getTableau().length != 0){
                        while(pile.getTableau().length != 0){
                            for(int i = 0; i < pile.getTableau().length;i++){
                                j1.addCart(j1.getTableau(),pile.getTableau()[0]);
                                pile.tireCarte(0);
                            }
                        }
                        j1.addCart(j1.getTableau(), j2.getTableau()[0]);
                        j1.addCart(j1.getTableau(), j1.getTableau()[0]);
                        j1.tireCarte(0);
                        j2.tireCarte(0);
                    }
                    else{
                        System.out.println("Le joueur 1 a gagné, il récupere donc le " + j2.getTableau()[0]);
                        j1.addCart(j1.getTableau(), j2.getTableau()[0]);
                        j1.addCart(j1.getTableau(), j1.getTableau()[0]);
                        j1.tireCarte(0);
                        j2.tireCarte(0);
                        j1.setPoint(j1.getPoint()+1);
                        System.out.println("Joueur 1: " + j1.getTableau().length + " cartes restantes, " +
                                j1.getPoint()+" points\n"+
                                "Joueur 2: " + j2.getTableau().length + " cartes restantes, " +
                                j2.getPoint()+" points\n");
                    }

                }

                else if (Carte.comparer(j1.getTableau()[0],j2.getTableau()[0]) == 2) {
                    if(pile.getTableau().length != 0){
                        while(pile.getTableau().length != 0){
                            for(int i = 0; i < pile.getTableau().length;i++){
                                j2.addCart(j2.getTableau(),pile.getTableau()[0]);
                                pile.tireCarte(0);
                            }
                        }
                        j2.addCart(j2.getTableau(), j1.getTableau()[0]);
                        j2.addCart(j2.getTableau(), j2.getTableau()[0]);
                        j2.tireCarte(0);
                        j1.tireCarte(0);
                    }
                    else{
                        System.out.println("Le joueur 2 a gagné, il récupere donc le " + j1.getTableau()[0]);
                        j2.addCart(j2.getTableau(), j2.getTableau()[0]);
                        j2.addCart(j2.getTableau(), j1.getTableau()[0]);
                        j1.tireCarte(0);
                        j2.tireCarte(0);
                        j2.setPoint(j2.getPoint()+1);
                        System.out.println("Joueur 1: " + j1.getTableau().length + " cartes restantes, " +
                                j1.getPoint()+" points\n"+
                                "Joueur 2: " + j2.getTableau().length + " cartes restantes, " +
                                j2.getPoint()+" points\n");
                    }
                }
                else{
                    System.out.println("Egalité, veuillez placer vos cartes dans" +
                            " la pile et relancer\n");
                    pile.addCart(pile.getTableau(), j1.getTableau()[0]);
                    pile.addCart(pile.getTableau(), j2.getTableau()[0]);
                    j1.tireCarte(0);
                    j2.tireCarte(0);
                }
            }
        }
    }
}
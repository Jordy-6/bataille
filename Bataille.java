package bataille;

import java.awt.*;
import java.util.Arrays;
import java.util.Calendar;

public class Bataille {
    public static void main(String[] args) {
        //on initialise le paquet
        Carte[] paquet = new Carte[52];
        String[] c = Carte.getColor_tab();
        int[] v = Carte.getValue_tab();

        //on initialise les cartes
        int index = 0;
        for (int i = 0; i < c.length; i++) {
            for (int j = 0; j < v.length; j++) {
                paquet[index] = new Carte(c[i], v[j]);
                index++;
            }
        }

        //on mélange les cartes du paquet
        for (int i = 0; i < paquet.length; i++) {
            int randomIndex = (int) (Math.random() * paquet.length);
            // Échangez les cartes aux indices i et randomIndex
            Carte temp = paquet[i];
            paquet[i] = paquet[randomIndex];
            paquet[randomIndex] = temp;
        }

        //initialise les joueurs et la pile pour l'égalité
        Joueur j1 = new Joueur();
        Joueur j2 = new Joueur();
        Joueur pile = new Joueur();

        //on sépare les paquet en 2 et on attribue chaque partie à un joueur
        j1.setTableau(Arrays.copyOfRange(paquet, 0, 26));
        j2.setTableau(Arrays.copyOfRange(paquet, 26, 52));

        //tant que le paquet de l'un des 2 joueurs n'est pas vide
        while (j1.getTableau().length != 0 || j2.getTableau().length != 0) {
            //si le paquet du joueur 1 est vide, le joueur 2 gagne
            if (j1.getTableau().length == 0) {
                System.out.println("Le joueur 2 a gagné !");
                break;
            }
            //sinon si le paquet du joueur 2 est vide, on annonce le joueur 1 comme vainqueur
            else if (j2.getTableau().length == 0) {
                System.out.println("Le joueur 1 a gagné ! ");
                break;
            }
            //si aucun des 2 joueurs n'a un paquet vide
            else {
                //on affiche la 1ere carte du joueur 1 et la 1ere carte du joueur 2
                System.out.println("Carte du joueur 1: " + j1.getTableau()[0] +
                        " vs Carte du joueur 2: " + j2.getTableau()[0]);

                //Si la valeur de la carte du joueur 1 est supérieure à la valeur de la carte du joueur 2:
                if (Carte.comparer(j1.getTableau()[0], j2.getTableau()[0]) == 1) {
                    //pile en cas d'égalité
                    if (pile.getTableau().length != 0) {
                        //on ajoute la pile au paquet du joueur 1 et on vide la pile
                        while (pile.getTableau().length != 0) {
                            for (int i = 0; i < pile.getTableau().length; i++) {
                                j1.addCart(j1.getTableau(), pile.getTableau()[0]);
                                pile.tireCarte(0);
                            }
                        }
                        //on ajoute la carte perdue du joueur 2 que l'on met à la fin du paquet avec la carte
                        //que le joueur 1 a utilisé
                        System.out.println("Le joueur 1 a gagné, il récupere donc le " + j2.getTableau()[0]
                                + " et la pile\n");
                        j1.addCart(j1.getTableau(), j2.getTableau()[0]);
                        j1.addCart(j1.getTableau(), j1.getTableau()[0]);
                        j1.tireCarte(0);
                        j2.tireCarte(0);
                        j1.setPoint(j1.getPoint() + 1);
                        System.out.println("Joueur 1: " + j1.getTableau().length + " cartes restantes, " +
                                j1.getPoint() + " points\n" +
                                "Joueur 2: " + j2.getTableau().length + " cartes restantes, " +
                                j2.getPoint() + " points\n");
                    }
                    else {
                        //on ajoute la carte perdue du joueur 2 au paquet du joueur 1 que l'on met à la fin du
                        //paquet avec la carte que le joueur 1 a utilisé
                        System.out.println("Le joueur 1 a gagné, il récupere donc le " + j2.getTableau()[0]);
                        j1.addCart(j1.getTableau(), j2.getTableau()[0]);
                        j1.addCart(j1.getTableau(), j1.getTableau()[0]);
                        j1.tireCarte(0);
                        j2.tireCarte(0);
                        j1.setPoint(j1.getPoint() + 1);
                        System.out.println("Joueur 1: " + j1.getTableau().length + " cartes restantes, " +
                                j1.getPoint() + " points\n" +
                                "Joueur 2: " + j2.getTableau().length + " cartes restantes, " +
                                j2.getPoint() + " points\n");
                    }
                }

                //le meme fonctionnement que la condition précédente cette fois-ci pour le joueur 2
                else if (Carte.comparer(j1.getTableau()[0], j2.getTableau()[0]) == 2) {
                    if (pile.getTableau().length != 0) {
                        while (pile.getTableau().length != 0) {
                            for (int i = 0; i < pile.getTableau().length; i++) {
                                j2.addCart(j2.getTableau(), pile.getTableau()[0]);
                                pile.tireCarte(0);
                            }
                        }
                        System.out.println("Le joueur 2 a gagné, il récupere donc le " + j1.getTableau()[0]
                                + " et la pile\n");
                        j2.addCart(j2.getTableau(), j1.getTableau()[0]);
                        j2.addCart(j2.getTableau(), j2.getTableau()[0]);
                        j2.tireCarte(0);
                        j1.tireCarte(0);
                        j2.setPoint(j2.getPoint() + 1);
                        System.out.println("Joueur 1: " + j1.getTableau().length + " cartes restantes, " +
                                j1.getPoint() + " points\n" +
                                "Joueur 2: " + j2.getTableau().length + " cartes restantes, " +
                                j2.getPoint() + " points\n");
                    } else {
                        System.out.println("Le joueur 2 a gagné, il récupere donc le " + j1.getTableau()[0]);
                        j2.addCart(j2.getTableau(), j2.getTableau()[0]);
                        j2.addCart(j2.getTableau(), j1.getTableau()[0]);
                        j1.tireCarte(0);
                        j2.tireCarte(0);
                        j2.setPoint(j2.getPoint() + 1);
                        System.out.println("Joueur 1: " + j1.getTableau().length + " cartes restantes, " +
                                j1.getPoint() + " points\n" +
                                "Joueur 2: " + j2.getTableau().length + " cartes restantes, " +
                                j2.getPoint() + " points\n");
                    }
                }

                else {
                    //Si il y'a égalité, on ajoute à la pile les cartes des 2 joueurs
                    System.out.println("Egalité, veuillez placer vos cartes dans" +
                            " la pile et relancer\n");
                    pile.addCart(pile.getTableau(), j1.getTableau()[0]);
                    pile.addCart(pile.getTableau(), j2.getTableau()[0]);
                    j1.tireCarte(0);
                    j2.tireCarte(0);
                    System.out.println("Joueur 1: " + j1.getTableau().length + " cartes restantes\n" +
                            "Joueur 2: " + j2.getTableau().length + " cartes restantes\n");
                }
            }
        }
    }
}
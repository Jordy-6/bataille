package bataille;

public class Carte {
    private String color;
    private int value;
    private static String[] color_tab = {"Carreau","Trèfle","Pique","Coeur"};
    private static int[] value_tab = {2,3,4,5,6,7,8,9,10,11,12,13,14};

    public Carte(String color, int value) {
        this.color = color;
        this.value = value;
    }

    public static String[] getColor_tab() {
        return color_tab;
    }

    public static int[] getValue_tab() {
        return value_tab;
    }


    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    // on compare la valeur de la carte 1 à celle de la carte 2
    public static int comparer(Carte carte1, Carte carte2){
        if(carte1.value > carte2.value){
            return 1;
        } else if (carte1.value < carte2.value) {
            return 2;
        }
        return 0;
    }
    @Override
    public String toString() {
        return value + " de " + color;
    }
}

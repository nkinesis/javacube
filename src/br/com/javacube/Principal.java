package br.com.javacube;

public class Principal {

    public static void main(String[] args) {
        
        Cubo c = new Cubo();

        c.populaOrganizado();
        c.print();
        c.mover("U", "H");
        c.print();
    }

    public static void resolveCubo(Cubo c) {
        int face = Utils.randInt(0, 5);
        int direcao = Utils.randInt(0, 1);
        System.out.println("TODO: Não implementado");
        c.mover("D", "H");
    }

    public static boolean isResolvido() {
        System.out.println("TODO: Não implementado");
        return false;
    }

    //FRONT, BOTTOM, UP, RIGHT, DOWN, LEFT
    //0    ,1      ,2  ,3     ,4    ,5
    //LEFT (0), RIGHT (1)
}

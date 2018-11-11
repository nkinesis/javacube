package br.com.javacube;

public class Impressora {

    public String getLinha(int[][] m, int linha) {
        String ret = "";
        for (int i = 0; i < 3; i++) {
            ret += m[linha][i] + " ";
        }
        return ret;
    }

    public String getLinhaInv(int[][] m, int linha) {
        String ret = "";
        for (int i = 0; i < 3; i++) {
            ret += m[linha][i] + " ";
        }
        return inverter(ret);
    }

    public String getCol(int[][] m, int col) {
        String ret = "";
        for (int i = 0; i < 3; i++) {
            ret += m[i][col] + " ";
        }
        return ret;
    }

    public String getColInv(int[][] m, int col) {
        String ret = "";
        for (int i = 0; i < 3; i++) {
            ret += m[i][col] + " ";
        }
        return inverter(ret);
    }

    public String inverter(String seq) {
        String[] validData = seq.split(" ");
        for (int i = 0; i < validData.length / 2; i++) {
            String temp = validData[i];
            validData[i] = validData[validData.length - i - 1];
            validData[validData.length - i - 1] = temp;
        }
        seq = "";
        for (String s : validData) {
            seq += s + " ";
        }
        return seq;
    }

    public void printarEstado(Cubo c) {
        String space = "       ";
        String horizontal = "--------";
        System.out.println(space + "|" + getLinhaInv(c.gMat("B"), 2) + "|");
        System.out.println(space + "|" + getLinhaInv(c.gMat("B"), 1) + "|");
        System.out.println(space + "|" + getLinhaInv(c.gMat("B"), 0) + "|");
        System.out.println(space + horizontal);
        System.out.print("|" + getColInv(c.gMat("L"), 0) + "|");
        System.out.print(getLinhaInv(c.gMat("U"), 2) + "|");
        System.out.print(getCol(c.gMat("R"), 2) + "|");
        System.out.print(getLinha(c.gMat("D"), 2) + "|");
        System.out.println("");
        System.out.print("|" + getColInv(c.gMat("L"), 1) + "|");
        System.out.print(getLinhaInv(c.gMat("U"), 1) + "|");
        System.out.print(getCol(c.gMat("R"), 1) + "|");
        System.out.print(getLinha(c.gMat("D"), 1) + "|");
        System.out.println("");
        System.out.print("|" + getColInv(c.gMat("L"), 2) + "|");
        System.out.print(getLinhaInv(c.gMat("U"), 0) + "|");
        System.out.print(getCol(c.gMat("R"), 0) + "|");
        System.out.print(getLinha(c.gMat("D"), 0) + "|");
        System.out.println("");
        System.out.println(space + horizontal);
        System.out.println(space + "|" + getLinha(c.gMat("F"), 0) + "|");
        System.out.println(space + "|" + getLinha(c.gMat("F"), 1) + "|");
        System.out.println(space + "|" + getLinha(c.gMat("F"), 2) + "|");
        System.out.println("");
        System.out.println("");
    }

    public static void printaSimples(Cubo c) {
        int cs = c.getCores().size();
        for (int i = 0; i < cs; i++) {
            int[][] m = c.getCores().get(i); //face
            for (int j = 0; j < 3; j++) { //linha
                for (int k = 0; k < 3; k++) { //coluna
                    System.out.print(m[j][k] + " ");
                }
                System.out.println("");
            }
            System.out.println("--------");
        }
        System.out.println("==============");
    }

}

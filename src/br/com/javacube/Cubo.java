package br.com.javacube;

import java.util.ArrayList;
import java.util.HashMap;

public class Cubo {

    private Impressora imp;
    private int[][] temp;
    private ArrayList<int[][]> cores;
    private ArrayList<Integer> coresAdd;
    private HashMap<String, Integer> matrizes;
    private final String[] NOMES = {"D", "U", "R", "L", "B", "F"};

    //Construtores
    public Cubo() {
        this.imp = new Impressora();
        this.cores = new ArrayList<>();
        this.matrizes = new HashMap<>();
        this.coresAdd = new ArrayList<>();
    }

    //Getters
    public String getNomeFace(int pos) {
        return this.NOMES[pos];
    }

    public String getNomeDirecao(int pos) {
        if (pos == 0) {
            return "H";
        } else {
            return "A";
        }
    }

    public ArrayList<int[][]> getCores() {
        return this.cores;
    }

    //População
    public void populaRandom() {
        for (int i = 0; i < 6; i++) {
            this.cores.add(new int[3][3]);
            this.matrizes.put(this.NOMES[i], i);
        }

        for (int i = 0; i < this.cores.size(); i++) {
            int[][] m = this.cores.get(i); //face
            for (int j = 0; j < 3; j++) { //linha
                for (int k = 0; k < 3; k++) { //coluna
                    int r = Utils.randInt(1, 6); //gera cor
                    if (verificaCor(r)) {
                        m[j][k] = r;
                    }
                }
            }
        }
    }

    public void populaResolvido() {
        for (int i = 0; i < 6; i++) {
            this.cores.add(new int[3][3]);
            this.matrizes.put(this.NOMES[i], i);
        }

        for (int i = 0; i < this.cores.size(); i++) {
            int[][] m = this.cores.get(i); //face
            int r = Utils.randInt(1, 6); //gera cor
            if (verificaCor(r)) {
                for (int j = 0; j < 3; j++) { //linha
                    for (int k = 0; k < 3; k++) { //coluna
                        m[j][k] = r;
                        this.coresAdd.add(r);
                    }
                }
            } else {
                i--;
            }
        }
    }

    public void populaTeste() {
        //Atenção: Método de teste de alinhamento das faces do cubo, não utilizar na prática
        for (int i = 0; i < 6; i++) {
            this.cores.add(new int[3][3]);
            this.matrizes.put(this.NOMES[i], i);
        }

        for (int i = 0; i < this.cores.size(); i++) {
            int[][] m = this.cores.get(i); //face
            int r = 1;
            for (int j = 0; j < 3; j++) { //linha
                for (int k = 0; k < 3; k++) { //coluna
                    m[j][k] = r++;
                }
            }
        }
    }

    //Movimento
    public void mover(String face, String direcao) {
        switch (direcao) {
            case "H":
                moverHor(face);
                break;
            case "A":
                moverAntihor(face);
                break;
            default:
                System.out.println("Direção inválida: " + direcao);
                break;
        }
    }

    public void moverHor(String mov) {
        switch (mov) {
            case "D":
                this.temp = passaLinha(gMat("F"), gMat("R"), 2);
                this.temp = passaLinha(this.temp, gMat("B"), 2);
                this.temp = passaLinha(this.temp, gMat("L"), 2);
                passaLinha(this.temp, gMat("F"), 2);
                break;
            case "U":
                this.temp = passaLinha(gMat("F"), gMat("L"), 0);
                this.temp = passaLinha(this.temp, gMat("B"), 0);
                this.temp = passaLinha(this.temp, gMat("R"), 0);
                passaLinha(this.temp, gMat("F"), 0);
                break;
            case "R":
                this.temp = passaColuna(gMat("F"), gMat("U"), 2);
                this.temp = passaColuna(this.temp, gMat("B"), 2, 0); //COL[2] U --> COL[0] B
                this.temp = passaColuna(this.temp, gMat("D"), 0, 2); //COL[0] B --> COL[2] D
                passaColuna(this.temp, gMat("F"), 2);
                break;
            case "L":
                this.temp = passaColuna(gMat("F"), gMat("D"), 0);
                this.temp = passaColuna(this.temp, gMat("B"), 0, 2); //COL[0] D --> COL[2] B
                this.temp = passaColuna(this.temp, gMat("U"), 2, 0); //COL[2] B --> COL[0] U
                passaColuna(this.temp, gMat("F"), 0);
                break;
            case "B":
                this.temp = tColLin(gMat("R"), gMat("U"), 2, 2); //COL[2] R --> LIN[2] U
                this.temp = tLinCol(this.temp, gMat("L"), 2, 0); //LIN[2] U --> COL[0] L
                this.temp = tColLin(this.temp, gMat("D"), 0, 2); //COL[0] L --> LIN[2] D
                tLinCol(this.temp, gMat("R"), 2, 2); //LIN[2] D --> COL[2] R
                break;
            case "F":
                this.temp = tColLin(gMat("R"), gMat("D"), 0, 0); //COL[0] R --> LIN[0] D 
                this.temp = tLinCol(this.temp, gMat("L"), 0, 2); //LIN[0] D --> COL[2] L
                this.temp = tColLin(this.temp, gMat("U"), 2, 0); //COL[2] L --> LIN[0] U
                tLinCol(this.temp, gMat("R"), 0, 0); //LIN[0] U --> COL[0] R
                break;
        }
    }

    public void moverAntihor(String mov) {
        switch (mov) {
            case "D":
                this.temp = passaLinha(gMat("F"), gMat("L"), 2);
                this.temp = passaLinha(this.temp, gMat("B"), 2);
                this.temp = passaLinha(this.temp, gMat("R"), 2);
                passaLinha(this.temp, gMat("F"), 2);
                break;
            case "U":
                this.temp = passaLinha(gMat("F"), gMat("R"), 0);
                this.temp = passaLinha(this.temp, gMat("B"), 0);
                this.temp = passaLinha(this.temp, gMat("L"), 0);
                passaLinha(this.temp, gMat("F"), 0);
                break;
            case "R":
                this.temp = passaColuna(gMat("F"), gMat("D"), 2);
                this.temp = passaColuna(this.temp, gMat("B"), 2, 0); //COL[2] D --> COL[0] B
                this.temp = passaColuna(this.temp, gMat("U"), 0, 2); //COL[0] B --> COL[2] U
                passaColuna(this.temp, gMat("F"), 2);
                break;
            case "L":
                this.temp = passaColuna(gMat("F"), gMat("U"), 0);
                this.temp = passaColuna(this.temp, gMat("B"), 0, 2); //COL[0] U --> COL[2] B
                this.temp = passaColuna(this.temp, gMat("D"), 2, 0); //COL[2] B --> COL[0] D
                passaColuna(this.temp, gMat("F"), 0);
                break;
            case "B":
                this.temp = tColLin(gMat("R"), gMat("D"), 2, 2); //COL[2] R --> LIN[2] D
                this.temp = tLinCol(this.temp, gMat("L"), 2, 0); //LIN[2] D --> COL[0] L
                this.temp = tColLin(this.temp, gMat("U"), 0, 2); //COL[0] L --> LIN[2] U
                tLinCol(this.temp, gMat("R"), 2, 2); //LIN[2] U --> COL[2] R
                break;
            case "F":
                this.temp = tColLin(gMat("R"), gMat("U"), 0, 0); //COL[0] R --> LIN[0] U 
                this.temp = tLinCol(this.temp, gMat("L"), 0, 2); //LIN[0] U --> COL[2] L
                this.temp = tColLin(this.temp, gMat("D"), 2, 0); //COL[2] L --> LIN[0] D
                tLinCol(this.temp, gMat("R"), 0, 0); //LIN[0] D --> COL[0] R
                break;
        }
    }

    public boolean verificaCor(int cor) {
        int count = 0;
        for (int c : this.coresAdd) {
            if (c == cor) {
                count++;
            }
        }
        return count < 9;
    }

    //Resolução
    public void resolver(int limite) {
        int passo = 1;
        while (!this.isResolvido() && passo < limite) {
            int face = Utils.randInt(0, 5);
            int direcao = Utils.randInt(0, 1);
            this.mover(this.getNomeFace(face), this.getNomeDirecao(direcao));
            System.out.println("Passo " + (passo++) + ":");
            this.print();
        }
        if (this.isResolvido()) {
            System.out.println("O cubo foi resolvido em " + passo + " tentativa(s)!");
        } else {
            System.out.println("Sem resolução após " + passo + " tentativas.");
        }
    }

    public boolean isResolvido() {
        int cs = this.cores.size();
        for (int i = 0; i < cs; i++) {
            int[][] m = this.cores.get(i); //face
            int primeira = m[0][0];
            for (int j = 0; j < 3; j++) { //linha
                for (int k = 0; k < 3; k++) { //coluna
                    if (m[j][k] != primeira) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    //Impressão
    public void print() {
        this.imp.printarEstado(this);
    }

    //Operações com matrizes
    public int[][] gMat(String mat) {
        return this.cores.get(this.matrizes.get(mat));
    }

    public int[][] cMat(int[][] destino) {
        int[][] aux = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                aux[i][j] = destino[i][j];
            }
        }
        return aux;
    }

    public int[][] passaColuna(int[][] origem, int[][] destino, int col) {
        int[][] aux = cMat(destino);
        for (int i = 0; i < 3; i++) {
            destino[i][col] = origem[i][col];
        }
        return aux;
    }

    public int[][] passaColuna(int[][] origem, int[][] destino, int colOrig, int colDest) {
        int[][] aux = cMat(destino);
        for (int i = 0; i < 3; i++) {
            destino[i][colDest] = origem[i][colOrig];
        }
        return aux;
    }

    public int[][] passaLinha(int[][] origem, int[][] destino, int lin) {
        int[][] aux = cMat(destino);
        for (int i = 0; i < 3; i++) {
            destino[lin][i] = origem[lin][i];
        }
        return aux;
    }

    public int[][] passaLinha(int[][] origem, int[][] destino, int linOrig, int linDest) {
        int[][] aux = cMat(destino);
        for (int i = 0; i < 3; i++) {
            destino[linDest][i] = origem[linOrig][i];
        }
        return aux;
    }

    public int[][] tLinCol(int[][] origem, int[][] destino, int lin, int col) {
        int[][] aux = cMat(destino);
        for (int i = 0; i < 3; i++) {
            destino[i][col] = origem[lin][i];
        }
        return aux;
    }

    public int[][] tColLin(int[][] origem, int[][] destino, int col, int lin) {
        int[][] aux = cMat(destino);
        for (int i = 0; i < 3; i++) {
            destino[i][lin] = origem[col][i];
        }
        return aux;
    }
}

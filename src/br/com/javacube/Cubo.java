package br.com.javacube;

import java.util.ArrayList;
import java.util.HashMap;

public class Cubo {

    private Impressora imp;
    private int[][] fantasma;
    private ArrayList<int[][]> cores;
        private ArrayList<Integer> coresAdd;
    private HashMap<String, Integer> matrizes;
    private final String[] NOMES = {"D", "U", "R", "L", "B", "F"};

    public Cubo() {
        imp = new Impressora();
        cores = new ArrayList<>();
        matrizes = new HashMap<>();
        coresAdd = new ArrayList<>();
    }

    public void print() {
        imp.printarEstado(this);
    }

    public ArrayList<int[][]> getCores() {
        return cores;
    }

    public void populaRandom() {
        for (int i = 0; i < 6; i++) {
            cores.add(new int[3][3]);
            matrizes.put(NOMES[i], i);
        }

        for (int i = 0; i < cores.size(); i++) {
            int[][] m = cores.get(i); //face
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

    public void populaOrganizado() {
        for (int i = 0; i < 6; i++) {
            cores.add(new int[3][3]);
            matrizes.put(NOMES[i], i);
        }

        for (int i = 0; i < cores.size(); i++) {
            int[][] m = cores.get(i); //face
            int r = Utils.randInt(1, 6); //gera cor
            if (verificaCor(r)) {
                for (int j = 0; j < 3; j++) { //linha
                    for (int k = 0; k < 3; k++) { //coluna
                        m[j][k] = r;
                        coresAdd.add(r);
                    }
                }
            } else {
                i--;
            }
        }
    }

    public void populaTeste() {
        for (int i = 0; i < 6; i++) {
            cores.add(new int[3][3]);
            matrizes.put(NOMES[i], i);
        }

        for (int i = 0; i < cores.size(); i++) {
            int[][] m = cores.get(i); //face
            int r = 1;
            for (int j = 0; j < 3; j++) { //linha
                for (int k = 0; k < 3; k++) { //coluna
                    m[j][k] = r++;
                }
            }
        }
    }

    public boolean verificaCor(int cor) {
        int count = 0;
        for (int c : coresAdd) {
            if (c == cor) {
                count++;
            }
        }
        return count < 9;
    }

    //TODO: fazer movimento anti-horário tbm
    //U, D, R, L, B: revisados
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
                fantasma = passaLinha(gMat("F"), gMat("R"), 2);
                fantasma = passaLinha(fantasma, gMat("B"), 2);
                fantasma = passaLinha(fantasma, gMat("L"), 2);
                passaLinha(fantasma, gMat("F"), 2);
                break;
            case "U":
                fantasma = passaLinha(gMat("F"), gMat("L"), 0);
                fantasma = passaLinha(fantasma, gMat("B"), 0);
                fantasma = passaLinha(fantasma, gMat("R"), 0);
                passaLinha(fantasma, gMat("F"), 0);
                break;
            case "R":
                fantasma = passaColuna(gMat("F"), gMat("U"), 2);
                fantasma = passaColuna(fantasma, gMat("B"), 2, 0); //COL[2] U --> COL[0] B
                fantasma = passaColuna(fantasma, gMat("D"), 0, 2); //COL[0] B --> COL[2] D
                passaColuna(fantasma, gMat("F"), 2);
                break;
            case "L":
                fantasma = passaColuna(gMat("F"), gMat("D"), 0);
                fantasma = passaColuna(fantasma, gMat("B"), 0, 2); //COL[0] D --> COL[2] B
                fantasma = passaColuna(fantasma, gMat("U"), 2, 0); //COL[2] B --> COL[0] U
                passaColuna(fantasma, gMat("F"), 0);
                break;
            case "B":
                fantasma = tColLin(gMat("R"), gMat("U"), 2, 2); //COL[2] R --> LIN[2] U
                fantasma = tLinCol(fantasma, gMat("L"), 2, 0); //LIN[2] U --> COL[0] L
                fantasma = tColLin(fantasma, gMat("D"), 0, 2); //COL[0] L --> LIN[2] D
                tLinCol(fantasma, gMat("R"), 2, 2); //LIN[2] D --> COL[2] R
                break;
            case "F":
                fantasma = tColLin(gMat("R"), gMat("D"), 0, 0); //COL[0] R --> LIN[0] D 
                fantasma = tLinCol(fantasma, gMat("L"), 0, 2); //LIN[0] D --> COL[2] L
                fantasma = tColLin(fantasma, gMat("U"), 2, 0); //COL[2] L --> LIN[0] U
                tLinCol(fantasma, gMat("R"), 0, 0); //LIN[0] U --> COL[0] R
                break;
        }
    }

    public void moverAntihor(String mov) {
        switch (mov) {
            case "D":
                fantasma = passaLinha(gMat("F"), gMat("L"), 2);
                fantasma = passaLinha(fantasma, gMat("B"), 2);
                fantasma = passaLinha(fantasma, gMat("R"), 2);
                passaLinha(fantasma, gMat("F"), 2);
                break;
            case "U":
                fantasma = passaLinha(gMat("F"), gMat("R"), 0);
                fantasma = passaLinha(fantasma, gMat("B"), 0);
                fantasma = passaLinha(fantasma, gMat("L"), 0);
                passaLinha(fantasma, gMat("F"), 0);
                break;
            case "R":
                fantasma = passaColuna(gMat("F"), gMat("D"), 2);
                fantasma = passaColuna(fantasma, gMat("B"), 2, 0); //COL[2] D --> COL[0] B
                fantasma = passaColuna(fantasma, gMat("U"), 0, 2); //COL[0] B --> COL[2] U
                passaColuna(fantasma, gMat("F"), 2);
                break;
            case "L":
                fantasma = passaColuna(gMat("F"), gMat("U"), 0);
                fantasma = passaColuna(fantasma, gMat("B"), 0, 2); //COL[0] U --> COL[2] B
                fantasma = passaColuna(fantasma, gMat("D"), 2, 0); //COL[2] B --> COL[0] D
                passaColuna(fantasma, gMat("F"), 0);
                break;
            case "B":
                fantasma = tColLin(gMat("R"), gMat("D"), 2, 2); //COL[2] R --> LIN[2] D
                fantasma = tLinCol(fantasma, gMat("L"), 2, 0); //LIN[2] D --> COL[0] L
                fantasma = tColLin(fantasma, gMat("U"), 0, 2); //COL[0] L --> LIN[2] U
                tLinCol(fantasma, gMat("R"), 2, 2); //LIN[2] U --> COL[2] R
                break;
            case "F":
                fantasma = tColLin(gMat("R"), gMat("U"), 0, 0); //COL[0] R --> LIN[0] U 
                fantasma = tLinCol(fantasma, gMat("L"), 0, 2); //LIN[0] U --> COL[2] L
                fantasma = tColLin(fantasma, gMat("D"), 2, 0); //COL[2] L --> LIN[0] D
                tLinCol(fantasma, gMat("R"), 0, 0); //LIN[0] D --> COL[0] R
                break;
        }
    }

    public int[][] gMat(String mat) {
        return cores.get(matrizes.get(mat));
    }

    public int[][] copyM(int[][] destino) {
        int[][] aux = new int[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                aux[i][j] = destino[i][j];
            }
        }
        return aux;
    }

    public int[][] passaLinha(int[][] origem, int[][] destino, int lin) {
        int[][] aux = copyM(destino);
        for (int i = 0; i < 3; i++) {
            destino[lin][i] = origem[lin][i];
        }
        return aux;
    }

    public int[][] passaLinha(int[][] origem, int[][] destino, int linOrig, int linDest) {
        int[][] aux = copyM(destino);
        for (int i = 0; i < 3; i++) {
            destino[linDest][i] = origem[linOrig][i];
        }
        return aux;
    }

    public int[][] passaColuna(int[][] origem, int[][] destino, int col) {
        int[][] aux = copyM(destino);
        for (int i = 0; i < 3; i++) {
            destino[i][col] = origem[i][col];
        }
        return aux;
    }

    public int[][] passaColuna(int[][] origem, int[][] destino, int colOrig, int colDest) {
        int[][] aux = copyM(destino);
        for (int i = 0; i < 3; i++) {
            destino[i][colDest] = origem[i][colOrig];
        }
        return aux;
    }

    public int[][] tLinCol(int[][] origem, int[][] destino, int lin, int col) {
        int[][] aux = copyM(destino);
        for (int i = 0; i < 3; i++) {
            destino[i][col] = origem[lin][i];
        }
        return aux;
    }

    public int[][] tColLin(int[][] origem, int[][] destino, int col, int lin) {
        int[][] aux = copyM(destino);
        for (int i = 0; i < 3; i++) {
            destino[i][lin] = origem[col][i];
        }
        return aux;
    }
}

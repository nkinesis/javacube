package br.com.javacube;

/* 
CUBO MÁGICO - RESOLUÇÃO POR MÉTODO FORÇA-BRUTA
AUTORES: ALEX JUNG CELMER, GABRIEL C. ULLMANN

REFERÊNCIA

- VALORES DAS FACES :
|FRONT|BACK   |UP |RIGHT |DOWN |LEFT|
|0    |1      |2  |3     |4    |5   |
    
- VALORES DAS DIREÇÕES:
|HORÁRIO|ANTI-HORÁRIO   |
|0      |1              |
 */

public class Principal {

    public static void main(String[] args) {
        int tentativas = 100;
        Cubo c = new Cubo();
        c.populaRandom();
        c.resolver(tentativas);
    }


}

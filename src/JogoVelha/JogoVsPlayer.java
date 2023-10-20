package JogoVelha;

import java.util.Scanner;

public class JogoVsPlayer {
    static Area[][] sustenido = new Area[3][3];
    static boolean reiniciar = true;
    static char jogadorAtual = 'X';
    static char respostaVitoria;
    static char respostaEmpate;
    static boolean campoUtilizado = true;

    public static void jogarJogadorXJogador(){
        String vitoria;
        Scanner ler = new Scanner(System.in);

        while (reiniciar) {
            iniciaJogo(sustenido);

            while (true) {
                mostraJogo(sustenido);
                vitoria = verificaVitoria(sustenido);
                if (verificaEmpate(sustenido)) {
                    do {
                        System.out.println("O jogo empatou!");
                        System.out.println("Deseja jogar novamente? (S/N): ");
                        respostaEmpate = ler.next().charAt(0);
                    }while(respostaEmpate != 'S' && respostaEmpate != 's' && respostaEmpate != 'N' && respostaEmpate != 'n');
                    if (respostaEmpate != 'S' && respostaEmpate != 's') {
                        reiniciar = false;
                    } break;
                }
                if (!vitoria.isEmpty()) {
                    do{
                        System.out.printf("Jogador %s ganhou! Deseja reiniciar o jogo? (S/N): ", vitoria);
                        respostaVitoria = ler.next().charAt(0);
                    }while(respostaVitoria != 'S' && respostaVitoria != 's' && respostaVitoria != 'N' && respostaVitoria != 'n');
                    if (respostaVitoria != 'S' && respostaVitoria != 's') {
                        reiniciar = false;
                    }
                    break;
                }
                try {
                    if (verificaJogada(sustenido, jogar(ler, jogadorAtual), jogadorAtual)) {
                        if (jogadorAtual == 'X') {
                            jogadorAtual = 'O';
                        } else {
                            jogadorAtual = 'X';
                        }
                    }

                } catch (Exception e) {
                    System.out.print("Erro");
                }
            }
        }
    }

    public static void mostraJogo(Area[][]sustenido){
        limparTela();
        System.out.println("    0   1    2");
        System.out.printf("0   %c | %c | %c %n", sustenido[0][0].getSimbolo(), sustenido[0][1].getSimbolo(), sustenido[0][2].getSimbolo());
        System.out.println("   ------------");
        System.out.printf("1   %c | %c | %c %n", sustenido[1][0].getSimbolo(), sustenido[1][1].getSimbolo(), sustenido[1][2].getSimbolo());
        System.out.println("   ------------");
        System.out.printf("2   %c | %c | %c %n", sustenido[2][0].getSimbolo(), sustenido[2][1].getSimbolo(), sustenido[2][2].getSimbolo());
    }

    public static void limparTela() {
        for (int cont = 0; cont < 10; cont++) {
            System.out.println(" ");
        }
    }

    public static int[] jogar(Scanner ler, char jogadorAtual) {
        int[] posicao = new int[2];
        System.out.printf("%s %c%n", "Quem deve jogar: ", jogadorAtual);
        try {
            if(!campoUtilizado){
                System.out.println("Campo já utilizado, escolha outro!");
            }
            System.out.print("Informe a linha que deseja jogar:");
            posicao[0] = Integer.parseInt(ler.next());
            System.out.print("Informe a coluna que deseja jogar:");
            posicao[1] = Integer.parseInt(ler.next());
            if (posicao[0] < 0 || posicao[0] > 2 || posicao[1] < 0 || posicao[1] > 2) {
                System.out.println("Informe um número válido entre 0 e 2!");
                return jogar(ler, jogadorAtual);
            }
        } catch (NumberFormatException e) {
            System.out.println("Informe apenas números!");
            return jogar(ler, jogadorAtual);
        }
        return posicao;
    }


    public static Boolean verificaJogada (Area[][]sustenido, int[] lc, char jogadorAtual ){
        if (sustenido[lc[0]][lc[1]].getSimbolo() == ' ') {
            sustenido[lc[0]][lc[1]].setSimbolo(jogadorAtual);
            return campoUtilizado = true;
        } else {
            return  campoUtilizado = false;
        }
    }

    public static void iniciaJogo (Area[][]sustenido){
        for (int linha = 0; linha < 3; linha++) {
            for (int coluna = 0; coluna < 3; coluna++) {
                sustenido[linha][coluna] = new Area();
            }
        }
    }
    public static String verificaVitoria(Area[][] velha) {
        for (int linha = 0; linha < 3; linha++) {
            if (velha[linha][0].getSimbolo() != ' ' &&
                    velha[linha][0].getSimbolo() == velha[linha][1].getSimbolo() &&
                    velha[linha][0].getSimbolo() == velha[linha][2].getSimbolo()) {
                return String.valueOf(velha[linha][0].getSimbolo());
            }
        }

        for (int coluna = 0; coluna < 3; coluna++) {
            if (velha[0][coluna].getSimbolo() != ' ' &&
                    velha[0][coluna].getSimbolo() == velha[1][coluna].getSimbolo() &&
                    velha[0][coluna].getSimbolo() == velha[2][coluna].getSimbolo()) {
                return String.valueOf(velha[0][coluna].getSimbolo());
            }
        }

        if (velha[0][0].getSimbolo() != ' ' &&
                velha[0][0].getSimbolo() == velha[1][1].getSimbolo() &&
                velha[0][0].getSimbolo() == velha[2][2].getSimbolo()) {
            return String.valueOf(velha[0][0].getSimbolo());
        }

        if (velha[0][2].getSimbolo() != ' ' &&
                velha[0][2].getSimbolo() == velha[1][1].getSimbolo() &&
                velha[0][2].getSimbolo() == velha[2][0].getSimbolo()) {
            return String.valueOf(velha[0][2].getSimbolo());
        }
        return "";
    }
    public static boolean verificaEmpate(Area[][] velha) {
        for (int linha = 0; linha < 3; linha++) {
            for (int coluna = 0; coluna < 3; coluna++) {
                if (velha[linha][coluna].getSimbolo() == ' ') {
                    return false;
                }
            }
        }
        return verificaVitoria(velha).isEmpty();
    }
    };
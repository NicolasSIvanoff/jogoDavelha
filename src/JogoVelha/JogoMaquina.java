package JogoVelha;
import java.util.Random;
import java.util.Scanner;

public class JogoMaquina {
    static Area[][] sustenido = new Area[3][3];
    static boolean reiniciar = true;
    static char jogadorAtual = 'X';

    public static void jogarJogadorXMaquina() {
        String vitoria;
        Scanner ler = new Scanner(System.in);

        while (reiniciar) {
            JogoVsPlayer.iniciaJogo(sustenido);

            while (true) {
                JogoVsPlayer.mostraJogo(sustenido);
                vitoria = JogoVsPlayer.verificaVitoria(sustenido);
                if (JogoVsPlayer.verificaEmpate(sustenido)) {
                    do {
                        System.out.println("O jogo empatou!");
                        System.out.println("Deseja jogar novamente? (S/N): ");
                        JogoVsPlayer.respostaEmpate = ler.next().charAt(0);
                    } while (JogoVsPlayer.respostaEmpate != 'S' && JogoVsPlayer.respostaEmpate != 's' && JogoVsPlayer.respostaEmpate != 'N' && JogoVsPlayer.respostaEmpate != 'n');
                    if (JogoVsPlayer.respostaEmpate != 'S' && JogoVsPlayer.respostaEmpate != 's') {
                        reiniciar = false;
                    }
                    break;
                }
                if (!vitoria.isEmpty()) {
                    do {
                        System.out.printf("Jogador %s ganhou! Deseja reiniciar o jogo? (S/N): ", vitoria);
                        JogoVsPlayer.respostaVitoria = ler.next().charAt(0);
                    } while (JogoVsPlayer.respostaVitoria != 'S' && JogoVsPlayer.respostaVitoria != 's' && JogoVsPlayer.respostaVitoria != 'N' && JogoVsPlayer.respostaVitoria != 'n');
                    if (JogoVsPlayer.respostaVitoria != 'S' && JogoVsPlayer.respostaVitoria != 's') {
                        reiniciar = false;
                    }
                    break;
                }

                int[] posicaoMaquina = jogarMaquina(sustenido, jogadorAtual);
                char computador = 'O';
                JogoVsPlayer.verificaJogada(sustenido, posicaoMaquina, computador);
            }
        }
    }

    private static int[] jogarMaquina(Area[][]sustenido, char jogadorAtual) {
        Scanner ler = new Scanner(System.in);

        int[] posicaoJogador = JogoVsPlayer.jogar(ler, jogadorAtual);
        JogoVsPlayer.verificaJogada(sustenido, posicaoJogador, jogadorAtual);

        Random random = new Random();
        int linha = random.nextInt(3);
        int coluna = random.nextInt(3);

        while (sustenido[linha][coluna].getSimbolo() != ' ') {
            linha = random.nextInt(3);
            coluna = random.nextInt(3);
        }

        return new int[]{linha, coluna};
    }

}

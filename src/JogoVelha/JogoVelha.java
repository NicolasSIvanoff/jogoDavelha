package JogoVelha;
import java.util.Scanner;

public class JogoVelha {

    public static void main(String[] args) {
        Scanner ler = new Scanner(System.in);

        int opcao = 0;
        while (opcao != 1 && opcao != 2) {
            System.out.println("1. Jogador vs Jogador");
            System.out.println("2. Jogador vs Máquina");
            System.out.println("Escolha o modo de jogo:");
            opcao = ler.nextInt();
        }

        if (opcao == 1) {
            JogoVsPlayer.jogarJogadorXJogador();
        } else {
            JogoMaquina.jogarJogadorXMaquina();
        }
    }
}

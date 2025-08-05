package jogo_pokemon.telas;

import java.util.Random;
import jogo_pokemon.Batalha;
import jogo_pokemon.LiderGin;
import jogo_pokemon.utils.ConsoleUtils;

public class TelaBatalha extends Tela {

    LiderGin oponente;
    Batalha batalha;
    Random random = new Random(); // O objeto Random pode ser um membro da classe

    public TelaBatalha(TelaContext context, LiderGin oponente, Batalha batalha) {
        super(context);
        this.oponente = oponente;
        this.batalha = batalha;
    }

    @Override
    public void mostrarTela() {
        if (batalha.getEmExecucao()) {
            exibirStatus();
            processarTurnoJogador();
            if (batalha.getEmExecucao()) { // Verifica de novo, pois o jogador pode ter vencido
                processarTurnoInimigo();
            }
        } else {
            // A batalha terminou, transiciona para a tela de vitória ou derrota
            if (batalha.getVitoria()) {
                this.trocarTela(new TelaVitoria(this.contexto));
            } else {
                this.trocarTela(new TelaDerrota(this.contexto));
            }
        }
    }

    private void exibirStatus() {
        System.out.println("---------- Batalha ----------\n");
        System.out.println("Seu pokemon: " + batalha.getPkmAmigo().getNome() + " | HP: " + batalha.getPkmAmigo().getHpAtual() + "/" + batalha.getPkmAmigo().getHp());
        System.out.println("Pokemon inimigo: " + batalha.getPkmInimigo().getNome() + " | HP: " + batalha.getPkmInimigo().getHpAtual() + "/" + batalha.getPkmInimigo().getHp());
        System.out.println("\n-----------------------------\n");
    }

    private void processarTurnoJogador() {
        System.out.println("Escolha seu ataque:");
        System.out.println("(1) - Físico");
        System.out.println("(2) - Especial (Restantes: " + batalha.getContEspecial() + ")");
        System.out.println("\nX - Desistir");

        String escolha = this.contexto.getUserInput();
        boolean turnoValido = true;

        switch (escolha) {
            case "1":
                System.out.println("\n" + batalha.getPkmAmigo().getNome() + " usa um ataque físico!");
                batalha.atacar(batalha.getPkmAmigo(), batalha.getPkmInimigo(), batalha.getPkmAmigo().getMovimentosList().get(0));
                break;
            case "2":
                if (batalha.getContEspecial() > 0) {
                    System.out.println("\n" + batalha.getPkmAmigo().getNome() + " usa um ataque especial!");
                    batalha.atacar(batalha.getPkmAmigo(), batalha.getPkmInimigo(), batalha.getPkmAmigo().getMovimentosList().get(1));
                    batalha.decrementarContEspecial();
                } else {
                    System.out.println("\nSem ataques especiais! Usando um ataque físico.");
                    batalha.atacar(batalha.getPkmAmigo(), batalha.getPkmInimigo(), batalha.getPkmAmigo().getMovimentosList().get(0));
                }
                break;
            case "x":
            case "X":
                System.out.println("\nVocê desistiu da batalha.");
                batalha.getEmExecucao(); // Força o fim da batalha
                break;
            default:
                System.out.println("\nEscolha inválida.");
                turnoValido = false;
                break;
        }

        if (turnoValido) {
            System.out.println(batalha.getPkmInimigo().getNome() + " sofreu dano!");
            ConsoleUtils.sleep(2000);
        }
    }

    private void processarTurnoInimigo() {
        // IA Simples: 50% de chance de usar especial se ainda tiver
        int escolhaInimigo = (batalha.getContEspecialLider() > 0 && random.nextBoolean()) ? 1 : 0;

        if (escolhaInimigo == 1) {
            System.out.println("\n" + batalha.getPkmInimigo().getNome() + " usa um ataque especial!");
            // CORREÇÃO CRÍTICA: Usa o movimento do inimigo!
            batalha.atacar(batalha.getPkmInimigo(), batalha.getPkmAmigo(), batalha.getPkmInimigo().getMovimentosList().get(1));
            batalha.decrementarContEspecialLider();
        } else {
            System.out.println("\n" + batalha.getPkmInimigo().getNome() + " usa um ataque físico!");
            // CORREÇÃO CRÍTICA: Usa o movimento do inimigo!
            batalha.atacar(batalha.getPkmInimigo(), batalha.getPkmAmigo(), batalha.getPkmInimigo().getMovimentosList().get(0));
        }
        System.out.println(batalha.getPkmAmigo().getNome() + " sofreu dano!");
        ConsoleUtils.sleep(2000);
    }
}
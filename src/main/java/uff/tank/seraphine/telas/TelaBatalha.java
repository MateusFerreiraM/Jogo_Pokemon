package uff.tank.seraphine.telas;

import java.util.Random;
import uff.tank.seraphine.Batalha;
import uff.tank.seraphine.LiderGin;
import uff.tank.seraphine.utils.ConsoleUtils;

//Tela exibida durante a batalha
public class TelaBatalha extends Tela {

    LiderGin oponente;
    Batalha batalha;

    @Override
    public void mostrarTela() {

        // TODO: Sistema de batalha
        Random random = new Random();
        String randomLider = Integer.toString(random.nextInt(2) + 1);

        if (batalha.getEmExecucao()) {
            System.out.println("---------- Batalha ----------\n");
            System.out.println("Seu pokemon: " + batalha.getPkmAmigo().getNome());
            System.out.println(
                    "Seu HP atual: " + batalha.getPkmAmigo().getHpAtual());
            System.out.println("Seu ataque: " + batalha.getPkmAmigo().getAtaque());
            System.out.println("Sua defesa: " + batalha.getPkmAmigo().getDefesa());
            System.out.println("-----------------------------");
            System.out.println("Pokemon inimigo: " + batalha.getPkmInimigo().getNome());
            System.out.println("HP atual do inimigo: " + batalha.getPkmInimigo().getHpAtual());
            System.out.println("Ataque do inimigo: " + batalha.getPkmInimigo().getAtaque());
            System.out.println("Defesa do inimigo: " + batalha.getPkmInimigo().getDefesa());
            System.out.println("\nEscolha seu ataque");
            System.out.println("(1) - Físico");
            System.out.println("(2) - Especial (Restantes: " + batalha.getContEspecial() + ")");
            System.out.println("\nX - Sair");

            String escolha = this.contexto.getUserInput();

            switch (escolha) {
                case "1":
                    System.out.println("\n" + batalha.getPkmAmigo().getNome() + " aplicou um ataque físico.");
                    batalha.atacar(batalha.getPkmAmigo(), batalha.getPkmInimigo(),
                            batalha.getPkmAmigo().getMovimento(0));
                    ConsoleUtils.sleep(2000);
                    break;
                case "2":
                    if (batalha.getContEspecial() > 0) {
                        System.out.println("\n" + batalha.getPkmAmigo().getNome() + " aplicou um ataque especial.");
                        batalha.atacar(batalha.getPkmAmigo(), batalha.getPkmInimigo(),
                                batalha.getPkmAmigo().getMovimento(1));
                        ConsoleUtils.sleep(2000);
                        batalha.decrementarContEspecial();
                    } else {
                        System.out.println("\nNão possui mais ataques especiais. Foi aplicado um ataque físico.");
                        batalha.atacar(batalha.getPkmAmigo(), batalha.getPkmInimigo(),
                                batalha.getPkmAmigo().getMovimento(0));
                        ConsoleUtils.sleep(1500);
                    }
                    break;
                case "x":
                case "X":
                    this.contexto.sairPrograma();
                    System.exit(0);
                default:
                    System.out.println("\nPor favor insira um valor válido");
                    ConsoleUtils.sleep(1500);
                    break;
            }

            if (batalha.getPkmInimigo().getHpAtual() <= 0) {
                System.out.println("\nHP atual do inimigo: 0");
                System.out.println("-----------------------------\n");
            } else {
                System.out.println("\nHP atual do inimigo: " + batalha.getPkmInimigo().getHpAtual());
                System.out.println("-----------------------------\n");
            }

            ConsoleUtils.sleep(2000);

            if (batalha.getPkmInimigo().getHpAtual() > 0) {
                switch (randomLider) {
                    case "1":
                        System.out.println("\n" + batalha.getPkmInimigo().getNome() + " aplicou um ataque físico.");
                        batalha.atacar(batalha.getPkmInimigo(), batalha.getPkmAmigo(),
                                batalha.getPkmAmigo().getMovimento(0));
                        ConsoleUtils.sleep(1500);
                        break;
                    case "2":
                        if (batalha.getContEspecialLider() > 0) {
                            System.out
                                    .println("\n" + batalha.getPkmInimigo().getNome() + " aplicou um ataque especial.");
                            batalha.atacar(batalha.getPkmInimigo(), batalha.getPkmAmigo(),
                                    batalha.getPkmAmigo().getMovimento(1));
                            ConsoleUtils.sleep(1500);
                            batalha.decrementarContEspecialLider();
                        } else {
                            System.out.println("\nNão possui mais ataques especiais. Foi aplicado um ataque físico.");
                            batalha.atacar(batalha.getPkmInimigo(), batalha.getPkmAmigo(),
                                    batalha.getPkmAmigo().getMovimento(0));
                            ConsoleUtils.sleep(1500);
                            break;
                        }
                        break;
                }

                if (batalha.getPkmAmigo().getHpAtual() <= 0) {
                    System.out.println("\nHP atual do seu pokemon: 0");
                    System.out.println("-----------------------------\n");
                } else {
                    System.out.println("\nHP atual do seu pokemon: " + batalha.getPkmAmigo().getHpAtual());
                    System.out.println("-----------------------------\n");
                }

                ConsoleUtils.sleep(2000);

            } else {
                System.out.println("Inimigo derrotado!");
                ConsoleUtils.sleep(2000);
            }
        } else {
            if (batalha.getVitoria()) {
                this.trocarTela(new TelaVitoria(this.contexto));
            } else {
                this.trocarTela(new TelaDerrota(this.contexto));
            }
        }
    }

    public TelaBatalha(TelaContext context, LiderGin oponente, Batalha batalha) {
        super(context);
        this.oponente = oponente;
        this.batalha = batalha;
    }
}

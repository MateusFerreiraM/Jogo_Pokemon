package uff.tank.seraphine.telas;

import java.util.Random;

import org.json.simple.JSONObject;

import uff.tank.seraphine.Batalha;
import uff.tank.seraphine.CadastroTreinador;
import uff.tank.seraphine.LiderGin;
import uff.tank.seraphine.Pokemon;
import uff.tank.seraphine.Treinador;
import uff.tank.seraphine.utils.JSONUtils;

//Tela exibida durante a batalha
public class TelaBatalha extends Tela {
    LiderGin oponente;
<<<<<<< HEAD
    Batalha batalha;

=======
>>>>>>> 7c396a327dedc009bd40a3c5838b40055d949381
    @Override
    public void mostrarTela() {
        Treinador treinador = this.contexto.getTreinador();

<<<<<<< HEAD
        int contEspecial = 2, contEspecialLider = 2;

        // TODO: Sistema de batalha

        String escolha = this.contexto.getUserInput();

        Random random = new Random();
        String randomLider = Integer.toString(random.nextInt(2) + 1);

        if (batalha.getEmExecucao()) {
            System.out.println("---------- Batalha ----------");
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
            System.out.println("-----------------------------\n");
            System.out.println("Escolha seu ataque");
            System.out.println("(1) - Físico");
            System.out.println("(2) - Especial (Restantes: " + contEspecial);
            System.out.println("\nX - Sair");

            switch (escolha) {
                case "1":
                    System.out.println("Usuario aplicou um ataque físico.");
                    batalha.atacar(batalha.getPkmAmigo(), batalha.getPkmInimigo(),
                            batalha.getPkmAmigo().getMovimento(0));
                    break;
                case "2":
                    System.out.println("Usuario aplicou um ataque especial.");
                    batalha.atacar(batalha.getPkmAmigo(), batalha.getPkmInimigo(),
                            batalha.getPkmAmigo().getMovimento(1));
=======
        int hpTreinador = treinador.getPokemonAtual().getHpAtual();
        int hpLider = oponente.getPokemonAtual().getHpAtual();
        int contEspecial = 2, contEspecialLider = 2;

        Batalha batalha = new Batalha(treinador.getPokemonAtual(), oponente.getPokemonAtual());

        // TODO: Sistema de batalha

        System.out.println("---------- Batalha ----------");
        System.out.println("Seu pokemon: " + treinador.getPokemonAtual().getNome());
        System.out.println(
                "Seu HP atual: " + treinador.getPokemonAtual().getHpAtual());
        System.out.println("Seu ataque: " + treinador.getPokemonAtual().getAtaque());
        System.out.println("Sua defesa: " + treinador.getPokemonAtual().getDefesa());
        System.out.println("-----------------------------");
        System.out.println("Pokemon inimigo: " + oponente.getPokemonAtual().getNome());
        System.out.println("HP atual do inimigo: " + oponente.getPokemonAtual().getHpAtual());
        System.out.println("Ataque do inimigo: " + oponente.getPokemonAtual().getAtaque());
        System.out.println("Defesa do inimigo: " + oponente.getPokemonAtual().getDefesa());
        System.out.println("-----------------------------\n");
        System.out.println("Escolha seu ataque");
        System.out.println("(1) - Físico");
        System.out.println("(2) - Especial (Restantes: " + contEspecial);
        System.out.println("\nX - Sair");

        String escolha = this.contexto.getUserInput();

        Random random = new Random();
        String randomLider = Integer.toString(random.nextInt(2)+1);

        while (hpTreinador > 0 && hpLider > 0) {
            switch (escolha) {
                case "1":
                    System.out.println("Usuario aplicou um ataque físico.");
                    batalha.atacar(treinador.pokemonAtual.getMovimento(0));
                    break;
                case "2":
                    System.out.println("Usuario aplicou um ataque especial.");
                    batalha.atacar(treinador.pokemonAtual.getMovimento(1));
>>>>>>> 7c396a327dedc009bd40a3c5838b40055d949381
                    contEspecial--;
                    break;
                case "x":
                case "X":
                    this.contexto.sairPrograma();
                    break;
                default:
                    System.out.println("Por favor insira um valor válido");
                    break;
            }
<<<<<<< HEAD
            if (batalha.getPkmInimigo().getHpAtual() > 0) {
                switch (randomLider) {
                    case "1":
                        System.out.println("Líder " + oponente.getNome() + " aplicou um ataque físico.");
                        batalha.atacar(batalha.getPkmInimigo(), batalha.getPkmAmigo(),
                                batalha.getPkmAmigo().getMovimento(0));
                        break;
                    case "2":
                        if (contEspecialLider > 0) {
                            System.out.println("Líder " + oponente.getNome() + " aplicou um ataque especial.");
                            batalha.atacar(batalha.getPkmInimigo(), batalha.getPkmAmigo(),
                                    batalha.getPkmAmigo().getMovimento(1));
                            contEspecialLider--;
                        } else {
                            System.out.println("Líder " + oponente.getNome() + " aplicou um ataque físico.");
                            batalha.atacar(batalha.getPkmInimigo(), batalha.getPkmAmigo(),
                                    batalha.getPkmAmigo().getMovimento(0));
=======
            if (hpLider > 0) {
                switch (randomLider) {
                    case "1":
                        System.out.println("Líder " + oponente.getNome() + " aplicou um ataque físico.");
                        batalha.atacar(oponente.pokemonAtual.getMovimento(0));
                        break;
                    case "2":
                        if (contEspecialLider>0) {
                            System.out.println("Líder " + oponente.getNome() + " aplicou um ataque especial.");
                            batalha.atacar(oponente.pokemonAtual.getMovimento(1));
                            contEspecialLider--;
                        } else {
                            System.out.println("Líder " + oponente.getNome() + " aplicou um ataque físico.");
                            batalha.atacar(oponente.pokemonAtual.getMovimento(0));
>>>>>>> 7c396a327dedc009bd40a3c5838b40055d949381
                            break;
                        }
                        break;
                }
            } else {
                System.out.println("Inimigo derrotado!");
            }
<<<<<<< HEAD
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
=======
        }
        if (hpTreinador <= 0) {
            System.out.println("Tente novamente depois :(");
        } 

    }

    public TelaBatalha(TelaContext context, LiderGin oponente) {
        super(context);
        this.oponente = oponente;
>>>>>>> 7c396a327dedc009bd40a3c5838b40055d949381
    }
}

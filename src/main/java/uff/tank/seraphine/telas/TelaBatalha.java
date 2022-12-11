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
    @Override
    public void mostrarTela() {
        Treinador treinador = this.contexto.getTreinador();
        LiderGin lider = new LiderGin(null, null, null);

        JSONObject objPkmTreinador = JSONUtils.getObjectByName(treinador.pokemonAtual.getNome(), "assets/pokemon.json");
        JSONObject objLider = JSONUtils.getObjectByID(i, "assets/lideres.json");

        int hpTreinador = this.contexto.getTreinador().getPokemons().get(pkmSelecionado).getHpAtual();
        int hpLider = 100;
        int contEspecial = 2, contEspecialLider = 2;

        Batalha batalha = new Batalha(treinador.pokemonAtual, null);

        // TODO: Sistema de batalha

        System.out.println("---------- Batalha ----------");
        System.out.println("Seu pokemon: " + objPkmTreinador.get("Nome"));
        System.out.println(
                "Seu HP atual: " + objPkmTreinador.get("HP"));
        System.out.println("Seu ataque: " + objPkmTreinador.get("Ataque"));
        System.out.println("Sua defesa: " + objPkmTreinador.get("Defesa"));
        System.out.println("-----------------------------");
        System.out.println("Pokemon inimigo: " + obj.get("Nome"));
        System.out.println("HP atual do inimigo: " + obj.get("HP"));
        System.out.println("Ataque do inimigo: " + obj.get("Ataque"));
        System.out.println("Defesa do inimigo: " + obj.get("Defesa"));
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
            if (hpLider > 0) {
                switch (randomLider) {
                    case "1":
                        System.out.println("Líder " + lider.getNome() + " aplicou um ataque físico.");
                        batalha.atacar(lider.pokemonAtual.getMovimento(0));
                        break;
                    case "2":
                        if (contEspecialLider>0) {
                            System.out.println("Líder " + lider.getNome() + " aplicou um ataque especial.");
                            batalha.atacar(lider.pokemonAtual.getMovimento(1));
                            contEspecialLider--;
                        } else {
                            System.out.println("Líder " + lider.getNome() + " aplicou um ataque físico.");
                            batalha.atacar(lider.pokemonAtual.getMovimento(0));
                            break;
                        }
                        break;
                }
            } else {
                System.out.println("Inimigo derrotado!");
            }
        }
        if (hpTreinador <= 0) {
            System.out.println("Tente novamente depois :(");
        } 

    }

    public TelaBatalha(TelaContext context) {
        super(context);
    }
}

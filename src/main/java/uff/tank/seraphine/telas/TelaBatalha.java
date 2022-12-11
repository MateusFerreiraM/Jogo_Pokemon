package uff.tank.seraphine.telas;

import org.json.simple.JSONObject;
import uff.tank.seraphine.CadastroTreinador;
import uff.tank.seraphine.Pokemon;
import uff.tank.seraphine.Treinador;
import uff.tank.seraphine.utils.JSONUtils;

//Tela exibida durante a batalha
public class TelaBatalha extends Tela {
    @Override
    public void mostrarTela() {
        int i = this.contexto.getTreinador().getId();
        int pkmSelecionado = 0;

        JSONObject objTreinador = JSONUtils.getObjectByID(i, "assets/dados.json");
        JSONObject objPkmTreinador = JSONUtils.getObjectByID(pkmSelecionado, "assets/pokemon.json");
        JSONObject objLider = JSONUtils.getObjectByID(i, "assets/lideres.json");

        int hpTreinador = this.contexto.getTreinador().getPokemons().get(pkmSelecionado).getHpAtual();
        int hpLider = 100;
        int contagemEspecial = 5;
        // TODO: Sistema de batalha

        System.out.println("---------- Batalha ----------");
        System.out.println("Seu pokemon: " + objPkmTreinador.get("Nome"));
        System.out.println(
                "Seu HP atual: " + this.contexto.getTreinador().getPokemons().get(pkmSelecionado).getHpAtual());
        System.out.println("Seu ataque: " + objPkmTreinador.get("Ataque"));
        System.out.println("Sua defesa: " + objPkmTreinador.get("Defesa"));
        System.out.println("-----------------------------");
        System.out.println("Pokemon inimigo: " + obj.get("Nome")); // atribuir dados do pokemon inimigo a variavel e
                                                                   // buscar ela aqui
        System.out.println("HP atual do inimigo: " + obj.get("HP"));
        System.out.println("Ataque do inimigo: " + obj.get("Ataque"));
        System.out.println("Defesa do inimigo: " + obj.get("Defesa"));
        System.out.println("-----------------------------\n");
        System.out.println("Escolha seu ataque");
        System.out.println("(1) - Físico");
        System.out.println("(2) - Especial");
        System.out.println("\nX - Sair");

        String escolha = this.contexto.getUserInput();
        /*
         * 
         * static void imprimeHP(int hpUsuario, int hpComputador, int contagemEspeciais)
         * {
         * System.out.println("====================");
         * System.out.println("- HP Usuario: " + hpUsuario);
         * System.out.println("- HP Computador: " + hpComputador);
         * System.out.println("* Contagem Especiais: " + contagemEspeciais);
         * System.out.println("====================");
         * }
         */

        while (hpTreinador > 0 && hpLider > 0) {
            switch (escolha) {
                case "1":
                    System.out.println("Usuario aplicou um soco.");
                    hpLider -= 7;
                    break;
                case "2":
                    System.out.println("Usuario aplicou um ataque especial.");
                    hpLider -= 20;
                    contagemEspecial--; // contagemespecial = contagemEspecial -1
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
                switch (escolha) {
                    case "1":
                        System.out.println("Computador aplicou um soco.");
                        hpTreinador -= 2 + (int) (i / 10);
                        break;
                    case "2":
                        System.out.println("Computador aplicou um chute.");
                        hpTreinador -= 3 + (int) (i / 10);
                        contagemEspecial--; // contagemespecial = contagemEspecial -1
                        break;
                    case "x":
                    case "X":
                        this.contexto.sairPrograma();
                        break;
                    default:
                        System.out.println("Por favor insira um valor válido");
                        break;
                }
            } else {
                System.out.println("Inimigo derrotado");
            }
        }
        if (hpTreinador > 0) {
            hpTreinador += 5;
            if (hpTreinador > 150) {
                hpTreinador = 150;
            }
            if (i % 10 == 0) {
                contagemEspecial++;
                if (contagemEspecial > 5) {
                    contagemEspecial = 5;
                }
            }
        }
        i++;

    }

    public TelaBatalha(TelaContext context) {
        super(context);
    }
}

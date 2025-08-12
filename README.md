# Jogo de Batalha Pokémon em JavaFX

| Menu Principal | Tela de Batalha | Pokédex |
|:---:|:---:|:---:|
| ![Tela de Menu](/jogo_pokemon/images/menu1.png) | ![Gameplay](/jogo_pokemon/images/gameplay.png) | ![Tela de Pokédex](/jogo_pokemon/images/pokedex.png) |

Assuma o papel de um treinador Pokémon nesta aplicação de desktop desenvolvida em Java e JavaFX. 
Crie o seu perfil, escolha o seu Pokémon inicial e embarque numa jornada para desafiar líderes de ginásio e capturar novos Pokémon. 
Com um sistema de batalha por turnos, uma interface gráfica completa e funcionalidades imersivas, este projeto oferece uma experiência clássica de Pokémon com uma roupagem moderna.

---

## ✨ Funcionalidades

* **Interface Gráfica Completa:** Toda a interação do jogo ocorre através de uma GUI moderna e estilizada com JavaFX, abandonando completamente o terminal.
* **Sistema de Batalha por Turnos:** Um sistema de combate completo com cálculo de dano, vantagens de tipo (super eficaz, pouco eficaz), ataques físicos e especiais.
* **Perfis de Treinador Persistentes:** Crie e carregue perfis de treinador, com todo o progresso (equipa de Pokémon) guardado localmente em ficheiros JSON.
* **Animações e Efeitos Visuais:**
    * Barras de vida que descem de forma animada e mudam de cor.
    * Animações de "salto" para ataques e "tremor" para danos, tornando o combate mais dinâmico.
* **Áudio Imersivo:**
    * Música de fundo para os menus e uma música de batalha diferente e mais intensa.
    * Efeitos sonoros únicos para vitória e derrota.
* **Gestão de Equipa e Pokédex:** Telas dedicadas para visualizar e gerir a sua equipa de Pokémon, definir o Pokémon ativo para batalhas e consultar uma Pokédex com todos os Pokémon do jogo.
* **Desafio a Ginásios:** Enfrente líderes de ginásio, incluindo um "Ginásio Misterioso" especial cujo Pokémon é escolhido aleatoriamente, oferecendo um desafio surpresa.
* **Captura de Pokémon:** Ganhe novos Pokémon para a sua equipa como recompensa por vencer batalhas e conquiste todos os pokémons do jogo.

---

## 🔧 Tecnologias Utilizadas

* **Linguagem:** Java 21
* **Interface Gráfica:** [JavaFX](https://openjfx.io/)
* **Gestão de Projeto e Dependências:** [Apache Maven](https://maven.apache.org/)
* **Manipulação de JSON:** [Jackson](https://github.com/FasterXML/jackson)
* **Testes Automatizados:** [JUnit 5](https://junit.org/junit5/)

---

## 🚀 Como Executar o Projeto

Siga os passos abaixo para compilar e executar o projeto na sua máquina local.

### Pré-requisitos

* Java Development Kit (JDK) 21 ou superior.
* Apache Maven.

### Instalação e Execução

1.  **Clone o repositório:**
    ```sh
    git clone https://github.com/MateusFerreiraM/Jogo_Pokemon.git
    cd Jogo_Pokemon
    ```

2.  **Compile o projeto e instale as dependências:**
    O Maven irá ler o ficheiro `pom.xml` e descarregar todas as bibliotecas necessárias automaticamente.
    ```sh
    mvn clean install
    ```

3.  **Execute o Jogo:**
    Com as dependências instaladas, basta usar o plugin do JavaFX via Maven.
    ```sh
    mvn javafx:run
    ```

---

## 📂 Estrutura de Ficheiros

O projeto foi organizado seguindo a arquitetura MVC (Model-View-Controller) para garantir uma separação clara de responsabilidades e facilitar a manutenção.

```
Jogo_Pokemon/
├── assets/
│   ├── dados.json
│   ├── lideres.json
│   └── pokemon.json
│
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── jogo_pokemon/
│   │   │       ├── App.java            # Ponto de entrada e estado global
│   │   │       ├── data/
│   │   │       │   └── GerenciadorDados.java
│   │   │       ├── model/              # Lógica de negócio (Batalha, Pokemon, etc.)
│   │   │       ├── utils/              # Classes de ajuda (Música, Alertas, etc.)
│   │   │       └── view/
│   │   │           ├── GerenciadorDeTelas.java
│   │   │           └── controller/     # Controladores das telas FXML
│   │   │
│   │   └── resources/
│   │       └── jogo_pokemon/
│   │           ├── audio/
│   │           ├── fonts/
│   │           ├── images/
│   │           └── view/               # Ficheiros .fxml e .css
│   │
│   └── test/
│       └── java/
│           └── jogo_pokemon/
│               ├── BatalhaTest.java
│               └── GerenciadorDadosTest.java
│
├── .gitignore
├── pom.xml
└── README.md
```

---

## 📄 Licença

Este projeto está sob a licença MIT. Veja o arquivo `LICENSE` para mais detalhes.

---

## 👥 Agradecimentos

Este projeto é uma significativa refatoração e modernização de um trabalho académico da disciplina de Programação Orientada a Objetos, originalmente desenvolvido em grupo.
A versão inicial, baseada em terminal, serviu como fundação e inspiração para esta nova aplicação com interface gráfica completa, aproveitando conceitos e a lógica de negócio do projeto original.
Agradecimentos aos membros do projeto fonte pela base e pelos conceitos iniciais.
* [Repositório do Projeto Original (Terminal)](https://github.com/B-Cut/trabalho-poo.git)
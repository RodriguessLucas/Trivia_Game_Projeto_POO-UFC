# Projeto POO - Trivia Game

## 📌 Sobre o Projeto
Este projeto é um jogo de perguntas e respostas desenvolvido utilizando **Java** e **JavaFX**, seguindo o padrão **MVC (Model-View-Controller)**. Ele permite que os usuários adicionem perguntas, ajustem configurações e visualizem estatísticas. O sistema foi projetado para ser modular e expansível, permitindo futuras melhorias e adições de funcionalidades.

Este projeto é o trabalho final da disciplina **Programação Orientada a Objetos (POO)** da **Universidade Federal do Ceará (UFC) - Campus de Russas**, ministrada pelo professor **Marcos Vinícius de Andrade Lima**. O objetivo é aplicar o padrão **MVC** manualmente, sem o uso de frameworks que abstraiam essa implementação.

### 👥 Integrantes do Grupo
- Lucas Rodrigues Pereira
- João Pedro Lucena
- Vitor Manuel
- Guilherme Augusto Luna
- Pedro Arthur Maia Gama

---

## 🎯 Estrutura do Projeto
O projeto está organizado conforme o padrão **MVC**, separando responsabilidades em três camadas principais: **Model (Modelo), View (Visão) e Controller (Controle)**.

### 📂 Model (Modelo)
O **modelo** representa a lógica de negócio e os dados do jogo. Ele gerencia as entidades principais e suas interações.

- `Jogador.java` → Representa um jogador do jogo, armazenando seu nome e estatísticas.
- `Questao.java` → Contém as informações de uma questão, incluindo pergunta, opções e resposta correta.
- `Estatistica.java` → Gerencia os dados estatísticos do jogador, como número de acertos e erros.
- `Configuracoes.java` → Armazena preferências do usuário, como nível de dificuldade e tempo de resposta.
- `Model.java` → Classe principal do modelo, gerencia a lógica central do jogo, como pontuação e controle de fluxo.
- `DebugWin.java` → Possível classe de debug para testes e depuração do sistema.

### 📂 View (Visão)
A **camada de visão** é responsável por exibir as informações ao usuário e capturar suas interações. Utiliza JavaFX para renderizar as interfaces gráficas.

- `AdicionarQuestaoView.java` → Tela para adicionar perguntas ao banco de dados temporário.
- `ConfiguracaoView.java` → Tela de configurações do jogo, onde o usuário pode definir preferências.
- `TelaInicialView.java` → Tela inicial que apresenta as opções ao jogador.
- `TelaMenuView.java` → Menu principal do jogo, oferecendo navegação entre diferentes funcionalidades.
- `EstatisticasView.java` → Tela para exibição de estatísticas, como desempenho do jogador.

Cada uma dessas telas possui um arquivo correspondente **Controller**, responsável pela lógica associada à interface.

### 📂 Controller (Controle)
Os **controladores** fazem a ponte entre a interface gráfica (**View**) e os dados (**Model**), garantindo a interação dinâmica do usuário.

- `AdicionarQuestaoViewController.java` → Gerencia a funcionalidade de adicionar novas questões.
- `ConfiguracaoViewController.java` → Controla as configurações do jogo e sua aplicação.
- `TelaInicialViewController.java` → Gerencia a interação na tela inicial.
- `TelaMenuViewController.java` → Controla a navegação no menu principal.
- `EstatisticasViewController.java` → Exibe os dados de estatísticas e desempenho do jogador.

### 📂 Outros Arquivos Importantes
- `MainApp.java` → Classe principal que inicia o aplicativo, carregando a tela inicial.
- `Observer.java` → Implementação do padrão **Observer**, permitindo que as views sejam atualizadas automaticamente quando o modelo sofre alterações.

---

## 🛠️ Como Funciona o Sistema
O sistema segue a arquitetura **MVC**, garantindo separação clara de responsabilidades:

1. O usuário interage com a **View** (exemplo: responde uma pergunta ou altera configurações).
2. A **View** encaminha os dados para o **Controller**, que processa a ação e comunica com o **Model**.
3. O **Model** atualiza os dados conforme necessário (exemplo: salva uma nova estatística ou verifica a resposta do usuário).
4. O **Observer** notifica a **View** sobre mudanças, garantindo que a interface reflita o estado atualizado do jogo.

Esse fluxo permite que a aplicação seja modular, escalável e mais fácil de manter.

---

## 🚀 Como Usar o Aplicativo
### 📌 Pré-requisitos
- **Java 23+** instalado.
- **Maven** configurado para gerenciar dependências.

### 🔧 Executando o Projeto
1. Clone o repositório:
   ```sh
   git clone <URL_DO_REPOSITORIO>
   ```
2. Acesse o diretório do projeto:
   ```sh
   cd projeto_poo
   ```
3. Compile e execute o jogo com Maven:
   ```sh
   mvn clean javafx:run
   ```

### 🎮 Jogando o Debug&Win
// ainda irá ser adicionado

---

## ⚠️ Limitações do Projeto
Atualmente, o projeto possui algumas limitações:
- **Persistência de dados:** As questões e estatísticas são armazenadas apenas em memória, sendo perdidas ao fechar o jogo.
- **Interface simples:** A UI é funcional, mas não conta com animações avançadas.
- **Dependência do JavaFX:** O uso do JavaFX pode limitar a portabilidade para alguns sistemas sem suporte nativo.

---

## 🔮 Futuras Melhorias
- Implementação de um banco de dados para salvar progresso e estatísticas.
- Suporte para múltiplos jogadores e modos de jogo competitivos.

---

## 📄 Licença
Este projeto é de uso acadêmico e pode ser modificado conforme necessário.


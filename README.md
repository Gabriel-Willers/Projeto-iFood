# Projeto-iFood-JavaFX
Integrantes:
- Gabriel Willers Teixeira
- Juan Pablo Santos Correa
# Sistema de Gerenciamento de Restaurante (JavaFX)

Este é um projeto desenvolvido em Java utilizando JavaFX para interface gráfica.
O sistema simula uma plataforma de delivery, permitindo interação entre Clientes e Donos de Restaurantes, seguindo boas práticas de POO (Programação Orientada a Objetos), padrões de projeto simples e organização em camadas.

O objetivo do sistema é permitir que:

Donos de restaurantes cadastrem seus estabelecimentos, adicionem e gerenciem produtos do cardápio (comidas e bebidas).

Clientes possam visualizar restaurantes disponíveis, montar pedidos e finalizar compras.

O sistema controle automaticamente cálculo de totais, tempo estimado, e finalização de pedidos.

Contas e dados cadastrados fiquem acessíveis durante a execução (persistência via objetos estáticos).

# Organização do Código

O sistema foi dividido em pacotes específicos para manter clareza, separação de responsabilidades e boa manutenção do código.
src/

└── com/example
    ├── ]
    
    ├── App.java
    ├── PrimaryController.java
    ├── SecondaryController.java
    ├── ClienteController.java
    ├── DonoController.java
    │
    ├── usuarios/
    │   ├── User.java
    │   ├── Cliente.java
    │   ├── DonoR.java
    │   └── Usuarios.java
    │
    ├── itens/
    │   ├── Produto.java
    │   ├── Comida.java
    │   ├── Bebida.java
    │   ├── Pedido.java
    │   └── Pedidos.java
    │
    ├── restaurante/
    │   ├── Restaurante.java
    │   └── Restaurantes.java
    │
    └── resources (FXML + layouts)
    
# Funcionalidades do Sistema
🔐 Segurança do Sistema

Tela única com Login e Cadastro.

Validação de e-mail (impede cadastros duplicados).

Impede cadastro com senhas vazias.

Apenas usuários existentes podem efetuar login.

Separação automática entre acesso de Cliente e Dono no login.

👨‍🍳 Funcionalidades do Dono de Restaurante
✔ Cadastro de Restaurante
Cada Dono cria um restaurante próprio ao se registrar.
O restaurante é automaticamente vinculado ao usuário.

✔ Gerenciamento de Cardápio (CRUD)
Adicionar Comidas ou Bebidas.
Atualizar produtos do cardápio.
Excluir itens.
Cardápio separado por restaurante.

✔ Polimorfismo Visual (JavaFX)
Se o tipo for Comida, aparecem:
Tipo de cozinha
Opções vegetariano/vegano
Se o tipo for Bebida, aparecem:
Tamanho em ML
Opção alcoólica/não alcoólica

✔ Restrições de Acesso
Dono só acessa seu próprio cardápio.
Não é possível adicionar item sem restaurante cadastrado.

🛒 Funcionalidades do Cliente
✔ Navegação de Restaurantes
Lista dinâmica de restaurantes cadastrados.
Ao selecionar um restaurante, o cardápio aparece automaticamente.

✔ Monte seu Pedido
Adicionar itens ao carrinho.
Remover itens.
Visualização do pedido atual.

✔ Cálculo Automático
Soma total dos preços.
Tempo estimado = maior tempo de preparo entre os itens.

✔ Finalização de Pedido
Salva pedido globalmente (classe Pedidos).
Exibe mensagem de sucesso.
Limpa carrinho automaticamente.

🗃 Persistência em Memória (Objetos Singleton)
O sistema usa repositórios em memória durante a execução:
Usuarios → controla todos os usuários cadastrados
Restaurantes → armazena restaurantes cadastrados
Pedidos → salva pedidos finalizados

# Conceitos de POO Utilizados

Herança

Cliente extends User

DonoR extends User

Comida extends Produto

Bebida extends Produto

Polimorfismo

GerenciadorDeProdutos → implementado por Pedido e Restaurante

Carregamento dinâmico dos campos no DonoController via instanceof.

Encapsulamento

Getters e setters em todas as classes do modelo.

Interfaces

GerenciadorDeProdutos define ações padronizadas.

Coleções

Uso de ArrayList<> para cardápio, usuários e pedidos.

JavaFX

FXML + Controllers

Scene Switching (setRoot)

ListView, ComboBox, Label, TextField

Listeners para seleção dinâmica

Como executar o projeto
✔ Pré-requisitos

JDK 17+

JavaFX configurado (caso use terminal)

VSCode / IntelliJ / Eclipse com plugin JavaFX

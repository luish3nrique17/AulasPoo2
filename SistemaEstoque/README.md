# Sistema de Controle de Estoque

## Descrição

Sistema orientado a objetos para gerenciamento de produtos em estoque.
Permite cadastrar, pesquisar, listar e remover produtos, além de
persistir os dados em arquivo para que não se percam ao encerrar o programa.

## Funcionalidades

- **Cadastrar produto** — adiciona um produto com código, nome, preço e quantidade
- **Pesquisar produto** — busca rápida por código usando HashMap (O(1))
- **Listar todos os produtos** — exibe todos os produtos cadastrados
- **Pesquisar estoque baixo** — lista produtos abaixo de uma quantidade mínima
- **Remover produto** — remove um produto pelo código
- **Salvar dados** — persiste os dados em arquivo binário (`estoque.dat`)
- **Recuperar dados** — carrega os dados salvos anteriormente

## Estrutura das Classes

| Classe / Interface | Descrição |
|---|---|
| `Estoque` | Interface com Javadoc definindo as operações do sistema |
| `Produto` | Entidade principal, implementa `Serializable` |
| `SistemaDeEstoque` | Implementação da interface usando `HashMap<String, Produto>` |
| `GravadorDeDados` | Responsável pela leitura e gravação em arquivo com `ObjectInputStream`/`ObjectOutputStream` |
| `ProdutoInexistenteException` | Exceção lançada ao pesquisar/remover produto inexistente |
| `ProdutoJaExisteException` | Exceção lançada ao cadastrar produto com código duplicado |
| `SistemaDeEstoqueTest` | Testes JUnit cobrindo cadastro, pesquisa, remoção e persistência |

## Tecnologias

- Java 17+
- JUnit 4
- Serialização de objetos (`ObjectOutputStream` / `ObjectInputStream`)

## Como executar os testes

Importe o projeto no IntelliJ IDEA e execute a classe `SistemaDeEstoqueTest` com JUnit.

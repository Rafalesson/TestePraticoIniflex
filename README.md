# Teste Prático Programação - Iniflex

Este projeto é a resposta ao teste prático de programação para a vaga de Desenvolvedor de Software Júnior na Iniflex. O objetivo era desenvolver um programa em Java que manipula informações de pseudos funcionários de uma indústria, seguindo uma série de requisitos específicos.

## Requisitos Implementados

### 1. Classes

- **Pessoa**: Classe base com atributos nome e data de nascimento.
- **Funcionário**: Classe que estende `Pessoa`, adicionando atributos salário e função.

### 2. Funcionalidades

#### Principal

- **Inserção de Funcionários**: Todos os funcionários são inseridos conforme as informações fornecidas:
![Tabela com informações dos funcionários](https://raw.githubusercontent.com/Rafalesson/TestePraticoIniflex/refs/heads/main/src/img/img.png)
- **Remoção de Funcionário**: O funcionário "João" é removido da lista.
- **Impressão de Funcionários**: Todos os funcionários são impressos com suas informações formatadas corretamente.
- **Aumento Salarial**: Cada funcionário recebe um aumento de 10% no salário.
- **Agrupamento por Função**: Os funcionários são agrupados em um `Map` por função.
- **Impressão Agrupada**: Os funcionários são impressos agrupados por função.
- **Aniversariantes**: Funcionários que fazem aniversário nos meses de outubro e dezembro são identificados e impressos.
- **Maior Idade**: O funcionário mais velho é identificado e seus dados são impressos.
- **Ordenação Alfabética**: A lista de funcionários é ordenada alfabeticamente e impressa.
- **Total de Salários**: O total dos salários de todos os funcionários é calculado e impresso.
- **Salários Mínimos**: É calculado quantos salários mínimos cada funcionário ganha.

### 3. Princípios SOLID

O projeto segue os princípios SOLID, especialmente o Single Responsibility Principle (SRP). Cada classe tem uma única responsabilidade:
- `Pessoa`: Armazenar informações básicas de uma pessoa.
- `Funcionário`: Armazenar informações específicas de um funcionário.
- `FuncionarioService`: Manipular operações relacionadas a funcionários.
- `RelatorioService`: Gerenciar a geração de relatórios.
- `FormatadorDados`: Formatar dados para exibição.

## Como Executar

Para executar o projeto, siga os passos abaixo:

1. Clone este repositório.
2. Abra o projeto em sua IDE favorita (eu usei Visual Studio Code).
3. Compile e execute o arquivo `App.java`.

### Detalhes das Classes

#### Pessoa.java

A classe `Pessoa` é uma classe base que contém informações básicas de uma pessoa. Ela tem dois atributos privados: `nome` (String) e `dataNascimento` (LocalDate). O construtor inicializa esses atributos e métodos getters são fornecidos para acessar os valores dos atributos.

#### Funcionario.java

A classe `Funcionario` estende a classe `Pessoa`, adicionando atributos específicos de um funcionário: `salario` (BigDecimal) e `funcao` (String). O construtor chama o construtor da classe pai (`super`) e inicializa os novos atributos. Métodos getters e setters são fornecidos para acessar e modificar os valores dos atributos.

#### FuncionarioService.java

A classe `FuncionarioService` é responsável por operações relacionadas aos funcionários. Ela mantém uma lista de funcionários e fornece métodos para remover funcionários por nome, aplicar aumentos salariais e obter a lista de funcionários.

#### RelatorioService.java

A classe `RelatorioService` é responsável por gerar relatórios sobre os funcionários. Ela fornece métodos para agrupar funcionários por função, filtrar aniversariantes, encontrar o funcionário mais velho e calcular o total dos salários.

#### FormatadorDados.java

A classe `FormatadorDados` é responsável por formatar dados para exibição. Ela fornece métodos para formatar datas no formato "dd/MM/yyyy" e formatar valores numéricos com separador de milhar como ponto e decimal como vírgula.

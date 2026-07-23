# 🏦 Sistema de Conta Bancária em Java

Um sistema bancário simulado desenvolvido em Java, com foco em precisão financeira, segurança de dados e aplicação de boas práticas de Programação Orientada a Objetos (POO). Este projeto nasceu como um exercício prático e evoluiu para uma arquitetura robusta utilizando recursos modernos do Java.

---

## Funcionalidades

*   **Abertura de Conta:** Inicialização segura da conta com registro automático do depósito inicial no histórico.
*   **Depósitos e Saques:** Operações matemáticas exatas, prevenindo problemas comuns de arredondamento de ponto flutuante.
*   **Validações de Regra de Negócio:** Bloqueio de depósitos/saques negativos ou zerados e prevenção de saques maiores que o saldo disponível.
*   **Histórico Imutável:** Registro seguro de todas as transações, protegido contra modificações indevidas por agentes externos.
*   **Extrato Inteligente:** Capacidade de gerar o extrato completo ou realizar filtragens avançadas (como buscar transações de um mês e ano específicos).
*   **Formatação Localizada:** Exibição amigável de datas e valores monetários para o usuário final no console.

---

##  Tecnologias e Conceitos Aplicados

O projeto foi construído utilizando as seguintes ferramentas e conceitos avançados do ecossistema Java:

*   **Java 17+:** Uso de *Records* para criação de classes de transporte de dados limpas e imutáveis.
*   **BigDecimal:** Substituição do tipo primitivo `double` para garantir 100% de precisão nas operações financeiras (End-to-End).
*   **API Date/Time (`java.time`):** Uso de `LocalDateTime` para marcação temporal precisa das transações e `DateTimeFormatter` para a camada de visualização.
*   **Streams API:** Utilizada para processamento de coleções e filtragem funcional do histórico de transações.
*   **Enums (Type Safety):** Implementação de enumerações (`TipoTransacao`) para garantir a integridade e segurança dos tipos de operações registradas.
*   **Encapsulamento Estrito:** Proteção da lista de histórico interna utilizando `Collections.unmodifiableList()`.
*   **Tratamento de Exceções Customizadas:** Criação de classes como `SaldoInsuficienteException`, `ValorInvalidoException` e `ExtratoDoMesInvalido` para mapeamento claro de erros de negócio.

---

## Estrutura de Métodos (Classe `ContaBancaria`)

| Método | Descrição | Tratamento de Erros |
| :--- | :--- | :--- |
| `ContaBancaria(BigDecimal)` | Construtor que inicializa a conta e registra o 1º depósito. | Lança erro se saldo inicial for negativo. |
| `depositar(BigDecimal)` | Adiciona fundos ao saldo e salva no histórico. | Bloqueia valores `<= 0`. |
| `sacar(BigDecimal)` | Subtrai fundos e salva a transação no histórico. | Bloqueia valores `<= 0` e saldos insuficientes. |
| `getSaldo()` | Retorna o saldo atual exato. | - |
| `getHistorico()` | Retorna uma **cópia imutável** (apenas leitura) do histórico. | - |
| `getExtratoDoMes(int, int)` | Retorna uma lista filtrada via Stream com dados de um mês/ano. | - |

---

## Como Executar

1. Clone este repositório:
   ```bash
   git clone [https://github.com/seu-usuario/nome-do-repositorio.git](https://github.com/seu-usuario/nome-do-repositorio.git)
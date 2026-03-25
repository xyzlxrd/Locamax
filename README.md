# Locamax 🚗

Sistema desktop de locação de veículos desenvolvido em Java com JavaFX, utilizando arquitetura MVC e integração com banco de dados MySQL.

---

## Sobre o projeto

O Locamax é uma aplicação desktop que simula o sistema de reservas de uma locadora de veículos. O cliente pode visualizar o catálogo de carros disponíveis, filtrar por categoria e especificações, ver os detalhes de cada veículo e realizar uma reserva.

Este projeto foi desenvolvido como projeto acadêmico e posteriormente refatorado para aplicar boas práticas de organização de código, separação de responsabilidades e padronização de nomenclatura.

---

## Funcionalidades

- **Login** de cliente com validação de credenciais
- **Cadastro** de novo cliente com validação de CPF, CNH, telefone, e-mail e endereço completo
- **Catálogo** de veículos com cards dinâmicos carregados do banco
- **Filtros** por categoria (Hatch, SUV, Sedan, Pickup), combustível, potência e número de assentos
- **Detalhes** do veículo com ficha técnica completa
- **Carrinho de reserva** com cálculo automático por diárias
- **Confirmação de reserva** com feedback ao usuário

---

## Tecnologias utilizadas

| Tecnologia | Versão |
|---|---|
| Java | 8 (JDK 8u202) |
| JavaFX | 24.0.1 |
| MySQL | 8+ / MariaDB 10.4+ |
| MySQL Connector/J | 9.3.0 |
| SceneBuilder | 24.0.0 |

---

## Arquitetura

O projeto segue o padrão **MVC (Model-View-Controller)**:

```
src/main/java/com/locamax/
├── app/           → Ponto de entrada da aplicação (MainApp)
├── controller/    → Controllers JavaFX de cada tela
├── model/         → Entidades de domínio (Carro, Carrinho)
├── repository/    → Acesso ao banco de dados (JDBC)
└── util/          → Utilitários: validação, navegação de telas

src/main/resources/
├── fxml/          → Layouts das telas (.fxml)
├── css/           → Estilização (main.css)
└── img/           → Imagens e ícones

database/
└── applocadora.sql  → Script de criação e população do banco
```

---

## Como executar

### Pré-requisitos

- [JDK 8u202](https://www.oracle.com/java/technologies/javase/javase8-archive-downloads.html)
- [JavaFX SDK 24.0.1](https://gluonhq.com/products/javafx/)
- [MySQL Connector/J 9.3.0](https://dev.mysql.com/downloads/connector/j/)
- MySQL ou MariaDB rodando localmente

### Configuração do banco de dados

1. Crie o banco e importe o script:
   ```sql
   CREATE DATABASE applocadora;
   USE applocadora;
   SOURCE database/applocadora.sql;
   ```

2. Credenciais padrão de teste:
   ```
   Usuário: admin
   Senha:   1234
   ```

### Configuração da conexão

As credenciais do banco estão em `DatabaseConnection.java`. Para alterar:

```java
private static final String URL     = "jdbc:mysql://localhost:3306/applocadora?useSSL=false&serverTimezone=UTC";
private static final String USUARIO = "root";
private static final String SENHA   = "";
```

### Executando no NetBeans / IntelliJ

1. Adicione o **JavaFX SDK** às bibliotecas do projeto
2. Adicione o **MySQL Connector/J** às bibliotecas do projeto
3. Configure a plataforma Java 8
4. Execute a classe `com.locamax.app.MainApp`

---

## Autor

Desenvolvido como projeto acadêmico e refatorado para portfólio.

# api-automation-test-with-rest-assured

## Descrição
Este código foi desenvolvido para o desafio técnico Sicredi.
Estas instruções fornecerão uma cópia do projeto para execução e alteração em sua máquina local para fins de desenvolvimento e teste. 
Consulte as instruções abaixo para saber como baixar e executar o projeto.

---

## Sobre os testes implementados neste repositório

Este repositório contém os seguintes testes:

| Classe      | Nome do teste                           | Descrição                                                                                  |
  |-----------------------------------------|--------------------------------------------------------------------------------------------| --- |
| ConsultRestrictionsTest  | validateCpfWithRestrictionTest          | Verifica se o CPF informado possui alguma restricao.                                       |
| ConsultRestrictionsTest  | validateCpfWithOutRestrictionTest       | Realiza a consulta de um CPF inexistente na lista de restricoes.                           |
|   |                                         |                                                                                            |
| ConsultSimulationTest  | consultSimulationTest                   | Realiza a consulta de uma simulacao.                                                       |
| ConsultSimulationTest  | consultCpfWithOutSimulationTest         | Consulta um CPF inexistente.                                                               |
| ConsultSimulationTest  | consultSimulationByName                 | Recebe uma lista de simulacoes e verifica a existencia de um nome especifico.              |
| ConsultSimulationTest  | verifyIfListOfSimulationIsEmpity        | Verifica se existe alguma simulacao cadastrada.                                            |
|   |                                         |                                                                                            |
| CreateSimalationTest  | createSimulationTest                    | Cria uma nova simulacao utilizando os seguinte dados randomizados: cpf(válido), nome, email. |
| CreateSimalationTest  | validateExistingCpfTest                 | Tenta criar uma nova simulacao utilizando um CPF já existente.                             |
| CreateSimalationTest  | validateMandatoryDataTest               | Verifica os dados obrigatorios na criacao de uma nova simulacao.                           |
| CreateSimalationTest  | validateMaximumValueTest                | Tenta criar uma nova simulacao com valor maior que R$40.000.                               |
| CreateSimalationTest  | validateMinimumNumberInstallments       | Tenta criar uma nova simulacao com numero de parcelas menor que 2.                         |
|   |                                         |                                                                                            |
| DeleteSimulationTest  | deleteSimulation                        | Deleta a simulacao passada por parametro.                                                  |
|   |                                         |                                                                                            |
| SchemaTest  | validateSchemaTest                      | Valida o contrato do recurso simulacoes.                                                   |
|   |                         |                                                                                            |
| UpdateSimulationTest  | updateSimulationTest                        | Altera os dados de uma simulacao existente.                                                |
| UpdateSimulationTest  | verifyMandatoryDataInUpdateSimulationTest   | Verifica os dados obrigatorios durante a alteracao de uma simulacao.                       |
| UpdateSimulationTest  | updateNonexistentSimulationTest             |Tenta alterar uma simulacao inexistente.                                                                                          |
---

## Configuração do repositório

- Clone o repositório
- Navegue até o diretório raiz do projeto
- Certifique-se de ter as seguintes dependências instaladas na máquina
    - Java >= 11
    - Maven >= 3.1+

  Execute o seguinte comando para insatalar as dependências necessárias:
    ```sh
     mvn install -DskipTests
    ```
---

## Executando os testes

Nesse projeto foi utilizado [Maven Profiles]( https://maven.apache.org/guides/introduction/introduction-to-profiles.html#introduction-to-build-profiles) para organizar um conjunto de testes e suas características.

- Como executar um perfil?

  Foi criado um perfil que contém todas as suites de testes, o propósito dessa estratégia é simular a execução dos testes regressivos.
  Para executar o perfil de teste padrão, use o seguinte comando:

   ```sh
    mvn clean test -P regressionTest
    ```

  Para executar uma classe específica, use o seguinte comando:

  ```sh
    mvn clean test -P regressionTest -Dtest=ConsultRestrictionsTest
    ```
  Onde, o argumento `test` pode ser qualquer classe de teste implementada neste repositório.

---

## Gerar relatórios

- Após a execução dos testes, gere o relatório usando o seguinte comando:


  ```sh
  mvn allure:report
  ```

- Inciar o servidor e visualizar o relatório:


  ```sh
  mvn allure:serve
  ```
---

## Logs

Os logs gerados durante a execuçãp dos testes são armazenadps na pasta ```logs ```

---


## Estrutura do projeto

```
.
└── logs
    └── .gitkeep
└── src
   └── test
       └── java
           ├── core
           │   └── BaseTest.java
           │   └── Constants.java
           ├── test
           │   └── ConsultRestrictionsTest.java
           │   └── ConsultSimulationTest.java
           │   └── CreateSimalationTest.java
           │   └── DeleteSimulationTest.java
           │   └── SchemaTest.java
           │   └── UpdateSimulationTest.java                      
           │── util
           │   └── CsvUtil.java
           │   └── DataGenerator.java

           └── resources
                  └── data
                  │   └── restrictions.csv
                  ├── runners
                  │   └── consult.restriction.xml
                  │   └── consult.simulation.xml
                  │   └── create.simulation.xml
                  │   └── delete.simulation..xml
                  │   └── schema.xml
                  │   └── update.simulation.xml
                  └── allure.properties
                  └── logging.properties
                  └── simulation.json
└── .gitignore
└── README.md
└── pom.xml

```
---

## Tarefas realizadas
Foi utilizado para fins de controle de atividades a funcionalidade *Issues* que o GitLab fornece.
Acessando a seção [issues](https://gitlab.com/andersonmann/api-automation-with-rest-assured/-/issues/?sort=created_date&state=all&first_page_size=20) podem ser
encontradas todas as atividades criadas durante o desenvolvimento desse projeto.

---

## Merge Requests

Para fins de compreensão das práticas de desenvolvimento utilizadas, pode ser verificado a seção [Merge requests](https://gitlab.com/andersonmann/api-automation-with-rest-assured/-/merge_requests?scope=all&state=merged)
do projeto.

---

## Stack 

* [RestAssured](https://github.com/rest-assured/rest-assured) - Framework de testes 
* [TestNG](https://testng.org/doc/documentation-main.html/) - Framework de testes
* [MAVEN](https://maven.apache.org/) - Gerenciador de dependências e build
* [JAVA](https://www.oracle.com/technetwork/pt/java/javase/downloads/jdk8-downloads-2133151.html/) - Linguagem de programação
* [SonarLint](https://www.sonarlint.org/eclipse/) - Lint

---

## Versionamento
* [Semantic Versioning 2.0.0](https://semver.org/)

---

## Autor

**Anderson Mann** - *Software Development Specialist II* 
- [GitHub](https://github.com/andersonmann)
- [Linkedin](https://www.linkedin.com/in/andersonmann/)

---

## Licença

This project is licensed under the GNU License.

---

## Roadmap
- imagem Docker do Java e Maven
- broker para teste de contrato com [PACT.IO](https://pact.io/)


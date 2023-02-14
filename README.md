# api-automation-test-with-rest-assured

## Descrição
Este código foi desenvolvido para o desafio técnico Sicredi.
Estas instruções fornecerão uma cópia do projeto para execução e alteração em sua máquina local para fins de desenvolvimento e teste. 
Consulte as instruções abaixo para saber como baixar e executar o projeto.

---

## Configuração da aplicação

Para poder executar os testes implementados nesse projeto, será necessário executar a aplicação na sua máquina.\
Execute os seguintes passos:
  - Faça download e descompacte o arquivo ``prova-tecnica-api-master.zip`` localizado na pasta ``src/test/resources/app``
  - Abra o terminal e navegue até a raiz da pasta compacta 
  - Execute o seguinte comando:
    ```sh
    mvn clean spring-boot:run
    ```
 

---

## Configuração do repositório

- Certifique-se de ter as seguintes dependências instaladas na máquina
  - Java >= 11
  - Maven >= 3.1+
  
- Clone o repositório
- Navegue até a raiz do diretório  do projeto

  Execute o seguinte comando para insatalar as dependências necessárias:
    ```sh
     mvn install -DskipTests
    ```
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

> ## Bugs e inconsistências
>- **Deletar simulação**  
>   - **Parametro utilizado** 
>  
>     Na documentação ```swagger``` consta a seguinte descrição sobre o método DELETE:
>  
>     ```
>     DELETE /api/v1/simulacoes/{id} Remove uma simulação existente através do CPF
>     ```
>  
>     Contudo, na documentação do arquivo ```.pdf``` essa operação é descrita da seguinte maneira:
>  
>     ```
>     DELETE <host>/api/v1/simulacoes/{id} Remove uma simulação previamente cadastrada pelo seu ID
>     ```
>   
>     A inconsitência encontrada é referente ao atributo utilizado para a exclusão de uma simulação.
>     A primeira cita o CPF como sendo o atributo a ser utilizado, enquanto a segunda refere o ID da simulação.\
>     Porém, durante os testes foi constatado que a aplicação efetivamente utiliza o atributo ID para realizar a exclusão.
>  
>
>- **Deletar simulação**
>   - **Status code**
>
>    Na documentação ```swagger``` consta a seguinte descrição sobre o método DELETE:
>
>    ```
>    Response: status code 200, description: Simulação removida com sucesso
>    ```
>    
>    Contudo, na documentação do arquivo ```.pdf``` essa operação é descrita da seguinte maneira:
>
>    ```
>    Response: status code 204
>    ```
>    
>    Além do status code estar divergente, nenhuma mensagem é retornada.
>    Durante os testes foi constatado que a aplicação efetivamente retorna o status code 200.
>
>  
>- **Deletar simulação**
>   - **Simulação inexistente**
>
>    Na documentação ```swagger``` NÃO existe previsão de tratamento de erro para o método DELETE:
>  
>    Contudo, na documentação do arquivo ```.pdf``` essa operação é descrita da seguinte maneira:
>
>    ```
>    Response: status code 204, description: Simulação não encontrada
>    ```
>
>    Além das informações, estarem divergentes, durante os testes foi constatado que a aplicação efetivamente não trata esse tipo de erro.
>    Se for enviado uma requisição para uma simulação inexistente, é retornado o status code 200.
>
> 
> - **Criar Simulação**
>   - **Dados obrigatórios**
>     
>     Na documentação do arquivo ```.pdf``` diz que os seguintes atributos são obrigatórios:
>   
>     ```
>     cpf
>     nome
>     email
>     valor
>     parcela
>     seguro
>     ```
>     
>     Contudo, durantes os testes foi constatado que somente os seguintes atribuitos são obrigatórios:
> 
>     ```
>     parcelas
>     valor
>     email
>     ```
>     
>     
> - **Criar simulação**
>   - **CPF duplicado**
>   
>     Nas documentações ```swagger``` e ```.pdf``` constam que ao tentar criar uma simulação passando um
>     CPF já existente, o comportamento deve ser:
> 
>     ```
>     response: status code 409, description: CPF já existente
>     ```
> 
>     Contudo, durante os testes foi verificado o seguinte comportamento:
>   
>     ```
>     response: status code 400, description: CPF duplicado
>     ```
>     
>     
> - **Criar simulação**
>    - **Valores da simulação**
>   
>     Na documentação do arquivo ```.pdf``` diz que as regras do atributo valor são:
>   
>     ```
>     valor da simulação deve ser igual ou maior que R$ 1.000 e menor ou igual que R$ 40.000
>     ```
>     
>     Porém, durante os testes foi verificado o seguinte comportamento:
> 
>     ```
>     valor da parcela PODE ser menor que R$ 1.000
>     ```
>   
>     
> - **Criar simulação**
>    - **Numero de parcelas**  
> 
>     Na documentação do arquivo ```.pdf``` diz que as regras do atributo parcela são:
> 
>     ```
>     número de parcelas para pagamento que deve ser igual ou maior que 2 e menor ou igual a 48
>     ````
>     
>     Porém, durante os testes foi verificado o seguinte comportamento:
> 
>     ```
>     quantidade de parcelas PODE ser menor que 2
>     ```
>     
> - **Criar simulação**
>   - **Numero de CPF inválido** 
> 
>     Na documentação do arquivo ```.pdf``` não fica claro o comportamento referente ao atributo CPF.\
>     Durante os testes foi possível criar simulações com CPFs inválidos ou passando um valor nulo.


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
                  └── app
                  │   └── prova-tecnica-api-master.zip
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


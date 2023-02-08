package br.rs.ammann.rest.test;

import br.rs.ammann.rest.core.BaseTest;
import br.rs.ammann.rest.util.CsvUtil;
import br.rs.ammann.rest.util.DataGenerator;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import java.util.Iterator;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;
/**
 * @author anderson.mann
 *
 */

public class RestrictionsConsultTest extends BaseTest {
    DataGenerator dataGenerator = new DataGenerator();

    @DataProvider(name = "restriction_users")
    public Iterator<String[]> provider() throws Exception {
        return CsvUtil.readAll("src/main/resources/data/restrictions.csv", false);
    }

    @Test(priority = 0, description = "Verifica se o CPF informado possui alguma restrição", dataProvider = "restriction_users", groups = {"smoke.test"})
    public void validateCpfWithRestrictionTest(String cpf) {
        given()
        .when()
                .get("restricoes/" .concat(cpf))
        .then()
                .statusCode(200)
                .body("mensagem", is("O CPF ".concat(String.valueOf(cpf)).concat(" tem problema")))
        ;
    }

    @Test(priority = 1, description = "Realiza a consulta de restrição de um CPF inexistente na lista de restrições", groups = {"smoke.test"})
    public void validateCpfWithOutRestrictionTest() {
        String cpf = dataGenerator.createCpf();
        given()
        .when()
                .get("restricoes/" .concat(String.valueOf(cpf)))
        .then()
                .statusCode(204)
        ;
    }
}
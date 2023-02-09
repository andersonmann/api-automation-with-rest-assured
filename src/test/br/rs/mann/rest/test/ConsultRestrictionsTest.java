package br.rs.mann.rest.test;

import br.rs.mann.rest.util.CsvUtil;
import br.rs.mann.rest.util.DataGenerator;
import br.rs.mann.rest.core.BaseTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

/**
 * @author anderson.mann
 */

public class ConsultRestrictionsTest extends BaseTest {
    DataGenerator dataGenerator = new DataGenerator();

    @DataProvider(name = "restriction_users")
    public Iterator<String[]> provider() throws Exception {
        return CsvUtil.readAll("src/test/resources/data/restrictions.csv", false);
    }

    @Test(description = "Verifica se o CPF informado possui alguma restrição", dataProvider = "restriction_users")
    public void validateCpfWithRestrictionTest(String cpf) {
        given()
        .when()
                .get("restricoes/".concat(cpf))
        .then()
                .statusCode(200)
                .body("mensagem", is("O CPF ".concat(String.valueOf(cpf)).concat(" tem problema")))
        ;
    }

    @Test(description = "Realiza a consulta de um CPF inexistente na lista de restrições")
    public void validateCpfWithOutRestrictionTest() {
        String cpf = dataGenerator.createCpf();
        given()
        .when()
                .get("restricoes/".concat(String.valueOf(cpf)))
        .then()
                .statusCode(204)
        ;
    }
}
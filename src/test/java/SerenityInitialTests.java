

import static net.serenitybdd.screenplay.GivenWhenThen.seeThat;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.notNullValue;

import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.rest.abilities.CallAnApi;
import org.junit.Test;
import org.junit.runner.RunWith;



@RunWith(SerenityRunner.class)

public class SerenityInitialTests {

  private static final String restAPIUrl = "http://localhost:5000/api";

  @Test
  public void getUsers(){

    Actor Camilo = Actor.named("Camilo the trainer")
        .whoCan(CallAnApi.at(restAPIUrl));

    Camilo.attemptsTo(
        GetUsersTask.fromPage(1)
    );

    //assertThat(SerenityRest.lastResponse().statusCode()).isEqualTo(200);
    Camilo.should(
        seeThat("El código de respuesta", ResponseCode.was(), equalTo(200))
    );

    Datum user = new GetUsers().answeredBy(Camilo)
        .getData().stream().filter(x -> x.getId() ==1).findFirst().orElse(null);

    Camilo.should(
        seeThat("Usuario no es nullo", act -> user, notNullValue())
    );

    Camilo.should(
        seeThat("El email del usuario", act -> user.getEmail(), equalTo("george.bluth@reqres.in")),
        seeThat("El email del usuario", act -> user.getAvatar(), equalTo("https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg"))
    );

    Camilo.should(
        seeThat("El email del usuario", act -> user.getEmail(), equalTo("george.bluth@reqres.in")),
        seeThat("El email del usuario", act -> user.getAvatar(), equalTo("https://s3.amazonaws.com/uifaces/faces/twitter/calebogden/128.jpg"))
    );
  }

  @Test
  public void crearUsersTests(){

    Actor Camilo = Actor.named("Camilo the trainer")
        .whoCan(CallAnApi.at(restAPIUrl));

    String registerUserInfo = "{\n"
        + "    \"email\": \"eve.holt@reqres.in\",\n"
        + "    \"password\": \"pistol\"\n"
        + "}";

    Camilo.attemptsTo(
        RegisterUserTask.withInfo(registerUserInfo)
    );

    Camilo.should(
        seeThat("El código de respuesta", ResponseCode.was(), equalTo(200))
    );


  }

  @Test
  public void crearUsersTests2(){

    Actor Camilo = Actor.named("Camilo the trainer")
        .whoCan(CallAnApi.at(restAPIUrl));

    RegisterUserInfo registerUserInfo = new RegisterUserInfo();

    registerUserInfo.setName("morpheus");
    registerUserInfo.setJob("leader");
    registerUserInfo.setEmail("eve.holt@reqres.in");
    registerUserInfo.setPassword("pistol");

    Camilo.attemptsTo(
        RegisterUserTask.withInfo(registerUserInfo)
    );

    Camilo.should(
        seeThat("El código de respuesta", ResponseCode.was(), equalTo(200))
    );

  }

  @Test
  public void FactTest(){

    Actor Camilo = Actor.named("Camilo the trainer")
        .whoCan(CallAnApi.at(restAPIUrl));

    Camilo.has(NexflixPlans.toViewSeries());

  }
}

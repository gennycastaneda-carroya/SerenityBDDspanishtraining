import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Question;

public class GetUsers implements Question {

  @Override
  public Users answeredBy(Actor actor) {
    return SerenityRest.lastResponse().as(Users.class);
  }
}
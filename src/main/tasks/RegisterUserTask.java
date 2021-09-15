import static net.serenitybdd.screenplay.Tasks.instrumented;

import io.restassured.http.ContentType;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.Performable;
import net.serenitybdd.screenplay.Task;
import net.serenitybdd.screenplay.rest.interactions.Post;


public class RegisterUserTask implements Task {

  private final Object userInfo;

  public RegisterUserTask(Object userInfo){

    this.userInfo = userInfo;
  }

  public static Performable withInfo(Object userInfo){

    return instrumented(RegisterUserTask.class, userInfo);
  }


  @Override
  public <T extends Actor> void performAs(T actor) {

    actor.attemptsTo(
        Post.to("/register").with(
            requestSpecification -> requestSpecification
                .contentType(ContentType.JSON)
                .body(userInfo)
        )
    );

  }
}

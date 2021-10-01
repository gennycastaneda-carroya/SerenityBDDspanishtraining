import java.util.HashMap;
import java.util.List;
import net.serenitybdd.rest.SerenityRest;
import net.serenitybdd.screenplay.Actor;
import net.serenitybdd.screenplay.facts.Fact;
import net.serenitybdd.screenplay.rest.interactions.Get;

public class NexflixPlans implements Fact {

  private String planInfo;

  public static NexflixPlans toViewSeries(){
    return new NexflixPlans();
  }


  @Override
  public void setup(Actor actor) {

    actor.attemptsTo(
        Get.resource("/plans")
    );
    List<HashMap<String, String>> plans = SerenityRest.lastResponse().path("data");

    actor.remember("plans", plans);

    planInfo = plans.toString();
  }

  public String toString(){
    return "Los planes son:" + planInfo;
  }
}

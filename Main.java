import java.util.Scanner;
import java.util.*;
import java.lang.annotation.*;
interface Action {
  void run(Scanner args);
}

@Retention(RetentionPolicy.RUNTIME)
@interface Command {
  String value();
}

@Command("drink")
class Drink implements Action {
  @Override
  public void run(Scanner args) {
    if(!args.hasNext()) {
      System.out.println("No argument for drink");
    }
    System.out.println("Drinking " + args.next());
  }
}

public class Main {
  public static void main(String[] args) {
    List<Action> actions = Arrays.asList(new Drink());
    
    String command = "drink coke";
    
    Scanner cmdScanner = new Scanner(command);
    
    String cmd = cmdScanner.next();
    
    for(Action a: actions) {
      if(a.getClass().getAnnotation(Command.class).value().equals(cmd)) {
        a.run(cmdScanner);
      }
    }
    
    
  }
}

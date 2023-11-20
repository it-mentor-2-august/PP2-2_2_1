package hiber;

import hiber.config.AppConfig;
import hiber.model.Car;
import hiber.model.User;
import hiber.service.UserService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.System.*;

public class MainApp {

   public static void main(String[] args) {
      Random random = new Random();

      AnnotationConfigApplicationContext context =
            new AnnotationConfigApplicationContext(AppConfig.class);

      UserService userService = context.getBean(UserService.class);
      List<Integer> bmwNumbers = new ArrayList<>();

      String[] models = {"bmw", "lada", "toyota", "nissan"};
      for (int i = 1; i <= 10; i++) {
         String model = models[random.nextInt(models.length)];
         int randomNumber = 100 + random.nextInt(101);
         userService.add(
                 new User(
                         "User" + i,
                         "lastname" + i,
                         String.format("user%d@mail.ru", i),
                         new Car(model, randomNumber)
                 )
         );
         if (model.equals("bmw")) {
            bmwNumbers.add(randomNumber);
         }
      }

      userService.listUsers().forEach(out::println);

      int randomValue = bmwNumbers.get(random.nextInt(bmwNumbers.size()));

      List<User> userList = userService.getUserByCarModelandSeries("bmw", randomValue);
      userList.forEach(user -> out.printf(String.valueOf(user)));

      context.close();
   }
}

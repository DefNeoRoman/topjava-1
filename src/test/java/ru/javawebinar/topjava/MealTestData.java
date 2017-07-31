package ru.javawebinar.topjava;

import ru.javawebinar.topjava.matcher.BeanMatcher;
import ru.javawebinar.topjava.model.Meal;

import java.time.Month;

import static java.time.LocalDateTime.of;

public class MealTestData {
//    INSERT INTO meals (date_time, description, calories, user_id) VALUES
//          ('2017-05-30 10:00:00', 'Завтрак', 500, 100000),
//          ('2017-05-30 13:00:00', 'Обед', 1000, 100000),
//          ('2017-05-30 20:00:00', 'Ужин', 500, 100000),
//          ('2017-05-31 10:00:00', 'Завтрак', 500, 100000),
//          ('2017-05-31 13:00:00', 'Обед', 1000, 100000),
//          ('2017-05-31 20:00:00', 'Ужин', 510, 100000),
//          ('2017-06-01 14:00:00', 'Админ ланч', 510, 100001),
//          ('2017-06-01 21:00:00', 'Админ ужин', 1500, 100001);
    public static final BeanMatcher<Meal> MATCHER = new BeanMatcher<>();
//    public static final Meal MEAL1 = new Meal(of(2017, Month.MAY, 30, 10, 0), "Завтрак", 500, 100000);
//    public static final Meal MEAL2 = new Meal(of(2017, Month.MAY, 30, 13, 0), "Обед", 1000, 100000);
//    public static final Meal MEAL3 = new Meal(of(2017, Month.MAY, 30, 20, 0), "Ужин", 500, 100000);
//    public static final Meal MEAL4 = new Meal(of(2017, Month.MAY, 31, 20, 0), "Завтрак", 500, 100000);
//    public static final Meal MEAL5 = new Meal(of(2017, Month.MAY, 31, 20, 0), "Обед", 500, 100000);
//    public static final Meal MEAL6 = new Meal(of(2017, Month.MAY, 31, 20, 0), "Ужин", 500, 100000);
//    public static final Meal MEAL7 = new Meal(of(2017, Month.JUNE, 1, 14, 0), "ЕдаАдмина", 510, 100001);
//    public static final Meal MEAL8 = new Meal(of(2017, Month.JUNE, 1, 21, 0), "УжинАдмина", 1500, 100001);

}

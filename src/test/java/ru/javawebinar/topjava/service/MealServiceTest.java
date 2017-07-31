package ru.javawebinar.topjava.service;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.MealTestData;
import ru.javawebinar.topjava.UserTestData;
import ru.javawebinar.topjava.model.Meal;

import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.MealTestData.MATCHER;
import static ru.javawebinar.topjava.UserTestData.*;

import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.util.DbPopulator;

import java.util.Arrays;
import java.util.Collections;


@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
public class MealServiceTest {
    @Autowired
    private MealService service;

    @Autowired
    private DbPopulator dbPopulator;
    @Before
    public void setUp() throws Exception {
        dbPopulator.execute();
    }



    @Test
    public void get() throws Exception {
        Meal mealForGet = MEAL1;
        MealTestData.MATCHER.assertEquals(mealForGet, service.get(MEAL_ID+2,USER_ID));

    }

    @Test
    public void delete() throws Exception {
    service.delete(MEAL_ID+2,USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL6,MEAL5,MEAL4,MEAL3,MEAL2), service.getAll(USER_ID));
    }


    @Test
    public void getBetweenDates() throws Exception {

    }

    @Test
    public void getBetweenDateTimes() throws Exception {

    }

    @Test
    public void getAll() throws Exception {
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL6,MEAL5,MEAL4,MEAL3,MEAL2,MEAL1), service.getAll(USER_ID));
    }

    @Test
    public void update() throws Exception {
        Meal updated = MEAL1;
        updated.setDescription("Измененная еда юзера");
        updated.setCalories(500);
        service.update(updated,USER_ID);
        MealTestData.MATCHER.assertEquals(updated, service.get(MEAL_ID+2,USER_ID));
    }

    @Test
    public void save() throws Exception {

        service.save(MEAL_FOR_SAVE,USER_ID);
        MATCHER.assertCollectionEquals(Arrays.asList(MEAL6,MEAL5,MEAL4,MEAL3,MEAL2,MEAL1,MEAL_FOR_SAVE), service.getAll(USER_ID));
    }

}
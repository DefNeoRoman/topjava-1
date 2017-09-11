package ru.javawebinar.topjava.web.meal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.ResultActions;
import ru.javawebinar.topjava.AuthorizedUser;
import ru.javawebinar.topjava.matcher.BeanMatcher;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.to.MealWithExceed;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.AbstractControllerTest;
import ru.javawebinar.topjava.web.json.JsonUtil;

import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;

import static java.time.LocalDateTime.of;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER;
import static ru.javawebinar.topjava.UserTestData.USER_ID;
import static org.junit.Assert.*;

public class MealRestControllerTest extends AbstractControllerTest{
    private static final String REST_URL = MealRestController.REST_URL + '/';

    private final BeanMatcher<MealWithExceed> mealWithExceedBeanMatcher = BeanMatcher.of(MealWithExceed.class);
    @Override
    @Before
    public void setUp() {
        super.setUp();
        try {
            mockMvc.perform(post("/users").param("userId", String.valueOf(USER_ID)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void testGet() throws Exception {
        mockMvc.perform(get(REST_URL + String.valueOf(MEAL1_ID + 4)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(MATCHER.contentMatcher(MEAL5));
    }

    @Test
    public void testDelete() throws Exception {
        mockMvc.perform(delete(REST_URL + String.valueOf(MEAL1_ID + 3)))
                .andDo(print())
                .andExpect(status().isOk());
        MATCHER.assertListEquals(
                Arrays.asList(MEAL6, MEAL5, MEAL3, MEAL2, MEAL1),
                mealService.getAll(USER_ID)
        );
    }

    @Test
    public void testGetAll() throws Exception {
        AuthorizedUser.setId(USER_ID);
        mockMvc.perform(get(MealRestController.REST_URL))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(mealWithExceedBeanMatcher.contentListMatcher(MealsUtil.getWithExceeded(MEALS, AuthorizedUser.getCaloriesPerDay())));
    }

    @Test
    public void testCreateMeal() throws Exception {
        Meal expected = new Meal(of(2017, Month.MAY, 30, 13, 0), "Новая еда", 555);

        ResultActions action = mockMvc.perform(post(REST_URL)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(expected))).andExpect(status().isCreated());

        Meal returned = MATCHER.fromJsonAction(action);
        expected.setId(returned.getId());

        MATCHER.assertEquals(expected, returned);
        MATCHER.assertListEquals(
                Arrays.asList(expected, MEAL6, MEAL5, MEAL4, MEAL3, MEAL2, MEAL1),
                mealService.getAll(USER_ID)
        );
    }

    @Test
    public void testUpdate() throws Exception {
        Meal updated = getUpdated();

        mockMvc.perform(put(REST_URL + MEAL1_ID)
                .contentType(MediaType.APPLICATION_JSON)
                .content(JsonUtil.writeValue(updated)))
                .andExpect(status().isOk());

        MATCHER.assertEquals(updated, mealService.get(MEAL1_ID, USER_ID));
    }

    @Test
    public void testGetBetween() throws Exception {
        mockMvc.perform(post(REST_URL + "/filter")
                .param("startDate", "2015-05-30")
                .param("endTime", "14:00"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(mealWithExceedBeanMatcher.contentListMatcher(MealsUtil.getFilteredWithExceeded(
                        MEALS,
                        LocalTime.MIN,
                        LocalTime.of(14, 0),
                        USER.getCaloriesPerDay())
                        )
                );
    }

    @Test
    public void testGetSimpleBetween() throws Exception {
        mockMvc.perform(get(REST_URL + "/simple-filter")
                .param("start", "2015-05-30T09:15:30")
                .param("end", "2015-05-31T14:15:30"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(mealWithExceedBeanMatcher.contentListMatcher(MealsUtil.getFilteredWithExceeded(
                        MEALS,
                        LocalTime.of(9, 15, 30),
                        LocalTime.of(14, 15, 30),
                        USER.getCaloriesPerDay())
                        )
                );
    }

}
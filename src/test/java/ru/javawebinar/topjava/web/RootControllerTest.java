package ru.javawebinar.topjava.web;

import org.junit.Test;

import java.time.Month;

import static java.time.LocalDateTime.of;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.javawebinar.topjava.MealTestData.ADMIN_MEAL_ID;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class RootControllerTest extends AbstractControllerTest {

    @Test
    public void testUsers() throws Exception {
        mockMvc.perform(get("/users"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(view().name("users"))
                .andExpect(forwardedUrl("/WEB-INF/jsp/users.jsp"))
                .andExpect(model().attribute("users", hasSize(2)))
                .andExpect(model().attribute("users", hasItem(
                        allOf(
                                hasProperty("id", is(START_SEQ)),
                                hasProperty("name", is(USER.getName()))
                        )
                )));
    }
    @Test
    public void testMeals() throws Exception {
        mockMvc.perform(post("/users").param("userId", String.valueOf(ADMIN_ID)))
                //отправили пост - запрос с параметром
                .andDo(print())
                //распечатали что получилось
                .andExpect(status().is3xxRedirection())
                .andExpect(view().name("redirect:meals"))
                .andExpect(redirectedUrl("meals"));

        mockMvc.perform(get("/meals"))
                .andDo(print())
                .andExpect(status().isOk())
                // вроде код возврата 200 из документации видно
                .andExpect(forwardedUrl("/WEB-INF/jsp/meals.jsp"))
                .andExpect(model().attribute("meals", hasSize(2)))
                .andExpect(model().attribute("meals", hasItem(
                        allOf(
                                hasProperty("id", is(ADMIN_MEAL_ID + 1)),
                                hasProperty("dateTime", is(of(2015, Month.JUNE, 1, 21, 0))),
                                hasProperty("description", is("Админ ужин")),
                                hasProperty("calories", is(1500)),
                                hasProperty("exceed", is(true))
                        )
                )));
    }
}
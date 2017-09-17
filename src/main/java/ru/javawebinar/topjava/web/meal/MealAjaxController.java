package ru.javawebinar.topjava.web.meal;


import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import ru.javawebinar.topjava.model.Meal;

import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.to.MealWithExceed;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;


@RestController
@RequestMapping("/ajax/profile/meals")
public class MealAjaxController extends AbstractMealController {

    @Override
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public List<MealWithExceed> getAll() {
        return super.getAll();
    }
    @Override
    @GetMapping(value = "/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public Meal get(@PathVariable("id")int id) {

        return super.get(id);
    }

 @PostMapping
public void createOrUpdate(@RequestParam("id") Integer id,
                           @RequestParam("dateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime,
                           @RequestParam("description") String description,
                           @RequestParam("calories") int calories) {
    Meal meal = new Meal(id, dateTime, description, calories);
    if (meal.isNew()) {
        super.create(meal);
    }  else{
        super.update(meal,id);
    }
}
    @PostMapping(value = "/{mealId}")
    public void update(@PathVariable("mealId") Integer id,
                               @RequestParam("dateTime") String dateTime,
                               @RequestParam("description") String description,
                               @RequestParam("calories") String calories) {

        Meal meal = new Meal(LocalDateTime.parse(dateTime),description,
                Integer.valueOf(calories));

        if (meal.isNew()) {
            super.create(meal);
        }else{
            super.update(meal,id);
        }

    }
    @Override
    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") int id) {
        super.delete(id);
    }
    @PostMapping("/filter")
    public List<MealWithExceed> Between(
            @RequestParam(value = "startDate", required = false) LocalDate startDate, @RequestParam(value = "startTime", required = false) LocalTime startTime,
            @RequestParam(value = "endDate", required = false) LocalDate endDate, @RequestParam(value = "endTime", required = false) LocalTime endTime) {
        return super.getBetween(startDate, startTime, endDate, endTime);
    }
    @GetMapping("/filter")
    public List<MealWithExceed> getBetween(
            @RequestParam(value = "startDate", required = false) LocalDate startDate, @RequestParam(value = "startTime", required = false) LocalTime startTime,
            @RequestParam(value = "endDate", required = false) LocalDate endDate, @RequestParam(value = "endTime", required = false) LocalTime endTime) {
        return super.getBetween(startDate, startTime, endDate, endTime);
    }
}

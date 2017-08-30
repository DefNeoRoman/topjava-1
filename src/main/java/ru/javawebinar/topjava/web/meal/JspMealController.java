package ru.javawebinar.topjava.web.meal;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.javawebinar.topjava.service.MealService;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;

@Controller
@RequestMapping(value= "/meals")
public class JspMealController extends MealRestController{
    public JspMealController(MealService service) {
        super(service);
    }
    @RequestMapping(method = RequestMethod.GET)
    public String mealList(Model model){
        model.addAttribute("meals",super.getAll());
        return "meals";
    }
    private int getId(HttpServletRequest request){
        String parameterId = Objects.requireNonNull(request.getParameter("id"));
        return Integer.valueOf(parameterId);

    }
}

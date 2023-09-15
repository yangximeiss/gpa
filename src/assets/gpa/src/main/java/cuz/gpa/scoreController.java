package cuz.gpa;

import com.google.gson.Gson;
import cuz.gpa.mapper.scoreMapper;
import cuz.gpa.model.score;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@RestController
public class scoreController {
    @Autowired
    private scoreMapper Sm;
    private final Gson gson = new Gson();

    @RequestMapping("/score")
    public String getStudent() {
        List<score> studentList = Sm.selectList(null);
        return gson.toJson(studentList);
    }
}

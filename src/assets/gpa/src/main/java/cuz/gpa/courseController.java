package cuz.gpa;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import cuz.gpa.model.course;
import cuz.gpa.mapper.courseMapper;

import java.util.List;

@RestController
public class courseController {

    @Autowired
    public courseMapper cm;
    public final Gson gson = new Gson();

    @RequestMapping("/course")
    public String getStudent() {
        List<course> studentList = cm.selectList(null);
        return gson.toJson(studentList);
    }
}
package cuz.gpa;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.google.gson.Gson;
import cuz.gpa.Page.QuarryPageParam;
import cuz.gpa.Service.studentService;
import cuz.gpa.mapper.studentMapper;
import cuz.gpa.model.student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@CrossOrigin
@RestController //将返回的数据结构转换成Json格式`（类，List和Map对象）
public class studentController {

    @Autowired
    private studentService studentservice;

    @Autowired
    private studentMapper sm;
    private final Gson gson = new Gson();
    @RequestMapping("/student")
    public String getStudent() {
        List<student> studentList = sm.selectList(null);
        return gson.toJson(studentList);
    }

    //数据的删除
    @DeleteMapping("/delstudent")
    public void DeleteStudent(@RequestBody student s){
        sm.deleteById(s);
    }
    //数据的增加
    @PostMapping("/addstudent")
    public void AddStudent(@RequestBody student s){
        sm.insert(s);
    }
    //改变数据
    @PutMapping("/updatestudent")
    public void UpdateStudent(@RequestBody student s){
        sm.updateById(s);
    }

    //查找数据
    @GetMapping("/student/{id}")
    public String getStudentById(@PathVariable("id") String id){
        student s = sm.selectById(id);
        return gson.toJson(s);
    }

    //名字查询
    @PostMapping("search")
    public String search(@RequestBody HashMap<String, String> data){
        String name = data.get("name");
        QueryWrapper<student> studentQueryWrapper = new QueryWrapper<>();
        studentQueryWrapper.like("name",name);
        List<student> students = sm.selectList(studentQueryWrapper);
        return gson.toJson(students);
    }

    //分页管理
    @RequestMapping("/studentpage")
    public Result listPage(@RequestBody QuarryPageParam query){

        HashMap param = query.getParam();
        String name = (String)param.get("name");
        System.out.println("name="+(String)param.get("name"));

        Page<student> page = new Page();
        page.setCurrent(query.getPageNum());
        page.setSize(query.getPageSize());

        LambdaQueryWrapper<student> lambdaQueryWrapper = new LambdaQueryWrapper();
        lambdaQueryWrapper.like(student::getName,name);


        IPage result = studentservice.page(page,lambdaQueryWrapper);
        System.out.println("total="+result.getTotal());

        return Result.success(result.getRecords(), result.getTotal());
    }


}



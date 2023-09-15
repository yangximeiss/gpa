package cuz.gpa.Service;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import cuz.gpa.mapper.studentMapper;
import cuz.gpa.model.student;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentServiceImpl extends ServiceImpl<studentMapper, student> implements studentService {
    @Resource
    private studentMapper sM;
    @Override
    public List<student> listAll(){
        return sM.listAll();
    }

    @Override
    public IPage page(Page<student> page) {
        return null;
    }

}

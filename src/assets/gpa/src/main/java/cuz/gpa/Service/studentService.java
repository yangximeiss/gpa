package cuz.gpa.Service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;
import cuz.gpa.model.student;

import java.util.List;

public interface studentService extends IService<student> {
    List<student> listAll();

    IPage page(Page<student> page);
}

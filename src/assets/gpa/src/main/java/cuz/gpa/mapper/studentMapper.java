package cuz.gpa.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import cuz.gpa.model.student;

import java.util.List;

//数据库对应接口（增删改查）

public interface studentMapper extends BaseMapper<student> {
    List<student> listAll();
}

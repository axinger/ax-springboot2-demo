package generator.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import generator.domain.TStudent;
import generator.service.TStudentService;
import generator.mapper.TStudentMapper;
import org.springframework.stereotype.Service;

/**
* @author xing
* @description 针对表【t_student(学生信息)】的数据库操作Service实现
* @createDate 2022-01-23 21:32:41
*/
@Service
public class TStudentServiceImpl extends ServiceImpl<TStudentMapper, TStudent>
    implements TStudentService{

}





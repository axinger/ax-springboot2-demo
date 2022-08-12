package com.xing.service;

import com.xing.entity.StudentInfo;
import org.bson.types.ObjectId;

import java.util.List;

/**
 * @Description：学生Service接口
 * @Author :zks
 * @Date :9:46 2020/9/15
 */
public interface IStudentInfoService {

    /**
     * 添加学生信息
     *
     * @param studentInfo
     * @return
     */
    public void save(StudentInfo studentInfo);

    /**
     * 根据主键id查找学生信息
     *
     * @param id
     * @return
     */
    public StudentInfo getStudentInfoById(ObjectId id);

    /**
     * 根据所选课程id查找学生信息列表并分页
     *
     * @param courseId
     * @param page
     * @param limit
     * @param sort
     * @param order
     * @return
     */
    public List<StudentInfo> getStudentInfoListByCourseId(Integer courseId, Integer page, Integer limit, String sort, String order);

    /**
     * 更新学生信息
     *
     * @param studentInfo
     * @return
     */
    public void update(StudentInfo studentInfo);

    /**
     * 根据学生id以及课程id删除选修课程
     *
     * @param id
     * @param courseId
     */
    public void deleteCourseByIdAndCourseId(ObjectId id, Integer courseId);

    /**
     * 删除学生信息
     *
     * @param id
     * @return
     */
    public void delete(ObjectId id);
}

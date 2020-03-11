package com.yc.service.impl;

import java.util.List;
import com.yc.beans.Student;
import com.yc.service.StudentService;
import com.yc.dao.StudentDao;
import com.yc.dao.impl.StudentDaoImpl;
public class StudentServiceImpl implements StudentService{
private StudentDao studentDao=new StudentDaoImpl();
public List<Student> findAll() {
return studentDao.findAll();
}
public Student findById(Integer id) {
return studentDao.findById(id);
}
public int updateById(Student student) {
return studentDao.updateById(student);
}
public int insert(Student student) {
return studentDao.insert(student);
}
public int deleteById(Integer id) {
return studentDao.deleteById(id);
}
}

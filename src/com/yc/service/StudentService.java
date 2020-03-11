package com.yc.service;

import java.util.List;
import com.yc.beans.Student;
public interface StudentService {
List<Student> findAll();
Student findById(Integer id);
int deleteById(Integer id);
int updateById(Student student);
int insert(Student student);
}

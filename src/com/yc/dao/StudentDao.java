package com.yc.dao;

import java.util.List;

import com.yc.beans.Student;

public interface StudentDao {
	List<Student> findAll();

	Student findById(Integer id);

	int insert(Student student);

	int updateById(Student student);

	int deleteById(Integer id);
}

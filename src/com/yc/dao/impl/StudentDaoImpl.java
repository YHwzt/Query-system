package com.yc.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.yc.beans.Student;
import com.yc.dao.StudentDao;
import com.yc.dao.JDBC;

public class StudentDaoImpl implements StudentDao{
	public Connection connection = JDBC.getConnection();
	private ResultSet rs = null;
	private Statement statement = null;
	private  PreparedStatement preparedStatement=null;
	public  List<Student> findAll() {
		// TODO Auto-generated method stub
		ArrayList<Student> list = new ArrayList<Student>();
		try {
			statement = connection.createStatement();
			rs = statement.executeQuery("select * from student");
			while (rs.next()) {
				Student student = new Student();
student.setId(rs.getInt("id"
));student.setSno(rs.getInt("sno"
));student.setUsername(rs.getString("username"
));student.setSchool(rs.getString("school"
));student.setMyclass(rs.getString("myclass"
));student.setMydate(rs.getTimestamp("mydate"
));student.setIsinhbstudent(rs.getString("isinhbstudent"
));student.setIsinwh(rs.getString("isinwh"
));student.setIsinhb(rs.getString("isinhb"
));student.setIslike(rs.getString("islike"
));student.setIsconfirm(rs.getString("isconfirm"
));				list.add(student);
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		}finally{
			JDBC.close(rs,statement);
		}
		return list;
	}
	public Student findById(Integer id) {
		// TODO Auto-generated method stub
		try {
			String sql="select * from student where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			rs = preparedStatement.executeQuery();
			while (rs.next()) {
Student student = new Student();
student.setId(rs.getInt("id"
));student.setSno(rs.getInt("sno"
));student.setUsername(rs.getString("username"
));student.setSchool(rs.getString("school"
));student.setMyclass(rs.getString("myclass"
));student.setMydate(rs.getTimestamp("mydate"
));student.setIsinhbstudent(rs.getString("isinhbstudent"
));student.setIsinwh(rs.getString("isinwh"
));student.setIsinhb(rs.getString("isinhb"
));student.setIslike(rs.getString("islike"
));student.setIsconfirm(rs.getString("isconfirm"
));				return student;
			}
		} catch (SQLException e) {
			throw new RuntimeException();
		}finally{
			JDBC.close(preparedStatement, rs);
		}
		return null;
	}
public int insert(Student student) {
		try {
			String sql = null;
			if (student.getId()==null) {
				sql = "insert into student(`sno`,`username`,`school`,`myclass`,`mydate`,`isinhbstudent`,`isinwh`,`isinhb`,`islike`,`isconfirm`) values(?,?,?,?,?,?,?,?,?,?)";
				preparedStatement = connection.prepareStatement(sql);
preparedStatement.setInt(1, student.getSno());
preparedStatement.setString(2, student.getUsername());
preparedStatement.setString(3, student.getSchool());
preparedStatement.setString(4, student.getMyclass());
preparedStatement.setTimestamp(5, student.getMydate());
preparedStatement.setString(6, student.getIsinhbstudent());
preparedStatement.setString(7, student.getIsinwh());
preparedStatement.setString(8, student.getIsinhb());
preparedStatement.setString(9, student.getIslike());
preparedStatement.setString(10, student.getIsconfirm());
			}else {
				sql = "insert into student(`id`,`sno`,`username`,`school`,`myclass`,`mydate`,`isinhbstudent`,`isinwh`,`isinhb`,`islike`,`isconfirm`) values(?,?,?,?,?,?,?,?,?,?,?)";
				preparedStatement = connection.prepareStatement(sql);
preparedStatement.setInt(1, student.getId());
preparedStatement.setInt(2, student.getSno());
preparedStatement.setString(3, student.getUsername());
preparedStatement.setString(4, student.getSchool());
preparedStatement.setString(5, student.getMyclass());
preparedStatement.setTimestamp(6, student.getMydate());
preparedStatement.setString(7, student.getIsinhbstudent());
preparedStatement.setString(8, student.getIsinwh());
preparedStatement.setString(9, student.getIsinhb());
preparedStatement.setString(10, student.getIslike());
preparedStatement.setString(11, student.getIsconfirm());
			}
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException();
		} finally {
			JDBC.close(preparedStatement);
		}
	}	public int updateById(Student student) {
		// TODO Auto-generated method stub
		try {
			String sql="update `student` set `sno`=?,`username`=?,`school`=?,`myclass`=?,`mydate`=?,`isinhbstudent`=?,`isinwh`=?,`isinhb`=?,`islike`=?,`isconfirm`=? where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, student.getSno());
			preparedStatement.setString(2, student.getUsername());
			preparedStatement.setString(3, student.getSchool());
			preparedStatement.setString(4, student.getMyclass());
			preparedStatement.setTimestamp(5, student.getMydate());
			preparedStatement.setString(6, student.getIsinhbstudent());
			preparedStatement.setString(7, student.getIsinwh());
			preparedStatement.setString(8, student.getIsinhb());
			preparedStatement.setString(9, student.getIslike());
			preparedStatement.setString(10, student.getIsconfirm());
			preparedStatement.setInt(11, student.getId());
			return preparedStatement.executeUpdate();
		} catch (SQLException e) {
			throw new RuntimeException();
		}finally{
			JDBC.close(preparedStatement);
		}
	}
	public  int deleteById(Integer id) {
		try {
			String sql="delete from student where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,id);
			int i = preparedStatement.executeUpdate();
			if (i==0) {
				throw new RuntimeException();
			}
			return i;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		}finally{
			JDBC.close(preparedStatement);
		}
	}
}

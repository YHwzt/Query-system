package com.yc.farme;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;
import com.yc.beans.Student;
import com.yc.service.StudentService;
import com.yc.service.impl.StudentServiceImpl;

@SuppressWarnings("all")
public class StudentFrame extends JFrame {

//	创建Student对应的service对象
	private StudentService studentService = new StudentServiceImpl();
//	定义窗体宽高属性
	private Integer WIDTH = 600, HEIGHT = 400;
//	声明表格对象的引用
	private JTable jTable;
//	声明添加按钮的引用
	private JButton insert;
//	声明刷新按钮的引用
	private JButton flush;
//	声明删除按钮的引用
	private JButton delete;
//	声明更新按钮的引用
	private JButton update;
//	声明条件查询按钮的引用
	private JButton whereQuery;
//	创建表头数组对象
	private String[] tittle = {"id","学号","姓名","学院","班级","填报日期","是否湖北籍","是否在武汉","是否在湖北","是否疑似病例","是否确诊病例"};
//	声明List集合
	private List<Student> list;

	/**
	 * 无参构造器
	 */
	public StudentFrame() {
//		设置窗体标题
		setTitle("冠状病毒统计列表");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		设置宽高
		setBounds(((int) screenSize.getWidth() - WIDTH) / 2, ((int) screenSize.getHeight() - HEIGHT) / 2, WIDTH,
				HEIGHT);
		this.WIDTH = this.WIDTH / 2;
		this.HEIGHT = (int) (this.HEIGHT * 0.8);
//		设置退出并结束程序
		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		创建面板对象
		JPanel southjPanel = new JPanel();
//		创建添加按钮对象
		insert = new JButton("添加");
//		把添加按钮对象添加到面板中
		southjPanel.add(insert);
//		创建刷新按钮对象
		flush = new JButton("刷新");
//		把刷新按钮对象添加到面板中
		southjPanel.add(flush);
//		创建删除按钮对象
		delete = new JButton("删除");
//		把删除按钮对象添加到面板中
		southjPanel.add(delete);
//		创建更新按钮对象
		update = new JButton("更新");
//		把更新按钮对象添加到面板中
		southjPanel.add(update);
//		创建条件查询按钮对象
		whereQuery = new JButton("条件查询");
//		把条件查询按钮对象添加到面板中
		southjPanel.add(whereQuery);
//		把面板对象添加到窗体底部中
		add(southjPanel, BorderLayout.SOUTH);
//		创建表格对象
		jTable = new JTable();
		jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		创建滚动面板对象
		JScrollPane jScrollPane = new JScrollPane();
//		把表格对象对象添加到滚动面板中
		jScrollPane.setViewportView(jTable);
//		把滚动面板对象添加到窗体中
		add(jScrollPane);
//		刷新表格数据
		flush();
//		显示窗体
		setVisible(true);

		/********************************************添加***********************************************/
		insert.addActionListener(new ActionListener() {

			private JDialog insertjDialog;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (insertjDialog==null) {
					insertjDialog = new JDialog();
					insertjDialog.setTitle("添加");
					insertjDialog.setBounds(((int) screenSize.getWidth() - WIDTH) / 2,
							((int) screenSize.getHeight() - HEIGHT) / 2, WIDTH, HEIGHT);
					insertjDialog.setLayout(new GridLayout(12, 2));
					insertjDialog.add(new JLabel(tittle[0] + ":", JLabel.CENTER));
					final TextField idTf = new TextField();
					insertjDialog.add(idTf);
					insertjDialog.add(new JLabel(tittle[1] + ":", JLabel.CENTER));
					final TextField snoTf = new TextField();
					insertjDialog.add(snoTf);
					insertjDialog.add(new JLabel(tittle[2] + ":", JLabel.CENTER));
					final TextField usernameTf = new TextField();
					insertjDialog.add(usernameTf);
					insertjDialog.add(new JLabel(tittle[3] + ":", JLabel.CENTER));
					final TextField schoolTf = new TextField();
					insertjDialog.add(schoolTf);
					insertjDialog.add(new JLabel(tittle[4] + ":", JLabel.CENTER));
					final TextField myclassTf = new TextField();
					insertjDialog.add(myclassTf);
					insertjDialog.add(new JLabel(tittle[5] + ":", JLabel.CENTER));
					final TextField mydateTf = new TextField();
					insertjDialog.add(mydateTf);
					insertjDialog.add(new JLabel(tittle[6] + ":", JLabel.CENTER));
					final TextField isinhbstudentTf = new TextField();
					insertjDialog.add(isinhbstudentTf);
					insertjDialog.add(new JLabel(tittle[7] + ":", JLabel.CENTER));
					final TextField isinwhTf = new TextField();
					insertjDialog.add(isinwhTf);
					insertjDialog.add(new JLabel(tittle[8] + ":", JLabel.CENTER));
					final TextField isinhbTf = new TextField();
					insertjDialog.add(isinhbTf);
					insertjDialog.add(new JLabel(tittle[9] + ":", JLabel.CENTER));
					final TextField islikeTf = new TextField();
					insertjDialog.add(islikeTf);
					insertjDialog.add(new JLabel(tittle[10] + ":", JLabel.CENTER));
					final TextField isconfirmTf = new TextField();
					insertjDialog.add(isconfirmTf);
					JButton ok = new JButton("确定");
					ok.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							Student student = new Student();
							try {
								student.setId(idTf.getText().trim().equals("")?null:Integer.valueOf(idTf.getText()));
								student.setSno(snoTf.getText().trim().equals("")?null:Integer.valueOf(snoTf.getText()));
								student.setUsername(usernameTf.getText().trim().equals("")?null:String.valueOf(usernameTf.getText()));
								student.setSchool(schoolTf.getText().trim().equals("")?null:String.valueOf(schoolTf.getText()));
								student.setMyclass(myclassTf.getText().trim().equals("")?null:String.valueOf(myclassTf.getText()));
								student.setMydate(mydateTf.getText().trim().equals("")?null:Timestamp.valueOf(mydateTf.getText()+" 0:0:0"));
								student.setIsinhbstudent(isinhbstudentTf.getText().trim().equals("")?null:String.valueOf(isinhbstudentTf.getText()));
								student.setIsinwh(isinwhTf.getText().trim().equals("")?null:String.valueOf(isinwhTf.getText()));
								student.setIsinhb(isinhbTf.getText().trim().equals("")?null:String.valueOf(isinhbTf.getText()));
								student.setIslike(islikeTf.getText().trim().equals("")?null:String.valueOf(islikeTf.getText()));
								student.setIsconfirm(isconfirmTf.getText().trim().equals("")?null:String.valueOf(isconfirmTf.getText()));
							} catch (Exception e1) {
								e1.printStackTrace();
								JOptionPane.showMessageDialog(StudentFrame.this, "非法数据");
								return;
							}
							
							try {
								studentService.insert(student);
							} catch (Exception e1) {
								e1.printStackTrace();
								JOptionPane.showMessageDialog(StudentFrame.this, "非法数据");
								return;
							}
							insertjDialog.setVisible(false);
							flush();
							idTf.setText("");
							snoTf.setText("");
							usernameTf.setText("");
							schoolTf.setText("");
							myclassTf.setText("");
							mydateTf.setText("");
							isinhbstudentTf.setText("");
							isinwhTf.setText("");
							isinhbTf.setText("");
							islikeTf.setText("");
							isconfirmTf.setText("");
						}
					});
					insertjDialog.add(ok);
					JButton cancel = new JButton("取消");
					cancel.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							insertjDialog.setVisible(false);
						}
					});
					insertjDialog.add(cancel);
				}
				insertjDialog.setVisible(true);
			}
		});
		/********************************************刷新***********************************************/
		flush.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				flush();
			}
		});
		/********************************************删除***********************************************/
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = jTable.getSelectedRow();
				if (row==-1) {
					JOptionPane.showMessageDialog(StudentFrame.this, "请选择一行！！！");
					return;
				}
				Integer id = list.get(row).getId();
				studentService.deleteById(id);
				flush();
			}
		});		
		/********************************************更新***********************************************/
		update.addActionListener(new ActionListener() {

			private JDialog updatejDialog;
			private TextField idTf;
			private TextField snoTf;
			private TextField usernameTf;
			private TextField schoolTf;
			private TextField myclassTf;
			private TextField mydateTf;
			private TextField isinhbstudentTf;
			private TextField isinwhTf;
			private TextField isinhbTf;
			private TextField islikeTf;
			private TextField isconfirmTf;

			@Override
			public void actionPerformed(ActionEvent e) {
				
				int row = jTable.getSelectedRow();
				if (row==-1) {
					JOptionPane.showMessageDialog(StudentFrame.this, "请选择一行！！！");
					return;
				}
				
				if (updatejDialog==null) {
					updatejDialog = new JDialog();
					updatejDialog.setTitle("更新");
					updatejDialog.setBounds(((int) screenSize.getWidth() - WIDTH) / 2,
							((int) screenSize.getHeight() - HEIGHT) / 2, WIDTH, HEIGHT);
					updatejDialog.setLayout(new GridLayout(12, 2));
					updatejDialog.add(new JLabel(tittle[0] + ":", JLabel.CENTER));
					idTf = new TextField();
					updatejDialog.add(idTf);
					updatejDialog.add(new JLabel(tittle[1] + ":", JLabel.CENTER));
					snoTf = new TextField();
					updatejDialog.add(snoTf);
					updatejDialog.add(new JLabel(tittle[2] + ":", JLabel.CENTER));
					usernameTf = new TextField();
					updatejDialog.add(usernameTf);
					updatejDialog.add(new JLabel(tittle[3] + ":", JLabel.CENTER));
					schoolTf = new TextField();
					updatejDialog.add(schoolTf);
					updatejDialog.add(new JLabel(tittle[4] + ":", JLabel.CENTER));
					myclassTf = new TextField();
					updatejDialog.add(myclassTf);
					updatejDialog.add(new JLabel(tittle[5] + ":", JLabel.CENTER));
					mydateTf = new TextField();
					updatejDialog.add(mydateTf);
					updatejDialog.add(new JLabel(tittle[6] + ":", JLabel.CENTER));
					isinhbstudentTf = new TextField();
					updatejDialog.add(isinhbstudentTf);
					updatejDialog.add(new JLabel(tittle[7] + ":", JLabel.CENTER));
					isinwhTf = new TextField();
					updatejDialog.add(isinwhTf);
					updatejDialog.add(new JLabel(tittle[8] + ":", JLabel.CENTER));
					isinhbTf = new TextField();
					updatejDialog.add(isinhbTf);
					updatejDialog.add(new JLabel(tittle[9] + ":", JLabel.CENTER));
					islikeTf = new TextField();
					updatejDialog.add(islikeTf);
					updatejDialog.add(new JLabel(tittle[10] + ":", JLabel.CENTER));
					isconfirmTf = new TextField();
					updatejDialog.add(isconfirmTf);
					JButton ok = new JButton("确定");
					ok.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							Student student = new Student();
							try {
								student.setId(idTf.getText().trim().equals("")?null:Integer.valueOf(idTf.getText()));
								student.setSno(snoTf.getText().trim().equals("")?null:Integer.valueOf(snoTf.getText()));
								student.setUsername(usernameTf.getText().trim().equals("")?null:String.valueOf(usernameTf.getText()));
								student.setSchool(schoolTf.getText().trim().equals("")?null:String.valueOf(schoolTf.getText()));
								student.setMyclass(myclassTf.getText().trim().equals("")?null:String.valueOf(myclassTf.getText()));
								student.setMydate(mydateTf.getText().trim().equals("")?null:Timestamp.valueOf(mydateTf.getText()+" 0:0:0"));
								student.setIsinhbstudent(isinhbstudentTf.getText().trim().equals("")?null:String.valueOf(isinhbstudentTf.getText()));
								student.setIsinwh(isinwhTf.getText().trim().equals("")?null:String.valueOf(isinwhTf.getText()));
								student.setIsinhb(isinhbTf.getText().trim().equals("")?null:String.valueOf(isinhbTf.getText()));
								student.setIslike(islikeTf.getText().trim().equals("")?null:String.valueOf(islikeTf.getText()));
								student.setIsconfirm(isconfirmTf.getText().trim().equals("")?null:String.valueOf(isconfirmTf.getText()));
							} catch (Exception e1) {
								e1.printStackTrace();
								JOptionPane.showMessageDialog(StudentFrame.this, "非法数据");
								return;
							}
							try {
								studentService.updateById(student);
							} catch (Exception e1) {
								e1.printStackTrace();
								JOptionPane.showMessageDialog(StudentFrame.this, "非法数据");
								return;
							}
							updatejDialog.setVisible(false);
							flush();
						}
					});
					updatejDialog.add(ok);
					JButton cancel = new JButton("取消");
					cancel.addActionListener(new ActionListener() {
						@Override
						public void actionPerformed(ActionEvent e) {
							updatejDialog.setVisible(false);
						}
					});
					updatejDialog.add(cancel);
				}
				
				Student student = list.get(row);
				idTf.setText(student.getId()==null?"":student.getId()+"");
				snoTf.setText(student.getSno()==null?"":student.getSno()+"");
				usernameTf.setText(student.getUsername()==null?"":student.getUsername()+"");
				schoolTf.setText(student.getSchool()==null?"":student.getSchool()+"");
				myclassTf.setText(student.getMyclass()==null?"":student.getMyclass()+"");
				mydateTf.setText(student.getMydate()==null?"":student.getMydate().toString().substring(0, 10)+"");
				isinhbstudentTf.setText(student.getIsinhbstudent()==null?"":student.getIsinhbstudent()+"");
				isinwhTf.setText(student.getIsinwh()==null?"":student.getIsinwh()+"");
				isinhbTf.setText(student.getIsinhb()==null?"":student.getIsinhb()+"");
				islikeTf.setText(student.getIslike()==null?"":student.getIslike()+"");
				isconfirmTf.setText(student.getIsconfirm()==null?"":student.getIsconfirm()+"");
				updatejDialog.setVisible(true);
			}
		});
		
		
		/********************************************条件查询***********************************************/
		whereQuery.addActionListener(new ActionListener() {

			private JDialog whereQuery;
			private TextField idTf;
			private TextField snoTf;
			private TextField usernameTf;
			private TextField schoolTf;
			private TextField myclassTf;
			private TextField mydateTf;
			private TextField isinhbstudentTf;
			private TextField isinwhTf;
			private TextField isinhbTf;
			private TextField islikeTf;
			private TextField isconfirmTf;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if (whereQuery==null) {
					whereQuery = new JDialog();
					whereQuery.setTitle("条件查询");
					whereQuery.setBounds(((int) screenSize.getWidth() - WIDTH) / 2,
							((int) screenSize.getHeight() - HEIGHT-50) / 2, WIDTH, HEIGHT+50);
					whereQuery.setLayout(new GridLayout(14, 2));
					whereQuery.add(new JLabel(tittle[0] + ":", JLabel.CENTER));
					final TextField idTf = new TextField();
					whereQuery.add(idTf);
					whereQuery.add(new JLabel(tittle[1] + ":", JLabel.CENTER));
					final TextField snoTf = new TextField();
					whereQuery.add(snoTf);
					whereQuery.add(new JLabel(tittle[2] + ":", JLabel.CENTER));
					final TextField usernameTf = new TextField();
					whereQuery.add(usernameTf);
					whereQuery.add(new JLabel(tittle[3] + ":", JLabel.CENTER));
					final TextField schoolTf = new TextField();
					whereQuery.add(schoolTf);
					whereQuery.add(new JLabel(tittle[4] + ":", JLabel.CENTER));
					final TextField myclassTf = new TextField();
					whereQuery.add(myclassTf);
					whereQuery.add(new JLabel(tittle[5] + ":", JLabel.CENTER));
					final TextField mydateTf = new TextField();
					whereQuery.add(mydateTf);
					whereQuery.add(new JLabel(tittle[6] + ":", JLabel.CENTER));
					final TextField isinhbstudentTf = new TextField();
					whereQuery.add(isinhbstudentTf);
					whereQuery.add(new JLabel(tittle[7] + ":", JLabel.CENTER));
					final TextField isinwhTf = new TextField();
					whereQuery.add(isinwhTf);
					whereQuery.add(new JLabel(tittle[8] + ":", JLabel.CENTER));
					final TextField isinhbTf = new TextField();
					whereQuery.add(isinhbTf);
					whereQuery.add(new JLabel(tittle[9] + ":", JLabel.CENTER));
					final TextField islikeTf = new TextField();
					whereQuery.add(islikeTf);
					whereQuery.add(new JLabel(tittle[10] + ":", JLabel.CENTER));
					final TextField isconfirmTf = new TextField();
					whereQuery.add(isconfirmTf);
					whereQuery.add(new JLabel("开始时间" + ":", JLabel.CENTER));
					final TextField startTf = new TextField();
					whereQuery.add(startTf);
					whereQuery.add(new JLabel("结束时间" + ":", JLabel.CENTER));
					final TextField endTf = new TextField();
					whereQuery.add(endTf);
					
					JPanel panel = new JPanel();
					Choice choice = new Choice();
					choice.add("模糊查询");
					choice.add("准确查询");
					panel.add(choice);
					whereQuery.add(panel);
					JButton query = new JButton("查询");
					query.addActionListener(new ActionListener() {
						Date startDate,endDate;
						@Override
						public void actionPerformed(ActionEvent e) {
							Student student = new Student();
							try {
								student.setId(idTf.getText().trim().equals("")?null:Integer.valueOf(idTf.getText()));
								student.setSno(snoTf.getText().trim().equals("")?null:Integer.valueOf(snoTf.getText()));
								student.setUsername(usernameTf.getText().trim().equals("")?null:String.valueOf(usernameTf.getText()));
								student.setSchool(schoolTf.getText().trim().equals("")?null:String.valueOf(schoolTf.getText()));
								student.setMyclass(myclassTf.getText().trim().equals("")?null:String.valueOf(myclassTf.getText()));
								student.setMydate(mydateTf.getText().trim().equals("")?null:Timestamp.valueOf(mydateTf.getText()+" 0:0:0"));
								student.setIsinhbstudent(isinhbstudentTf.getText().trim().equals("")?null:String.valueOf(isinhbstudentTf.getText()));
								student.setIsinwh(isinwhTf.getText().trim().equals("")?null:String.valueOf(isinwhTf.getText()));
								student.setIsinhb(isinhbTf.getText().trim().equals("")?null:String.valueOf(isinhbTf.getText()));
								student.setIslike(islikeTf.getText().trim().equals("")?null:String.valueOf(islikeTf.getText()));
								student.setIsconfirm(isconfirmTf.getText().trim().equals("")?null:String.valueOf(isconfirmTf.getText()));
								SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
								startDate=startTf.getText().trim().equals("")?null:simpleDateFormat.parse(startTf.getText().trim());
								endDate=endTf.getText().trim().equals("")?null:simpleDateFormat.parse(endTf.getText().trim());
								
							} catch (Exception e1) {
								e1.printStackTrace();
								JOptionPane.showMessageDialog(StudentFrame.this, "非法数据");
								return;
							}
							flush(student,choice.getSelectedItem().equals("准确查询"),startDate,endDate);
							whereQuery.setVisible(false);
						}
					});
					whereQuery.add(query);
				}
				whereQuery.setVisible(true);
			}
		});
		
		JButton circle = new JButton("查看柱状图");
		circle.addActionListener(new ActionListener() {
			
			private JDialog 
				jDialog;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (jDialog==null) {
					jDialog = new JDialog() {
							@Override
							public void paint(Graphics g) {
								super.paint(g);
								System.out.println("paint................");
								Graphics2D graphics2d=(Graphics2D) g;
								
								int like=0,confirm=0;
								for (Student student : list) {
									if (student.getIsconfirm().equals("是")) {
										confirm++;
									}
									if (student.getIslike().equals("是")) {
										like++;
									}
								}
//								graphics2d.fillRect(x, y, width, height);
								graphics2d.setColor(Color.blue);
								graphics2d.drawString("疑似病例", StudentFrame.this.WIDTH/2-70, StudentFrame.this.HEIGHT-30);
								graphics2d.fillRect(StudentFrame.this.WIDTH/2-70, StudentFrame.this.HEIGHT-50, 50, -like);
								graphics2d.drawString(""+like, StudentFrame.this.WIDTH/2-70+20, StudentFrame.this.HEIGHT-50-like-10);
								
								
								graphics2d.setColor(Color.red);
								graphics2d.drawString("确诊病例", StudentFrame.this.WIDTH/2+35, StudentFrame.this.HEIGHT-30);
								graphics2d.fillRect(StudentFrame.this.WIDTH/2+35, StudentFrame.this.HEIGHT-50, 50, -confirm);
								graphics2d.drawString(""+confirm, StudentFrame.this.WIDTH/2+35+20, StudentFrame.this.HEIGHT-50-confirm-10);
								
							}
					};
					jDialog.setTitle("冠状病毒统计柱状图");
					jDialog.setBounds(((int) screenSize.getWidth() - WIDTH) / 2,
							((int) screenSize.getHeight() - HEIGHT) / 2, WIDTH, HEIGHT);
				}
				
				jDialog.show();
				jDialog.repaint();
			}
		});
		southjPanel.add(circle);
		
		
		
		
	}
	
	private void flush(Student student,boolean isDistance,Date startDate,Date endDate) {
		list = studentService.findAll();
		Iterator<Student> iterator = list.iterator();
		while (iterator.hasNext()) {
			Student u = (Student) iterator.next();
			if (startDate!=null) {
				try {
					if (u.getMydate().before(startDate)) {
						iterator.remove();
						continue;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
				}
			}
			if (endDate!=null) {
				try {
					if (u.getMydate().after(endDate)) {
						iterator.remove();
						continue;
					}
				} catch (Exception e) {
					// TODO Auto-generated catch block
				}
			}
			if (student.getId()!=null&&!(student.getId()+"").equals("")) {
				if (isDistance) {
					if (!u.getId().equals(student.getId())) {
						iterator.remove();
						continue;
					}
				}else {
					if (!(u.getId()+"").contains(student.getId()+"")) {
						iterator.remove();
						continue;
					}
				}
			}
			if (student.getSno()!=null&&!(student.getSno()+"").equals("")) {
				if (isDistance) {
					if (!u.getSno().equals(student.getSno())) {
						iterator.remove();
						continue;
					}
				}else {
					if (!(u.getSno()+"").contains(student.getSno()+"")) {
						iterator.remove();
						continue;
					}
				}
			}
			if (student.getUsername()!=null&&!(student.getUsername()+"").equals("")) {
				if (isDistance) {
					if (!u.getUsername().equals(student.getUsername())) {
						iterator.remove();
						continue;
					}
				}else {
					if (!(u.getUsername()+"").contains(student.getUsername()+"")) {
						iterator.remove();
						continue;
					}
				}
			}
			if (student.getSchool()!=null&&!(student.getSchool()+"").equals("")) {
				if (isDistance) {
					if (!u.getSchool().equals(student.getSchool())) {
						iterator.remove();
						continue;
					}
				}else {
					if (!(u.getSchool()+"").contains(student.getSchool()+"")) {
						iterator.remove();
						continue;
					}
				}
			}
			if (student.getMyclass()!=null&&!(student.getMyclass()+"").equals("")) {
				if (isDistance) {
					if (!u.getMyclass().equals(student.getMyclass())) {
						iterator.remove();
						continue;
					}
				}else {
					if (!(u.getMyclass()+"").contains(student.getMyclass()+"")) {
						iterator.remove();
						continue;
					}
				}
			}
			if (student.getMydate()!=null&&!(student.getMydate()+"").equals("")) {
				if (isDistance) {
					if (!u.getMydate().equals(student.getMydate())) {
						iterator.remove();
						continue;
					}
				}else {
					if (!(u.getMydate()+"").contains(student.getMydate()+"")) {
						iterator.remove();
						continue;
					}
				}
			}
			if (student.getIsinhbstudent()!=null&&!(student.getIsinhbstudent()+"").equals("")) {
				if (isDistance) {
					if (!u.getIsinhbstudent().equals(student.getIsinhbstudent())) {
						iterator.remove();
						continue;
					}
				}else {
					if (!(u.getIsinhbstudent()+"").contains(student.getIsinhbstudent()+"")) {
						iterator.remove();
						continue;
					}
				}
			}
			if (student.getIsinwh()!=null&&!(student.getIsinwh()+"").equals("")) {
				if (isDistance) {
					if (!u.getIsinwh().equals(student.getIsinwh())) {
						iterator.remove();
						continue;
					}
				}else {
					if (!(u.getIsinwh()+"").contains(student.getIsinwh()+"")) {
						iterator.remove();
						continue;
					}
				}
			}
			if (student.getIsinhb()!=null&&!(student.getIsinhb()+"").equals("")) {
				if (isDistance) {
					if (!u.getIsinhb().equals(student.getIsinhb())) {
						iterator.remove();
						continue;
					}
				}else {
					if (!(u.getIsinhb()+"").contains(student.getIsinhb()+"")) {
						iterator.remove();
						continue;
					}
				}
			}
			if (student.getIslike()!=null&&!(student.getIslike()+"").equals("")) {
				if (isDistance) {
					if (!u.getIslike().equals(student.getIslike())) {
						iterator.remove();
						continue;
					}
				}else {
					if (!(u.getIslike()+"").contains(student.getIslike()+"")) {
						iterator.remove();
						continue;
					}
				}
			}
			if (student.getIsconfirm()!=null&&!(student.getIsconfirm()+"").equals("")) {
				if (isDistance) {
					if (!u.getIsconfirm().equals(student.getIsconfirm())) {
						iterator.remove();
						continue;
					}
				}else {
					if (!(u.getIsconfirm()+"").contains(student.getIsconfirm()+"")) {
						iterator.remove();
						continue;
					}
				}
			}
		}
		String[][] data = new String[list.size()][11];
		for (int i = 0; i < list.size(); i++) {
			Student student2 = list.get(i);
			data[i][0] = student2.getId()==null?"":student2.getId()+ "";
			data[i][1] = student2.getSno()==null?"":student2.getSno()+ "";
			data[i][2] = student2.getUsername()==null?"":student2.getUsername()+ "";
			data[i][3] = student2.getSchool()==null?"":student2.getSchool()+ "";
			data[i][4] = student2.getMyclass()==null?"":student2.getMyclass()+ "";
			data[i][5] = student2.getMydate()==null?"":student2.getMydate().toString().substring(0, 10)+ "";
			data[i][6] = student2.getIsinhbstudent()==null?"":student2.getIsinhbstudent()+ "";
			data[i][7] = student2.getIsinwh()==null?"":student2.getIsinwh()+ "";
			data[i][8] = student2.getIsinhb()==null?"":student2.getIsinhb()+ "";
			data[i][9] = student2.getIslike()==null?"":student2.getIslike()+ "";
			data[i][10] = student2.getIsconfirm()==null?"":student2.getIsconfirm()+ "";
		}
		jTable.setModel(new DefaultTableModel(data, tittle));
	}

	private void flush() {
		list = studentService.findAll();
		String[][] data = new String[list.size()][11];
		for (int i = 0; i < list.size(); i++) {
			Student student2 = list.get(i);
			data[i][0] = student2.getId()==null?"":student2.getId()+ "";
			data[i][1] = student2.getSno()==null?"":student2.getSno()+ "";
			data[i][2] = student2.getUsername()==null?"":student2.getUsername()+ "";
			data[i][3] = student2.getSchool()==null?"":student2.getSchool()+ "";
			data[i][4] = student2.getMyclass()==null?"":student2.getMyclass()+ "";
			data[i][5] = student2.getMydate()==null?"":student2.getMydate().toString().substring(0, 10)+ "";
			data[i][6] = student2.getIsinhbstudent()==null?"":student2.getIsinhbstudent()+ "";
			data[i][7] = student2.getIsinwh()==null?"":student2.getIsinwh()+ "";
			data[i][8] = student2.getIsinhb()==null?"":student2.getIsinhb()+ "";
			data[i][9] = student2.getIslike()==null?"":student2.getIslike()+ "";
			data[i][10] = student2.getIsconfirm()==null?"":student2.getIsconfirm()+ "";
		}
		jTable.setModel(new DefaultTableModel(data, tittle));
	}

	public static void main(String[] args) {
		new StudentFrame();
	}
}

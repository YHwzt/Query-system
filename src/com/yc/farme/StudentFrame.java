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

//	����Student��Ӧ��service����
	private StudentService studentService = new StudentServiceImpl();
//	���崰��������
	private Integer WIDTH = 600, HEIGHT = 400;
//	���������������
	private JTable jTable;
//	������Ӱ�ť������
	private JButton insert;
//	����ˢ�°�ť������
	private JButton flush;
//	����ɾ����ť������
	private JButton delete;
//	�������°�ť������
	private JButton update;
//	����������ѯ��ť������
	private JButton whereQuery;
//	������ͷ�������
	private String[] tittle = {"id","ѧ��","����","ѧԺ","�༶","�����","�Ƿ������","�Ƿ����人","�Ƿ��ں���","�Ƿ����Ʋ���","�Ƿ�ȷ�ﲡ��"};
//	����List����
	private List<Student> list;

	/**
	 * �޲ι�����
	 */
	public StudentFrame() {
//		���ô������
		setTitle("��״����ͳ���б�");
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
//		���ÿ��
		setBounds(((int) screenSize.getWidth() - WIDTH) / 2, ((int) screenSize.getHeight() - HEIGHT) / 2, WIDTH,
				HEIGHT);
		this.WIDTH = this.WIDTH / 2;
		this.HEIGHT = (int) (this.HEIGHT * 0.8);
//		�����˳�����������
		setDefaultCloseOperation(EXIT_ON_CLOSE);
//		����������
		JPanel southjPanel = new JPanel();
//		������Ӱ�ť����
		insert = new JButton("���");
//		����Ӱ�ť������ӵ������
		southjPanel.add(insert);
//		����ˢ�°�ť����
		flush = new JButton("ˢ��");
//		��ˢ�°�ť������ӵ������
		southjPanel.add(flush);
//		����ɾ����ť����
		delete = new JButton("ɾ��");
//		��ɾ����ť������ӵ������
		southjPanel.add(delete);
//		�������°�ť����
		update = new JButton("����");
//		�Ѹ��°�ť������ӵ������
		southjPanel.add(update);
//		����������ѯ��ť����
		whereQuery = new JButton("������ѯ");
//		��������ѯ��ť������ӵ������
		southjPanel.add(whereQuery);
//		����������ӵ�����ײ���
		add(southjPanel, BorderLayout.SOUTH);
//		����������
		jTable = new JTable();
		jTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
//		��������������
		JScrollPane jScrollPane = new JScrollPane();
//		�ѱ����������ӵ����������
		jScrollPane.setViewportView(jTable);
//		�ѹ�����������ӵ�������
		add(jScrollPane);
//		ˢ�±������
		flush();
//		��ʾ����
		setVisible(true);

		/********************************************���***********************************************/
		insert.addActionListener(new ActionListener() {

			private JDialog insertjDialog;

			@Override
			public void actionPerformed(ActionEvent e) {
				if (insertjDialog==null) {
					insertjDialog = new JDialog();
					insertjDialog.setTitle("���");
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
					JButton ok = new JButton("ȷ��");
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
								JOptionPane.showMessageDialog(StudentFrame.this, "�Ƿ�����");
								return;
							}
							
							try {
								studentService.insert(student);
							} catch (Exception e1) {
								e1.printStackTrace();
								JOptionPane.showMessageDialog(StudentFrame.this, "�Ƿ�����");
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
					JButton cancel = new JButton("ȡ��");
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
		/********************************************ˢ��***********************************************/
		flush.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				flush();
			}
		});
		/********************************************ɾ��***********************************************/
		delete.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				int row = jTable.getSelectedRow();
				if (row==-1) {
					JOptionPane.showMessageDialog(StudentFrame.this, "��ѡ��һ�У�����");
					return;
				}
				Integer id = list.get(row).getId();
				studentService.deleteById(id);
				flush();
			}
		});		
		/********************************************����***********************************************/
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
					JOptionPane.showMessageDialog(StudentFrame.this, "��ѡ��һ�У�����");
					return;
				}
				
				if (updatejDialog==null) {
					updatejDialog = new JDialog();
					updatejDialog.setTitle("����");
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
					JButton ok = new JButton("ȷ��");
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
								JOptionPane.showMessageDialog(StudentFrame.this, "�Ƿ�����");
								return;
							}
							try {
								studentService.updateById(student);
							} catch (Exception e1) {
								e1.printStackTrace();
								JOptionPane.showMessageDialog(StudentFrame.this, "�Ƿ�����");
								return;
							}
							updatejDialog.setVisible(false);
							flush();
						}
					});
					updatejDialog.add(ok);
					JButton cancel = new JButton("ȡ��");
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
		
		
		/********************************************������ѯ***********************************************/
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
					whereQuery.setTitle("������ѯ");
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
					whereQuery.add(new JLabel("��ʼʱ��" + ":", JLabel.CENTER));
					final TextField startTf = new TextField();
					whereQuery.add(startTf);
					whereQuery.add(new JLabel("����ʱ��" + ":", JLabel.CENTER));
					final TextField endTf = new TextField();
					whereQuery.add(endTf);
					
					JPanel panel = new JPanel();
					Choice choice = new Choice();
					choice.add("ģ����ѯ");
					choice.add("׼ȷ��ѯ");
					panel.add(choice);
					whereQuery.add(panel);
					JButton query = new JButton("��ѯ");
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
								JOptionPane.showMessageDialog(StudentFrame.this, "�Ƿ�����");
								return;
							}
							flush(student,choice.getSelectedItem().equals("׼ȷ��ѯ"),startDate,endDate);
							whereQuery.setVisible(false);
						}
					});
					whereQuery.add(query);
				}
				whereQuery.setVisible(true);
			}
		});
		
		JButton circle = new JButton("�鿴��״ͼ");
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
									if (student.getIsconfirm().equals("��")) {
										confirm++;
									}
									if (student.getIslike().equals("��")) {
										like++;
									}
								}
//								graphics2d.fillRect(x, y, width, height);
								graphics2d.setColor(Color.blue);
								graphics2d.drawString("���Ʋ���", StudentFrame.this.WIDTH/2-70, StudentFrame.this.HEIGHT-30);
								graphics2d.fillRect(StudentFrame.this.WIDTH/2-70, StudentFrame.this.HEIGHT-50, 50, -like);
								graphics2d.drawString(""+like, StudentFrame.this.WIDTH/2-70+20, StudentFrame.this.HEIGHT-50-like-10);
								
								
								graphics2d.setColor(Color.red);
								graphics2d.drawString("ȷ�ﲡ��", StudentFrame.this.WIDTH/2+35, StudentFrame.this.HEIGHT-30);
								graphics2d.fillRect(StudentFrame.this.WIDTH/2+35, StudentFrame.this.HEIGHT-50, 50, -confirm);
								graphics2d.drawString(""+confirm, StudentFrame.this.WIDTH/2+35+20, StudentFrame.this.HEIGHT-50-confirm-10);
								
							}
					};
					jDialog.setTitle("��״����ͳ����״ͼ");
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

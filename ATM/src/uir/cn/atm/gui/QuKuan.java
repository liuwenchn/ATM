package uir.cn.atm.gui;


import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import cn.uir.atm.sql.Con2sql;
import uir.cn.atm.utils.RemindFrame;
import uir.cn.atm.utils.ScreenSize;

public class QuKuan 
{
	JFrame jf;
	JButton one,two,three,five,ten,others,done,quit;
	
	JPanel jp1,jp2,jp3,jp4,brick1,brick2,brick3,brick4;
	JLabel welcome1,welcome2;
	JLabel yuE1,yuE2;
	JLabel userName1,userName2;
	JTextField jtf;
	
	
	public QuKuan()
	{
		jf=new JFrame("取款");
		jf.setBounds(ScreenSize.SCREENWIDTH/4, ScreenSize.SCREENHEIGHT/4,ScreenSize.SCREENWIDTH/2, ScreenSize.SCREENHEIGHT/2);
		jf.setVisible(true);
		
		
		
		one=new JButton("100");
		one.setFont(new Font("楷体", Font.BOLD, 25));
		
		
		
		three=new JButton("300");
		three.setFont(new Font("楷体", Font.BOLD, 25));
		
		ten=new JButton("1000");
		ten.setFont(new Font("楷体", Font.BOLD, 25));
		
		jp1=new JPanel();
		jp1.setLayout(new GridLayout(5,1));

		
		brick1=new JPanel();
		brick2=new JPanel();
		
		jp1.add(brick1);
		jp1.add(one);
		jp1.add(three);
		jp1.add(ten);
		jp1.add(brick2);
		
		
		
		two=new JButton("200");
		two.setFont(new Font("楷体", Font.BOLD, 25));
		
		five=new JButton("500");
		five.setFont(new Font("楷体", Font.BOLD, 25));
		
		others=new JButton("其他金额");
		others.setFont(new Font("楷体", Font.BOLD, 25));
		
		brick3=new JPanel();
		brick4=new JPanel();
		
		
		jp2=new JPanel();
		
		jp2.setLayout(new GridLayout(5,1));
		
		jp2.add(brick3);
		
		jp2.add(two);
		jp2.add(five);
		jp2.add(others);
		jp2.add(brick4);
		
		
		
		jp3=new JPanel();
		jp4=new JPanel();
		
		
		
		welcome1=new JLabel("欢迎",JLabel.RIGHT);
		welcome1.setFont(new Font("楷体", Font.BOLD, 25));
		
		userName1=new JLabel("账户名称：",JLabel.RIGHT);
		userName1.setFont(new Font("楷体", Font.BOLD, 25));
		
		yuE1=new JLabel("取款：",JLabel.RIGHT);
		yuE1.setFont(new Font("楷体", Font.BOLD, 25));
		
		done=new JButton("确定");
		done.setFont(new Font("楷体", Font.BOLD, 25));
		
		jp3.setLayout(new GridLayout(5,1));
		jp3.add(welcome1);
		jp3.add(userName1);
		jp3.add(yuE1);
		jp3.add(new JPanel());
		jp3.add(done);
		
		
		welcome2=new JLabel("光临!",JLabel.LEFT);
		welcome2.setFont(new Font("楷体", Font.BOLD, 25));
		
		userName2=new JLabel(Login.username);
		userName2.setFont(new Font("楷体", Font.BOLD, 25));
		
//		yuE2=new JLabel("10000元");
		jtf=new JTextField(5);
		jtf.setFont(new Font("楷体", Font.BOLD, 25));
		jtf.setEditable(false);
		
		quit=new JButton("上一步");
		quit.setFont(new Font("楷体", Font.BOLD, 25));
		
		
		jp4.setLayout(new GridLayout(5,1));
		jp4.add(welcome2);
		jp4.add(userName2);
		jp4.add(jtf);
		jp4.add(new JPanel());
		jp4.add(quit);
		
		
		
		jf.setLayout(new GridLayout(1,4));
		jf.add(jp1);
		jf.add(jp3);
		jf.add(jp4);
		jf.add(jp2);
	}
	
	public void myEvent()
	{
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.dispose();
				MainFrame.jf.setVisible(true);
			}
		});
		
		one.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtf.setText("100");
			}
		});
		
		two.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtf.setText("200");
			}
		});
		
		three.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtf.setText("300");
			}
		});
		
		five.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtf.setText("500");
			}
		});
		
		ten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtf.setText("1000");
			}
		});
		others.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jtf.setText("");
				jtf.setEditable(true);
				jtf.requestFocus();
			}
		});
		
		done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				Connection conn=Con2sql.con2sql();
				//String sql="UPDATE `atm`.`userinfo` SET `NUM`='"+(Login.num-Integer.parseInt(jtf.getText()))+"' WHERE `ID`='"+Login.ID+"' and`username`='"+Login.username+"';";
				String sql="UPDATE `atm`.`userinfo` SET `NUM`=`NUM`-'"+Integer.parseInt(jtf.getText())+"' WHERE `ID`='"+Login.ID+"' and`username`='"+Login.username+"';";
				//UPDATE `atm`.`userinfo` SET `NUM`=`NUM`-'500' WHERE `ID`='3' and`username`='刘文';
				
				try {
					Statement stat=conn.createStatement();				
					stat.execute(sql);
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				
				
				
				jf.dispose();
				MainFrame.jf.setVisible(true);
				RemindFrame.remind("出钞完成，请您取走钞票！");
			}
			
			
		});
		
		jf.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				jf.dispose();
				MainFrame.jf.setVisible(true);
			}
		});
		
	}

}

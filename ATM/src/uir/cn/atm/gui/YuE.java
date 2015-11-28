package uir.cn.atm.gui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import uir.cn.atm.utils.ScreenSize;
import cn.uir.atm.sql.Con2sql;



public class YuE 
{
	public JFrame jf;
	JButton done,quit;
	JTextField jtf;
	ResultSet rs;
	Connection conn;
	Statement stat;
	
	public YuE()
	{
		jf=new JFrame("余额查询");
		jf.setBounds(ScreenSize.SCREENWIDTH/4, ScreenSize.SCREENHEIGHT/4,ScreenSize.SCREENWIDTH/2, ScreenSize.SCREENHEIGHT/2);
		jf.setVisible(true);
		
		jf.setLayout(new GridLayout(5,1));
		
		JPanel brick1=new JPanel();
		brick1.setLayout(new GridLayout(1,2));
		JLabel huanying=new JLabel("欢迎",JLabel.RIGHT);
		JLabel guanglin=new JLabel("光临!",JLabel.LEFT);
		huanying.setFont(new Font("楷体", Font.BOLD, 25));
		guanglin.setFont(new Font("楷体", Font.BOLD, 25));		
		brick1.add(huanying);
		brick1.add(guanglin);
		
		JPanel brick2=new JPanel();
		brick2.setLayout(new GridLayout(1,2));
		JLabel label1=new JLabel(Login.username+":您的帐",JLabel.RIGHT);
		JLabel label2=new JLabel("户余额：",JLabel.LEFT);
		label1.setFont(new Font("楷体", Font.BOLD, 25));
		label2.setFont(new Font("楷体", Font.BOLD, 25));		
		brick2.add(label1);
		brick2.add(label2);
		
		JPanel brick3=new JPanel();
		brick3.setLayout(new GridLayout(1,3));
		
		jtf=new JTextField();
		jtf.setEditable(false);
		jtf.setFont(new Font("楷体", Font.BOLD, 25));	
		
		//rs=Login.selectSql();
		conn=Con2sql.con2sql();
		String SQL="Select * from userinfo where username ='"+Login.username+"';"; 
		try {
			stat=conn.createStatement();
			rs=stat.executeQuery(SQL);
		} catch (SQLException e1) {
			// TODO 自动生成的 catch 块
			e1.printStackTrace();
		}
		
		
		
		
		int num=0;
		try 
		{
			while(rs.next())
			{
				num=rs.getInt("NUM");
			}
		} 
		catch (SQLException e) {
			// TODO 自动生成的 catch 块
			e.printStackTrace();
		}
		jtf.setText(new Integer(num).toString());
		
		
		brick3.add(new JPanel());
		brick3.add(jtf);
		JLabel label=new JLabel("元",JLabel.LEFT);
		label.setFont(new Font("楷体", Font.BOLD, 25));
		
		brick3.add(label);
		
		
		JPanel brick4=new JPanel();
		brick4.setLayout(new GridLayout(1,4));
		
		done=new JButton("完成");
		done.setFont(new Font("楷体", Font.BOLD, 25));
		quit=new JButton("上一步");
		quit.setFont(new Font("楷体", Font.BOLD, 25));
		brick4.add(new JPanel());
		brick4.add(done);
		brick4.add(quit);
		brick4.add(new JPanel());
		
		
		jf.add(brick1);
		jf.add(brick2);
		jf.add(brick3);
		jf.add(new JPanel());
		jf.add(brick4);
	}
	
	public void myEvent()
	{
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.dispose();
				//MainFrame.jf.setVisible(true);
				
				try {
					rs.close();
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				MainFrame.jf.setVisible(true);
			}
		});
		
		done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.dispose();
				//MainFrame.jf.setVisible(true);
				
				try {
					rs.close();
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				MainFrame.jf.setVisible(true);
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

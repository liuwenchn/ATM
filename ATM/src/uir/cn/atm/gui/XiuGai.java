package uir.cn.atm.gui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
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
import javax.swing.JPasswordField;

import uir.cn.atm.utils.RemindFrame;
import uir.cn.atm.utils.ScreenSize;
import cn.uir.atm.sql.Con2sql;

public class XiuGai 
{
	JFrame jf;
	JButton quit,done,check;
	JPasswordField jpf1,jpf2,jpf3;
	public XiuGai()
	{
		jf=new JFrame("修改密码");
		jf.setBounds(ScreenSize.SCREENWIDTH/4, ScreenSize.SCREENHEIGHT/4,ScreenSize.SCREENWIDTH/2, ScreenSize.SCREENHEIGHT/2);
		jf.setVisible(true);
		jf.setLayout(new GridLayout(5,1));
		
		JPanel brick1=new JPanel();
		brick1.setLayout(new GridLayout(1,2));
		
		JLabel label=new JLabel("请输入原密码：",JLabel.RIGHT);
		label.setFont(new Font("楷体", Font.BOLD, 25));
		brick1.add(label);
		
		
		
		JPanel subbrick1=new JPanel();
		subbrick1.setLayout(new GridLayout(1,2));
		//brick2.add();
		
		jpf1=new JPasswordField();
		
		subbrick1.add(jpf1);
		
		check=new JButton("确认原密码"); 
		check.setFont(new Font("楷体", Font.BOLD, 25));
		subbrick1.add(check);
		brick1.add(subbrick1);
		
		JPanel brick2=new JPanel();
		brick2.setLayout(new GridLayout(1,2));
		JLabel label2=new JLabel("请输入新密码：",JLabel.RIGHT);
		label2.setFont(new Font("楷体", Font.BOLD, 25));
		brick2.add(label2);
		
		JPanel subbrick2=new JPanel();
		subbrick2.setLayout(new GridLayout(1,2));
		//brick2.add();
		
		jpf2=new JPasswordField();
		jpf2.setEditable(true);
		
		
		
		subbrick2.add(jpf2);
		subbrick2.add(new JPanel());
		brick2.add(subbrick2);
		
		
		JPanel brick3=new JPanel();
		brick3.setLayout(new GridLayout(1,2));
		JLabel label3=new JLabel("请再次输入新密码：",JLabel.RIGHT);
		label3.setFont(new Font("楷体", Font.BOLD, 25));
		brick3.add(label3);
		
		JPanel subbrick3=new JPanel();
		subbrick3.setLayout(new GridLayout(1,2));
		//brick2.add();
		
		jpf3=new JPasswordField();
		
		
		subbrick3.add(jpf3);
		subbrick3.add(new JPanel());
		brick3.add(subbrick3);
		
		
		JPanel brick4=new JPanel();
		brick4.setLayout(new GridLayout(1,4));
		done=new JButton("确认");
		done.setFont(new Font("楷体", Font.BOLD, 25));
		quit=new JButton("上一步");
		quit.setFont(new Font("楷体", Font.BOLD, 25));
	
		brick4.add(new Panel());
		brick4.add(done);
		brick4.add(quit);
		brick4.add(new Panel());
		
		JPanel brick5=new JPanel();
		brick5.setLayout(new GridLayout(1,2));
		JLabel huanying=new JLabel("欢迎",JLabel.RIGHT);
		JLabel guanglin=new JLabel("光临!",JLabel.LEFT);
		huanying.setFont(new Font("楷体", Font.BOLD, 25));
		guanglin.setFont(new Font("楷体", Font.BOLD, 25));
		
		brick5.add(huanying);
		brick5.add(guanglin);
		
		
		
		jf.add(brick5);
		jf.add(brick1);
		jf.add(brick2);
		jf.add(brick3);
		jf.add(brick4);
	}
	
	
	public void myEvent()
	{
		Connection conn=Con2sql.con2sql();
		check.addActionListener(new ActionListener() 
		{
			
			public void actionPerformed(ActionEvent e) 
			{
				
				String checkSql="SELECT * FROM atm.userinfo where username='"+Login.username+"';";
			try {
				Statement stat1=conn.createStatement();
				
				ResultSet rs1=stat1.executeQuery(checkSql);
				
					while(rs1.next())
					{
//						System.out.println(String.valueOf(jpf1.getPassword()));
//						System.out.println(rs.getString("password"));
						if(!String.valueOf(jpf1.getPassword()).equals(rs1.getString("password")))
						{
							RemindFrame.remind("原密码输入错误！");
							jpf1.setText("");
							return;
						}
						else
						{
							RemindFrame.remind("原密码正确！");
						}
						
					}
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
			}
		});
		done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				String checkSql="SELECT * FROM atm.userinfo where username='"+Login.username+"';";
			try {
					Statement stat=conn.createStatement();
					
					ResultSet rs=stat.executeQuery(checkSql);
				
					
					
						rs.first();
						
//						while(rs.next())
//						{
							boolean flag=String.valueOf(jpf1.getPassword()).equals(rs.getString("password"));
							if(flag&&String.valueOf(jpf2.getPassword()).equals(String.valueOf(jpf3.getPassword())))
							{
								String updateSql="UPDATE `atm`.`userinfo` SET `password`='"+String.valueOf(jpf2.getPassword())+"' WHERE `ID`='3' and`username`='"+Login.username+"';";
								stat.execute(updateSql);
								jf.dispose();
								MainFrame.jf.setVisible(true);
								RemindFrame.remind("密码修改成功！");
							}
//						}
						

						
					
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				
			}
		});
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.dispose();
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

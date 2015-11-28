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
import javax.swing.JTextField;

import cn.uir.atm.sql.Con2sql;
import uir.cn.atm.utils.RemindFrame;
import uir.cn.atm.utils.ScreenSize;

public class ZhuanZhang 
{
	JFrame jf;
	JButton quit,done,correct;
	String plusSql,minusSql,sql;
	JTextField jtfID,jtfName,jtfNum;
	
	public ZhuanZhang()
	{
		jf=new JFrame("转账");
		jf.setBounds(ScreenSize.SCREENWIDTH/4, ScreenSize.SCREENHEIGHT/4,ScreenSize.SCREENWIDTH/2, ScreenSize.SCREENHEIGHT/2);
		jf.setVisible(true);
		
		jf.setLayout(new GridLayout(5,1));
		
		JPanel brick1=new JPanel();
		brick1.setLayout(new GridLayout(1,2));
		
		JLabel label=new JLabel("请输入转入账户号：",JLabel.RIGHT);
		label.setFont(new Font("楷体", Font.BOLD, 25));
		brick1.add(label);
		
		JPanel subbrick1=new JPanel();
		subbrick1.setLayout(new GridLayout(1,2));
		//brick2.add();
		
		jtfID=new JTextField();
		jtfID.setFont(new Font("楷体", Font.BOLD, 25));
		subbrick1.add(jtfID);
		
		correct=new JButton("确认无误");
		correct.setFont(new Font("楷体", Font.BOLD, 25));
		subbrick1.add(correct);
		brick1.add(subbrick1);
		
		JPanel brick2=new JPanel();
		brick2.setLayout(new GridLayout(1,2));
		JLabel label2=new JLabel("确认账户名：",JLabel.RIGHT);
		label2.setFont(new Font("楷体", Font.BOLD, 25));
		brick2.add(label2);
		
		JPanel subbrick2=new JPanel();
		subbrick2.setLayout(new GridLayout(1,2));
		//brick2.add();
		
		jtfName=new JTextField();
		jtfName.setEditable(false);
		jtfName.setFont(new Font("楷体", Font.BOLD, 25));
		
		subbrick2.add(jtfName);
		subbrick2.add(new JPanel());
		brick2.add(subbrick2);
		
		
		JPanel brick3=new JPanel();
		brick3.setLayout(new GridLayout(1,2));
		JLabel label3=new JLabel("请输入转账金额：",JLabel.RIGHT);
		label3.setFont(new Font("楷体", Font.BOLD, 25));
		brick3.add(label3);
		
		JPanel subbrick3=new JPanel();
		subbrick3.setLayout(new GridLayout(1,2));
		//brick2.add();
		
		jtfNum=new JTextField();
		jtfNum.setFont(new Font("楷体", Font.BOLD, 25));
		
		subbrick3.add(jtfNum);
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
		

		
		correct.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(Login.ID.equals(jtfID.getText()))
				{
					RemindFrame.remind("这是您自己的账户号！");
					return;
				}
				
				
				
				
				sql="SELECT * FROM atm.userinfo WHERE ID='"+jtfID.getText()+"';";
				
				//plusSql="UPDATE `atm`.`userinfo` SET `NUM`=`NUM`+'"+Integer.parseInt(jtfNum.getText())+"' WHERE `ID`='"+jtfID.getText()+"' and`username`='"+jtfName.getText()+"';";
				//SQL="SELECT * FROM atm.userinfo WHERE ID='"+jtf.getText()+"'; ";
				//UPDATE `atm`.`userinfo` SET `NUM`=`NUM`+'200' WHERE `ID`='3' and`username`='刘文';
				Statement stat=null;
				ResultSet rs=null;
				
				try 
				{
					stat=conn.createStatement();
					rs=stat.executeQuery(sql);
					
					if(!rs.next())
					{
						RemindFrame.remind("没有这个账户号!");
						return;
					}
					rs.beforeFirst();
					while(rs.next())
					{
						jtfName.setText(rs.getString("username"));
					}
					
				} 
				catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				finally
				{
					try {
						rs.close();
						stat.close();
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
				}
				
			}
		});
		done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				plusSql="UPDATE `atm`.`userinfo` SET `NUM`=(`NUM`+'"+Integer.parseInt(jtfNum.getText())+"') WHERE `ID`='"+jtfID.getText()+"' and`username`='"+jtfName.getText()+"';";
				minusSql="UPDATE `atm`.`userinfo` SET `NUM`=(`NUM`-'"+Integer.parseInt(jtfNum.getText())+"') WHERE `ID`='"+Login.ID+"' and`username`='"+Login.username+"';";
				Statement stat=null;
				//System.out.println(plusSql);
				
				try {
					stat= conn.createStatement();
					stat.execute(plusSql);
					stat.execute(minusSql);
					
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				
				finally
				{
					try {
						stat.close();
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
					
				}
				RemindFrame.remind("转账成功！");
				
			}
		});
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.dispose();
				
				try {
					conn.close();
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
				
				try {
					conn.close();
				} catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				
				MainFrame.jf.setVisible(true);
			}
		});
	}

}

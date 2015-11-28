package uir.cn.atm.gui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Panel;
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
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import cn.uir.atm.sql.Con2sql;
import uir.cn.atm.bean.User;
import uir.cn.atm.utils.RemindFrame;
import uir.cn.atm.utils.ScreenSize;

public class CreateUser 
{
	public JFrame jf;
	JButton done,quit;
	JTextField usernameField;
	JPasswordField pswField1,pswField2;
	JTextField cardText;
	
	public CreateUser()
	{
		jf=new JFrame("填写用户资料");
		jf.setBounds(ScreenSize.SCREENWIDTH/4, ScreenSize.SCREENHEIGHT/4,ScreenSize.SCREENWIDTH/2, ScreenSize.SCREENHEIGHT/2);
		jf.setVisible(true);
		
		jf.setLayout(new GridLayout(5,1));
		
		//---------------------------------------------------
		
		JPanel brick1=new JPanel();
		brick1.setLayout(new GridLayout(1,2));
		
		JLabel usernameLabel=new JLabel("请输入用户名:",JLabel.RIGHT);
		usernameLabel.setFont(new Font("楷体", Font.BOLD, 25));

		usernameField=new JTextField();
		usernameField.setFont(new Font("楷体", Font.BOLD, 25));
		
		
		JPanel subbrick=new JPanel();
		subbrick.setLayout(new GridLayout(1,2));
		subbrick.add(usernameField);
		subbrick.add(new Panel());
		
		brick1.add(usernameLabel);
		brick1.add(subbrick);
		
		//---------------------------------------------------
		
		
		JPanel brick2=new JPanel();
		brick2.setLayout(new GridLayout(1,2));
		
		JLabel pswLabel1=new JLabel("请输入密码:",JLabel.RIGHT);
		pswLabel1.setFont(new Font("楷体", Font.BOLD, 25));

		pswField1=new JPasswordField();
		pswField1.setFont(new Font("", Font.BOLD, 25));
		
		
		JPanel subbrick2=new JPanel();
		subbrick2.setLayout(new GridLayout(1,2));
		
		subbrick2.add(pswField1);
		subbrick2.add(new Panel());
		
		brick2.add(pswLabel1);
		brick2.add(subbrick2);
		
		//---------------------------------------------------
		
		JPanel brick3=new JPanel();
		brick3.setLayout(new GridLayout(1,2));
		
		JLabel pswLabel2=new JLabel("请再次输入密码:",JLabel.RIGHT);
		pswLabel2.setFont(new Font("楷体", Font.BOLD, 25));

		pswField2=new JPasswordField();
		pswField2.setFont(new Font("", Font.BOLD, 25));
		
		
		JPanel subbrick3=new JPanel();
		subbrick3.setLayout(new GridLayout(1,2));

		subbrick3.add(pswField2);
		subbrick3.add(new Panel());
		
		brick3.add(pswLabel2);
		brick3.add(subbrick3);
		
		//---------------------------------------------------
		JPanel brick4=new JPanel();
		brick4.setLayout(new GridLayout(1,2));
		
		JLabel cardLabel=new JLabel("请输入证件号:",JLabel.RIGHT);
		cardLabel.setFont(new Font("楷体", Font.BOLD, 25));

		cardText=new JTextField();
		cardText.setFont(new Font("楷体", Font.BOLD, 25));
		
		
		JPanel subbrick4=new JPanel();
		subbrick4.setLayout(new GridLayout(1,2));

		subbrick4.add(cardText);
		subbrick4.add(new Panel());
		
		brick4.add(cardLabel);
		brick4.add(subbrick4);
		
		
		//---------------------------------------------------
		
		JPanel brick5=new JPanel();
		brick5.setLayout(new GridLayout(1,4));
		done=new JButton("确认");
		quit=new JButton("上一步");
		done.setFont(new Font("楷体", Font.BOLD, 25));
		quit.setFont(new Font("楷体", Font.BOLD, 25));
		brick5.add(new JPanel());
		brick5.add(done);
		brick5.add(quit);
		brick5.add(new JPanel());
		
		
		//---------------------------------------------------
		
		
		jf.add(brick1);
		jf.add(brick2);
		jf.add(brick3);
		jf.add(brick4);
		jf.add(brick5);
		
	}
	
	public void myEvent()
	{
		jf.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				jf.dispose();
				Login.jf.setVisible(true);
			}
		});
		
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.dispose();
				Login.jf.setVisible(true);
			}
		});
		
		done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{				
				if ("".equals(usernameField.getText())
						|| "".equals(new String(pswField1.getPassword()))
						|| pswField1.getPassword() == null
						|| "".equals(new String(pswField2.getPassword()))
						|| pswField2.getPassword() == null
						|| "".equals(cardText.getText())) 
				{					
					RemindFrame.remind("请填写完整信息！");					
				}
																
				else if(!String.valueOf(pswField1.getPassword()).equals(String.valueOf(pswField2.getPassword())))
				{										
					RemindFrame.remind("密码输入不一致，请重新输入！");					
					usernameField.setText("");
					pswField1.setText("");
					pswField2.setText("");
					usernameField.requestFocus();
					cardText.setText("");
				}
				else
				{
					User user=new User();
					user.setUserName(usernameField.getText());
					user.setPassWord(String.valueOf(pswField1.getPassword()));
					user.setNUM("0");
					user.setCardID(cardText.getText());
					
					Connection conn=Con2sql.con2sql();
					Statement stat=null;
					
					String SQL="CREATE TABLE if not exists userinfo"
							+  "(`ID` INT NOT NULL AUTO_INCREMENT,"
							+  "`username` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,"
							+  "`password` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,"
							+  "`CardID` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,"
							+  "`num` INT NOT NULL,"
							+ "PRIMARY KEY (`ID`,`username`));";
					
					
					String insert="insert into userinfo (`username`, `password`,`cardid` ,`NUM`) values ('"							
							+     user.getUserName() +"','"
							+     user.getPassWord()+"','"
							+     user.getCardID()+"','"
							+     Integer.parseInt("0")+"');";
					
					
					try 
					{
						stat=conn.createStatement();
						stat.execute(SQL);
						stat.execute(insert);
					}
					catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
					
					finally
					{
						try {
							stat.close();
							conn.close();
						} catch (SQLException e1) {
							// TODO 自动生成的 catch 块
							e1.printStackTrace();
						}
					}
					
					RemindFrame.remind("开户成功！");
					usernameField.setText("");
					pswField1.setText("");
					pswField2.setText("");
					cardText.setText("");									
				}
				
			}
		});		
	}

}

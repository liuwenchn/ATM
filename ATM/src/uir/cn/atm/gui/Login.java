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
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;








import cn.uir.atm.sql.Con2sql;
import uir.cn.atm.utils.RemindFrame;
import uir.cn.atm.utils.ScreenSize;

public class Login 
{
	public static JFrame jf;
	
	JButton done,quit,create;
	public static JTextField usernamejtf;
	public static JPasswordField pswjpf;
	public static String ID;
	public static String username;
	public static int num;
	
	public Login()
	{
		jf=new JFrame("登录");
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
		JLabel usernamelabel=new JLabel("请输入用户名或账户ID：",JLabel.RIGHT);
		usernamelabel.setFont(new Font("楷体", Font.BOLD, 25));
		usernamejtf=new JTextField();
		usernamejtf.setFont(new Font("",Font.BOLD, 25));
		brick2.add(usernamelabel);
		JPanel subbrick=new JPanel();
		subbrick.setLayout(new GridLayout(1,2));
		
		subbrick.add(usernamejtf);
		subbrick.add(new JPanel());
		brick2.add(subbrick);
		
		JPanel brick3=new JPanel();
		brick3.setLayout(new GridLayout(1,2));
		JLabel pswlabel=new JLabel("请输入密码：",JLabel.RIGHT);
	    pswlabel.setFont(new Font("楷体",Font.BOLD, 25));
		pswjpf=new JPasswordField();
		pswjpf.setFont(new Font("", Font.BOLD, 25));
		brick3.add(pswlabel);
		JPanel subbrick2=new JPanel();
		subbrick2.setLayout(new GridLayout(1,2));
		
		subbrick2.add(pswjpf);
		subbrick2.add(new JPanel());
		brick3.add(subbrick2);
		
		JPanel brick4=new JPanel();
		brick4.setLayout(new GridLayout(1,5));
		done=new JButton("确认");		
		done.setFont(new Font("楷体",Font.BOLD, 25));
		
		create=new JButton("我要开户");
		create.setFont(new Font("楷体",Font.BOLD, 25));
		
		
		quit=new JButton("退出");
		quit.setFont(new Font("楷体",Font.BOLD, 25));
		
		
		brick4.add(new JPanel());
		brick4.add(create);
		
		brick4.add(done);
		brick4.add(quit);
		brick4.add(new JPanel());
		
		jf.add(brick1);
		jf.add(brick2);
		jf.add(brick3);
		jf.add(brick4);
		jf.validate();		
		
	}
	
	public void myEvent()
	{
		jf.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
								
				System.exit(0);
			}
		});
		
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				System.exit(0);
			}
		});
		
		create.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new CreateUser().myEvent();
				jf.setVisible(false);
			}
		});
		
		done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) 
			{
				
				if ("".equals(usernamejtf.getText())
						|| "".equals(new String(pswjpf.getPassword()))
						|| pswjpf.getPassword() == null) {
					JOptionPane.showConfirmDialog(
							jf, // 如果为null，此框架显示在中央，为jf则显示为jf的中央
							"用户名 或者 密码 不能为空！!\n请重新输入！", "错误",
							JOptionPane.DEFAULT_OPTION);
					usernamejtf.setText(null);
					pswjpf.setText(null);
					usernamejtf.requestFocus();// 光标回来
				} 
				else 
				{
					String passWord=String.valueOf(pswjpf.getPassword());
					
					ResultSet rs=selectSql();
					try {
							if(!rs.next())
							{
								RemindFrame.remind("用户名不存在！");
							}
							 
							rs.beforeFirst();  //复位结果集
							while(rs.next())
							{
									//System.out.println("111");												
									if(passWord.equals(rs.getString("password")))
									{
										username=rs.getString("username");
										ID=rs.getString("id");
										num=rs.getInt("num");
										jf.setVisible(false);
										new MainFrame().myEvent();									
									}
									
									else
									{									
										RemindFrame.remind("密码输入不正确，请重新输入！");
										pswjpf.setText("");									
									}													
							}
					} catch (SQLException e1) {
						// TODO 自动生成的 catch 块
						e1.printStackTrace();
					}
				}
			}			
		});
	}
	
	private static ResultSet selectSql() 
	{			
			String username=usernamejtf.getText();
		    Connection conn=null;
			Statement stat=null;
			ResultSet rs=null;			
			try 
			{
				conn=Con2sql.con2sql();				
				stat=conn.createStatement();			
				rs=stat.executeQuery("Select * from userinfo where username ='"+username+"';");
						
				
			} 
			catch (SQLException e1) 
			{
				// TODO 自动生成的 catch 块
				e1.printStackTrace();
			}
			
			return rs;
		}
	
}

package uir.cn.atm.gui;

import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import uir.cn.atm.utils.ScreenSize;

public class MainFrame 
{
	public static JFrame jf;
	JButton quKuan,cunKuan,zhuanZhang,yue,xiuGai,quXiao;
	
	JPanel jp1,jp2,jp3,jp4,brick1,brick2,brick3,brick4;
	JLabel welcome1,welcome2;
	JLabel yuE1=null;
	JLabel yuE2=null;
	JLabel userName1,userName2;
	
	public MainFrame()
	{		
		jf=new JFrame("主界面");
		jf.setBounds(ScreenSize.SCREENWIDTH/4, ScreenSize.SCREENHEIGHT/4,ScreenSize.SCREENWIDTH/2, ScreenSize.SCREENHEIGHT/2);
		jf.setVisible(true);
		quKuan=new JButton("取款");
		quKuan.setFont(new Font("楷体", Font.BOLD, 25));
		yue=new JButton("查询余额");
		yue.setFont(new Font("楷体", Font.BOLD, 25));
		xiuGai=new JButton("修改密码");
		xiuGai.setFont(new Font("楷体", Font.BOLD, 25));
		
		jp1=new JPanel();
		jp1.setLayout(new GridLayout(5,1));

		
		brick1=new JPanel();
		brick2=new JPanel();
		
		jp1.add(brick1);
		jp1.add(quKuan);
		jp1.add(yue);
		jp1.add(xiuGai);
		jp1.add(brick2);
		
		
		
		cunKuan=new JButton("存款");
		cunKuan.setFont(new Font("楷体", Font.BOLD, 25));
		zhuanZhang=new JButton("转账");
		zhuanZhang.setFont(new Font("楷体", Font.BOLD, 25));
		quXiao=new JButton("退出");
		quXiao.setFont(new Font("楷体", Font.BOLD, 25));
		
		brick3=new JPanel();
		brick4=new JPanel();
		
		
		jp2=new JPanel();				
		jp2.setLayout(new GridLayout(5,1));		
		jp2.add(brick3);		
		jp2.add(cunKuan);
		jp2.add(zhuanZhang);
		jp2.add(quXiao);
		jp2.add(brick4);
		
		
		
		jp3=new JPanel();
		jp4=new JPanel();
		
		Date now=new Date();
		SimpleDateFormat sdf1=new SimpleDateFormat("yyyy-MM-dd");
		
		
		welcome1=new JLabel("欢迎 "+Login.username,JLabel.RIGHT);
		welcome1.setFont(new Font("楷体", Font.BOLD, 25));
		
		userName1=new JLabel("当次登",JLabel.RIGHT);
		userName1.setFont(new Font("楷体", Font.BOLD, 25));
		
		yuE1=new JLabel(sdf1.format(now),JLabel.RIGHT);
		yuE1.setFont(new Font("楷体", Font.BOLD, 25));
		
		jp3.setLayout(new GridLayout(5,1));
		jp3.add(welcome1);
		jp3.add(userName1);
		jp3.add(yuE1);
		
		
		welcome2=new JLabel(" 先生",JLabel.LEFT);
		welcome2.setFont(new Font("楷体", Font.BOLD, 25));
		
		
		SimpleDateFormat sdf2=new SimpleDateFormat("HH:mm:ss");
		
		
		userName2=new JLabel("录时间:",JLabel.LEFT);
		
		
		userName2.setFont(new Font("楷体", Font.BOLD, 25));
		
		yuE2=new JLabel(sdf2.format(now),JLabel.CENTER);
		
		
		yuE2.setFont(new Font("楷体", Font.BOLD, 25));
		
		
		jp4.setLayout(new GridLayout(5,1));
		jp4.add(welcome2);
		jp4.add(userName2);
		jp4.add(yuE2);
		
		
		
		jf.setLayout(new GridLayout(1,4));
		jf.add(jp1);
		jf.add(jp3);
		jf.add(jp4);
		jf.add(jp2);
		
		jf.validate();
		 
		
	}
	
	
	public void myEvent()
	{
		quXiao.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.dispose();
				Login.jf.setVisible(true);
				Login.usernamejtf.setText("");
				Login.pswjpf.setText("");
			}
		});
		
		quKuan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				jf.setVisible(false);
				new QuKuan().myEvent();
				
			}
		});
		
		cunKuan.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				jf.setVisible(false);
				new CunKuan().myEvent();				
			}
		});
		
		zhuanZhang.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				jf.setVisible(false);
				new ZhuanZhang().myEvent();				
			}
		});
		
		yue.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e)
			{
				jf.setVisible(false);
				new YuE().myEvent();
				
			}
		});
		
		xiuGai.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				jf.setVisible(false);
				new XiuGai().myEvent();
				
																			
			}
		});
		
		jf.addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				jf.dispose();
				Login.jf.setVisible(true);
			}
		});
	}
	

}

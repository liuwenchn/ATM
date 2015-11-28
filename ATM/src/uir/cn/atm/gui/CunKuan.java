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
import uir.cn.atm.utils.ScreenSize;

public class CunKuan 
{
	JFrame jf;
	JLabel label;
	JTextField jtf;
	JPanel brick1,brick2,brick3,brick4;
	JPanel subBrick;
	JButton done,quit;
	

	
	public CunKuan()
	{
		jf=new JFrame("存款");
		jf.setBounds(ScreenSize.SCREENWIDTH/4, ScreenSize.SCREENHEIGHT/4,ScreenSize.SCREENWIDTH/2, ScreenSize.SCREENHEIGHT/2);
		jf.setVisible(true);
		
		jf.setLayout(new GridLayout(5, 1));
		
		brick1=new JPanel();
		brick2=new JPanel();
		
		brick3=new JPanel();
		brick3.setLayout(new GridLayout(1,2));
		
		
		label=new JLabel("请输入您的存款金额：",JLabel.RIGHT);
		label.setFont(new Font("楷体", Font.BOLD, 25));
		
		jtf=new JTextField();
		jtf.setFont(new Font("楷体", Font.BOLD, 25));
		
		subBrick=new JPanel();
		subBrick.setLayout(new GridLayout(1,2));
		subBrick.add(jtf);
		JLabel label2=new JLabel("元",JLabel.LEFT);
		label2.setFont(new Font("楷体", Font.BOLD, 25));
		subBrick.add(label2);
		
		brick3.add(label);
		brick3.add(subBrick);
		
		
		brick4=new JPanel();
		brick4.setLayout(new GridLayout(1,4));
		
		done=new JButton("确认");
		done.setFont(new Font("楷体", Font.BOLD, 25));
		quit=new JButton("上一步");
		quit.setFont(new Font("楷体", Font.BOLD, 25));
		
		brick4.add(new JPanel());
		brick4.add(done);
		brick4.add(quit);
		brick4.add(new JPanel());
		
		

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
		jf.add(brick3);
		jf.add(brick2);
		jf.add(brick4);
	}
	
	public void myEvent()
	{
		done.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.dispose();
				Connection conn=Con2sql.con2sql();
				
				if("".equals(jtf.getText())||jtf.getText()==null)
				{
					jf.dispose();
					MainFrame.jf.setVisible(true);
//					RemindFrame.remind/("没有成功存入！");
					return;
				}
				//String sql="UPDATE `atm`.`userinfo` SET `NUM`='"+(Login.num+Integer.parseInt(jtf.getText()))+"' WHERE `ID`='"+Login.ID+"' and`username`='"+Login.username+"';";
				String sql="UPDATE `atm`.`userinfo` SET `NUM`=`NUM`+'"+Integer.parseInt(jtf.getText())+"' WHERE `ID`='"+Login.ID+"' and`username`='"+Login.username+"';";
				
				try {
					Statement stat=conn.createStatement();				
					stat.execute(sql);
				} 				
				catch (SQLException e1) {
					// TODO 自动生成的 catch 块
					e1.printStackTrace();
				}
				
				
				MainFrame.jf.setVisible(true);
			}
		});
		quit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jf.dispose();
				
//				try {
//					rs.close();
//				} catch (SQLException e1) {
//					// TODO 自动生成的 catch 块
//					e1.printStackTrace();
//				}
				
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

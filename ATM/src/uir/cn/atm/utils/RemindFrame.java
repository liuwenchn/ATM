package uir.cn.atm.utils;


import java.awt.Font;
import java.awt.GridLayout;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class RemindFrame 
{
	public static void remind(String remindContext)
	{
		
		
		
		final JFrame jf = new JFrame("提醒");
		
		jf.setLayout(new GridLayout(4, 1));
		
		jf.setResizable(false);
		jf.setVisible(true);
		
		jf.setBounds(3*ScreenSize.SCREENWIDTH/8, 3*ScreenSize.SCREENHEIGHT/8,ScreenSize.SCREENWIDTH/4, ScreenSize.SCREENHEIGHT/4);
		
		//Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize(); //获取当前屏幕大小
		//Dimension frameSize = jf.getPreferredSize();//获取当前窗口大小
		
		//jf.setLocation((screenSize.width - frameSize.width)/2, (screenSize.height - frameSize.height)/2);
		
		JLabel jl = new JLabel(remindContext);
		jl.setFont(new Font("楷体", Font.BOLD, 25));
		
		JPanel jp1 = new JPanel();
		
		jp1.add(jl);
		
		JPanel jp2 = new JPanel();
		jp2.setLayout(new GridLayout(1,3));
		
		JButton jb = new JButton("确定");
		jb.setFont(new Font("楷体", Font.BOLD, 20));
		
		jp2.add(new JPanel());
		jp2.add(jb);
		jp2.add(new JPanel());
		
		jf.add(new JPanel());
		jf.add(jp1);
		jf.add(new JPanel());
		jf.add(jp2);
		
		
		jb.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				jf.dispose();
			}

		});
		
		
	}

}

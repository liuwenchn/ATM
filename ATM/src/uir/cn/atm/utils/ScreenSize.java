package uir.cn.atm.utils;

import java.awt.Dimension;
import java.awt.Toolkit;

public class ScreenSize 
{
	public static Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
	public static int SCREENWIDTH=screenSize.width;
	public static int SCREENHEIGHT=screenSize.height;

}

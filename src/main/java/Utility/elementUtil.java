package Utility;

import Base.base;

public class elementUtil extends base
{
	public static void acceptAlert()
	{
		getPage().onDialog(dialog -> {
			   try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 dialog.accept();
			System.out.println( dialog.message());
			 });
		 try {
			Thread.sleep(3000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}

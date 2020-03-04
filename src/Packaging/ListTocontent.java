package Packaging;

import java.util.ArrayList;

public class ListTocontent {
	
	Object[][] change(ArrayList<Object[]> newsList)
	{
		
		Object[][] datacontent=new Object[newsList.size()][3];
		for(int i=0;i<newsList.size();i++)
		{
			datacontent[i]=newsList.get(i);
		}
		return datacontent;
	}

}

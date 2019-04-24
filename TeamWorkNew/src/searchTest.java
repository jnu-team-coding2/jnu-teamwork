import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
public class searchTest {
	 AppFrame testframe=new AppFrame();
	public search searchtest=new search();
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		testframe.launch();
		String testKyword="Ùﬂƒœ¥Û—ß";
		testframe.searchText.setText(testKyword);
		testframe.searchButton.doClick();
		
		Object[][] search_result=testframe.tableData_result;
		for(int i=0;i<3;i++)
		{
			String result=search_result[i][0].toString();
			assertThat(result,containsString(testKyword));
		}
	}

}

package Test;

import junit.framework.TestCase;
import org.jsoup.nodes.Document;
import org.junit.Assert;

import School_office.GetScore;

public class GetScoreTest extends TestCase {
private static GetScore Jwxt=new GetScore("2016052374","h19980401",0);
    public void testGetTrain() {
        Document doc=Jwxt.getTrain();//获取成绩页面
        assertEquals("培养方案查询",doc.title());
    }

    public void testGetSelectTrain() {
        Document doc=Jwxt.getSelectTrain(2);//获取成绩页面
        assertEquals("培养方案查询",doc.title());
    }

    public void testGetScoredoc() {
        Document doc=Jwxt.getScoredoc();
        assertEquals("我的成绩",doc.title());
    }
}
package Test;

import junit.framework.TestCase;
import org.jsoup.nodes.Document;
import org.junit.Assert;

import School_office.GetScore;

public class GetScoreTest extends TestCase {
private static GetScore Jwxt=new GetScore("2016052374","h19980401",0);
    public void testGetTrain() {
        Document doc=Jwxt.getTrain();//��ȡ�ɼ�ҳ��
        assertEquals("����������ѯ",doc.title());
    }

    public void testGetSelectTrain() {
        Document doc=Jwxt.getSelectTrain(2);//��ȡ�ɼ�ҳ��
        assertEquals("����������ѯ",doc.title());
    }

    public void testGetScoredoc() {
        Document doc=Jwxt.getScoredoc();
        assertEquals("�ҵĳɼ�",doc.title());
    }
}
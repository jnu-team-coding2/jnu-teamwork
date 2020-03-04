package School_office;

import net.sourceforge.tess4j.Tesseract;
import net.sourceforge.tess4j.TesseractException;
import org.apache.commons.io.FileUtils;
import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.opencv.core.Core;
import org.opencv.core.CvType;
import org.opencv.core.Mat;
import org.opencv.core.Scalar;
import org.opencv.imgcodecs.Imgcodecs;
import org.opencv.imgproc.Imgproc;
import org.opencv.photo.Photo;

import javax.imageio.ImageIO;
import javax.print.Doc;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

public class GetScore  {
    private int status=-1;
    private Map<String,String> Cookie;
    private int trytime=3;//验证码重试次数
    private String Account;
    private String Password;
    static int a=1;
    
    
    
    public GetScore(String account,String password){
        this.Account=account;
        this.Password=password;
        Batchlogin();
    }
    public GetScore(String account,String password,int time)
    {
        this.Account=account;
        this.Password=password;
        this.trytime=time+1;
        Batchlogin();
    }
    /**重载构造函数，设置识别重试次数*/
    /**获取人才培养方案*/
    public Document getTrain(){
        if(this.status==-1){
            System.out.println("登陆错误");
            return null;
        }
        Document doc=null;
        try {
            doc=Jsoup.connect("https://jwxt.jnu.edu.cn/Secure/PaiKeXuanKe/wfrm_Xk_PyfaCx.aspx").cookies(this.Cookie).get();
        }catch (IOException e){
            e.printStackTrace();
        }
        //System.out.println(doc.body());
        return doc;
    }
    public Document getSelectTrain(int id){
        Document doc=getTrain();
        String __EVENTTARGET="grdvJhkc";
        String __EVENTARGUMENT="Page$"+id;
        String __VIEWSTATE=doc.getElementById("__VIEWSTATE").toString();
        String __VIEWSTATEGENERATOR=doc.getElementById("__VIEWSTATEGENERATOR").toString();
        String __EVENTVALIDATION=doc.getElementById("__EVENTVALIDATION").toString();
        String txtNjL=doc.getElementById("txtNjL").toString();
        String txtJdL=doc.getElementById("txtJdL").toString();
        String txtXzL=doc.getElementById("txtXzL").toString();
        String txtZsdxL=doc.getElementById("txtZsdxL").toString();
        String txtXyL=doc.getElementById("txtXyL").toString();
        String txtXxlbL=doc.getElementById("txtXxlbL").toString();
        String txtZyL=doc.getElementById("txtZyL").toString();
        String txtPyccL=doc.getElementById("txtPyccL").toString();
        String hidZx=doc.getElementById("hidZx").toString();
        String hidXh=doc.getElementById("hidXh").toString();
        String txtXiaoQu=doc.getElementById("txtXiaoQu").toString();
        //System.out.println(doc.body());

        Document document=null;
        Connection con=Jsoup.connect("https://jwxt.jnu.edu.cn/Secure/PaiKeXuanKe/wfrm_Xk_PyfaCx.aspx");
        con.data("__EVENTTARGET",__EVENTTARGET);
        con.data("__EVENTARGUMENT",__EVENTARGUMENT);
        con.data("__VIEWSTATE",getValue(__VIEWSTATE));
        con.data("__VIEWSTATEGENERATOR",getValue(__VIEWSTATEGENERATOR));
        con.data("__EVENTVALIDATION",getValue(__EVENTVALIDATION));
        con.data("txtNjL",getReadValue(txtNjL));
        con.data("txtJdL",getReadValue(txtJdL));
        con.data("txtXzL",getReadValue(txtXzL));
        con.data("txtZsdxL",getReadValue(txtZsdxL));
        con.data("txtZyfxL","");
        con.data("txtXyL",getReadValue(txtXyL));
        con.data("txtXxlbL",getReadValue(txtXxlbL));
        con.data("txtZyL",getReadValue(txtZyL));
        con.data("txtPyccL",getReadValue(txtPyccL));
        con.data("hidZx",getValue(hidZx));
        con.data("hidXh",getValue(hidXh));
        con.data("txtXiaoQu",getReadValue(txtXiaoQu));
        con.cookies(this.Cookie);
        try {
            document=con.post();
        }catch (IOException e){
            e.printStackTrace();
        }
        assert document !=null;
        //System.out.println(document.body());
        return document;
    }
    private String getValue(String value){
        return  value.substring(value.indexOf("value")+7,value.indexOf(">")-1);
    }
    private String getReadValue(String value)
    {
        return  value.substring(value.indexOf("value")+7,value.indexOf("r")-2);
    }      
    
    public Document getScoredoc(){
        if(this.status==-1){
            System.out.println("登陆错误");
            return null;
        }
        Document doc=null;
        try {
            doc=Jsoup.connect("https://jwxt.jnu.edu.cn/Secure/Cjgl/Cjgl_Cjcx_WdCj.aspx").cookies(this.Cookie).get();
        }catch (IOException e){
            e.printStackTrace();
        }

        assert doc != null;
        String VIEWSTATE=doc.getElementById("__VIEWSTATE").toString();
        String VIEWSTATEGENERATOR=doc.getElementById("__VIEWSTATEGENERATOR").toString();
        String EVENTVALIDATION=doc.getElementById("__EVENTVALIDATION").toString();
        String txtXH=doc.getElementById("txtXH").toString();
        String txtXM=doc.getElementById("txtXM").toString();
        String txtYXZY=doc.getElementById("txtYXZY").toString();


        Connection con=Jsoup.connect("https://jwxt.jnu.edu.cn/Secure/Cjgl/Cjgl_Cjcx_WdCj.aspx");
        con.data("__EVENTTARGET","lbtnQuery");
        con.data("__EVENTARGUMENT","");
        con.data("__VIEWSTATE",VIEWSTATE.substring(VIEWSTATE.indexOf("value")+7,VIEWSTATE.indexOf(">")-1));
        con.data("__VIEWSTATEGENERATOR",VIEWSTATEGENERATOR.substring(VIEWSTATEGENERATOR.indexOf("value")+7,VIEWSTATEGENERATOR.indexOf(">")-1));
        con.data("__EVENTVALIDATION",EVENTVALIDATION.substring(EVENTVALIDATION.indexOf("value")+7,EVENTVALIDATION.indexOf(">")-1));
        con.data("txtXH",txtXH.substring(txtXH.indexOf("value")+7,txtXH.indexOf("r")-1));
        con.data("txtXM",txtXM.substring(txtXM.indexOf("value")+7,txtXM.indexOf("r")-1));
        con.data("txtYXZY",txtYXZY.substring(txtYXZY.indexOf("value")+7,txtYXZY.indexOf("r")-1));
        con.data("rbtnListLBXX","历史成绩列表");
        con.data("ddlXXLB","主修");
        con.cookies(this.Cookie);
        try {
            doc=con.post();
        }catch (IOException e){
            e.printStackTrace();
        }
        //System.out.println(doc.body());
        return doc;
    }
    /**登录一次获取cookie*/
    private  void Batchlogin(){
        String loginurl="https://jwxt.jnu.edu.cn/Login.aspx";
        Connection.Response response=null;
        try {
              response=Jsoup.connect(loginurl).execute();
        }catch (Exception e){
            e.printStackTrace();
        }
        Map<String,String> cookie= null;Document doc=null;
        if (response != null) {
            cookie = response.cookies();
            doc=Jsoup.parse(response.body());
        }
        assert doc != null;
        String tmp=doc.getElementById("__VIEWSTATE").toString();
        String VIEWSTATE=tmp.substring(tmp.indexOf("value")+7,tmp.length()-2);

        tmp=doc.getElementById("__EVENTVALIDATION").toString();
        String EVENTVALIDATION=tmp.substring(tmp.indexOf("value")+7,tmp.length()-2);

        String VIEWSTATEGENERATOR="C2EE9ABB";

        tmp=doc.getElementById("btnLogin").toString();
        String btnLogin=tmp.substring(tmp.indexOf("value")+7,tmp.indexOf("onclick")-2);
        //System.out.println(cookie.toString().substring(1,cookie.toString().length()-1));
        String Cookies=cookie.toString().substring(1,cookie.toString().length()-1);
        String code=null;
        for (int i = 0; i < this.trytime; i++) {
            try {
                code = requestcode(Cookies, i==this.trytime-1?1:0);
            } catch (IOException e) {
                e.printStackTrace();
            }
            //System.out.println(code);
            //尝试一次登陆
            int status = checkLogin(VIEWSTATE, EVENTVALIDATION, btnLogin, this.Account, this.Password, cookie, code);
            if (status == 0) {
                this.status = 0;
                this.Cookie = cookie;
                break;
            }
        }
    }

    private int checkLogin(String VIEWSTATE,String EVENTVALIDATION,String btnLogin,String Account,String Password,Map<String,String> cookie,String code)
    {
        Connection con=Jsoup.connect("https://jwxt.jnu.edu.cn/Login.aspx");
        con.data("__VIEWSTATE",VIEWSTATE);
        con.data("__VIEWSTATEGENERATOR","C2EE9ABB");
        con.data("__EVENTVALIDATION",EVENTVALIDATION);
        con.data("txtYHBS",Account);
        con.data("txtYHMM",Password);
        con.data("btnLogin",btnLogin);
        con.data("txtFJM",code);
        Connection.Response response=null;
        con.cookies(cookie);
        try {
            response=con.method(Connection.Method.POST).execute();
        }catch (IOException e){
            e.printStackTrace();
        }
        assert response != null;
        int status=RequestStatus(Jsoup.parse(response.body()));
        if(status==0)
            return 0;
        else
            return -1;
    }
    private  int RequestStatus(Document doc){
        if(doc.title().equals("整体架构")){
            //JOptionPane.showMessageDialog(null, "登入成功");
            return 0;
        }
        else {
        String script=doc.select("script").get(0).toString();
        //System.out.println(script);
//        JOptionPane.showMessageDialog(null,"登陆错误");
        return -1;
        }
    }
    /**请求验证码
     * status:0->验证码识别
     * status:1->手动识别*/
    private   String requestcode(String cookie,int status)throws IOException{
        CloseableHttpClient httpclient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet("https://jwxt.jnu.edu.cn/ValidateCode.aspx");
        httpGet.addHeader("cookie",cookie);
        CloseableHttpResponse response1 = httpclient.execute(httpGet);
        try {
            HttpEntity entity1 = response1.getEntity();
            InputStream inputStream=entity1.getContent();//请求的内容的字
            FileUtils.copyToFile(inputStream, new File("code//code.png"+a));//字节流转存为图片
            
            BufferedImage img= ImageIO.read(entity1.getContent());
            //EntityUtils.consume(entity1);
            // System.out.println(entity1.getContentType().getElements()[0]);
        }
        finally {
            response1.close();
        }
        if(status==0)
        {
            processimg();
            return getcode();
        }
        else
            return showDialog();
        //processimg();//图像处理
        //return getcode();//验证码识别
    }
    /**图像处理：修复+二值化*/
    private  void processimg(){
        String originalImgPath = "code//code.png";
        System.load(System.getProperty("user.dir").replaceAll("\\\\","//")+"//dll//opencv_java410.dll");
        Mat img = Imgcodecs.imread(originalImgPath);
        Mat imgHSV = new Mat(img.rows(), img.cols(), CvType.CV_8U);
        //RGB->HSV
        Imgproc.cvtColor(img, imgHSV, Imgproc.COLOR_BGR2GRAY);
        Scalar minValues = new Scalar(90, 0, 46);
        Scalar maxValues = new Scalar(180, 43, 220);
        Mat mask = new Mat();
        Core.inRange(imgHSV, minValues, maxValues, mask);//实现二值化
        Imgcodecs.imwrite("code//code1.png",mask);
//        Mat tmp=new Mat();img.copyTo(tmp,mask);
//        Mat redmask=new Mat();
//        Core.bitwise_and(img,tmp,redmask);
//        Imgcodecs.imwrite("code//code2.png",redmask);
        Mat inpaintmask=Mat.zeros(mask.size(),CvType.CV_8U);//修复模板的归零化
        Mat repair=new Mat();
        Photo.inpaint(img,mask,repair,0.1, Photo.INPAINT_NS  );//图像修复
        Imgcodecs.imwrite("code//code2.png",repair);

        Imgproc.cvtColor(repair, imgHSV, Imgproc.COLOR_BGR2GRAY);
         minValues = new Scalar(0, 0, 46);
         maxValues = new Scalar(90, 43, 220);
        Core.inRange(imgHSV, minValues, maxValues, mask);//实现二值化
        Imgcodecs.imwrite("code//code3.png",mask);


    }
    /**验证码识别转换为String*/
    private   String getcode () {
        Tesseract instance = new Tesseract();
        instance.setLanguage("eng");//chi_sim ：简体中文， eng	根据需求选择语言库
        String result = null;
        try {
            long startTime = System.currentTimeMillis();
            File file1=new File("code//code3.png");

            result =  instance.doOCR(file1);
            long endTime = System.currentTimeMillis();
            //System.out.println("Time is：" + (endTime - startTime) + " 毫秒");
        } catch (TesseractException e) {
            e.printStackTrace();
        }
        //System.out.println("result: ");
       // System.out.println(result);
        return result;
    }
    /**弹出输入框*/
    private  String showDialog() {
    	
        Icon icon=new ImageIcon("code//code.png"+a);
       
        Object strid=JOptionPane.showInputDialog(null,"请输入验证码","验证码识别失败",JOptionPane.PLAIN_MESSAGE,icon,null,null);
        a++;
        
        if (strid == null) {
            return "fail";
        }
        else {
            return strid.toString();
        }
    }
}

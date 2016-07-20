package test;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import com.bruce.ftp.client.FtpUtil;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "file:WebContent/WEB-INF/applicationContext.xml" })
public class ftpTest {
	@Autowired
	private FtpUtil ftpUtil;
	
	//上传本地文件到服务器，测试成功。
	@Test  
	public void testUpload(){
	    try {  
	        FileInputStream in=new FileInputStream(new File("D:/ftp/xsprivateMovie.apk"));
	        boolean flag = ftpUtil.uploadFile("192.168.2.181", 21, "root", "123456", "", "xsprivateMovie.apk", in);  
	        System.out.println(flag);
	    } catch (FileNotFoundException e) {
	        e.printStackTrace();
	    }  
	}
	
	//在FTP服务器上生成一个文件，并将一个字符串写入到该文件中
	@Test  
	public void testUpLoadFromString(){
	    try {  
	        InputStream input = new ByteArrayInputStream("test ftp".getBytes("utf-8"));
	        boolean flag = ftpUtil.uploadFile("192.168.2.181", 21, "test", "test", "D:/ftp", "test.txt", input);
	        System.out.println(flag);
	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    }  
	}
	
	//下载文件，测试成功。
	@Test  
	public void testDownLoad(){
	    try {  
	        InputStream input = new ByteArrayInputStream("test ftp".getBytes("utf-8"));
	        boolean flag = false;
			try {
				flag = ftpUtil.downFile("192.168.2.181", 21, "root", "123456", "", "ftp20160619.txt", "D:/ftp/");
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        System.out.println(flag);
	    } catch (UnsupportedEncodingException e) {
	        e.printStackTrace();
	    }  
	}
}

package com.bruce.ftp.client;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.text.SimpleDateFormat;

import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPFile;
import org.apache.commons.net.ftp.FTPReply;
import org.apache.ftpserver.ftplet.FtpException;
import org.apache.ftpserver.ftplet.User;
import org.apache.ftpserver.impl.DefaultFtpServer;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.bruce.daoInterface.LogManager;

public class FtpUtil {
	/** 
	 * Description: 向FTP服务器上传文件 
	 * @Version1.0 Jul 27, 2008 4:31:09 PM by 崔红保（cuihongbao@d-heaven.com）创建 
	 * @param url FTP服务器hostname 
	 * @param port FTP服务器端口 
	 * @param username FTP登录账号 
	 * @param password FTP登录密码 
	 * @param path FTP服务器保存目录 
	 * @param filename 上传到FTP服务器上的文件名 
	 * @param input 输入流 
	 * @return 成功返回true，否则返回false 
	 */ 
	final static Logger logger = Logger.getLogger(FtpUtil.class.getName());
	private final static String FTPUSER_STRING = "root";

	
	public static boolean uploadFile(String url,int port,String username, String password, String path, String filename, InputStream input) {  
	    boolean success = false;  
	    FTPClient ftp = new FTPClient();  
	    try {  
	        int reply;  
	        ftp.connect(url, port);//连接FTP服务器  
	        //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器  
	        ftp.login(username, password);//登录  
	        reply = ftp.getReplyCode();  
	        if (!FTPReply.isPositiveCompletion(reply)) {  
	            ftp.disconnect();  
	            return success;  
	        }  
	       // ftp.changeWorkingDirectory(path);  
	        ftp.storeFile(filename, input);           
	          
	        input.close();  
	        ftp.logout();  
	        success = true;  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    } finally {  
	        if (ftp.isConnected()) {  
	            try {  
	                ftp.disconnect();  
	            } catch (IOException ioe) {  
	            }  
	        }  
	    }  
	    return success;  
	}
	
	
	/** 
	 * Description: 从FTP服务器下载文件 
	 * @Version1.0 Jul 27, 2008 5:32:36 PM by 崔红保（cuihongbao@d-heaven.com）创建 
	 * @param url FTP服务器hostname 
	 * @param port FTP服务器端口 
	 * @param username FTP登录账号 
	 * @param password FTP登录密码 
	 * @param remotePath FTP服务器上的相对路径 
	 * @param fileName 要下载的文件名 
	 * @param localPath 下载后保存到本地的路径 
	 * @return 
	 */  
	public boolean downFile(
			String url, int port,String username, String password, String remotePath,
			String remotfileName,String localPath) throws IOException{
		
		DownloadStatus result = DownloadStatus.Download_New_Failed;
		
		
	    boolean success = false;  
	    FTPClient ftpClient = new FTPClient();
	    
		FTPFile[] files = ftpClient.listFiles(new String(remotfileName
				.getBytes("UTF-8"), "iso-8859-1"));
		if (files.length == 0) {
			logger.debug("no files exist");
			result = DownloadStatus.Remote_File_Noexist;
			return false;
		}
		
		// create socket
		Socket socketClient = null;
		PrintWriter pwPrintWriter = null;
		if (url != null && url.length() > 1) {
			socketClient = SocketClientUtil.createSocketClient(url);
			pwPrintWriter = SocketClientUtil.createPrintWriter(socketClient);
		}
		long lRemoteSize = files[0].getSize();
		long generatedTime = files[0].getTimestamp().getTimeInMillis();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String date_folder = sdf.format(generatedTime);
		//create time folder
//		String absolutLoaclPath = getFtpUserHome() + File.separator + "test";
//		String relativePath = File.separator + "test" + File.separator + date_folder + File.separator;
//		File pathFile = new File(absolutLoaclPath+File.separator+date_folder);
//		pathFile.mkdirs();
		
	    try {  
	        int reply;  
	        ftpClient.connect(url, port);  
	        //如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器  
	        ftpClient.login(username, password);//登录  
	        reply = ftpClient.getReplyCode();  
	        if (!FTPReply.isPositiveCompletion(reply)) {  
	        	ftpClient.disconnect();  
	            return success;  
	        }
	        
	        //转移到FTP服务器目录
	        ftpClient.changeWorkingDirectory(remotePath);  
	        
	        FTPFile[] fs = ftpClient.listFiles();
	        for(FTPFile ff:fs){  
	            if(ff.getName().equals(remotfileName)){  
	                File localFile = new File(localPath+"/"+ff.getName());
	                  
	                OutputStream is = new FileOutputStream(localFile);
	                ftpClient.retrieveFile(ff.getName(), is);
	                is.close();
	            }  
	        }
	          
	        ftpClient.logout();
	        success = true;
	    } catch (IOException e) {  
	        e.printStackTrace();
	    } finally {  
	        if (ftpClient.isConnected()) {  
	            try {  
	            	ftpClient.disconnect();
	            } catch (IOException ioe) {  
	            }  
	        }  
	    }  
	    return success;
	}
}

package com.bruce.ftp.client;

public enum DownloadStatus {
	Remote_File_Noexist("remote file not exist",false), 
	Local_Bigger_Remote("local file is bigger than the remote one",false), 
	Download_From_Break_Success("download from breakpoin successfully",true),
	Download_From_Break_Failed("download from breakpoin failed",false), 
	Download_New_Success("download new file successfully",true), 
	Download_New_Failed("download new file failed",false); 

	private String description;
	private boolean success;
	
	 private  DownloadStatus(String description,boolean success) {  
	        this.description = description;  
	        this.success = success;
	    }  

	public String toString() {
		return description+" download "+(success?"success":"failed");
	}
	
	public boolean getSuccess(){
		return success;
	}
}
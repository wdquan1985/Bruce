package com.bruce.ftp.client;

import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

public class SocketClientUtil {

	// 创建socket

	public static Socket createSocketClient(String IP) {
		
		Socket socket = null;
		try {
			socket = createSocketClient(IP, PORT);			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return socket;
	}

	public static Socket createSocketClient(String IP, int port) {
		Socket socketClient = null;
		try {
			socketClient = new Socket(IP, port);
			socketClient.setKeepAlive(true);

		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return socketClient;
	}

	// 断开socket
	public static void disconnectSocketClient(Socket socketClient) {
		try {
			if (socketClient != null) {
				socketClient.close();
			}
		} catch (Exception ex) {

		}
	}

	// 创建 PrintWriter
	public static PrintWriter createPrintWriter(Socket socketClient) {
		OutputStream out = null;
		PrintWriter pw = null;
		try {
			out = socketClient.getOutputStream();
			pw = new PrintWriter(out);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return pw;
	}

	// close PrintWriter
	public static void closePrintWriter(PrintWriter printWriter) {
		try {
			if (printWriter != null) {
				printWriter.close();
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// 发送 数据
	public static void sendDataBySocket(PrintWriter printWriter, String message) {
		try {
			if (printWriter == null)
				return;
			printWriter.println(message);
			printWriter.flush();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public static final int PORT = 9999;
}

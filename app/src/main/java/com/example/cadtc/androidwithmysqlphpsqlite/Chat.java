package com.example.cadtc.androidwithmysqlphpsqlite;

import android.app.Fragment;
import android.os.Bundle;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.InetAddress;
import java.net.Socket;


public class Chat extends Fragment {
	public static Handler mHandler = new Handler();
	TextView TextView01;	// 用來顯示文字訊息
	EditText EditText01;	// 文字方塊
	EditText EditText02;	// 文字方塊
	String tmp;				// 暫存文字訊息
	Socket clientSocket;	// 客戶端socket
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
							 Bundle savedInstanceState) {
		//填滿此片段的佈局
		return inflater.inflate(R.layout.chat, container, false);
	}
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onActivityCreated(savedInstanceState);
		// 從資源檔裡取得位址後強制轉型成文字方塊
		TextView01 = (TextView)  getView().findViewById(R.id.TextView01);
		EditText01=(EditText)  getView().findViewById(R.id.EditText01);
		EditText02=(EditText)  getView().findViewById(R.id.EditText02);

		// 以新的執行緒來讀取資料
		Thread t = new Thread(readData);

		// 啟動執行緒
		t.start();

		// 從資源檔裡取得位址後強制轉型成按鈕
		Button button1=(Button) getView(). findViewById(R.id.Button01);

		// 設定按鈕的事件
		button1.setOnClickListener(new Button.OnClickListener() {


			// 當按下按鈕的時候觸發以下的方法
			@Override
			public void onClick(View v) {
				// 如果已連接則
				if(clientSocket.isConnected()){

					BufferedWriter bw;

					try {
						// 取得網路輸出串流
						bw = new BufferedWriter( new OutputStreamWriter(clientSocket.getOutputStream()));

						// 寫入訊息
						bw.write(EditText01.getText()+":"+EditText02.getText()+"\n");

						// 立即發送
						bw.flush();
					} catch (IOException e) {

					}
					// 將文字方塊清空
					EditText02.setText("");
				}
			}
		});

	}

	// 顯示更新訊息
	private Runnable updateText = new Runnable() {
		public void run() {
			// 加入新訊息並換行
			TextView01.append(tmp + "\n");
		}
	};

	// 取得網路資料
	private Runnable readData = new Runnable() {
		public void run() {
			// server端的IP
			InetAddress serverIp;

			try {
				// 以內定(本機電腦端)IP為Server端
				serverIp = InetAddress.getByName("192.168.1.111");
				int serverPort = 5050;
				clientSocket = new Socket(serverIp, serverPort);

				// 取得網路輸入串流
				BufferedReader br = new BufferedReader(new InputStreamReader(
						clientSocket.getInputStream()));

				// 當連線後
				while (clientSocket.isConnected()) {
					// 取得網路訊息
					tmp = br.readLine();

					// 如果不是空訊息則
					if(tmp!=null)
						// 顯示新的訊息
						mHandler.post(updateText);
				}

			} catch (IOException e) {

			}
		}
	};

}
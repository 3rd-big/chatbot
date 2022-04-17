package com.hecto.chatbot.service;

import java.io.*;
import java.net.Socket;

public class EchoClient {
    public static void main(String[] args) throws IOException {
        Socket clientSocket = new Socket("127.0.0.1", 9000);
        // 서버에 전송할 문자열을 입력받기 위해 객체 생성
        InputStreamReader ink = new InputStreamReader(System.in);
        BufferedReader keyboard = new BufferedReader(ink);


        //서버에 내용을 전송할 객체
        OutputStream os = clientSocket.getOutputStream();
        OutputStreamWriter osr = new OutputStreamWriter(os);
        PrintWriter pw = new PrintWriter(osr);

        //서버에서 재전송한 내용을 받는 객체
        InputStream is = clientSocket.getInputStream();
        InputStreamReader isr = new InputStreamReader(is);
        BufferedReader br = new BufferedReader(isr);

        String str = "";
        while (true) {
            System.out.println(br.readLine());
            //System.out.println("서버에 전송할 메세지 입력(quit입력시 종료) : ");
            String line = keyboard.readLine();
            if (line.equals("quit")) {
                System.out.println("서버와의 연결을 종료 합니다.");
                clientSocket.close();
                break;
            }
            pw.println(line);
            pw.flush();
        }
    }
}

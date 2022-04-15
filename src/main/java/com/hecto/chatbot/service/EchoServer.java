package com.hecto.chatbot.service;


import com.hecto.chatbot.domain.Member;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class EchoServer {
    List<Member> memberList = new ArrayList<>();

    public final void ReadyCommunication() throws IOException {
        ServerSocket serverSocket = new ServerSocket(9000);

        // accept() 호출 시  대기하고 있다가
        // 클라이언트 접속 시 accept()는 새로운 연결 소켓을 반환
        System.out.println("Wait client");
        Socket conSocket = serverSocket.accept();

        // 상대방의 연결 정보 확인
        InetAddress inetAddress = conSocket.getInetAddress();
        System.out.println(inetAddress.getHostAddress() + " connect");


        // 연결 소켓으로 통신
        // 전송 스트림
        OutputStream out = conSocket.getOutputStream();
        OutputStreamWriter outW = new OutputStreamWriter(out);
        PrintWriter pw = new PrintWriter(outW);

        // 수신 스트림
        InputStream in = conSocket.getInputStream();
        InputStreamReader inR = new InputStreamReader(in);
        BufferedReader br = new BufferedReader(inR);

        String str = br.readLine();
        if (memberCheck(str) == true) {
            pw.println("아이디를 입력해주세요.");
            pw.flush();
            String id = br.readLine();
            pw.println("비밀번호를 입력해주세요.");
            pw.flush();
            String password = br.readLine();
            login(id, password);
        }
        if (memberCheck(str) == false) {
            pw.println("회원가입할 아이디를 입력");
            pw.flush();
            String id = br.readLine();
            pw.println("비밀번호 입력");
            String password = br.readLine();
            join(id,password);
        }

        // 송수신
        while (true) {

            String line = br.readLine();

            if (line == null) {
                System.out.println("Client Disconnect!");
                break;
            }

            System.out.println("Received : " + line);
            pw.println(line); // 클라이언트에 돌려줌
            pw.flush(); // 버퍼에 있는 데이터 즉시 전송

        }
        // 스트림 종료
        pw.close();
        br.close();
        System.out.println("연결 종료");

    }

    private final void join(String id, String password) {
        duplicationCheck(id);
    }


    private final boolean memberCheck(String str) {

        if (str.equals("1")) {
            return true;
        }
        if (str.equals("2")) {
            return false;
        }

    }

    private final Member login(String id, String password) {

    }

    private final void joinMember() {

    }
    private final void loadMembersId(){
        memberList
    }
}

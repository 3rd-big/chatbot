package com.hecto.chatbot.service;


import com.hecto.chatbot.domain.Member;
import com.hecto.chatbot.repository.MemberReadFile;
import com.hecto.chatbot.repository.MemberWriteFile;

import java.io.*;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class EchoServer {
    List<Member> memberList = new ArrayList<>();
    Member member;
    PrintWriter pw;
    BufferedReader br;


    public final void ReadyCommunication() throws IOException {

        LoadingLoginFile();

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
        pw = new PrintWriter(outW);

        // 수신 스트림
        InputStream in = conSocket.getInputStream();
        InputStreamReader inR = new InputStreamReader(in);
        br = new BufferedReader(inR);

        pw.println("로그인은1번, 회원가입은 2번");
        pw.flush();
        String str = SelectLoginOrJoin(br.readLine());
        while (true) {
            String id;
            if (str.equals("1")) {
                String password;
                do {
                    pw.println("아이디를 입력해주세요.");
                    pw.flush();
                    id = br.readLine();
                    pw.println("비밀번호를 입력해주세요.");
                    pw.flush();
                    password = br.readLine();

                } while (!login(id, password));
                break;
            }
            if (str.equals("2")) {
                while (true) {
                    pw.println("회원가입할 아이디를 입력");
                    pw.flush();
                    id = br.readLine();
                    if (idCheck(id)) break;
                }
                pw.println("비밀번호 입력");
                pw.flush();
                String password = br.readLine();
                pw.println("이름 입력");
                pw.flush();
                String name = br.readLine();
                pw.println("전화번호 입력");
                pw.flush();
                String mobileNumber = br.readLine();
                memberList.add(Member.joinMember(id, password, name, mobileNumber));
                member = memberList.get(memberList.size() - 1);
                pw.println("회원가입완료");
                pw.flush();
                MemberWriteFile.memberAddFile(member);
                break;
            }
        }

        // 윤범님 소스 추가

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

    private String SelectLoginOrJoin(String str) throws IOException {
        while (true) {
            if (str.equals("1") || str.equals("2")) return str;
            pw.println("로그인은1번, 회원가입은 2번");
            pw.flush();
            str = br.readLine();

        }
    }

    private void LoadingLoginFile() {
        MemberReadFile file = new MemberReadFile();
        file.ReadTextFile();
        memberList = file.saveData();
    }

    private boolean idCheck(String id) {
        for (Member member : memberList
        ) {
            if (!member.duplicationCheck(id)) {
                pw.append(id + " 는 이미 존재하는 ID 입니다.  ");
                return false;
            }
        }
        pw.append(id + " 는 사용 가능한 ID 입니다.  ");
        return true;
    }


    private Boolean login(String id, String password) {
        for (Member member : memberList
        ) {
            if (member.getId().equals(id)) {
                if (member.getPassword().equals(password)) {
                    this.member = member;
                    pw.println(id + " 로그인 성공");
                    pw.flush();
                    return true;
                }
                pw.append("비밀번호 잘못 입력 ");

                return false;
            }
        }
        pw.append("존재하지 않는 아이디 입니다. ");
        pw.flush();
        return false;
    }

}

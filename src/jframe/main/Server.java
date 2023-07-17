package jframe.main;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Server implements Runnable {
    // Server클래스: 소켓을 통한 접속서비스, 접속클라이언트 관리

    List<Service> all; // 모든 사용자(대기실사용자 + 대화방사용자)
    List<Service> wait; // 대기실 사용자
    List<Room> room; // 개설된 대화방 Room-vs(Vector) : 대화방사용자
    Socket socket;
    ServerSocket serverSocket;

    public Server() {
        all = new ArrayList<>();
        wait = new ArrayList<>();
        room = new ArrayList<>();

        new Thread(this).start();
    }// 생성자

    @Override
    public void run() {
        try {
            serverSocket = new ServerSocket(5509); // 현재 실행중인 ip + 명시된 port ----> 소켓서비스
            System.out.println("Start Server.......");
            while (true) {
                socket = serverSocket.accept(); // 클라이언트 접속 대기
                // s: 접속한 클라이언트의 소켓정보
                Service ser = new Service(socket, this);
                all.add(ser); // 전체 사용자 등록
                wait.add(ser); // 대기실사용자에 등록
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }// run

    public static void main(String[] args) {
        new Server();
    }
}

package jframe.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class Service extends Thread {
    Room myRoom;

    BufferedReader in;
    OutputStream out;
    List<Service> all;
    List<Service> wait;
    List<Room> roomSer;
    Socket socket;
    String nickName;

    public Service(Socket socket, Server server) {
        all = server.all;
        wait = server.wait;
        roomSer = server.room;
        this.socket = socket;
        try {
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            out = socket.getOutputStream();
            start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        try {
            while (true) {
                String msg = in.readLine();
                if (msg == null)
                    return;
                if (msg.trim().length() > 0) {
                    System.out.println("from Client: " + msg + ":" + socket.getInetAddress().getHostAddress());
                    String msgs[] = msg.split("\\|");
                    String protocol = msgs[0];

                    switch (protocol) {
                        case "100":
                            all.add(this);
                            wait.add(this);
                            break;

                        case "150":
                            nickName = msgs[1];
                            messageWait("160|" + getRoomInfo());
                            messageWait("180|" + getWaitInwon());
                            break;

                        case "160":
                            myRoom = new Room();
                            myRoom.title = msgs[1];
                            myRoom.count = 1;
                            myRoom.boss = nickName;
                            roomSer.add(myRoom);

                            wait.remove(this);
                            myRoom.user.add(this);
                            messageRoom("200|" + nickName);

                            messageWait("160|" + getRoomInfo());
                            messageWait("180|" + getWaitInwon());
                            break;

                        case "170":
                            messageTo("170|" + getRoomInwon(msgs[1]));
                            break;

                        case "175":
                            messageRoom("175|" + getRoomInwon());
                            break;

                        case "200":
                            String roomTitle[] = msg.split("\\|");
                            String room = roomTitle[1];
                            for (int i = 0; i <roomSer.size(); i++) {
                                Room r = roomSer.get(i);
                                if (r.title.equals(room)) {
                                    myRoom = r;
                                    myRoom.count++;
                                    break;
                                }
                            }

                            wait.remove(this);
                            myRoom.user.add(this);
                            messageRoom("200|" + nickName);

                            messageTo("202|" + myRoom.title);
                            messageWait("160|" + getRoomInfo());
                            messageWait("180|" + getWaitInwon());
                            break;

                        case "300":
                            messageRoom("300|[" + nickName + "] ▶▶▶" +msgs[1]);
                            break;

                        case "400":
                            myRoom.count--;
                            messageRoom("400|" + nickName);

                            myRoom.user.remove(this);
                            wait.add(this);

                            messageRoom("175|" + getRoomInwon());
                            messageWait("160|" + getRoomInfo());
                            break;
                    }
                }
            }

        } catch (IOException e) {
            System.out.println("★");
            e.printStackTrace();
        }
    }

    public String getRoomInfo() {
        String str = "";
        for (int i = 0; i < roomSer.size(); i++) {
            Room r = roomSer.get(i);
            str += r.title + "--" + r.count;
            if (i < roomSer.size() - 1)
                str += ",";
        }
        return str;
    }

    public String getRoomInwon() {
        String str = "";
        for (int i = 0; i < myRoom.user.size(); i++) {
            Service ser = myRoom.user.get(i);
            str += ser.nickName;
            if (i < myRoom.user.size() - 1)
                str += ",";
        }
        return str;
    }

    public String getRoomInwon(String title) {
        String str = "";
        for (int i = 0; i < roomSer.size(); i++) {
            Room room =roomSer.get(i);
            if (room.title.equals(title)) {
                for (int j = 0; j < room.user.size(); j++) {
                    Service ser = room.user.get(j);
                    str += ser.nickName;
                    if (j < room.user.size() - 1)
                        str += ",";
                }
                break;
            }
        }
        return str;
    }

    public String getWaitInwon() {
        String str = "";
        for (int i = 0; i < wait.size(); i++) {
            Service ser = wait.get(i);
            str += ser.nickName;
            if (i < wait.size() - 1)
                str += ",";
        }
        return str;
    }

    public void messageAll(String msg) {
        for (int i = 0; i < all.size(); i++) {
            Service service = all.get(i);
            try {
                service.messageTo(msg);
            } catch (IOException e) {
                all.remove(i--);
                System.out.println("클라이언트 접속 끊음!!");
            }
        }
    }

    public void messageWait(String msg) {
        for (int i = 0; i < wait.size(); i++) {
            Service service = wait.get(i);
            try {
                service.messageTo(msg);
            } catch (IOException e) {
                wait.remove(i--);
                System.out.println("클라이언트 접속 끊음!!");
            }
        }
    }

    public void messageRoom(String msg) {
        for (int i = 0; i < myRoom.user.size(); i++) {
            Service service = myRoom.user.get(i);
            try {
                service.messageTo(msg);
            } catch (IOException e) {
                myRoom.user.remove(i--); // 예외 발생시 my
                System.out.println("클라이언트 접속 끊음!!");
            }
        }
    }

    public void messageTo(String msg) throws IOException {
        out.write((msg + "\n").getBytes());
    }
}

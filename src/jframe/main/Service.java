package jframe.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import javax.sound.midi.Soundbank;
import javax.swing.JOptionPane;

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
						myRoom.count++;
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
						for (int i = 0; i < roomSer.size(); i++) {
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
						messageRoom("300|[" + nickName + "]  " + msgs[1]);
						break;

					case "310": // 귓속말 대화
						String targetUser = msgs[1];
						String whisperMsg = msgs[2];
						Service targetService = null;

						// 대상 사용자 찾기
						for (Service service : myRoom.user) {
							if (service.nickName.equals(targetUser)) {
								targetService = service;
								break;
							}
						}
						if (targetService != null) {
					        // 귓속말 타켓 서비스(클라이언트 소켓) 에게 전달
					        messageTo(targetService, "320|[귓속말 -"+ nickName +"] "+ whisperMsg + "\n");
					        // 귓속말 보내는 사람에 채팅창에도 뜨게 만듦
					        messageTo(this, "320|[귓속말 -"+ nickName +"] " + whisperMsg + "\n");
					    } else {
				
					        messageTo(this, "320|귓속말 대상 유저를 찾을 수 없습니다.");
					    }
					    break;
					    
					    
					case "600": // 강퇴
						
						String target = msgs[1];
						
						Service targetServ = null;

						// 대상 사용자 찾기
						for (Service service : myRoom.user) {
							if (service.nickName.equals(target)) {
								targetServ = service;
								break;
							}
						}
						if (targetServ != null) {
					        messageTo(targetServ, "610|"); // 강퇴당한 유저
					       
					        disconnect(targetServ); // 사용자를 강퇴 처리
					        
					    }
						messageRoom("701|" + target);
						
					    break;
					
						
					case "700":
						String target2 = msgs[1];
						Service targetServ2 = null;

						// 대상 사용자 찾기
						for (Service service : myRoom.user) {
							if (service.nickName.equals(target2)) {
								targetServ2 = service;
								myRoom.user.remove(targetServ2);
								
								break;
							} if (targetServ2 != null) {
						        myRoom.count--;
						        messageRoom("175|" + getRoomInwon());
						        messageWait("160|" + getRoomInfo());
						        messageRoom("701|" + target2);
						    }
							
						}

						break;
					
					case "400":
					    System.out.println(msgs[1] + "server");
					    System.out.println("방인원 -> " + myRoom.count);
					   
					      if (myRoom.count >= 1) {
					        myRoom.count--;
					        messageRoom("400|" + nickName); // 다른 사용자들에게 퇴장 메시지 전송
					        myRoom.user.remove(this); // 사용자를 방에서 제거
					        wait.add(this); // 대기방 목록에 사용자 추가
					        messageRoom("175|" + getRoomInwon()); // 방의 사용자 목록을 업데이트
					        messageWait("160|" + getRoomInfo()); // 대기방의 방 목록을 업데이트
					        if (myRoom.count == 0) {
						        System.out.println(msgs[1]);
						        myRoom.user.remove(this);
						        messageRoom("175|" + getRoomInwon());
						       // messageRoom("165|" + msgs[1]); // 남은 사용자에게 방이 삭제되었음을 알림
						        roomSer.remove(myRoom); // 방 목록에서 해당 방을 삭제
						        myRoom = null;
						        messageWait("165|" + msgs[1]);
						        messageWait("160|" + getRoomInfo()); // 대기방의 방 목록을 업데이트
					        }
					    }

					    // 방에 남은 유저가 없으면 방을 삭제하고 모든 클라이언트에게 방이 삭제되었다고 알립니다.
					    if (myRoom == null) {
					        // 방이 비어있으면 방을 삭제하고 모든 클라이언트에게 방이 삭제되었다고 알림
					        messageWait("165|" + msgs[1]);
					        messageWait("160|" + getRoomInfo());
					    }
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
			str += r.title + "- 현재인원  " + r.count+"명";
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
			Room room = roomSer.get(i);
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
	
	public void messageTo(Service service, String msg) {
	    try {
	        service.messageTo(msg);
	    } catch (IOException e) {
	        myRoom.user.remove(service);
	        System.out.println("클라이언트 접속 끊음!!");
	    }
	}
	
	public void disconnect(Service service) {
		/*try {
			service.socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		myRoom.count--;
		myRoom.user.remove(service);
		messageRoom("175|" + getRoomInwon());
		messageWait("160|" + getRoomInfo());
		

	}
	
}

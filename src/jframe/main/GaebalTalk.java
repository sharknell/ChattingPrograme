package jframe.main;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

public class GaebalTalk extends JFrame {

	private JPanel contentPane;
	private JFrame externalFrame;
	private JPanel waitingListPanel;
	private DefaultListModel<String> listModel;
	private JPanel panel_3;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GaebalTalk frame = new GaebalTalk();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public GaebalTalk() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 360, 600);
		contentPane = new JPanel();
		contentPane.setBackground(new Color(185, 207, 210));
		contentPane.setForeground(new Color(185, 207, 210));
		contentPane.setBorder(new EmptyBorder(0, 0, 0, 0));

		setContentPane(contentPane);
		contentPane.setLayout(null);

		JPanel panel = new JPanel();
		panel.setBackground(new Color(27, 35, 42));
		panel.setBounds(0, 0, 60, 561);
		contentPane.add(panel);
		panel.setLayout(null);

		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 20, 40, 40);
		panel_1.setBackground(new Color(27, 35, 42));
		panel_1.setBorder(null);
		panel.add(panel_1);

		ImageIcon imageIcon = new ImageIcon("image/human.png");
		panel_1.setLayout(null);
		JLabel lblImage = new JLabel(imageIcon);
		lblImage.setBounds(0, 0, 40, 40);
		panel_1.add(lblImage);

		JPanel panel_2 = new JPanel();
		panel_2.setBackground(new Color(27, 35, 42));
		panel_2.setBounds(10, 90, 40, 40);
		panel.add(panel_2);

		ImageIcon imageIcon_1 = new ImageIcon("image/speech.png");
		JLabel lblImage_1 = new JLabel(imageIcon_1);
		lblImage_1.setBounds(0, 0, 40, 40);
		panel_2.add(lblImage_1);

		panel_3 = new JPanel();
		panel_3.setBounds(60, 0, 300, 80);
		contentPane.add(panel_3);
		panel_3.setLayout(null);

		JPanel panel_4 = new JPanel();
		panel_4.setBounds(12, 40, 30, 30);
		panel_3.add(panel_4);

		ImageIcon imageIcon_2 = new ImageIcon("image/따봉.png");
		JLabel lblImage_2 = new JLabel(imageIcon_2);
		lblImage_2.setBounds(0, 0, 30, 30);
		panel_4.add(lblImage_2);

		JPanel panel_5 = new JPanel();
		panel_5.setBounds(79, 40, 25, 25);
		panel_3.add(panel_5);

		ImageIcon imageIcon_3 = new ImageIcon("image/야유.png");
		JLabel lblImage_3 = new JLabel(imageIcon_3);
		lblImage_2.setBounds(0, 0, 25, 25);
		panel_5.add(lblImage_3);

		addComponentListener(new java.awt.event.ComponentAdapter() {
			public void componentResized(java.awt.event.ComponentEvent evt) {
				panel.setBounds(0, 0, 60, getHeight());
			}
		});

		panel_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				if (externalFrame == null) {
					createExternalFrame();
				} else {
					externalFrame.setVisible(!externalFrame.isVisible());
				}
			}
		});

		panel_2.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				// TODO: 다른 동작 처리
			}
		});

		fetchWeatherData();
	}

	private void createExternalFrame() {
		externalFrame = new JFrame();
		externalFrame.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
		externalFrame.setBounds(getX() + getWidth(), getY(), 200, getHeight());

		waitingListPanel = new JPanel();
		waitingListPanel.setBackground(Color.WHITE);
		externalFrame.getContentPane().add(waitingListPanel);
		waitingListPanel.setLayout(null);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(0, 0, 200, getHeight());
		waitingListPanel.add(scrollPane);

		listModel = new DefaultListModel<>();
		JList<String> list = new JList<>(listModel);
		scrollPane.setViewportView(list);

		externalFrame.setVisible(true);
	}

	public void addWaitingPerson(String person) {
		listModel.addElement(person);
	}

	public void removeWaitingPerson(String person) {
		listModel.removeElement(person);
	}

	private void displayWeatherData(String data) {
		try {
			JSONParser parser = new JSONParser();
			JSONObject responseJson = (JSONObject) parser.parse(data);
			JSONObject bodyJson = (JSONObject) responseJson.get("response");
			JSONObject itemsJson = (JSONObject) bodyJson.get("body");
			JSONObject items = (JSONObject) itemsJson.get("items");
			JSONArray itemArray = (JSONArray) items.get("item");

			if (itemArray.size() > 0) {
				JSONObject itemJson = (JSONObject) itemArray.get(0);
				String category = (String) itemJson.get("category");
				String obsrValue = (String) itemJson.get("obsrValue");

				System.out.println("Category: " + category);
				System.out.println("Observed Value: " + obsrValue);

				LocalTime currentTime = LocalTime.now();

				if (category.equals("PTY") && obsrValue.equals("0")) {
					// 맑은 날씨인 경우
					ImageIcon imageIcon;
					if (currentTime.isAfter(LocalTime.of(8, 0)) && currentTime.isBefore(LocalTime.of(18, 0))) {
						// 08-18시인 경우, 해가 뜨는 사진 출력
						imageIcon = new ImageIcon("image/sunny.png");
					} else {
						// 19-07시인 경우, 달이 뜨는 사진 출력
						imageIcon = new ImageIcon("image/moon.png");
					}
					JLabel lblWeatherImage = new JLabel(imageIcon);
					lblWeatherImage.setBounds(0, 0, 300, 80);
					panel_3.add(lblWeatherImage);
				} else if (category.equals("PTY") && obsrValue.equals("1")) {
					// 비가 오는 날씨인 경우
					ImageIcon imageIcon = new ImageIcon("image/rainy.png");
					JLabel lblWeatherImage = new JLabel(imageIcon);
					lblWeatherImage.setBounds(0, 0, 300, 80);
					panel_3.add(lblWeatherImage);
				}
			} else {
				JLabel lblError = new JLabel("날씨 정보를 가져오는데 실패했습니다.");
				lblError.setBounds(0, 0, 300, 80);
				panel_3.add(lblError);
			}
		} catch (ParseException e) {
			e.printStackTrace();
			JLabel lblError = new JLabel("날씨 정보를 가져오는데 실패했습니다.");
			lblError.setBounds(0, 0, 300, 80);
			panel_3.add(lblError);
		}
	}

	private void fetchWeatherData() {
		try {
			String serviceKey = "IF39yNIX9Ja%2FY5eNLM8JL%2F28xJLNKsmrx3f0W%2BikqZ2rT2qC%2F5uOhwv%2FOl%2FJoq3bTXBH%2BAnZ%2FhaqYO3l1r4UtA%3D%3D";
			String baseDate = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd"));
			String baseTime = "0900";
			String nx = "60";
			String ny = "127";

			StringBuilder urlBuilder = new StringBuilder("http://apis.data.go.kr/1360000/VilageFcstInfoService_2.0/getUltraSrtNcst"); /*URL*/
			urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=" + serviceKey); /*Service Key*/
			urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
			urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*한 페이지 결과 수*/
			urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("JSON", "UTF-8")); /*요청자료형식(XML/JSON) Default: XML*/
			urlBuilder.append("&" + URLEncoder.encode("base_date","UTF-8") + "=" + URLEncoder.encode(baseDate, "UTF-8")); /*날짜*/
			urlBuilder.append("&" + URLEncoder.encode("base_time","UTF-8") + "=" + URLEncoder.encode(baseTime, "UTF-8")); /*시간*/
			urlBuilder.append("&" + URLEncoder.encode("nx","UTF-8") + "=" + URLEncoder.encode(nx, "UTF-8")); /*예보지점의 X 좌표값*/
			urlBuilder.append("&" + URLEncoder.encode("ny","UTF-8") + "=" + URLEncoder.encode(ny, "UTF-8")); /*예보지점의 Y 좌표값*/

			URL url = new URL(urlBuilder.toString());
			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.setRequestProperty("Content-type", "application/json");

			System.out.println("Response Code: " + conn.getResponseCode());

			BufferedReader rd;
			if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
				rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			} else {
				rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
			}

			StringBuilder sb = new StringBuilder();
			String line;
			while ((line = rd.readLine()) != null) {
				sb.append(line);
			}

			rd.close();
			conn.disconnect();

			System.out.println("Response Body:\n" + sb.toString());

			displayWeatherData(sb.toString());
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			JLabel lblError = new JLabel("날씨 정보를 가져오는데 실패했습니다.");
			lblError.setBounds(0, 0, 300, 80);
			panel_3.add(lblError);
		}
	}
}


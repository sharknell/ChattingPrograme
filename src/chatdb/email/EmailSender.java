package chatdb.email;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.util.Properties;

public class EmailSender {
    private static final String EMAIL_USERNAME = "kimm9487@gmail.com";
    private static final String EMAIL_PASSWORD = "grdlkpadbjwrdmyz";

    public static void sendVerificationEmail(String recipientEmail, String verificationCode) {
        String subject = "회원가입 인증 메일";
        String body = "회원가입을 완료하려면 아래 인증 코드를 입력하세요:\n\n" + verificationCode;

        Properties properties = new Properties();
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "587");

        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(EMAIL_USERNAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(recipientEmail));
            message.setSubject(subject);
            message.setText(body);

            Transport.send(message);

            System.out.println("인증 메일이 전송되었습니다. 이메일을 확인하세요.");

        } catch (MessagingException e) {
            System.out.println("인증 이메일 전송 중 오류가 발생했습니다. : " + e.getMessage());
        }
    }
}

package utility;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.*;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;

public class EmailUtil {

    public static void sendReportsWithLogs() {
        try {
            String timestamp = new SimpleDateFormat("yyyy-MM-dd HH:mm").format(new Date());
            String subjectTemplate = ConfigReader.getProperty("email.subject");
            String subject = subjectTemplate != null ? subjectTemplate.replace("${timestamp}", timestamp) : "Test Report " + timestamp;

            String host = ConfigReader.getProperty("email.host");
            String port = ConfigReader.getProperty("email.port");
            String username = ConfigReader.getProperty("email.username");
            String password = ConfigReader.getProperty("email.password");
            String from = ConfigReader.getProperty("email.from");
            String to = ConfigReader.getProperty("email.to");
            String body = ConfigReader.getProperty("email.body");

            String extentReportPath = ConfigReader.getProperty("report.extent");
            String excelReportPath = ConfigReader.getProperty("report.excel");
            String logFilePath = ConfigReader.getProperty("report.log");

            if (!verifyFilesExist(extentReportPath, excelReportPath, logFilePath)) {
                System.err.println("❌ One or more report files are missing. Email not sent.");
                return;
            }

            Properties mailProps = new Properties();
            mailProps.put("mail.smtp.auth", "true");
            mailProps.put("mail.smtp.starttls.enable", "true");
            mailProps.put("mail.smtp.host", host);
            mailProps.put("mail.smtp.port", port);

            Session session = Session.getInstance(mailProps, new Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(from));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(to));
            message.setSubject(subject);

            MimeBodyPart textPart = new MimeBodyPart();
            textPart.setText(body);

            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(textPart);

            addAttachment(multipart, extentReportPath, "ExtentReport.html");
            addAttachment(multipart, excelReportPath, "TestResults.xlsx");
            addAttachment(multipart, logFilePath, "TestExecution.log");

            message.setContent(multipart);

            Transport.send(message);
            System.out.println("✅ Email with report and logs sent successfully!");

        } catch (AuthenticationFailedException authEx) {
            System.err.println("❌ Authentication failed.");
            System.err.println("🔗 Visit: https://support.google.com/mail/?p=BadCredentials");
        } catch (Exception e) {
            System.err.println("❌ Failed to send email: " + e.getMessage());
            e.printStackTrace();
        }
    }

    private static void addAttachment(Multipart multipart, String filePath, String fileName) throws Exception {
        MimeBodyPart attachmentPart = new MimeBodyPart();
        DataSource source = new FileDataSource(filePath);
        attachmentPart.setDataHandler(new DataHandler(source));
        attachmentPart.setFileName(fileName);
        multipart.addBodyPart(attachmentPart);
    }

    private static boolean verifyFilesExist(String... filePaths) {
        for (String path : filePaths) {
            if (path == null || path.isEmpty() || !new File(path).exists()) {
                System.err.println("❌ File not found: " + path);
                return false;
            }
        }
        return true;
    }
}

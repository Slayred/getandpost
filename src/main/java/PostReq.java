
import javax.mail.Session;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;



public class PostReq {
    private static final int CONNECTION_TIMEOUT = 5000;

    public static void main(final String[] args) throws Exception {
        String id = post();
        //System.out.println(id);
        int res = getReq(id);
        if (res == 200) {
           //sendMail();
            //sendMailSSL();
            sendTeleg();
        }
    }

    public static String post() throws IOException {
        URL url = new URL("https://meissa.russoutdoor.ru/LoginService.svc/Login/");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("POST");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setDoOutput(true);
        String jIS = "{\"username\":\"achibisovm\",\"password\":\"1\",}";
        try (OutputStream os = con.getOutputStream()) {
            byte[] inp = jIS.getBytes();
            os.write(inp, 0, inp.length);
        }
        System.out.println(con.getResponseCode());
        try (BufferedReader br = new BufferedReader(
                new InputStreamReader(con.getInputStream(), "utf-8"))) {
            StringBuilder resp = new StringBuilder();
            String responseLine = null;
            while ((responseLine = br.readLine()) != null) {
                resp.append(responseLine.trim());
            }
            //System.out.println(resp.toString());
            String res = resp.toString();
            String[] resArr = res.split("\"");
            //System.out.println(resArr[3]);
            return resArr[3];

        }
    }

    public static int getReq(String tok) throws IOException {
        URL url = new URL("https://meissa.russoutdoor.ru/PanelService.svc/PanelsAround?lat=57.01612772&lon=41.00218892&radius=100.0&free_or_all=2");
        HttpURLConnection con = (HttpURLConnection) url.openConnection();
        con.setRequestMethod("GET");
        con.setRequestProperty("Content-Type", "application/json");
        con.setRequestProperty("Accept", "application/json");
        con.setRequestProperty("Authorization", tok);
        con.setDoOutput(true);
        con.setDoInput(true);
        return con.getResponseCode();
//        try (BufferedReader br = new BufferedReader(
//                new InputStreamReader(con.getInputStream(), "utf-8"))) {
//            StringBuilder resp = new StringBuilder();
//            String responseLine = null;
//            while ((responseLine = br.readLine()) != null) {
//                resp.append(responseLine.trim());
//            }
//            System.out.println(resp.toString());
//
//
//        }

    }
//    public static void sendMail() {
//
//        final String username = "aleksch1989@gmail.com";
//        final String password = "z63r5wi7qt";
//
//        Properties prop = new Properties();
//        prop.put("mail.smtp.host", "smtp.gmail.com");
//        prop.put("mail.smtp.port", "587");
//        prop.put("mail.smtp.auth", "true");
//        prop.put("mail.smtp.starttls.enable", "true"); //TLS
//
//        Session session = Session.getInstance(prop,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(username, password);
//                    }
//                });
//
//        try {
//
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress("aleksch1989@gmail.com"));
//            message.setRecipients(
//                    Message.RecipientType.TO,
//                    InternetAddress.parse("achibisov@russoutdoor.ru")
//            );
//            message.setSubject("Testing Gmail TLS");
//            message.setText("Dear Mail Crawler,"
//                    + "\n\n Please do not spam my email!");
//
//            Transport.send(message);
//
//            System.out.println("Done");
//
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//    }
//    public static void sendMailSSL(){
//
//        final String username = "aleksch1989@gmail.com";
//        final String password = "z63r5wi7qt";
//
//        Properties prop = new Properties();
//        prop.put("mail.smtp.host", "smtp.gmail.com");
//        prop.put("mail.smtp.port", "465");
//        prop.put("mail.smtp.auth", "true");
//        prop.put("mail.smtp.socketFactory.port", "465");
//        prop.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
//
//
//        Session session = Session.getInstance(prop,
//                new javax.mail.Authenticator() {
//                    protected PasswordAuthentication getPasswordAuthentication() {
//                        return new PasswordAuthentication(username, password);
//                    }
//                });
//
//        try {
//
//            Message message = new MimeMessage(session);
//            message.setFrom(new InternetAddress("aleksch1989@gmail.com"));
//            message.setRecipients(
//                    Message.RecipientType.TO,
//                    InternetAddress.parse("achibisov@russoutdoor.ru")
//            );
//            message.setSubject("Testing Gmail SSL");
//            message.setText("Dear Mail Crawler,"
//                    + "\n\n Please do not spam my email!");
//
//            Transport.send(message);
//
//            System.out.println("Done");
//
//        } catch (MessagingException e) {
//            e.printStackTrace();
//        }
//    }
    public static void sendTeleg() throws IOException {
        String urlString = "https://api.telegram.org/bot%s/sendMessage?chat_id=%s&text=%s";String apiToken = "1438985088:AAHj7q5-F86a-mZMh6Ckp1KtD1oABI5rtis";
        String chatId = "-260956938";
        String text = "it's didn't work";urlString = String.format(urlString, apiToken, chatId, text);URL url = new URL(urlString);
        URLConnection conn = url.openConnection();StringBuilder sb = new StringBuilder();
        InputStream is = new BufferedInputStream(conn.getInputStream());
        BufferedReader br = new BufferedReader(new InputStreamReader(is));
        String inputLine = "";
        while ((inputLine = br.readLine()) != null) {
            sb.append(inputLine);
        }
        String response = sb.toString();
    }
}


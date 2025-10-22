package com.test.iipsrv_api;

import org.testng.Assert;
import org.testng.annotations.Test;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public class apollo2_iipsrv_api {

    @Test(priority = 1)
    public void iipsrv() {
        Response response1 = RestAssured
                .given()
                .auth()
                .basic("admin", "admin")
                .when()
                .get("https://preprod.humanbrain.in//iipsrv/fcgi-bin/iipsrv.fcgi?FIF=/ddn/storageIIT/humanbrain/analytics/222/NISL/B_222_FB74-SL_316-ST_NISL-SE_946_compressed.jp2&WID=1024&GAM=3.2&MINMAX=1:0,141&MINMAX=2:0,153&MINMAX=3:117,255&JTL=0,0");

        int statusCode1 = response1.getStatusCode();
        if (statusCode1 == 200) {
            System.out.println("API request to the apollo2 iipsrv passed. Status code: " + statusCode1);
        } else {
            System.out.println("API request to the apollo2 iipsrv failed. Status code: " + statusCode1);
            // sendFailureEmail(statusCode1); // ❌ Commented out
        }

        Assert.assertEquals(statusCode1, 200, "API request to the apollo2 iipsrv failed");
    }

    /*
    // ❌ Email functionality has been disabled as per your request
    public void sendFailureEmail(int statusCode) {
        final String fromEmail = "automationsoftware25@gmail.com";
        final String toEmail = "nitheshkumar.s@htic.iitm.ac.in";
        final String password = "wjzcgaramsqvagxu"; // App-specific password

        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.port", "587");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        Session session = Session.getInstance(props, new javax.mail.Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, password);
            }
        });

        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(fromEmail));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));

            // ✅ Add CC recipients
            message.setRecipients(Message.RecipientType.CC, InternetAddress.parse(
                "divya.d@htic.iitm.ac.in," +
                "venip@htic.iitm.ac.in" 
            ));

            message.setSubject("IIPSRV Failed");

            message.setText("Hi,\n\n"
                    + "I am iipsrv... and I’ve failed to serve the request.\n\n"
                    + "Tried my best to fetch the image, but a " + statusCode + " error knocked me down.\n\n"
                    + "Please take a look and help me get back on track. 🙏");

            Transport.send(message);
            System.out.println("Failure notification email sent successfully.");

        } catch (MessagingException e) {
            e.printStackTrace();
            System.err.println("Failed to send email notification.");
        }
    }
    */
}

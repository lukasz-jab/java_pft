package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

/**
 * Created by luk on 2017-05-15.
 */
public class RegistrationTest extends TestBase {

    // @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testRegistration() throws IOException, MessagingException {
        long now = System.currentTimeMillis();
        String user = String.format("user%s", now);
        String password = "password";
        String emailAddress = user + "@localhost.localdomain";
        app.james().createUser(user, password);
        app.registration().start("user" + System.currentTimeMillis(), emailAddress);
        //List<MailMessage> email = app.mail().waitForMail(2, 10000);
        List<MailMessage> email = app.james().waitForMail(user, password, 60000);
        String confirmationLink = findConfirmationLink(email, emailAddress);
        app.registration().finish(confirmationLink, "password");
    }

    private String findConfirmationLink(List<MailMessage> email, String emailAddress) {
        MailMessage mailMessage = email.stream().filter((m) -> m.to.equals(emailAddress)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }

    // @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }

}

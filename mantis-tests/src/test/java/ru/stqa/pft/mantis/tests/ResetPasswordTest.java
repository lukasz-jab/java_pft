package ru.stqa.pft.mantis.tests;


import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.lanwen.verbalregex.VerbalExpression;
import ru.stqa.pft.mantis.model.MailMessage;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.List;

/**
 * Created by luk on 2017-05-22.
 */
public class ResetPasswordTest extends TestBase {

    @BeforeMethod
    public void startMailServer() {
        app.mail().start();
    }

    @Test
    public void testResetPassword() throws IOException, MessagingException {
        app.navigationHelper().gotoManageUsersPage();
        String[] emailAddress = app.navigationHelper().selectUserToReset();
        List<MailMessage> email = app.mail().waitForMail(1, 10000);
        String confirmationLink = findConfirmationLink(email, emailAddress[1]);
        app.navigationHelper().confirmationPassword(confirmationLink);

        app.sessionHelper().login(emailAddress[0],"password");
    }

    private String findConfirmationLink(List<MailMessage> email, String emailAddress) {
        MailMessage mailMessage = email.stream().filter((m) -> m.to.equals(emailAddress)).findFirst().get();
        VerbalExpression regex = VerbalExpression.regex().find("http://").nonSpace().oneOrMore().build();
        return regex.getText(mailMessage.text);
    }
    @AfterMethod(alwaysRun = true)
    public void stopMailServer() {
        app.mail().stop();
    }
}

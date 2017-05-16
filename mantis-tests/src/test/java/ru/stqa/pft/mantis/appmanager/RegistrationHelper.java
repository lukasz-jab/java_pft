package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by luk on 2017-05-15.
 */
public class RegistrationHelper extends HelperBase {

    public RegistrationHelper(ApplicationManager app) {
        super(app);
    }

    public void start(String username, String email) {
        wd.get(app.getProperty("web.baseURL"));
        click(By.cssSelector("[href='signup_page.php'"));
        type(By.name("username"), username);
        type(By.name("email"), email);       //input value (in Polish "Zapisz się")
        click(By.cssSelector("input[type=submit]"));

    }

    public void finish(String confirmationLink, String password) {

        wd.navigate().to(confirmationLink);
        type(By.name("password"), password);
        type(By.name("password_confirm"), password);
        click(By.cssSelector("button[type=submit]"));

    }

}

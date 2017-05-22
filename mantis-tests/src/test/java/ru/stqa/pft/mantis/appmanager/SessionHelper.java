package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;

/**
 * Created by luk on 2017-05-22.
 */
public class SessionHelper extends HelperBase {

    public SessionHelper(ApplicationManager app) {
        super(app);

    }

    public void login(String user, String password) {
        wd.findElement(By.cssSelector("input#username")).sendKeys(user);
        wd.findElement(By.cssSelector("input[type='submit']")).click();
        wd.findElement(By.cssSelector("input#password")).sendKeys(password);
        wd.findElement(By.cssSelector("input[type='submit']")).click();
    }
}

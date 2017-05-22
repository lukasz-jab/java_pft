package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by luk on 2017-05-22.
 */
public class NavigationHelper extends HelperBase {

    //private WebDriver wd;

    public NavigationHelper(ApplicationManager app) {
        super(app);
    }

    public void NavigationHelper(WebDriver wd) {
        this.wd = wd;
    }

    public void gotoManageUsersPage() {
        wd.findElement(By.cssSelector("a[href='/mantisbt/manage_user_page.php']")).click();
    }
    public String[] selectUserToReset(){
        List<WebElement> users =
        wd.findElements(By.cssSelector("div.table-responsive tr a[href^='manage_user_edit_page.php']"));
        // first is admin secomd is "user+timeinmilis"
        users.get(1).click();
        String data[]= new String[2];
        data[0] = wd.findElement(By.cssSelector("input#edit-username")).getAttribute("value");
        data[1]=wd.findElement(By.cssSelector("input#email-field")).getAttribute("value");
        wd.findElement(By.cssSelector("input[value='Nowe hasło']")).click();
        return data;
    }
    public void confirmationPassword(String link){
        wd.navigate().to(link);
        wd.findElement(By.cssSelector("input#password")).sendKeys("password");
        wd.findElement(By.cssSelector("input#password-confirm")).sendKeys("password");
        wd.findElement(By.cssSelector("button[type='submit']")).click();
    }



}

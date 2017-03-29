package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 * Created by luk on 2017-03-27.
 */
public class NavigationHelper extends HelperBase {

    public NavigationHelper(ChromeDriver wd) {
        super(wd);
    }

    public void gotoGroupPage() {
        click(By.linkText("groups"));
    }

    public void gotoContactAddPage() {
        click(By.linkText("add new"));
    }

    public void gotoMainPage() {
        click(By.xpath("//div[@id='nav']//a[contains(text(),'home')]"));
    }
}

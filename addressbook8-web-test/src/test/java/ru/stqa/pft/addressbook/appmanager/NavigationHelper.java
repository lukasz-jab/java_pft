package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

/**
 * Created by luk on 2017-03-27.
 */
public class NavigationHelper extends HelperBase {

    public NavigationHelper(WebDriver wd) {
        super(wd);
    }

    public void group() {
        if (isElementPresent(By.tagName("h1"))
                && (wd.findElement(By.tagName("h1")).getText().equals("Groups"))
                && (isElementPresent(By.name("new")))) {
            return;
        }
        click(By.linkText("groups"));


    }

    public void contactPage() {
        if (wd.findElement(By.tagName("h1")).getText().equals("Edit / add address book entry")) {
            return;
        }
        click(By.linkText("add new"));
    }

    public void mainPage() {
        if (isElementPresent(By.id("maintable"))) {

            return;
        }
        click(By.xpath("//div[@id='nav']//a[contains(text(),'home')]"));
    }
}

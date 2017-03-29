package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;

import org.openqa.selenium.chrome.ChromeDriver;
import ru.stqa.pft.addressbook.model.ContactData;

/**
 * Created by luk on 2017-03-29.
 */
public class ContactHelper extends HelperBase {

    public ContactHelper(ChromeDriver wd) {
        super(wd);
    }


    public void submitContactCreation() {
        click(By.xpath("//input[@name='submit']"));
    }

    public void fillContactForm(ContactData contactData) {
        type(By.xpath("//input[@name='firstname']"), contactData.getFirstName());
        type(By.xpath("//input[@name='lastname']"), contactData.getLastName());
        type(By.xpath("//textarea[@name='address']"), contactData.getAddress());
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();

    }

    public void selectContact() {
        click(By.xpath("//input[@name='selected[]']"));
    }

    public void editContact() {
        click(By.xpath("//img[@title='Edit']"));
    }

    public void submitEditConact() {
        click(By.xpath("//input[@name='update']"));
    }


}

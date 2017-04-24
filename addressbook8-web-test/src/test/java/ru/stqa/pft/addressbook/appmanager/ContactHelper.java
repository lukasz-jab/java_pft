package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;

import java.util.List;

/**
 * Created by luk on 2017-03-29.
 */
public class ContactHelper extends HelperBase {

    public ContactHelper(WebDriver wd) {
        super(wd);
    }

    public void getPhonesFromMainPage(int id) {
        String phones = wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']/../../td[6]")).getText();
        String[] allPhones = phones.split("\n");


    }

    public void submitContactCreation() {
        click(By.xpath("//input[@name='submit']"));
    }

    public void fillContactForm(ContactData contactData, boolean creation) {
        type(By.xpath("//input[@name='firstname']"), contactData.getFirstName());
        type(By.xpath("//input[@name='lastname']"), contactData.getLastName());
        type(By.xpath("//textarea[@name='address']"), contactData.getAddress());
        //if (creation){
        //  new Select(wd.findElement(By.name("new_group"))).selectByVisibleText(contactData.getGroup());
        //}else {
        //  Assert.assertFalse(isElementPresent(By.name("new_group")));
        //}

    }

    public void modify(ContactData contactEdited) {
        editContact();
        fillContactForm((contactEdited), false);
        submitEditConact();
        contactCache = null;
    }

    public void deleteSelectedContact() {
        click(By.xpath("//input[@value='Delete']"));
        wd.switchTo().alert().accept();
        contactCache = null;

    }

    public void delete(ContactData contact) {
        selectContactById(contact.getId());
        deleteSelectedContact();
        contactCache = null;
    }

    public void selectContactById(int id) {
        wd.findElement(By.xpath("//input[@value='" + id + "']")).click();
    }

    public void selectContactWithPhonesById(int id) {
        wd.findElement(By.xpath("//a[@href='edit.php?id=" + id + "']")).click();
    }

    public void editContact() {
        click(By.xpath("//img[@title='Edit']"));
    }

    public void submitEditConact() {
        click(By.xpath("//input[@value='Update']"));
    }

    public void createContact(ContactData contact) {
        fillContactForm(new ContactData().withId(0).withFirstname("Grzegorz").withLastname("Brzęczyszczykiewicz")
                .withAddress("Poland").withGroup(null), true);
        submitContactCreation();
    }

    public boolean isThereAContact() {
        return isElementPresent(By.xpath("//input[@name='selected[]']"));
    }

    private Contacts contactCache = null;

    public Contacts c_all() {
        if (!(contactCache == null)) {
            return new Contacts(contactCache);
            // return copy of cache
        } else {
            List<WebElement> elementContactData = wd.findElements(By.xpath("//table[@id='maintable']//tr"));
            Contacts contactCache = new Contacts();
            int i = 2;
            for (WebElement element : elementContactData) {
                String lastName = wd.findElement(By.xpath("//table[@id='maintable']//tr[" + i + "]//td[2]")).getText();
                String name = wd.findElement(By.xpath("//table[@id='maintable']//tr[" + i + "]//td[3]")).getText();
                String country = wd.findElement(By.xpath("//table[@id='maintable']//tr[" + i + "]//td[4]")).getText();
                int id = Integer.parseInt(element.findElement(By.xpath("//table[@id='maintable']//tr[" + i + "]//input"))
                        .getAttribute("value"));
                String phones = wd.findElement(By.xpath("//table[@id='maintable']//tr[" + i + "]//td[6]")).getText();
                String allPhones = wd.findElement(By.xpath("//table[@id='maintable']//tr[" + i + "]//td[6]")).getText();
                String email = wd.findElement(By.xpath("//table[@id='maintable']//tr[" + i + "]//td[5]")).getText();

                contactCache.add(new ContactData().withId(id).withFirstname(name).withLastname(lastName).withAddress(country)
                        .withGroup(null).withtAllPhones(allPhones).withEmail(email));

                //eliminate first element-ramka
                if (i < elementContactData.size()) {
                    i++;
                } else continue;
            }
            return new Contacts(contactCache);
        }
    }

    public ContactData getPhonesFromContactPage(int id) {
        String lastName = wd.findElement(By.xpath("//input[@name='lastname']")).getAttribute("value");
        String name = wd.findElement(By.xpath("//input[@name='firstname']")).getAttribute("value");
        String country = wd.findElement(By.xpath("//textarea[@name='address']")).getAttribute("value");
        String homePhone = wd.findElement(By.xpath("//input[@name='home']")).getAttribute("value");
        String mobilePhone = wd.findElement(By.xpath("//input[@name='mobile']")).getAttribute("value");
        String workPhone = wd.findElement(By.xpath("//input[@name='work']")).getAttribute("value");
        String email = wd.findElement(By.xpath("//input[@name='email']")).getAttribute("value");
        return new ContactData().withId(id).withFirstname(name).withLastname(lastName)
                .withAddress(country).withHomePhone(homePhone).withMobilePhone(mobilePhone).withWorkPhone(workPhone)
                .withEmail(email);
    }

}

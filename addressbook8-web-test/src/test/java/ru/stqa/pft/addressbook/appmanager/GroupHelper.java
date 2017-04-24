package ru.stqa.pft.addressbook.appmanager;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.util.List;

/**
 * Created by luk on 2017-03-27.
 */
public class GroupHelper extends HelperBase {

    public GroupHelper(WebDriver wd) {
        super(wd);
    }

    public void returnToGroupPage() {
        click(By.linkText("group page"));
    }

    public void submitGroupCreation() {
        click(By.name("submit"));
    }

    public void fillGroupForm(GroupData groupData) {
        type(By.name("group_name"), groupData.getName());
        type(By.name("group_header"), groupData.getHeader());
        type(By.name("group_footer"), groupData.getFooter());
    }


    public void initGroupCreation() {
        click(By.name("new"));
    }

    public void deleteSelectedGroups() {
        click(By.name("delete"));
    }

    public void selectGroup(int index) {
        if (!wd.findElement(By.name("selected[]")).isSelected()) {
            wd.findElements(By.name("selected[]")).get(index).click();
        }
    }

    private void selectById(int id) {
        if (!wd.findElement(By.name("selected[]")).isSelected()) {
            wd.findElement(By.cssSelector("input[value='" + id + "']")).click();
        }
    }

    public void editGroup() {
        click(By.xpath("//input[@name='edit']"));
    }

    public void submitEditGroup() {
        click(By.xpath("//input[@name='update']"));
    }


    public void create(GroupData group) {
        initGroupCreation();
        fillGroupForm(group);
        submitGroupCreation();
        groupCache=null;
        returnToGroupPage();
    }

    public void modify(GroupData group) {
        selectById(group.getId());
        editGroup();
        fillGroupForm(group);
        submitEditGroup();
        groupCache=null;
        returnToGroupPage();
    }

    public void delete(GroupData deletedGroup) {
        selectById(deletedGroup.getId());
        deleteSelectedGroups();
        groupCache=null;
        returnToGroupPage();

    }

    public boolean isThereAGroup() {
        return isElementPresent(By.name("selected[]"));
    }

    public int count() {
        return wd.findElements(By.cssSelector("span.group")).size();
    }

    private Groups groupCache = null;

    public Groups g_all() {
        if (!(groupCache == null)) {
            return new Groups(groupCache);
            // return copy of cache
        } else {
            Groups groupCache = new Groups();
            List<WebElement> elements = wd.findElements(By.cssSelector("span.group"));
            for (WebElement element : elements) {
                String name = element.getText();
                int id = Integer.parseInt(element.findElement(By.tagName("input")).getAttribute("value"));
                groupCache.add(new GroupData().withId(id).withName(name).withHeader(null).withFooter(null));
            }
            return new Groups(groupCache);
        }
    }

}

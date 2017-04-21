package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

/**
 * Created by luk on 2017-03-29.
 */
public class GroupEditTest extends TestBase {

    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().group();
        if (!app.group().isThereAGroup()) {
            app.group().create(new GroupData().withName("group 1").withHeader("header 1").withFooter("footer 1"));
        }
    }

    @Test
    public void groupEditTest() {

        List<GroupData> before = app.group().list();
        int index = before.size() - 1;
        GroupData group = new GroupData().withId(before.get(index).getId()).withName("new group name").withHeader("new group header").withFooter("new group footer");
        app.group().modify(index, group);

        List<GroupData> after = app.group().list();
        before.remove(index);
        before.add(group);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    }

}

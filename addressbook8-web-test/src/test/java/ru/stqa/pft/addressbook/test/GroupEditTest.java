package ru.stqa.pft.addressbook.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by luk on 2017-03-29.
 */
public class GroupEditTest extends TestBase {
    Logger logger = LoggerFactory.getLogger(ContactCreationTest.class);
    //slf4j
    @BeforeMethod
    public void ensurePreconditions() {
        app.goTo().group();
        if (!app.group().isThereAGroup()) {
            app.group().create(new GroupData().withName("group 1").withHeader("header 1").withFooter("footer 1"));
        }
    }

    @Test
    public void groupEditTest() {
        Groups before = app.group().g_all();
        GroupData modifiedGroup = before.iterator().next();
        GroupData group = new GroupData().withId(modifiedGroup.getId()).withName("new group name").withHeader("new group header").withFooter("new group footer");
        app.group().modify(group);

        Groups after = app.group().g_all();
        assertThat(before.withOuth(modifiedGroup).withAdded(group), equalTo(after));
    }

}

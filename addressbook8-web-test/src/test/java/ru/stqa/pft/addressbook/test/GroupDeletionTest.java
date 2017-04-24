package ru.stqa.pft.addressbook.test;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupDeletionTest extends TestBase {

    @BeforeMethod
    private void ensurePreconditions() {
        app.goTo().group();
        if (app.group().g_all().size() == 0) {
            app.group().create(new GroupData().withName("group 1").withHeader("header 1").withFooter("footer 1"));
        }
    }


    @Test
    public void testGroupDeletion() {
        Groups before = app.group().g_all();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        Groups after = app.group().g_all();
        assertThat(before.withOuth(deletedGroup), equalTo(after));
    }

}
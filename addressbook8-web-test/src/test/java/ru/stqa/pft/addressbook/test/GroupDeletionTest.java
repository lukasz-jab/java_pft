package ru.stqa.pft.addressbook.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;


public class GroupDeletionTest extends TestBase {
    Logger logger = LoggerFactory.getLogger(ContactCreationTest.class);

    //slf4j
    @BeforeMethod
    private void ensurePreconditions() {
        app.goTo().group();
        if (app.db().group().size() == 0) {
            app.group().create(new GroupData().withName("group 1").withHeader("header 1").withFooter("footer 1"));
        }
    }


    @Test
    public void testGroupDeletion() {
        Groups before = app.db().group();
        GroupData deletedGroup = before.iterator().next();
        app.group().delete(deletedGroup);
        Groups after = app.db().group();
        assertThat(before.withOuth(deletedGroup), equalTo(after));
    }

}
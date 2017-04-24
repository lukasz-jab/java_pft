package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTest extends TestBase {

    @Test
    public void testGroupCreation() {
        app.goTo().group();
        Groups before = app.group().g_all();
        GroupData group = new GroupData().withName("group 1").withHeader("header 1").withFooter("footer 1");
        app.group().create(group.withId(0));
        Groups after = app.group().g_all();
        assertThat(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))
                , equalTo(after));
    }
    @Test
    public void testBadGroupCreation() {
        app.goTo().group();
        Groups before = app.group().g_all();
        GroupData group = new GroupData().withName("group 1'''").withHeader("header 1").withFooter("footer 1");
                                                   // not authorized sign: '
        app.group().create(group.withId(0));
        assertThat(before, equalTo(app.group().count()));
        Groups after = app.group().g_all();
        assertThat(before, equalTo(after));
    }


}

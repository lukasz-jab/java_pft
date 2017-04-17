package ru.stqa.pft.addressbook.test;

import org.testng.Assert;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

import java.util.HashSet;
import java.util.List;

/**
 * Created by luk on 2017-03-29.
 */
public class GroupEditTest extends TestBase {

    @Test
    public void groupEditTest() {
        app.getNavigationHelper().gotoGroupPage();
        if (! app.getGroupHelper().isThereAGroup()){
            app.getGroupHelper().createGroup(new GroupData("group name 2", null, null));
        }
        List<GroupData> before = app.getGroupHelper().getGroupList();
        app.getGroupHelper().selectGroup(before.size()-1);
        app.getGroupHelper().editGroup();
        GroupData group=new GroupData(before.get(before.size()-1).getId(),"new group name","new group header","new group footer");
        app.getGroupHelper().fillGroupForm(group);
        app.getGroupHelper().submitEditGroup();
        app.getGroupHelper().returnToGroupPage();

        List<GroupData> after = app.getGroupHelper().getGroupList();
        before.remove(before.size()-1);
        before.add(group);
        Assert.assertEquals(new HashSet<Object>(before), new HashSet<Object>(after));

    }

}

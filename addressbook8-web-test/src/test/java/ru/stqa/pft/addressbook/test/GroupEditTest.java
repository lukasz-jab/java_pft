package ru.stqa.pft.addressbook.test;

import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;

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
        app.getGroupHelper().selectGroup();
        app.getGroupHelper().editGroup();
        app.getGroupHelper().fillGroupForm(new GroupData("new group name","new group header","new group footer"));
        app.getGroupHelper().submitEditGroup();
        app.getGroupHelper().returnToGroupPage();

    }

}

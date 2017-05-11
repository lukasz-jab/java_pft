package ru.stqa.pft.addressbook.test;

import com.google.common.reflect.TypeToken;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.thoughtworks.xstream.XStream;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class GroupCreationTest extends TestBase {
    Logger logger = LoggerFactory.getLogger(ContactCreationTest.class);

    //slf4j
    @DataProvider
    public Iterator<Object[]> validGroupsFromJSON() throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.json")));
        String json = "";
        String line = reader.readLine();
        while (line != null) {
            json += line;
            line = reader.readLine();
        }
        Gson gson = new GsonBuilder().setPrettyPrinting().excludeFieldsWithoutExposeAnnotation().create();
        List<GroupData> groups = gson.fromJson(json, new TypeToken<List<GroupData>>() {
        }.getType());
        return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }

    @DataProvider
    public Iterator<Object[]> validGroupsFromXML() throws IOException {
        List<Object[]> list = new ArrayList<Object[]>();
        BufferedReader reader = new BufferedReader(new FileReader(new File("src/test/resources/groups.xml")));
        String xml = "";
        String line = reader.readLine();
        while (line != null) {
            xml += line;
            line = reader.readLine();
        }
        XStream xstream = new XStream();
        xstream.processAnnotations(GroupData.class);
        List<GroupData> groups = (List<GroupData>) xstream.fromXML(xml);
        return groups.stream().map((g) -> new Object[]{g}).collect(Collectors.toList()).iterator();
    }


    @Test(dataProvider = "validGroupsFromJSON")
    public void testGroupCreation(GroupData group) {
        app.goTo().group();
        Groups before = app.db().group();
        app.group().create(group);
        Groups after = app.db().group();
        assertThat(after, equalTo(before.withAdded(group.withId(after.stream().mapToInt((g) -> g.getId()).max().getAsInt()))));
        verifyGroupListInUI();
    }

    @Test(enabled = true)
    public void testBadGroupCreation() {
        app.goTo().group();
        Groups before = app.db().group();
        GroupData group = new GroupData().withName("group 1'''").withHeader("header 1").withFooter("footer 1");
        // not authorized sign: '
        app.group().create(group);
        Groups after = app.db().group();
        assertThat(before, equalTo(after));
        verifyGroupListInUI();
    }


}

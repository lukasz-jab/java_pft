package ru.stqa.pft.addressbook.test;

import org.openqa.selenium.remote.BrowserType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.addressbook.appmanager.ApplicationManager;
import ru.stqa.pft.addressbook.model.ContactData;
import ru.stqa.pft.addressbook.model.Contacts;
import ru.stqa.pft.addressbook.model.GroupData;
import ru.stqa.pft.addressbook.model.Groups;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.stream.Collectors;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

/**
 * Created by luk on 2017-03-26.
 */
public class TestBase {
    //slf4j
    protected static final ApplicationManager app =
            new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
    Logger logger = LoggerFactory.getLogger(TestBase.class);

    @BeforeSuite
    public void setUp() throws Exception {
        app.init();
    }

    @AfterSuite
    public void tearDown() {
        app.stop();
    }

    @BeforeMethod
    public void logTestStart(Method m, Object[] p) {
        logger.info("Start " + m.getName() + "with parameters " + Arrays.asList(p));
    }

    @AfterMethod(alwaysRun = true)
    public void logTestStop(Method m) {
        logger.info("Stop " + m.getName());
    }

    public void verifyGroupListInUI() {
        Groups dbGroups = app.db().group();
        Groups uiGroups = app.group().g_all();
        assertThat(uiGroups, equalTo(dbGroups.stream().map((g) -> new GroupData().withId(g.getId()).withName(g.getName()))
                .collect(Collectors.toSet())));

    }

    public void verifyContactListInUI() {
        if (Boolean.getBoolean("verifyUI")) {
            Contacts dbContacts = app.db().contacts();
            Contacts uiContacts = app.contact().c_all();
            assertThat(uiContacts, equalTo(dbContacts.stream().map((c) -> new ContactData()
                    .withId(c.getId()).withFirstname(c.getFirstName()).withLastname(c.getLastName()))
                    .collect(Collectors.toSet())));
        }
    }

}

package ru.stqa.pft.mantis.tests;

import org.testng.annotations.Test;
import ru.stqa.pft.mantis.appmanager.HttpSession;

import java.io.IOException;

import static org.testng.Assert.assertTrue;

/**
 * Created by luk on 2017-05-13.
 */
public class loginTest extends TestBase {
    @Test
    public void testLoogin() throws IOException {
        HttpSession session = app.newSession();
        assertTrue(session.login("Administrator", "root"));
        assertTrue(session.isLoggedInAs("administrator"));


    }
}

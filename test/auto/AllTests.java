package auto;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
		AddActivity.class, AddProject.class, AddUser.class, Login.class, Followup.class, Planning.class,
		SwitchProjectLeader.class, TimeRegistration.class, SuperUserTest.class
})
public class AllTests {

}

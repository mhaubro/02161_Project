package auto;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({
		AddActivity.class, AddProject.class, AddUser.class, Login.class, Opf�lgningAfProjekt.class, Planning.class,
		SwitchProjectLeader.class, TimeRegistration.class
})
public class AllTests {

}

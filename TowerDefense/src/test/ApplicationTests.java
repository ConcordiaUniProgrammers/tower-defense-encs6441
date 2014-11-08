package test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

import test.core.applicationservice.MapServiceSuiteTest;
import test.core.applicationservice.locationservices.LocationSuiteTest;
import test.core.domain.warriors.TowersSuiteTest;

@RunWith(Suite.class)
@SuiteClasses({TowersSuiteTest.class, MapServiceSuiteTest.class, LocationSuiteTest.class})
public class ApplicationTests {
	
}

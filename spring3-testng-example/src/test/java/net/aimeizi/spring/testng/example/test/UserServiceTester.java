package net.aimeizi.spring.testng.example.test;

import junit.framework.Assert;
import net.aimeizi.spring.testng.example.common.exceptions.NonExistentUserException;
import net.aimeizi.spring.testng.example.model.User;
import net.aimeizi.spring.testng.example.service.IUserService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.*;

/**
 * Created by 冯靖 on 2014/7/1 0001.
 */
@ContextConfiguration(locations={"classpath:applicationContext.xml"})
public class UserServiceTester extends AbstractTestNGSpringContextTests {

    private static Logger logger = Logger.getLogger(UserServiceTester.class);

    @Autowired
    private IUserService userService;

    private User firstUser;
    private User secondUser;
    private User thirdUser;

    /**
     * Creates Test Users
     *
     */
    private void createUsers() {
        firstUser = new User();
        firstUser.setId("1");
        firstUser.setName("Lionel");
        firstUser.setSurname("Messi");

        secondUser = new User();
        secondUser.setId("2");
        secondUser.setName("David");
        secondUser.setSurname("Villa");

        thirdUser = new User();
        thirdUser.setId("3");
        thirdUser.setName("Andres");
        thirdUser.setSurname("Iniesta");
    }

    /**
     * Asserts that User Properties are not null.
     *
     * @param user
     */
    private void assertNotNullUserProperties(User user) {
        Assert.assertNotNull("User must not be null!", user);
        Assert.assertNotNull("Id must not be null!", user.getId());
        Assert.assertNotNull("Name must not be null!", user.getName());
        Assert.assertNotNull("Surname must not be null!", user.getSurname());
    }

    /**
     * The annotated method will be run before any net.aimeizi.spring.testng.example.test method belonging to the classes
     * inside the <net.aimeizi.spring.testng.example.test> tag is run.
     *
     */
    @BeforeTest
    public void beforeTest() {
        logger.debug("BeforeTest method is run...");
    }

    /**
     * The annotated method will be run before the first net.aimeizi.spring.testng.example.test method in the current class
     * is invoked.
     *
     */
    @BeforeClass
    public void beforeClass() {
        logger.debug("BeforeClass method is run...");
        createUsers();
    }

    /**
     * The annotated method will be run before each net.aimeizi.spring.testng.example.test method.
     *
     */
    @BeforeMethod
    public void beforeMethod() {
        logger.debug("BeforeMethod method is run...");
    }

    /**
     * Tests the process of adding user
     *
     */
    @Test
    public void addUser() {
        assertNotNullUserProperties(firstUser);
        Assert.assertTrue("User can not be added! User : " + firstUser, getUserService().addUser(firstUser));
    }

    /**
     * Tests the process of querying user
     *
     */
    @Test
    public void getUserById() {
        User tempUser = getUserService().getUserById(firstUser.getId());
        assertNotNullUserProperties(tempUser);
        Assert.assertEquals("Id is wrong!", "1", tempUser.getId());
        Assert.assertEquals("Name is wrong!", "Lionel", tempUser.getName());
        Assert.assertEquals("Surname is wrong!", "Messi", tempUser.getSurname());
    }

    /**
     * Tests the process of deleting user
     *
     */
    @Test
    public void deleteUser() {
        assertNotNullUserProperties(secondUser);
        Assert.assertTrue("User can not be added! User : " + secondUser, getUserService().addUser(secondUser));
        Assert.assertTrue("User can not be deleted! User : " + secondUser, getUserService().deleteUser(secondUser));
    }

    /**
     * Tests the process of updating user
     * @throws net.aimeizi.spring.testng.example.common.exceptions.NonExistentUserException
     *
     */
    @Test(expectedExceptions = NonExistentUserException.class)
    public void updateUser() throws NonExistentUserException {
        getUserService().updateUser(thirdUser);
    }

    /**
     * Test user count
     *
     */
    @Test
    public void getUserCount() {
        Assert.assertEquals(1, getUserService().getUsers().size());
    }

    /**
     * The annotated method will be run after all the net.aimeizi.spring.testng.example.test methods in the current class have been run.
     *
     */
    @AfterClass
    public void afterClass() {
        logger.debug("AfterClass method is run...");
    }

    /**
     * The annotated method will be run after all the net.aimeizi.spring.testng.example.test methods belonging to the classes inside the <net.aimeizi.spring.testng.example.test> tag have run.
     *
     */
    @AfterTest
    public void afterTest() {
        logger.debug("AfterTest method is run...");
    }

    /**
     * The annotated method will be run after each net.aimeizi.spring.testng.example.test method.
     *
     */
    @AfterMethod
    public void afterMethod() {
        logger.debug("AfterMethod method is run...");
    }

    /**
     * Gets User Service
     *
     * @return IUserService User Service
     */
    public IUserService getUserService() {
        return userService;
    }

    /**
     * Sets User Service
     *
     * @param userService
     */
    public void setUserService(IUserService userService) {
        this.userService = userService;
    }

}

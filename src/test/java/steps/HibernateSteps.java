package steps;


import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import dao.CompanyDao;
import dao.CompanyDaoImp;
import dao.UserDao;
import entity.User;
import org.junit.Assert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HibernateSteps {

    @Autowired
    CompanyDao companyDao;
    @Autowired
    UserDao userDao;
    String companyId;


    @Given("^Insert user (.*) from company (.*) in DB$")
    public void insert_user_Tony_from_company_Coca_in_DB(String userName, String companyName) {
        String companyId = companyDao.getCompanyIdByName(companyName).toString();
        User user = new User(userName, companyId);
        userDao.save(user);
    }

    @Given("^User (.*) from company (.*) exists in DB$")
    public void user_Tony_from_company_Coca_exists_DB(String userName, String companyName) {
        List<User> userList = userDao.getUserByNameList(userName);
        Assert.assertEquals("userList should contain 1 element", 1, userList.size());
    }

    @When("^Admin executes query to delete user (.*)")
    public void admin_executes_query_to_delete_user_Tony(String userName){
        userDao.deleteUserByName(userName);
    }

    @Then("^User (.*) is deleted from DB$")
    public void user_Tony_is_deleted_from_DB(String userName){
        List<User> userList = userDao.getUserByNameList(userName);
        Assert.assertEquals("userList should contain 0 elements", 0, userList.size());
    }



}

import dao.CompanyDao;
import dao.UserDao;
import dao.UserDaoImp;
import entity.Company;
import entity.User;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import pojo.RestResponse;
import pojo.Result;
import utils.Api;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String[] args) {

        Result result = new Result("United States of America", "US", "USA");
       // RestResponse restResponse = new RestResponse("Country found matching code [USA].", result, 200);

       // System.out.println(restResponse);

    }
}

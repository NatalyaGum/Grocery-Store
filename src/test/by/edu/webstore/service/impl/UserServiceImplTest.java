package by.edu.webstore.service.impl;


import by.edu.webstore.dao.DaoProvider;
import by.edu.webstore.dao.ProductDao;
import by.edu.webstore.dao.UserDao;
import by.edu.webstore.entity.User;
import by.edu.webstore.exception.DaoException;
import by.edu.webstore.exception.ServiceException;
import by.edu.webstore.service.ProductService;
import by.edu.webstore.service.ServiceProvider;
import by.edu.webstore.service.UserService;
import by.edu.webstore.util.PasswordEncoder;
import by.edu.webstore.util.validator.UserValidator;
import org.mockito.*;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;


public class UserServiceImplTest {

    private MockedStatic<DaoProvider> daoProviderHolder;
    private MockedStatic<UserValidator> userValidator;
    private MockedStatic<PasswordEncoder> passwordEncoder;

    @Mock
    private DaoProvider daoProvider;
    @Mock
    private UserDao userDao;
    @Mock
    private UserValidator validator;

    @InjectMocks
    private UserService userService = ServiceProvider.getInstance().getUserService();

    @BeforeClass
    public void init() {
        MockitoAnnotations.openMocks(this);
    }

    @BeforeTest
    public void initStatic() {
        daoProviderHolder = mockStatic(DaoProvider.class);
        userValidator = mockStatic(UserValidator.class);
        passwordEncoder = mockStatic(PasswordEncoder.class);
    }



    @Test
    public void testFindAllEntities() {

    }

    @Test
    public void testFindUserById()  {

    }

    @Test
    public void testRegisterUser() {

    }

    @Test
    public void testIsEmailExist() {
    }

    @Test (dataProvider = "userLoginPassword")
    public void testFindUser(String email, String password) throws DaoException, ServiceException {
        userValidator.when(UserValidator::getInstance).thenReturn(validator);
        daoProviderHolder.when(DaoProvider::getInstance).thenReturn(daoProvider);
        when(daoProvider.getUserDao()).thenReturn(userDao);
       // passwordEncoder.when(() -> PasswordEncoder.pasEncode(anyString())).thenReturn(Optional.empty());

        when(validator.checkEmail(anyString())).thenReturn(true);
        when(validator.checkPassword(anyString())).thenReturn(true);
        when(userDao.findUserByEmailAndPassword(anyString(),PasswordEncoder.pasEncode(anyString()))).thenReturn(Optional.empty());
       // doNothing().when(userDao).close();

        Optional<User> user = userService.findUser(email, password);
        Assert.assertTrue(user.isPresent());
    }

    @Test
    public void testUpdateUser() {
    }

    @Test
    public void testBlockUser() {
    }

    @Test
    public void testMakeAdmin() {
    }


    @DataProvider(name = "userLoginPassword")
    public Object[][] getUserLoginPassword() {
        return new Object[][]{
                {"noname1@mail.ru", "nbg222"}//, {"noname2@mail.ru", "nbg222"}
        };
    }
}
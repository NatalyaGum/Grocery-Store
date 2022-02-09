package by.edu.webstore._MAIN_;

import by.edu.webstore.exception.DaoException;
import by.edu.webstore.dao.impl.UserDaoImpl;
import by.edu.webstore.entity.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class main {

    static Logger logger = LogManager.getLogger();
    public static void main(String[] args) throws DaoException {
            System.out.println("121");
        logger.info("text: ");
      // ConnectionPool.getInstance();
        UserDaoImpl userDao=new UserDaoImpl();
        User user=new User("natalyagum@yandex.ru", "qwerty", "Natalya","Gum","+37525", User.Role.CLIENT,User.Status.ACTIVE);
       // userDao.insertNewEntity(user);
        userDao.findAllEntities();
    }

}

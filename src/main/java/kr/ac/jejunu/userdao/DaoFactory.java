package kr.ac.jejunu.userdao;

import org.springframework.context.annotation.Bean;

public class DaoFactory {
    @Bean
    public UserDao userDao() {
        return new UserDao(connectionMaker());
    }

    @Bean
    private JejuConnectionMaker connectionMaker() {
        return new JejuConnectionMaker();
    }
}

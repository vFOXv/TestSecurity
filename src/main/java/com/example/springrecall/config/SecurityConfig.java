package com.example.springrecall.config;

import com.example.springrecall.Services.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

import javax.sql.DataSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private UserService userService;

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().disable()
                .authorizeRequests()
                //в корень каталога могут заходить все
                .antMatchers("/").permitAll()
                //в  /show пропускаються только авторизированные пользователи
                //если поставить /show** то он пропускает всех аутенфицированных пользователей дальше
                //не смотря на запреты ниже
                .antMatchers("/show").authenticated()/*hasAnyRole(Role.ADMIN.name(),Role.USER.name())*/
                .antMatchers("/show/only_for_admins/**").hasRole("ADMIN")
                .antMatchers("/show/read_profile/**").hasAuthority("READ_PROFILE")
                //.antMatchers(HttpMethod.POST,"/show/**").hasRole(Role.ADMIN.name())
                //.antMatchers(HttpMethod.DELETE,"/show/**").hasRole(Role.ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                //окошко в котором вводяться логин и пароль
                //.httpBasic();

                //или страница входа(аунтификации) (ввод логина и пароля)
                //по умолчанию стандартная или можно настроить
                .formLogin()
                //логаут переводит в корень приложения
                .and()
                .logout().logoutSuccessUrl("/");
    }

    //in Memory
//    @Bean
//    @Override
//    protected UserDetailsService userDetailsService() {
//        // UserDetails минимальная информация о пользователе
//        UserDetails user = User.builder()
//                .username("admin")
//                //https://bcrypt-generator.com/  admin
//                .password("{bcrypt}$2a$12$SHJ5F9suWxKhgddB6jVgWu/UYyVbbBfPe.z2KUFa147VEQYBd2S/O")
//                .roles("ADMIN","USER")
//                .build();
//        UserDetails admin = User.builder()
//                .username("user")
//                //https://bcrypt-generator.com/  user
//                .password("{bcrypt}$2a$12$4GXpzEIsAjROEcBqX2eoneTTyQMLYK3j974OLu9PlcD0lO3x0UEhq")
//                .roles("USER")
//                .build();
//        // InMemoryUserDetailsManager  хранение users в памяти(логин и пароль)
//        return new InMemoryUserDetailsManager(user, admin);
//    }

    //jdbcAuthentication
    //ложит пользователей в DB при каждой загрузки программы
    //и если такой user есть то удаляет его через if
//    @Bean
//    public JdbcUserDetailsManager jdbcUserDetailsManager(DataSource dataSource){
        //создание пользователей
////        UserDetails user = User.builder()
////                .username("admin")
////                //https://bcrypt-generator.com/  admin
////                .password("{bcrypt}$2a$12$SHJ5F9suWxKhgddB6jVgWu/UYyVbbBfPe.z2KUFa147VEQYBd2S/O")
////                .roles("ADMIN","USER")
////                .build();
////        UserDetails admin = User.builder()
////                .username("user")
////                //https://bcrypt-generator.com/  user
////                .password("{bcrypt}$2a$12$4GXpzEIsAjROEcBqX2eoneTTyQMLYK3j974OLu9PlcD0lO3x0UEhq")
////                .roles("USER")
////                .build();
//        //создание списка пользователей     dataSours это настройки DB в resources/application.properties
//        //если пользователи заранее внесены в DB нужна только эта строка
//        JdbcUserDetailsManager jdbcUserDetailsManager = new JdbcUserDetailsManager(dataSource);
//        //проверка в DB наличия конкретного пользователя и если он есть - удаление
//        //т.к. если записать пользователя с тем же логином и паролем будет ошибка
////        if(jdbcUserDetailsManager.userExists(user.getUsername())){
////            jdbcUserDetailsManager.deleteUser(user.getUsername());
////        }
////        if(jdbcUserDetailsManager.userExists(admin.getUsername())){
////            jdbcUserDetailsManager.deleteUser(admin.getUsername());
////        }
////        jdbcUserDetailsManager.createUser(user);
////        jdbcUserDetailsManager.createUser(admin);
//        return jdbcUserDetailsManager;
//    }

    //преобразователь паролей(он шифрует их {bcrypt})
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    //DaoAuthenticationProvider получает логин и пароль и проверяет в DB наличее такого пользователя
    //если пользователь есть, то ложит его в SpringSecurityContext
    public DaoAuthenticationProvider daoAuthenticationProvider(){
        DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        //шифрует пароль при помощи PasswordEncoder созданного выше
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        //проверяет наличее пользователя в DB по логину и паролю
        //setUserDetailsService() прописываеться в Services
        authenticationProvider.setUserDetailsService(userService);

        return authenticationProvider;

    }
}

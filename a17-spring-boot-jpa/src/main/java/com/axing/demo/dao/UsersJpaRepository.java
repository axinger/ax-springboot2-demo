package com.axing.demo.dao;

import com.axing.demo.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UsersJpaRepository extends JpaRepository<Users,Integer> {

    List<Users> findByUserName(String userName);
    List<Users> findByUserNameLike(String userName);


    /**
     * 4.@Query 注解
     * 有的时候，Spring Data规范里提供的查询关键字并不能满足我们的查询需求，这个时候就可以使用 @Query 关键字，来自定义查询 SQL。
     * nativeQuery：代表本地查询，就是使用原生的sql语句。
     * @return
     */
    @Query(value="select * from users  where id=(select max(id) from users)",nativeQuery = true)
    Users getMaxIdUser();

    /**
     * 5.@Param注解
     * 用来注入参数
     * @param userName
     * @return
     */
    @Query(value = "select * from users where user_name like %:userName%",nativeQuery = true)
    List<Users> findByNameMatch(@Param("userName") String userName);
}

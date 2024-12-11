package study.cloud_user_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import study.cloud_user_service.entity.User;
import study.cloud_user_service.response.UserInfo;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {

    @Query("select new study.cloud_user_service.response.UserInfo(u.email, u.name, u.userId, u.createdDate) from User u")
    List<UserInfo> findAllUserInfo();

}

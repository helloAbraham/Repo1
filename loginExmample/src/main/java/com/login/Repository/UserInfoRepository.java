package com.login.Repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.login.Entity.UserInfo;

@Repository
public interface UserInfoRepository extends JpaRepository<UserInfo, Integer>{
	Optional<UserInfo> findByUsername(String username);
}

package sample.multimodule.user.api;

import sample.multimodule.domain.entity.UserDetails;

public interface UserService {

	public UserDetails saveUser(UserDetails userInfo);
}

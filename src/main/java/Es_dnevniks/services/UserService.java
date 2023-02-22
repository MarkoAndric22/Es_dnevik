package Es_dnevniks.services;

import Es_dnevniks.controllers.util.RESTError;
import Es_dnevniks.entities.UserEntity;
import Es_dnevniks.entities.dto.UserEntityDTO;

public interface UserService {
	
	public UserEntity addUser(UserEntityDTO users)throws RESTError;
	public UserEntity modify(Integer id,UserEntityDTO users)throws RESTError;
	public UserEntity delete(Integer id)throws RESTError;

}

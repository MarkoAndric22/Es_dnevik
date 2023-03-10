package Es_dnevniks.services;

import Es_dnevniks.entities.EmailObject;

public interface EmailService {
	
	public void sendSimpleMessage(EmailObject object) throws Exception;

}

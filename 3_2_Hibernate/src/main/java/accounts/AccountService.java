package accounts;

import db.DBService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author v.chibrikov
 *         <p>
 *         Пример кода для курса на https://stepic.org/
 *         <p>
 *         Описание курса и лицензия: https://github.com/vitaly-chibrikov/stepic_java_webserver
 */
public class AccountService {
    private final Map<String, UserProfile> sessionIdToProfile;

    private final DBService dbService;

    public AccountService() {
        sessionIdToProfile = new HashMap<>();
        dbService = new DBService();
    }

    public void addNewUser(UserProfile userProfile) {

        dbService.addUser(userProfile.getLogin(), userProfile.getPass());

    }

    public UserProfile getUserByLogin(String login) {

        return dbService.getUser(login);

    }

    public UserProfile getUserBySessionId(String sessionId) {
        return sessionIdToProfile.get(sessionId);
    }

    public void addSession(String sessionId, UserProfile userProfile) {
        sessionIdToProfile.put(sessionId, userProfile);
    }

    public void deleteSession(String sessionId) {
        sessionIdToProfile.remove(sessionId);
    }
}

package requests.testsData;

public class CourierCredsData {
    private String login;
    private String password;

    public CourierCredsData(){}

    public CourierCredsData(String login, String password){
        this.login = login;
        this.password = password;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}

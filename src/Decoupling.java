public interface Decoupling {

    public final static String ACTION_BACK = "BACK";
    public final static String ACTION_LOGIN = "LOGIN";
    public final static String ACTION_REGISTRIEREN = "REGISTRIEREN";
    public final static String ACTION_UPDATE = "UPDATE";


    public String getFullName();
    public String getMatrikel();
    public String getIfw();
    public String getUser();
    public String getVorname();
    public String getNachname();
    public String getStraße();
    public String getBirthday();

    public String getPassword();
    public String getPassword2();
    public String getPassword3();

    public void setRegistration();
    public void setLogin();
    public void setLoggedIn();
    public void setAdminLoggedIn();

    public void setFullName(String str);
    public void setPass(String str);


}

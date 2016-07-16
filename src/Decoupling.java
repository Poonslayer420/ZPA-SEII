public interface Decoupling {
    public final static String ACTION_CREATE = "CREATE";
    public final static String ACTION_NEXT = "NEXT";
    public final static String ACTION_BACK = "BACK";
    public final static String ACTION_LOGIN = "LOGIN";
    public final static String ACTION_REGISTRIEREN = "REGISTRIEREN";
    public final static String ACTION_UPDATE = "UPDATE";


    public String getFullName();

    public void setRegistration();
    public void setLogin();
    public void setLoggedIn();

    public String getPassword();
}

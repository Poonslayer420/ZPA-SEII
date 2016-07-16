import com.sun.xml.internal.fastinfoset.util.StringArray;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Created by tobia on 02.07.2016.
 */
public class ZPAMainTEST {


    public static void main(String[] args) throws SQLException, ClassNotFoundException {




        //String[] TestUSer = {"test","test12345","99999999","99999","Mustermann","Max","2000-01-01","Musterstr. 1","Muster@Mustermann.de","1","0"};
        //System.out.println(checkInput(TestUSer));

        //checkExecutor();
        String[] TestUSer2 = {"TestUser","test12345","99999999","99999","Mustermann","Max","2000-01-01","Musterstr. 1","Muster@Mustermann.de","1","0"};
        System.out.println(checkInput(TestUSer2));



    }

    private static boolean checkInput(String[] user) throws SQLException {
        DBCon ZPA = new DBCon();
        ZPA.connect_DB();

        int i = 0;
        int k = 1;

        while(i <= 10) {
            if(!user[i].equals(ZPA.getData(user[i], k))) {
                //ZPA.disconnect();
                return false;
            }
            else
            i++;
            k++;
        }
        //ZPA.disconnect();
        return true;

    }

    private static boolean checkExecutor() throws SQLException {
        DBCon ZPA = new DBCon();
        ZPA.connect_DB();

        String SetValue = "TestUser";
        String Mtrikel = "99999999";
        String IFW = "99999";

        return ZPA.executeSqlQuery(SetValue,Mtrikel,IFW, 1);

    }
}
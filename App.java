import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public static void main(String[] args) throws Exception {

        Scanner scanObj = new Scanner(System.in);
        
        String stringDB = JSONFileHandler.readFromJSONFile();

        JSONObject databaseObj = new JSONObject(stringDB);
        ArrayList<Account> accountList = JSONFileHandler.loadAccounts(databaseObj);
        Database nDB = new Database(accountList);

        try {
            ArrayList<Group> groupList = JSONFileHandler.loadGroups(databaseObj, accountList);
            nDB.setGroups(groupList);
        } catch (JSONException e) {
            System.out.println("An error has occurred. Possibly no group in DB.");
            e.printStackTrace();
        }
        JSONFileHandler.writeToJSONFile(JSONFileHandler.dbToJSONObject(nDB));
    }
}

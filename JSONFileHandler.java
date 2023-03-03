import org.json.JSONArray;
import org.json.JSONObject;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class JSONFileHandler {

    public static void createJSONFile() {
        try {
            File fileObject = new File("db.json");
            if (fileObject.createNewFile()) {
                System.out.println("File created");
            }
            else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error has occured.");
            e.printStackTrace();
        }
    }

    public static void writeToJSONFile(JSONObject db) {
        try {
            FileWriter myWriter = new FileWriter("db.json");
            myWriter.write(db.toString(4));
            myWriter.close();
            System.out.println("Successfully wrote to the file.");
          } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
          }
    } 

    public static String readFromJSONFile() {
        try {
            String data = "";
            File myObj = new File("db.json");
            Scanner myReader = new Scanner(myObj);
            while (myReader.hasNextLine()) {
              
              data += myReader.nextLine();
            }
            myReader.close();
            return data;
          } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return "Niggerlicious code !";
          }
    }

    public static ArrayList<Account> loadAccounts(JSONObject databaseObj) {
        JSONArray accountObj = databaseObj.getJSONArray("accounts");
        JSONObject tempObj = new JSONObject();
        ArrayList<Account> accountList = new ArrayList<Account>();
        for (int i = 0; i < accountObj.length(); i++) {
            tempObj = accountObj.getJSONObject(i);
            Account tempAcc = new Account(tempObj.getString("title"), tempObj.getString("username"), tempObj.getString("password"));
            accountList.add(tempAcc);
        }       
        return accountList;
    }

    public static ArrayList<Group> loadGroups(JSONObject databaseObj, ArrayList<Account> accountList) {
        /*
         * Instantiates Group objects from the database JObject.
         * Wacky use of .names() JSONArray function to get the key to the current element being iterated over.
         * Then compares the names in the db JSONObject to the names/titles of Accounts in the account list.
         */
        ArrayList<Group> groupList = new ArrayList<Group>();
        JSONArray groupObj = databaseObj.getJSONArray("groups");
        JSONObject tempObj = new JSONObject();
        for (int i = 0; i < groupObj.length(); i++) {
            tempObj = groupObj.getJSONObject(i);
            JSONArray key = tempObj.names();
            JSONArray tempArray = tempObj.getJSONArray(key.get(0).toString());
            ArrayList<Account> tempAccList = new ArrayList<Account>();
            for (int n = 0; n < tempArray.length(); n++) {
                for (int t = 0; t < accountList.size(); t++) {
                    if (tempArray.getString(n).equals(accountList.get(t).getName())) {
                        tempAccList.add(accountList.get(t));
                    }
                }
            }
            Group tempGroup = new Group(key.get(0).toString());
            tempGroup.setGroup(tempAccList);
            groupList.add(tempGroup);
        }
        return groupList;
    }

    public static JSONObject dbToJSONObject(Database db) {
        // Init JObjects
        JSONObject dbJObject = new JSONObject();
        JSONObject temporaryJObject = new JSONObject();
        // Init JArrays
        JSONArray accountJArray = new JSONArray();
        JSONArray groupJArray = new JSONArray();
        // Populate accountJArray 
        ArrayList<Account> accounts = db.getAccounts();
        for (int i = 0; i < accounts.size(); i++) {
            temporaryJObject = new JSONObject();
            temporaryJObject.put("title", accounts.get(i).getName());
            temporaryJObject.put("username", accounts.get(i).getUsername());
            temporaryJObject.put("password", accounts.get(i).getPassword());
            accountJArray.put(temporaryJObject);
        }
        dbJObject.put("accounts", accountJArray);
        // Populate groupJArray
        if (db.getGroups() != null) {
            ArrayList<Group> groups = db.getGroups();
            for (int i = 0; i < groups.size(); i++) {
                temporaryJObject = new JSONObject();
                temporaryJObject.put(groups.get(i).getName(), groups.get(i).accountsToArrayList());
                groupJArray.put(temporaryJObject);
            }
            dbJObject.put("groups", groupJArray.toList());
        }
        return dbJObject;
    }
}

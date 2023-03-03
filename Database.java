import java.util.ArrayList;

public class Database {
    
    private ArrayList<Account> accounts;
    private ArrayList<Group> groups;

    public Database(ArrayList<Account> accounts) {
    
        this.accounts = accounts;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public ArrayList<Group> getGroups() {
        return groups;
    }

    public void setAccounts(ArrayList<Account> accounts) {
        this.accounts = accounts;
    }

    public void setGroups(ArrayList<Group> groups) {
        this.groups = groups;
    }

}

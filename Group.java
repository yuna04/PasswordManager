import java.util.ArrayList;

public class Group {
    
    private String name;
    private ArrayList<Account> group;

    public Group(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }

    public ArrayList<String> accountsToArrayList() {
        ArrayList<String> strArr = new ArrayList<String>();
        for (int i = 0; i < group.size(); i++) {
           strArr.add(group.get(i).getName());
        }
        return strArr;
    }

    public void printAccounts() {
        for (int i = 0; i < group.size(); i++) {
            System.out.println(group.get(i));
        }
    }

    public String getName() {
        return name;
    }

    public ArrayList<Account> getGroup() {
        return group;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGroup(ArrayList<Account> group) {
        this.group = group;
    }
}

public class Account {

    private String name, username, password;
    private Group group = null;

    public Account(String name, String username, String password) {
        this.name = name;
        this.username = username;
        this.password = password;
    }

    @Override
    public String toString() {
        return String.format("%s\nUsername: %s\nPassword: %s", this.name, this.username, this.password);
        // return name;
    }

    public String getName() {
        return name;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public Group getGroup() {
        return group;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setGroup(Group group) {
        this.group = group;
    }
}

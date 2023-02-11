import db.PostgresDB;
import db.i_db;
import user.User_Controller;
import user.User_DB;
import user.i_User;

public class Main {
    public static void main(String[] args) {
        i_db db = new PostgresDB();
        i_User repo = new User_DB(db);
        User_Controller controller = new User_Controller(repo);

        MyApplication app = new MyApplication(controller);
        app.start();
    }
}
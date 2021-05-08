package geotrack;

import java.io.File;

public class User {

    File usersFile;

    public User (File guestFile) throws Exception {
        usersFile=guestFile;
        if (!this.usersFile.exists()) {
            this.usersFile.createNewFile();
        }
    }
}

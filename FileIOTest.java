public class FileIOTest {

    public static void main(String args[]) {

        // Read PlayerData file
        ProjectFileIO.readFile();

        // Get username input from user
        String user = IR4.getString("Please enter your username");
        
        // Check array for username
        boolean nameExists = false;
        nameExists = checkIfNameExistsInArray(user);

        // ======================== Existing User ======================== //
        if (nameExists) {
            System.out.println("Password check for user: " + user);
        }
        // ====================== End Existing User ====================== //

        // ======================== Creating User ======================== //
        if (!nameExists) {
            System.out.println("Creating user...");
        }
        // ====================== End Creating User ====================== //
    }

    // Check array for existing player with name
    public static boolean checkIfNameExistsInArray(String name) {

        // Import playerArray
        Player[] array = ProjectFileIO.getPlayerArray();

        // Loop through to check player names
        for (int i = 0; i < array.length; i++) {
            if (array[i].getName().equals(name)) {
                return true;
            }
        }
        return false;
    }


}
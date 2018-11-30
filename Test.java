import java.io.*;
import java.util.*;

public class Test {

    public static void main(String args[]) throws IOException {

        //ProjectFileIO_v2.runFileIO();

        /*
        ArrayList<String> myArrayList = new ArrayList<String>();
        myArrayList = ProjectFileIO_v2.getDictionary("TestLibrary.txt");
        System.out.println(myArrayList.toString());
        System.out.println(myArrayList.get(0).charAt(0));
        */
        /*
        ArrayList<Player> players = new ArrayList<Player>();
        players = ProjectFileIO_v2.getTopTen();
        System.out.println(players.toString());
        */

        /*try {
            ProjectFileIO_v2.readFile();
        } catch(IOException io) {
            System.out.println("IOException " + io.getMessage());
        }

        ArrayList<Player> players = new ArrayList<Player>();
        players = ProjectFileIO_v2.getPlayerArrayList();
        System.out.println(players.get(0).getHighScore());
        System.out.println(players.get(0).getName());
        */

        // ProjectFileIO_v2.displayTopTen();

        ProjectFileIO_v2.updateHighScore("sdg", 7777778);


    }    
}
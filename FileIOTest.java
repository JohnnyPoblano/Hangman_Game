import java.io.*;
import java.util.*;

public class FileIOTest {

    public static void main(String args[]) throws IOException {

        //ProjectFileIO_v2.runFileIO();

        ArrayList<String> myArrayList = new ArrayList<String>();
        myArrayList = ProjectFileIO_v2.getDictionary("TestLibrary.txt");
        System.out.println(myArrayList.toString());
        System.out.println(myArrayList.get(0));

    }    
}
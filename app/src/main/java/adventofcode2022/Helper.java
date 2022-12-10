package adventofcode2022;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Helper {
    public Helper() {}

    public List<String> convertFileToStringList(String name) {
        List<String> fileData = new ArrayList<String>();

        try {
            URL url = getClass().getResource(name);
            File file = new File(url.getPath());
            Scanner reader = new Scanner(file);
            while (reader.hasNextLine()) {
                fileData.add(reader.nextLine());
            }
            reader.close();
        } catch (FileNotFoundException e) {
            System.out.println("Unable to read file. Maybe it doesn't exist?");
            e.printStackTrace();
        }

        return fileData;
    }

    public String[] listDirectoriesInWorkingDirectory() {
        URL url = getClass().getResource(".");
        File file = new File(url.getPath());
        String[] directories = file.list(new FilenameFilter() {
            @Override
            public boolean accept(File current, String name) {
              return new File(current, name).isDirectory();
            }
          });

        return directories;
    }
}

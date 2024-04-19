import javax.swing.JFileChooser;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import static java.nio.file.StandardOpenOption.CREATE;

public class Main
{
    public static void main(String[] args)
    {
        JFileChooser chooser = new JFileChooser(); // initialize JFileChooser
        File selectedFile;
        String rec = ""; // rec will hold each line
        int lines = 0; // line count
        int words = 0; // word count
        int characters = 0; // character count
        String[] dataElements;

        try
        {
            File workingDirectory = new File(System.getProperty("user.dir"));

            chooser.setCurrentDirectory(workingDirectory);

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile(); // sets selectedFile to the file chosen by the user
                Path file = selectedFile.toPath();
                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));


                while (reader.ready())
                {
                    rec = reader.readLine(); // sets rec to the current line of the file
                    dataElements = rec.split(" "); // sets each item of the array to a single word
                    lines++;
                    System.out.printf("\nLine %4d %-60s", lines, rec);
                    words += dataElements.length;
                    characters += rec.length();

                }

                reader.close();


                System.out.println("\n\nFile read");
                System.out.println("\nFile name: " + selectedFile);
                System.out.println("Total number of lines: " + lines);
                System.out.println("Total number of words: " + words);
                System.out.println("Total number of words: " + characters);

            }
        }

        catch (FileNotFoundException e)
        {
            System.out.println("File not found");
            e.printStackTrace();
        }

        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
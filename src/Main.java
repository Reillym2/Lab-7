
import java.io.*;

public class Main {

    public static void main(String[] args) {

        File studentFile = new File("C:\\Users\\Reilly Martinez\\IdeaProjects\\Lab 7\\src\\students.txt");

        System.out.println("File location: "
                + studentFile.getAbsolutePath());

        if (!studentFile.exists()) {
            System.out.println("ERROR: students.txt not found!");
            return;
        }

        // Put Comment on RandomAccessFile
        // RandomAccessFile opens the StudentFile and allows the program to read and
        // Write Student records

        try (RandomAccessFile file =
                     new RandomAccessFile(studentFile, "rw")) {

            System.out.println("File size: "
                    + file.length() + " bytes");

            String line;
            boolean found = false;

            while (true) {

                // Put Comment on getFilePointer() method
                // The getFilePointer() stores current file positions so we can return
                // To student records if an update is needed

                long position = file.getFilePointer();

                line = file.readLine();

                if (line == null) {
                    break;
                }

                System.out.println("Reading: " + line);

                // Put Comment on split() method
                // The split() method is separating the student record at each comma and stored
                //each value in a string array

                String[] data = line.split(",");

                if (data.length < 3) {
                    continue;
                }

                // The trim() method is removing extra spaces found in the sart and end string
                String studentID = data[0].trim();

                if (studentID.equals("1002")) {

                    String updated =
                            "1002,Alice Brown,3.9";

                    // Verify same byte length
                    // This line of code verifies that the updated student record is not longer
                    // Or shorter than the original

                    if (updated.length() != line.length()) {
                        System.out.println(
                                "ERROR: Record length mismatch!");
                        break;
                    }

                    // Put Comment on seek(position) method
                    // The seek(position) moves to a saved position in the Student record
                    // so it can be updated.

                    file.seek(position);

                    // Put Comment on writeBytes() method
                    // The writeBytes() updates the information in the student file
                    file.writeBytes(updated);

                    System.out.println("Record updated!");

                    found = true;
                    break;
                }
            }

            if (!found) {
                System.out.println(
                        "Student ID 1002 was not updated.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

// The output for the program is
//File size: 61 bytes
//Reading: 1001,John Smith,3.5
//Reading: 1002,Alice Brown,3.9
//Record updated!
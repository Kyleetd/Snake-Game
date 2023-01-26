package persistence;

import org.json.JSONObject;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

// DISCLAIMER: I used the class JsonWriter from example JsonSerializationDemo on github to build this class.
//             https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public class JsonWriter {
    private PrintWriter writer;
    private String destination;

    // EFFECTS: constructs writer to write to destination file
    public JsonWriter(String destination) {
        this.destination = destination;
    }

    // MODIFIES: This.
    // EFFECTS: Opens writer; throws FileNotFoundException if destination file cannot
    //          be opened for writing.
    public void open() throws FileNotFoundException {
        writer = new PrintWriter(new File(destination));
    }

    // MODIFIES: This.
    // EFFECTS: Writes JSON to file.
    public void write(JSONObject json) {
        saveToFile(json.toString(4));
    }

    // MODIFIES: This.
    // EFFECTS: Closes writer.
    public void close() {
        writer.close();
    }

    // MODIFIES: This.
    // EFFECTS: Writes string to file.
    private void saveToFile(String json) {
        writer.print(json);
    }
}

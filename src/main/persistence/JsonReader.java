package persistence;

import model.Coordinate;
import model.LeaderboardModel;
import model.LeaderboardEntry;
import model.SnakeModel;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// DISCLAIMER: I used the class JsonReader from example JsonSerializationDemo on github to build this class.
//             https://github.students.cs.ubc.ca/CPSC210/JsonSerializationDemo.git

public class JsonReader {
    private String filePath;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.filePath = source;
    }

    // EFFECTS: reads workroom from file and returns it;
    // throws IOException if an error occurs reading data from file
    public JSONObject read() throws IOException {
        String jsonData = readFile(filePath);
        JSONObject jsonObject = new JSONObject(jsonData);
        return jsonObject;
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(contentBuilder::append);
        }

        return contentBuilder.toString();
    }
}

package bg.softuni._productshop.constants;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.util.List;

public enum Utils {
    ;
    public static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();
    public static void writeJsonToFile(List<?> list, Path path) throws IOException {
        final FileWriter fileWriter = new FileWriter(path.toFile());
        GSON.toJson(list,fileWriter);
        fileWriter.flush();
        fileWriter.close();
    }
}

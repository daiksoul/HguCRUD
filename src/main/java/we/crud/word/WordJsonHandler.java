package we.crud.word;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.stream.JsonReader;
import com.google.gson.stream.JsonWriter;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WordJsonHandler {
    static Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().setPrettyPrinting().create();

    public static JsonArray toJsonArray(List<Word> wordList){
        JsonArray toReturn = new JsonArray();
        for(Word word: wordList){
            toReturn.add(gson.toJsonTree(word));
        }
        return toReturn;
    }

    public static List<Word> fromJson(JsonArray array){
        List<Word> list = new ArrayList<>();
        for(JsonElement element: array){
            list.add(gson.fromJson(element,Word.class));
        }
        return list;
    }

    public static void saveFile(List<Word> wordList){
        try {
            JsonWriter writer = gson.newJsonWriter(new FileWriter("word.json"));
            writer.jsonValue(gson.toJson(toJsonArray(wordList)));
            writer.close();
            System.out.println("파일을 저장하였습니다!");
        } catch (IOException e) {
            System.out.println("파일 저장에 실패했습니다!");
        }
    }

    public static List<Word> loadFile(){
        try {
            JsonReader reader = gson.newJsonReader(new FileReader("word.json"));
            JsonArray array = gson.fromJson(reader,JsonArray.class);
            reader.close();
            return fromJson(array);
        } catch (IOException e) {
            System.out.println("파일 로딩에 실패했습니다!");
            return null;
        }
    }
}

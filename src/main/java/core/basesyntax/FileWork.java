package core.basesyntax;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;

public class FileWork {
    public String[] readFromFile(String fileName) {
        File file = new File(fileName);
        String[] strings = new String[300];
        String[] result = new String[strings.length];
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(file))) {
            if (file.length() == 0) {
                return new String[0];
            }
            StringBuilder builder = new StringBuilder();
            int value = bufferedReader.read();
            int count = 0;
            int count1 = 0;
            while (value != -1) {
                while (value != -1 && !Character.isWhitespace((char)value)) {
                    builder.append((char)value);
                    value = bufferedReader.read();
                }
                if (builder.length() > 0) {
                    strings[count++] = builder.toString();
                    builder.setLength(0);
                }
                value = bufferedReader.read();
            }
            for (String string : strings) {
                if (string != null && !string.isEmpty()) {
                    String cleaned = string.replaceAll("[^a-zA-Z]", "");
                    if (string.charAt(0) == 'w' || string.charAt(0) == 'W') {
                        result[count1++] = cleaned.toLowerCase();
                    }
                }
            }
            Arrays.sort(result, 0, count1);
            return Arrays.copyOf(result, count1);
        } catch (IOException e) {
            throw new RuntimeException("cant read file ",e);
        }
    }
}

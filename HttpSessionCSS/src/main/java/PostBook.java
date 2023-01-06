import java.io.*;
import java.util.*;

public class PostBook {
    private Map<String, String> post = new HashMap<>();


    public PostBook(){

    }

    public synchronized void add(String name, String text, String author, String time) {
        if (name != null || text != null) {
            if (!name.equals("") && !text.equals("")) {
                post.put(name, text + " " + author + " " + time);
            }
            try {
                addInFile();
            } catch (IOException r) {
                System.out.println("error");
            }
        }
    }
    public synchronized void addInFile() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : post.entrySet()) {
            String name = entry.getKey() + " " + entry.getValue();
//            System.out.println(name);
            stringBuilder.append(name).append("\n");
        }
        for(int i = 0; i < stringBuilder.length(); i++)
        {
            if(stringBuilder.charAt(i) == ']' || stringBuilder.charAt(i) == '[' || stringBuilder.charAt(i) == ','){
                stringBuilder.deleteCharAt(i);
            }
        }
        File file = new File("/home/kenan/JavaProjects/HttpSessionCSS/Post.txt");
        FileWriter fw = new FileWriter(file);
        fw.write(String.valueOf(stringBuilder));
        fw.flush();
        fw.close();
    }
    public synchronized StringBuffer get() {
        StringBuffer stringBuffer = new StringBuffer();
        for (Map.Entry<String, String> entry : post.entrySet()) {
            String name = entry.getKey() + " ";
            String text = entry.getValue();

            stringBuffer.append("<h1>").append(name).append("</h1>").append(text).append("<hr>");
        }
        return stringBuffer;
    }
    public synchronized void file() {
        try {
            File file = new File("/home/kenan/JavaProjects/HttpSessionCSS/Post.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String read;
            StringTokenizer stringTokenizer;
            String text2 = null;
            String text1 = null;
            String author = null;
            String time = "";
            while ((read = br.readLine()) != null) {
                stringTokenizer = new StringTokenizer(read, " ");
                for (int i = 0; stringTokenizer.hasMoreTokens(); i++) {
                    if (i == 0) {
                        text1 = stringTokenizer.nextToken();
                    }
                    else if(i == 1) {
                        text2 = stringTokenizer.nextToken();
                    }
                    else if(i == 2) {
                        author = stringTokenizer.nextToken();
                    }
                    else {
                        time += stringTokenizer.nextToken() + " ";
                    }
                }
                post.put(text1, text2 + " " + author + " " + time);
                time = "";
            }
        } catch (IOException e) {
            System.out.println(e);
        }
    }
}

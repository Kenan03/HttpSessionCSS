import javax.swing.*;
import java.io.*;
import java.util.*;

public class Notebook {
    private Map<String, String> map = new HashMap<>();
    public Notebook() {}

    public synchronized void add(String name, String number) {
        if(name != null || number != null) {
            if(!name.equals("") && !number.equals("")) {
                if (map.get(name) == null) {
                    map.put(name, number);
                } else {

                }
                try {
                    addInFile();
                }
                catch (IOException r){
                    System.out.println("error");
                }
            }
        }
    }

    public synchronized StringBuffer getNamesStrings() {
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("<form action=\"Delete\" method=\"POST\"> " +
                "<p> <select name=\"el\">\n");
        int i = 0;
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if(entry.getKey().equals("admin")){continue;}
            else {
                String name = entry.getKey();
//            stringBuffer.append("<p>").append(name).append("<a href=\"delete.html\"> delete</a>").append("</p>");
                stringBuffer.append("<option value=" + name + "> " + name + "</option>");
                i++;
            }
        }
        stringBuffer.append("</select>");
        stringBuffer.append("<input type=\"submit\" value=\"delete\"> </form>\n");
        return stringBuffer;
    }
    public synchronized void delete(String name) throws IOException {
        String deleteName = "";
        String deletePassword = "";
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if(entry.getKey().equals(name))
            {
                deleteName = entry.getKey();
                deletePassword = entry.getValue();
            }
        }
        map.remove(deleteName, deletePassword);
        addInFile();
    }

    public synchronized void reset() throws IOException {
        map.clear();
//        File file = new File("/home/kenan/JavaProjects/HttpSessionCSS/notebook.txt");
//        FileWriter fw = new FileWriter(file);
//        fw.write("");
//        fw.flush();
//        fw.close();
    }

    public synchronized void addInFile() throws IOException {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, String> entry : map.entrySet()) {
            String name = entry.getKey() + " " + entry.getValue();
//            System.out.println(name);
            stringBuilder.append(name).append("\n");
        }
//        for(int i = 0; i < stringBuilder.length(); i++)
//        {
//            if(stringBuilder.charAt(i) == ']' || stringBuilder.charAt(i) == '[' || stringBuilder.charAt(i) == ','){
//                stringBuilder.deleteCharAt(i);
//            }
//        }
        File file = new File("/home/kenan/JavaProjects/HttpSessionCSS/notebook.txt");
        FileWriter fw = new FileWriter(file);
        fw.write(String.valueOf(stringBuilder));
        fw.flush();
        fw.close();
    }

    public synchronized Boolean find(String name, String number){
        for (Map.Entry<String, String> entry : map.entrySet()) {
            if(entry.getKey().equals(name)) {
                String pass = entry.getValue().toString();
//                String newText = pass.substring(1, pass.length() - 1);
                if(pass.equals(number)){
                    System.out.println(entry.getKey() + " " + entry.getValue());
                    return true;
                }
            }
        }
        return false;
    }
    public synchronized Boolean findName(String name){
        if(map.get(name) != null){
            return false;
        }
        return true;
    }

    public synchronized void file(){
        try {
            File file = new File("/home/kenan/JavaProjects/HttpSessionCSS/notebook.txt");
            BufferedReader br = new BufferedReader(new FileReader(file));
            String read;
            StringTokenizer stringTokenizer;
            String name = null;
            String number = null;
            while((read = br.readLine()) != null) {
                stringTokenizer = new StringTokenizer(read, " ");
                for (int i = 0; stringTokenizer.hasMoreTokens() ; i++) {
                    if(i == 0) {
                        name = stringTokenizer.nextToken();
                    }
                    number = stringTokenizer.nextToken();
                    map.put(name, number);

                }
            }
        }
        catch (IOException e)
        {
            System.out.println(e);
        }
    }
}
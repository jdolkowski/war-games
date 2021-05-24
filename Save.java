import java.io.*;

public class Save {
    public void save(General ... generals){
       try {
           FileOutputStream file = new FileOutputStream("zapis.txt");
           ObjectOutputStream object = new ObjectOutputStream(file);

           for (General i : generals) {
               object.writeObject(i);
           }
           object.close();
           file.close();
       } catch (FileNotFoundException e){
           System.out.println("nie znaleziono pliku");
       } catch (IOException e) {
           e.printStackTrace();
       }
    }

    public void readFromFIle(General ...generals){
        try{
            FileInputStream file = new FileInputStream("zapis.txt");
            ObjectInputStream object = new ObjectInputStream(file);

            for(General i : generals){
                i = (General) object.readObject();
            }

            file.close();
            object.close();
        } catch (FileNotFoundException e){
            System.out.println("nie znaleziono pliku");
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}

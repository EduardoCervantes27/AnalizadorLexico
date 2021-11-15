import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Leer_archivo {
    public String leertxt(String dir) {
        String texto = "";
        try{
            BufferedReader bf = new BufferedReader(new FileReader(dir));
            String temp = "";
            String bfRead;
            while ((bfRead = bf.readLine()) != null){
                temp += bfRead;
            }
            texto = temp;
        } catch(IOException e) {
            System.err.println("Archivo no encontrado");
        }
        return texto;
    }    
}

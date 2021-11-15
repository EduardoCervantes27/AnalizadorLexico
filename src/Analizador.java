import java.util.ArrayList;
import java.util.StringTokenizer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Analizador {
    static ArrayList<String> tablaReservadas = new ArrayList<>();
    static ArrayList<String> tablaSimbolos = new ArrayList<>();
    public static void main(String[] args){
        Leer_archivo a = new Leer_archivo();
        String archivo = "C:/Users/luis_/Documents/JavaProjects/AnalizadorLexicoDef/src/programa.txt";
        String textoArchivo = a.leertxt(archivo);
        System.out.println(textoArchivo);

        ArrayList<Token> tokens = lex(textoArchivo);
        System.out.println("################### TABLA TOKENS ###################");
        for (Token token : tokens) {
            System.out.println("(" + token.getTipo() + ": " + token.getValor() + ")");
        }
        System.out.println("################### TABLA PALABRAS RESERVADAS ###################");
        for(String valor : tablaReservadas) {
            System.out.println(valor);
        }
        System.out.println("################### TABLA SIMBOLOS ###################");
        for(String valor : tablaSimbolos) {
            System.out.println(valor);
        }
    }
    private static ArrayList<Token> lex(String input) {
        final ArrayList<Token> tokens = new ArrayList<Token>();
        final StringTokenizer st = new StringTokenizer(input);

        while(st.hasMoreTokens()) {
            String palabra = st.nextToken();
            boolean matched = false;
            for (Token.Tipos tokenTipo : Token.Tipos.values()) {
                Pattern patron = Pattern.compile(tokenTipo.patron);
                Matcher matcher = patron.matcher(palabra);
                if(matcher.find()) {
                    Token tk = new Token();
                    tk.setTipo(tokenTipo);
                    tk.setValor(palabra);
                    tokens.add(tk);
                    matched = true;

                    if(tk.getTipo().equals(Token.Tipos.PALABRA_RESERVADA)){
                        tablaReservadas.add(tk.getValor());
                    }
                    if(tk.getTipo().equals(Token.Tipos.DELIMITADOR)){
                        tablaSimbolos.add(tk.getValor());
                    }
                }
            }
    
            if (!matched) {
                throw new RuntimeException("Se encontró un token invalido.");
            }
        }

        return tokens;
    }

}

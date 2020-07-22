package automatos;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
public class Token {
static String reservadas[]={"void","double","char","boolean","package","import","try","throws","throw","catch","switch","return",
                            "for","case","break","new","static","class","public","private","if","while", "int","float","String","else"};
 static String delimitadores[] ={"(",")","{","}",",",";","[","]"}; 
 static String operadores[] ={"=","==","+","-","*","/"};
 static String relacionais[] ={">","<","<=","!=",">="};
 public static void TokenProuId(String conteudo,int ini, int fim,String nometoken){
      int t= 0;
      String palavra= conteudo.substring(ini,fim);
      String teste = "\\W\\D\\S";
      Pattern p = Pattern.compile(teste);
      Matcher m = p.matcher(palavra);
      boolean pr = false;
       while(t!=reservadas.length){
       if(palavra.equals(reservadas[t])){
           nometoken = "T_"+palavra;
         System.out.println("Palavra: "+palavra+" Tipo: "+" PALAVRA RESERVADA "+"Nome do Token: "+nometoken);
         pr = true;
                 }t = t+1;
       }
     if(m.find()){
       System.out.println(" Erro: "+" Cadeia mal informado ou não reconhecido! "+"Palavra:"+palavra);
       }
      else if( pr == false) {
        nometoken = "T_Id";
        System.out.println("Palavra: "+palavra+" Tipo: "+" IDENTIFICADOR "+"Nome do Token: "+nometoken);
     }}
     public static void TokenDeNumeros(String conteudo,int ini, int fim,String nometoken){
         String palavra = conteudo.substring(ini,fim);
      String teste = "[a-zA-Z]";
      Pattern p = Pattern.compile(teste);
      Matcher m = p.matcher(palavra);
     boolean pr = false;
       if(m.find()){System.out.println("Palavra: "+conteudo.substring(ini, fim)+" Erro: "+" Número mal informado!");}
     else if(palavra.contains(".")){
         System.out.println("Palavra: "+palavra+" Tipo: "+" PONTO FLUTUANTE "+"Nome do Token: "+nometoken);
        pr = true;}
      else if (pr!=true){ System.out.println("Palavra: "+palavra+" Tipo: "+" NÚMERO INTEIRO "+"Nome do Token: "+nometoken);
      }
    }
    public static void TokenDelimitadores(String conteudo,int ini, int fim,String nometoken){
    int t= 0;
      String palavra = conteudo.substring(ini,fim);
     boolean pr = false;
      while(t!=delimitadores.length){
       if(palavra.equals(delimitadores[t])){
         System.out.println("Palavra: "+palavra+" Tipo: "+" DELIMITADOR "+"Nome do Token: "+nometoken);
        pr = true;
                 }t = t+1;
    }       
     
       if(palavra.contains("/")){//reconhecer comentario
         System.out.println("Palavra: "+palavra+" Tipo: "+" DELIMITADOR "+"Nome do Token: "+nometoken);
        pr = true;
                 }
    if (pr!=true){
        fim++;
        System.out.println("Palavra: "+palavra+" ERROR:DELIMITADOR");}
    }
    public static void TokenOperadores(String conteudo,int ini, int fim,String nometoken){
    int t= 0;
      String palavra = conteudo.substring(ini,fim);
     boolean pr = false;
      while(t!=operadores.length){
       if(palavra.equals(operadores[t])){
         System.out.println("Palavra: "+palavra+" Tipo: "+" OPERADOR "+"Nome do Token: "+nometoken);
        pr = true;
                 }t = t+1;
    } if (pr!=true){
        fim++;
        System.out.println("Palavra: "+palavra+" ERROR: OPERADOR");}
    }
    public static void TokenRelacionais(String conteudo,int ini, int fim,String nometoken){
    int t= 0;
    fim++;
      String palavra = conteudo.substring(ini,fim);
     boolean pr = false;
      while(t!=relacionais.length){
       if(palavra.equals(relacionais[t])){
         System.out.println("Palavra: "+palavra+" Tipo: "+" RELACIONAL "+"Nome do Token: "+nometoken);
        pr = true;
                 }t = t+1;
    } if (pr!=true){
        System.out.println("Palavra: "+palavra+" ERROR: RELACIONAL");}
    }
}
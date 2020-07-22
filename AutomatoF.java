package automatos;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import static automatos.Token.TokenProuId;
import static automatos.Token.TokenDelimitadores;
import static automatos.Token.TokenDeNumeros;
import static automatos.Token.TokenOperadores;
import static automatos.Token.TokenRelacionais;
public class AutomatoF {
    public static void main(String args[]) throws IOException{
        String nome = "D:\\Marcelo\\Documents\\NetBeansProjects\\automatos\\src\\automatos/meuteste.txt";//diretorio do arquivo
          FileReader arq = new FileReader(nome);
            BufferedReader lerarq = new BufferedReader(arq);
            String linha = lerarq.readLine();
           String conteudo = " ";
         int estado =0 ;
         int posicao = 0;
         while(linha != null){
        conteudo =conteudo + linha + "\n"; //convertendo meu arquivo em String
         linha = lerarq.readLine();
       }int ini=0;
        int fim=0;
        while ( posicao != conteudo.length()){
        char item = conteudo.charAt(posicao);
        String nometoken = "";
        switch(estado){
            case 0:
                if(item =='\n' || Character.isSpaceChar(item)){
             estado = 0;
                }
          else if (Character.isLetter(item) ){
             ini = posicao;
              estado = 1;
           }else if (Character.isDigit(item) ){
                     ini = posicao;
               estado = 3;
           }else if(item == '.'){
                   estado = 5;
               }else if(item == '='){
                    ini = posicao;
                   estado = 8;
               }else if(item == '+'){
                    ini = posicao;
                   estado = 12;
               }
                else if(item == '-'){
                     ini = posicao;
                   estado = 14;
               }else if(item == '*'){
                    ini = posicao;
                   estado = 15;
               }else if(item == '/'){
                    ini = posicao;
                   estado = 16;
               }
               else if(item == '!'){
                   ini = posicao;
                   estado = 24;
               }else if(item == '<'){
                    ini = posicao;
                   estado = 26;
               }else if(item == '>'){
                    ini = posicao;
                   estado = 29;
               }else if(item == '('){
                    ini = posicao;
                   estado = 32;
               }else if(item == ')'){
                    ini = posicao;
                   estado = 33;
               }else if(item == '{'){
                    ini = posicao;
                   estado = 34;
               }else if(item == '}'){
                    ini = posicao;
                   estado = 35;
               }else if(item == ','){
                    ini = posicao;
                   estado = 36;
               }else if(item == ';'){
                    ini = posicao;
                   estado = 37;
               }else if(item == '['){
                    ini = posicao;
                   estado = 38;
               }else if(item == ']'){
                    ini = posicao;
                   estado = 39;
               }
                break;
            case 1:
                fim = posicao;
                if (Character.isLetter(item) ){
                    estado = 1;
                } 
                else if(Character.isDigit(item)){
                    estado = 1;
                } else if(item == '_'){
                    estado = 1;
                }
                else if(item ==',' ||item ==';' ||item =='\n'||Character.isSpaceChar(item) ||item =='=' ||item =='+' ||item =='<' ||item =='>') {
                fim = posicao;
                posicao= posicao -1;
                estado = 2;
                   } else {
                    estado = 1; 
                 }
                break;
            case 2:
                    //botar retorno para id ou palavra reservada
                fim = posicao;
               TokenProuId(conteudo,ini,fim,nometoken);
                  estado = 0;
                  posicao =posicao-1;
                break;
           case 3:
                if (Character.isDigit(item)){
               estado = 3 ;
                }
               if(item == '.'){
                   estado = 5;
               }
               else {
                   posicao= posicao +1;
                    estado = 4;
                }
            break;
           case 4:
               //retorna numero inteiro
               nometoken = "T_Inteiro";
                fim = posicao;
                 TokenDeNumeros(conteudo,ini,fim,nometoken);
                 posicao = posicao++;
               estado = 0;
               break;
           case 5:
               if (Character.isDigit(item)){
               estado = 6 ;
                }else {
                   //erro
                fim = posicao;
                 TokenDeNumeros(conteudo,ini,fim,nometoken);
                 posicao = posicao++;
               estado = 0;
               }
               break;
           case 6:
               if (Character.isDigit(item)){
               estado = 6 ;
               }else if(item ==',' ||item ==';' ||item =='\n'||Character.isSpaceChar(item) ||item =='=' ||item =='+' ||item =='<' ||item =='>'){
                  fim = posicao;
                posicao= posicao -1;
               estado =7;
               }else{
               estado = 6;}
               break;
           case 7:                  
          //retorna um numero float
               nometoken = "T_Flutuante";
                fim = posicao;
                  TokenDeNumeros(conteudo,ini,fim,nometoken);
                  posicao =posicao-1;
               estado = 0;
               break;
           case 8:
               if (item == '='){
               estado = 9 ;
               }else {
                   estado = 11;
               }
               break;
           case 9:
               //retornar igual
               nometoken = "T_Operador_de_Igualdade";
                fim = posicao;
                TokenRelacionais(conteudo,ini,fim,nometoken);
               estado = 0;
               break;
           case 11:
                //retorna atributo
               nometoken = "T_Atribuição";
               posicao = posicao -1;
                fim = posicao;
               TokenOperadores(conteudo,ini,fim,nometoken);
               estado = 0;
               break;
           case 12:
               if(item =='+'){
                  //retorna soma + 1
                  nometoken = "T_Incremento";
                   fim = posicao;
                  TokenOperadores(conteudo,ini,fim,nometoken);
                  estado = 0;
               }else{
               estado = 13;
               }
               break;
           case 13:
              //retorna soma
                posicao = posicao -1;
                 fim = posicao;
                 nometoken = "T_Soma";
                TokenOperadores(conteudo,ini,fim,nometoken);
               estado = 0;
               break;
           case 14:
               //retorna subtração
               nometoken = "T_Subtraçao";
                fim = posicao;
                 TokenOperadores(conteudo,ini,fim,nometoken);
               estado = 0;
               break;
           case 15:
               if (item == '/'){
               estado = 23;
               }else{
               estado = 20;
               }
                break;
           case 16:
               if(item =='/'){
               estado = 18;
               }else if(item == '*'){
                   estado =19;
               }else {
               estado = 17;
               }
                break;
           case 17:
               //retornar divisao
               nometoken = "T_Divisão";
               posicao = posicao -1;
                fim = posicao;
                 TokenOperadores(conteudo,ini,fim,nometoken);
               estado = 0;
                break;
           case 18:
               if(item != '\n'){
               //retornar que é um comentario na linha
               estado = 18;}
               else{
               nometoken = "T_Comentario_na_linha";
               fim = posicao;
                 TokenDelimitadores(conteudo,ini,fim,nometoken);
               estado = 0;}
                break;
           case 19:
               if(item != '/'){
               //inicializador de comentario aberto
               estado = 19;}
               else{
               nometoken = "T_Abre_Comentario";
               posicao = posicao -1;
                fim = posicao;
                  TokenDelimitadores(conteudo,ini,fim,nometoken);
               estado = 23;}
              break;
           case 20:
               //retornar multiplicaçao
               nometoken = "T_multiplicação";
               posicao = posicao -1;
                fim = posicao;
                TokenOperadores(conteudo,ini,fim,nometoken);
                 estado = 0;
               break;
           case 23:
               //termino de comentario fechado
               nometoken = "T_Fecha_Comentario";
               posicao = posicao +1;
                fim = posicao;
                 TokenDelimitadores(conteudo,ini,fim,nometoken);
               estado = 0;
                break;
           case 24:
               if(item == '='){
               estado= 25;
               }
               else {
               //erro
                fim = posicao;
                 posicao =posicao++;
                   TokenRelacionais(conteudo, ini, fim, nometoken);
               estado = 0;
               }
               break;
           case 25:
               //retorna diferente
               nometoken = "T_Diferente";
               fim = posicao;
               TokenRelacionais(conteudo,ini,fim,nometoken);
               estado = 0;
               break;
           case 26:
               if(item == '='){
               estado = 27;}
               else {
                   posicao= posicao -1;
                   estado = 28;
               }
               break;
           case 27:
               //retorna menor igual
               nometoken = "T_Menor_Igual";
                fim = posicao;
                TokenRelacionais(conteudo,ini,fim,nometoken);
                 posicao =posicao-1;
               System.out.println("deucerto");
               estado = 0;
               break;
           case 28:
               //retornar menor que
               nometoken = "T_Menor";
               posicao = posicao -1;
                fim = posicao;
                TokenRelacionais(conteudo,ini,fim,nometoken);
                 posicao =posicao-1;
               estado = 0;
               break;
           case 29:
               if(item == '='){
               estado = 30;}
               else {
                   posicao= posicao -1;
                   estado = 31;
               }break;
           case 30:
               //retorna maior igual
                nometoken = "T_Maior_Igual";
                fim = posicao;
                TokenRelacionais(conteudo,ini,fim,nometoken);
               estado = 0;
               break;
           case 31:
               //retorna maoir que
                nometoken = "T_Maior";
               posicao = posicao -1;
                fim = posicao;
                TokenRelacionais(conteudo,ini,fim,nometoken);
               estado = 0;
               System.out.println("maoir que");
               break;
           case 32:
               //abre pare
                nometoken = "T_Abre_Parêntese";
               fim = posicao;
               TokenDelimitadores(conteudo,ini,fim,nometoken);
               //posicao =posicao-1;
               estado = 0;
               break;
           case 33:
               //fecha pare
                nometoken = "T_Fecha_Parêntese";
                fim = posicao;
                TokenDelimitadores(conteudo,ini,fim,nometoken);
                //posicao =posicao-1;
               estado = 0;
           case 34:
               //abre chave
               nometoken = "T_Abre_Chave";
                fim = posicao;
                  TokenDelimitadores(conteudo,ini,fim,nometoken);
               estado = 0;
               break;
           case 35:
               //fecha chave
                nometoken = "T_Fecha_Chave";
                fim = posicao;
                TokenDelimitadores(conteudo,ini,fim,nometoken);
               estado = 0;
               break;
           case 36:
               //virgula
               nometoken = "T_Virgula";
               fim = posicao;
               TokenDelimitadores(conteudo,ini,fim,nometoken);
                 posicao =posicao-1;
               estado = 0;
               break;
           case 37:
               //ponto e virgula
                nometoken = "T_Ponto_Virgula";
                fim = posicao;
               TokenDelimitadores(conteudo,ini,fim,nometoken);
               estado = 0;
               break;
           case 38:
               //abre Colchete
               nometoken = "T_Abre_Colchete";
               fim = posicao;
               TokenDelimitadores(conteudo,ini,fim,nometoken);
               estado = 0;
               break;
           case 39:
                //fecha Colchete
                nometoken = "T_Fecha_Colchete";
               fim = posicao;
               TokenDelimitadores(conteudo,ini,fim,nometoken);
               estado = 0;
               break;
        }
         posicao = posicao + 1;
    }
    }
}

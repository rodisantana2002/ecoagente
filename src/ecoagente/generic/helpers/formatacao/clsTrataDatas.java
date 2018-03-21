package ecoagente.generic.helpers.formatacao;

/**
 * Esta classe contém métodos para efetuar conversão de datas entre os formatos ISO (yyyy-MM-dd) e ABNT (dd/MM/yyyy).
 * Estes métodos são voltados pra agilizar a gravação e recuperação de datas em Bancos de Dados MySQL.
 */
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class clsTrataDatas {
   private SimpleDateFormat formatIso;
   private SimpleDateFormat formatBra;
   private Date   date;

   public clsTrataDatas() {
      formatIso = new SimpleDateFormat("yyyy-MM-dd");
      formatBra = new SimpleDateFormat("dd/MM/yyyy");
   }

   public String getDataAtual(){
       return formatBra.format(new Date(System.currentTimeMillis()));
   }
   
   /**
    * Converte uma data no formato ABNT em formato ISO;
    * @param strDataBra Argumento que recebe data no formato ABNT(dd/MM/yyyy);
    * @return Uma data no formato ISO(yyyy-MM-dd).
    */
   public String parseDataIso(String strDataBra){
      try {
         date = formatIso.parse(strDataBra);
         return(formatIso.format(date));
      }
      catch (ParseException e){return("Date Error");}
   }

   /**
    * Converte uma data no formato ISO em formato ABNT;
    * @param strDataIso Argumento que recebe data no formato ISO(yyyy-MM-dd);
    * @return Uma data no formato ABNT(dd/MM/yyyy).
    */
   public String parseDataBra(String strDataIso){
      try {
         date = formatBra.parse(strDataIso);
         return(formatBra.format(date));
      }
      catch(ParseException e){return("Date Error");}
   }
   
}  
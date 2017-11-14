/**
 * 
 */
package gervasio.system.util;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;
import java.util.Date;

/**
 * @author Éderson Gervásio Silva
 *
 */
public class DateHelper {

	public static final LocalDate primeiroDiaDoMesAtual() {
		return LocalDate.now().withDayOfMonth(1);
	}
	
	public static final LocalDate ultimoDiaDoMesAtual() {
		return LocalDate.now().withDayOfMonth(primeiroDiaDoMesAtual().lengthOfMonth());
	}
	
	public static final Date convertLocalDateParaDate(LocalDate localDate) {
		return Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}
	
	public static final LocalDate primeiroDiaDoMesPara(LocalDate local) {
		return local.withDayOfMonth(1);
	}
	
	public static final LocalDate ultimoDiaDoMesPara(LocalDate local) {
		return local.withDayOfMonth(local.lengthOfMonth());
	}
	
	public static final Date mesSeguinte(Date paraQualData, int avancarQuantosMeses) {
		
		Calendar cal = Calendar.getInstance();
	    cal.setTime(paraQualData);
	    cal.add(Calendar.MONTH, avancarQuantosMeses);
	    
	    return cal.getTime();
	}
	
	public static final int diferencaDeMesEntre(Date data1, Date data2) {
		
		Calendar cal1 = Calendar.getInstance();
	    cal1.setTime(data1);
	    
	    Calendar cal2 = Calendar.getInstance();
	    cal2.setTime(data2);
	    
	    return cal1.get(Calendar.MONTH) - cal2.get(Calendar.MONTH);
	}
	
	public static final boolean diaPrimeiraDataMaiorDiaSegundaData(Date data1, Date data2) {
		
		Calendar cal1 = Calendar.getInstance();
		Calendar cal2 = Calendar.getInstance();
		cal1.setTime(data1);
		cal2.setTime(data2);
	    
	    return cal1.get(Calendar.DAY_OF_YEAR) > cal2.get(Calendar.DAY_OF_YEAR);
	}
	
	public static final Date adicionaDiaParaData(Date data, int dia) {
		
		Calendar cal = Calendar.getInstance();
	    cal.setTime(data);
	    cal.add(Calendar.DAY_OF_MONTH, dia);
	    
	    return cal.getTime();
	}
	
	public static final Date truncaData(Date data) {
		
		Calendar cal = Calendar.getInstance();
		
		cal.setTime(data);
		cal.set(Calendar.HOUR_OF_DAY, 0);
		cal.set(Calendar.MINUTE, 0);
		cal.set(Calendar.SECOND, 0);
		cal.set(Calendar.MILLISECOND, 0);
		
		return cal.getTime();
	}
	
	public static void main(String[] args) {
		
		Date resulado = adicionaDiaParaData(new Date(), 1);
		
		System.out.print(LocalDate.now().plusMonths(1));
	}
}

package teste.junit;

import java.util.Calendar;

import br.com.project.report.util.DateUtils;

public class TesteData {

	
	public void testData() {
		
		System.out.println(DateUtils.formatDateSql(Calendar.getInstance().getTime()));
		
		
	}

}

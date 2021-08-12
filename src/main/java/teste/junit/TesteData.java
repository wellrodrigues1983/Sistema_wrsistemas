package teste.junit;

import java.util.Calendar;

import org.junit.Test;

import br.com.project.report.util.DateUtils;

public class TesteData {

	@Test
	public void testData() {
		
		System.out.println(DateUtils.formatDateSql(Calendar.getInstance().getTime()));
		
		
	}

}

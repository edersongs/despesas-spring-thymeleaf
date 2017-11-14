package gervasio.system;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import gervasio.system.repository.TotalDespesaMensalRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
public class GffApplicationTests {

	@Autowired private TotalDespesaMensalRepository rep;
	
	@Test
	public void contextLoads() {
		
		rep.findAll().forEach(r -> System.out.println(r.getTotal()));
	}

}

package jp.co.sample.emp_management.repository;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.sample.emp_management.domain.Employee;

@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	public void testFindAll() {
		System.out.println("全件検索するテスト開始");
		
		List<Employee> employeeList = employeeRepository.findAll();
		
		assertThat("件数が一致しません", employeeList.size(), is(22));
		assertThat("誕生日順に並んでいません", employeeList.get(0).getName(), is("渡辺三郎"));
		assertThat("誕生日順に並んでいません", employeeList.get(21).getName(), is("加藤十子"));
		
		System.out.println("全件検索するテスト終了");
	}

}

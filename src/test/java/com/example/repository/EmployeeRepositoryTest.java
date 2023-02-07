package com.example.repository;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.domain.Employee;

//@RunWith(SpringRunner.class)
@SpringBootTest
public class EmployeeRepositoryTest {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Test
	public void testFindAll() {
		System.out.println("全件検索するテスト開始");
		
		List<Employee> employeeList = employeeRepository.findAll();
		
		assertEquals(22, employeeList.size(), "件数が一致しません");
		assertEquals("渡辺三郎", employeeList.get(0).getName(), "入社日順に並んでいません");
		assertEquals("加藤十子", employeeList.get(21).getName(), "入社日順に並んでいません");
		
		System.out.println("全件検索するテスト終了");
	}

}

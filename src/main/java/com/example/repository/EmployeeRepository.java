package com.example.repository;

import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.stereotype.Repository;

import com.example.domain.Employee;

/**
 * employeesテーブルを操作するリポジトリ.
 * 
 * @author igamasayuki
 * 
 */
@Repository
public class EmployeeRepository {

	/**
	 * Employeeオブジェクトを生成するローマッパー.
	 */
	public static final RowMapper<Employee> EMPLOYEE_ROW_MAPPER = (rs, i) -> {
		Employee employee = new Employee();
		employee.setId(rs.getInt("id"));
		employee.setName(rs.getString("name"));
		employee.setImage(rs.getString("image"));
		employee.setGender(rs.getString("gender"));
		employee.setHireDate(rs.getDate("hire_date"));
		employee.setMailAddress(rs.getString("mail_address"));
		employee.setZipCode(rs.getString("zip_code"));
		employee.setAddress(rs.getString("address"));
		employee.setTelephone(rs.getString("telephone"));
		employee.setSalary(rs.getInt("salary"));
		employee.setCharacteristics(rs.getString("characteristics"));
		employee.setDependentsCount(rs.getInt("dependents_count"));
		return employee;
	};

	private final NamedParameterJdbcTemplate template;

	public EmployeeRepository(NamedParameterJdbcTemplate template){
		this.template = template;
	}

	/**
	 * 従業員一覧情報を入社日順で取得します.
	 * 
	 * @return 全従業員一覧 従業員が存在しない場合はサイズ0件の従業員一覧を返します
	 */
	public List<Employee> findAll() {
		String sql = """
                SELECT id, name, image, gender, hire_date, mail_address, zip_code,
                       address, telephone, salary, characteristics, dependents_count
                  FROM employees
                 ORDER BY hire_date DESC
                """;

		return template.query(sql, EMPLOYEE_ROW_MAPPER);
	}

	/**
	 * 主キーから従業員情報を取得します.
	 * 
	 * @param id 検索したい従業員ID
	 * @return 検索された従業員情報
	 */
	public Employee findById(Integer id) {
		String sql = """
                SELECT id, name, image, gender, hire_date, mail_address, zip_code,
                       address, telephone, salary, characteristics, dependents_count
                  FROM employees
                 WHERE id = :id
                """;

		SqlParameterSource param = new MapSqlParameterSource().addValue("id", id);
		return template.queryForObject(sql, param, EMPLOYEE_ROW_MAPPER);
	}

	/**
	 * 従業員情報を変更します.
	 */
	public void update(Employee employee) {
		SqlParameterSource param = new BeanPropertySqlParameterSource(employee);

		String sql = """
                UPDATE employees
                   SET name = :name,
                       image = :image,
                       gender = :gender,
                       hire_date = :hireDate,
                       mail_address = :mailAddress,
                       zip_code = :zipCode,
                       address = :address,
                       telephone = :telephone,
                       salary = :salary,
                       characteristics = :characteristics,
                       dependents_count = :dependentsCount
                 WHERE id = :id
                """;

		template.update(sql, param);
	}
}

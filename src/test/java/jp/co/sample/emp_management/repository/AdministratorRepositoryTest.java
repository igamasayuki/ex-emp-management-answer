package jp.co.sample.emp_management.repository;

//Matcher関連メソッドを利用するためのstaticインポート
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import jp.co.sample.emp_management.domain.Administrator;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AdministratorRepositoryTest {

	@Autowired
	private AdministratorRepository administratorRepository;

	@Autowired
	private NamedParameterJdbcTemplate template;

	@Before
	public void testInsert() {
		System.out.println("DB初期化処理開始");
		Administrator administrator = new Administrator();
		administrator.setName("伊賀将之");
		administrator.setMailAddress("iga@sample.com");
		administrator.setPassword("testtest");
		administratorRepository.insert(administrator);
		System.out.println("インサートが完了しました。");

		System.out.println("DB初期化処理終了");
	}

	@Test
	public void testLoad() {
		System.out.println("主キー検索するテスト開始");
		
		Integer maxId = template.queryForObject("select max(id) from administrators;", new MapSqlParameterSource(),
				Integer.class);
		Administrator resultAdministrator = administratorRepository.load(maxId);

		assertThat("名前が登録されていません", resultAdministrator.getName(), is("伊賀将之"));
		assertThat("メールアドレスが登録されていません", resultAdministrator.getMailAddress(), is("iga@sample.com"));
		assertThat("パスワードが登録されていません", resultAdministrator.getPassword(), is("testtest"));
		
		System.out.println("主キー検索するテスト終了");
	}
	
	@Test
	public void testFindByMailAddressAndPassward() {
		System.out.println("メールアドレスとパスワードで検索するテスト開始");
		Administrator resultAdministrator = administratorRepository.findByMailAddressAndPassward("iga@sample.com",
				"testtest");
		assertThat("名前が検索されていません", resultAdministrator.getName(), is("伊賀将之"));
		assertThat("メールアドレスが検索されていません", resultAdministrator.getMailAddress(), is("iga@sample.com"));
		assertThat("パスワードが検索されていません", resultAdministrator.getPassword(), is("testtest"));
		System.out.println("メールアドレスとパスワードで検索するテスト終了");
	}

	@After
	public void tearDownAfterClass() throws Exception {
		MapSqlParameterSource param = new MapSqlParameterSource().addValue("mailAddress", "iga@sample.com");
		template.update("delete from administrators where mail_address = :mailAddress", param);
		System.out.println("入れたデータを削除しました。");
	}
}

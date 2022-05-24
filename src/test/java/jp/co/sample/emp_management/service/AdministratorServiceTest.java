package jp.co.sample.emp_management.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import jp.co.sample.emp_management.domain.Administrator;
import jp.co.sample.emp_management.repository.AdministratorRepository;

@SpringBootTest
class AdministratorServiceTest {

	// テスト対象クラス
	@InjectMocks
	private AdministratorService administratorService;

	// テスト対象クラス内で使われている未実装のクラス
	@Mock
	private AdministratorRepository administratorRepository;

	@Test
	void testLogin() {
		when(administratorRepository.findByMailAddressAndPassward("iga@sample.com", "igaigaiga"))
				.thenReturn(new Administrator(1, "伊賀", "iga@sample.com", "igaigaiga"));

		Administrator actualAdministrator = administratorService.login("iga@sample.com", "igaigaiga");
		Administrator expectedAdministrator = new Administrator(1, "伊賀", "iga@sample.com", "igaigaiga");
		assertEquals(expectedAdministrator.getId(), actualAdministrator.getId(), "idが一致しません");
		assertEquals(expectedAdministrator.getName(), actualAdministrator.getName(), "nameが一致しません");
		assertEquals(expectedAdministrator.getMailAddress(), actualAdministrator.getMailAddress(),
				"mailAddressが一致しません");
		assertEquals(expectedAdministrator.getPassword(), actualAdministrator.getPassword(), "passwordが一致しません");
	}

	@Test
	void testInsert() {
		Administrator administrator = new Administrator(1, "伊賀", "iga@sample.com", "igaigaiga");
		administratorService.insert(administrator);

		// 以下はadministratorRepositoryのinsert(administrator)が実行されたかどうかテストしている。
		Mockito.verify(administratorRepository).insert(administrator);
	}

	@Test
	void testLogin2() {
		administratorService.login("iga@sample.com", "igaigaiga");

		// 以下はadministratorRepositoryのfindByMailAddressAndPassward("iga@sample.com",
		// "igaigaiga")が実行されたかどうかテストしている。
		Mockito.verify(administratorRepository).findByMailAddressAndPassward("iga@sample.com", "igaigaiga");
	}

}

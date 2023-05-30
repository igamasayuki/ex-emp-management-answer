package com.example.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.domain.Administrator;
import com.example.repository.AdministratorRepository;

/**
 * Mockito(モキート)を使用したサービスクラスのテストクラス.<br>
 * 
 * リポジトリクラスが未実装でもMockitoを使用すればサービスのテストができるのでそのサンプルです。
 * 
 * @author igamasayuki
 *
 */
@SpringBootTest
class AdministratorServiceTest {

	// テスト対象クラス
	@InjectMocks
	private AdministratorService administratorService;

	// テスト対象クラス内で使われている未実装のクラス
	// このクラスを実行する際にAdministratorRepositoryクラスの各メソッド内を全てコメントアウトして未実装状態にしておきます。
	// 戻り値を返すメソッドなら「return null;」を書いておく
	@Mock
	private AdministratorRepository administratorRepository;

	@Test
	void testLogin() {
		// 未実装のAdministratorRepository内のfindByMailAddressAndPassward()の動きを定義する
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

		// administratorRepositoryのinsert(administrator)が呼ばれたかどうかテストしている。
		verify(administratorRepository).insert(administrator);
	}

	@Test
	void testLogin2() {
		administratorService.login("iga@sample.com", "igaigaiga");

		// 以下はadministratorRepositoryの
		// findByMailAddressAndPassward("iga@sample.com","igaigaiga")が実行されたかどうかテストしている。
		verify(administratorRepository).findByMailAddressAndPassward("iga@sample.com", "igaigaiga");
	}

}

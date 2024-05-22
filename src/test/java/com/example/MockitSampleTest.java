package com.example;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * Mockitoを使用するサンプルプログラムです.<br>
 * 
 * 参考：https://site.mockito.org/
 * 
 * @author igamasayuki
 *
 */
@SpringBootTest
class MockitSampleTest {

	@Mock
	private List<String> mockedList;

	@Test
	void test() {
		mockedList.add("one");
		mockedList.clear();
		
		// mockedList.add("one")　が呼ばれたかテスト
		verify(mockedList).add("one");
		// verify(mockedList).add("two"); ←これは呼ばれていないので失敗する
		// mockedList.clear(); が呼ばれたかテスト
		verify(mockedList).clear();
	}

	@Test
	void test2() {
		// mockedList.get(0)が呼ばれたら、"first"を返すようなMockを作成
		when(mockedList.get(0)).thenReturn("first");
		
		assertEquals("first",mockedList.get(0),"get(0)の戻り値が正しくありません");

		assertNull(mockedList.get(999),"nullが返ってきません");
	}

}

package jp.kanagawa.kawasaki.lunchplaces.test;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.mockito.InOrder;
import org.mockito.Mockito;

import jp.kanagawa.kawasaki.lunchplaces.NetworkAccessRunnable;
import android.test.AndroidTestCase;

public class NetworkTest extends AndroidTestCase{
	NetworkAccessRunnable mNetworkAccessRunnable;
	protected void setUp() throws Exception {
        super.setUp();
        mNetworkAccessRunnable = new NetworkAccessRunnable();
    }

	/**
	 * テストメソッドが実行され、結果が記録された直後に呼び出されるメソッド
	 */
    protected void tearDown() throws Exception {
        super.tearDown();
    }
    
    
    /**
     * ダミーのHTTPレスポンスボディとなる{@link InputStream}インスタンスを生成して返す.
     */
    private InputStream createDummyInputStream() {
        return new InputStream() {
            @Override
            public int read() throws IOException {
                return 0;
            }
        };
    }
 
    /**
     * HttpClient#execute()が正常にレスポンス(200)を返すとき、レスポンスボディが返ること.
     */
    public void testRequestFeed_success() throws Exception {
        // ダミーのHTTPレスポンスボディ
        InputStream expectedContent = createDummyInputStream();
 
        // ダミーのレスポンスボディを返すHttpEntityモック
        HttpEntity mockHttpEntity = mock(HttpEntity.class);
        when(mockHttpEntity.getContent()).thenReturn(expectedContent);
 
        // HTTPステータス200を返すStatusLineモック
        StatusLine mockStatusLine = mock(StatusLine.class);
        when(mockStatusLine.getStatusCode()).thenReturn(200);
 
        // 上で定義したモックオブジェクトを返すHttpResponseモック
        // ステータスを確認してからEntityを取得していること（順序）を確認するため、StrictMock.
        HttpResponse mockResponse = mock(HttpResponse.class);
        when(mockResponse.getStatusLine()).thenReturn(mockStatusLine);
        when(mockResponse.getEntity()).thenReturn(mockHttpEntity);
 
        // 上で定義したモックオブジェクトを返すHttpClientモック
        HttpURLConnection mHttpURLConnection = mock(HttpURLConnection.class);
        when(mHttpURLConnection.getResponseCode()).thenReturn(200);
 
        // テスト対象のインスタンスフィールドにあるHttpClientをモックに差し替える
        mNetworkAccessRunnable.connection = mHttpURLConnection;
 
        // テスト実行
        mNetworkAccessRunnable.run();
        //assertEquals("Mockに仕込んだInputStreamが返される", expectedContent, actual);
 
        // HttpResponseのメソッドが正しい順序で呼ばれたかを検証
        //InOrder inOrder = inOrder(mockResponse);
        //inOrder.verify(mockResponse).getStatusLine();
        //inOrder.verify(mockResponse).getEntity();
    }
}

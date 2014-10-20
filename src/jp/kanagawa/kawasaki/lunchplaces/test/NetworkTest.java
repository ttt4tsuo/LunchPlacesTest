package jp.kanagawa.kawasaki.lunchplaces.test;

import static org.mockito.Mockito.inOrder;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.mockito.InOrder;
import org.mockito.Mockito;

import jp.kanagawa.kawasaki.lunchplaces.NetworkAccessRunnable;
import android.content.res.AssetManager;
import android.test.AndroidTestCase;
import android.util.Log;

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
     * レスポンス(200)を返すとき、レスポンスボディが返ること.
     */
    public void testRequestFeed_success() throws Exception {
        // ダミーのHTTPレスポンスボディ
        //InputStream expectedContent = createDummyInputStream();

        //InputStream expectedContent = getContext().getResources().getAssets().open("sample.json"); 

        String expectedContent="{\"Employee\":[{\"Age\": 25,\"Name\": \"田中　一郎\"},{\"Age\": 30,\"Name\": \"鈴木　次郎\"}]}";
        InputStream istreamExpectedContent = new ByteArrayInputStream(expectedContent.getBytes("utf-8"));
        
        // ダミーのレスポンスボディを返すHttpEntityモック
        //HttpEntity mockHttpEntity = mock(HttpEntity.class);
        //when(mockHttpEntity.getContent()).thenReturn(expectedContent);

        // HTTPステータス200を返すStatusLineモック
        //StatusLine mockStatusLine = mock(StatusLine.class);
        //when(mockStatusLine.getStatusCode()).thenReturn(200);

        // 上で定義したモックオブジェクトを返すHttpResponseモック
        // ステータスを確認してからEntityを取得していること（順序）を確認するため、StrictMock.
        //HttpResponse mockResponse = mock(HttpResponse.class);
        //when(mockResponse.getStatusLine()).thenReturn(mockStatusLine);
        //when(mockResponse.getEntity()).thenReturn(mockHttpEntity);
        
        // 上で定義したモックオブジェクトを返すモック
        HttpURLConnection conn = mock(HttpURLConnection.class);
        when(conn.getResponseCode()).thenReturn(HttpURLConnection.HTTP_OK);
        //Mockito.when(conn.getHeaderFields()).thenReturn(headers);
        Mockito.when(conn.getInputStream()).thenReturn(istreamExpectedContent);

        // テスト対象のインスタンスフィールドにあるHttpClientをモックに差し替える
        mNetworkAccessRunnable.connection = conn;

        // テスト実行
        mNetworkAccessRunnable.run();
        Log.d("myapptest",expectedContent);
        Log.d("myapptest",Integer.toString(mNetworkAccessRunnable.retCode));
        assertEquals(expectedContent, expectedContent, mNetworkAccessRunnable.str);
 
        // HttpResponseのメソッドが正しい順序で呼ばれたかを検証
        //InOrder inOrder = inOrder(mockResponse);
        //inOrder.verify(mockResponse).getStatusLine();
        //inOrder.verify(mockResponse).getEntity();
    }
}

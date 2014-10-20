package jp.kanagawa.kawasaki.lunchplaces.test;

import java.lang.reflect.Field;
import java.net.HttpURLConnection;

import jp.kanagawa.kawasaki.lunchplaces.NetworkAccessRunnable;
import junit.framework.TestCase;

public class NetworkAccessRunnableTest extends TestCase {

	public NetworkAccessRunnableTest(){}
	
	NetworkAccessRunnable nar;
	
	@Override
	protected void setUp() throws Exception {
	    super.setUp();
	    nar= new NetworkAccessRunnable();
	}
	/*public void testUrl() throws Exception {
		//クラスオブジェクトを取得する
		Class c = nar.getClass();
        //PrivateTestProjectのmValueメンバ変数を取得する
        Field fld = c.getDeclaredField("connection");
        //取得したメンバ変数にアクセス権限を与える
        fld.setAccessible(true);
        assertNull(fld.get(nar));
        nar.run();
		assertNotNull(fld.get(nar));
	}*/
}

package jp.kanagawa.kawasaki.lunchplaces.test;

import java.lang.reflect.Field;

import jp.kanagawa.kawasaki.lunchplaces.NetworkAccessManager;
import junit.framework.TestCase;

public class NetworkAccessManagerTest extends TestCase {
	public NetworkAccessManagerTest(){}
	
	NetworkAccessManager nam;
	
	@Override
	protected void setUp() throws Exception {
	    super.setUp();
	    nam = NetworkAccessManager.getInstance();
	}
	
	public void testManager() throws Exception {
		assertNotNull(nam);
	}
	public void testThraedPool() throws Exception {
		Class c = nam.getClass();
        Field fld = c.getDeclaredField("mDownloadThreadPool");
        fld.setAccessible(true);
        assertNotNull(fld.get(nam));
	}
	
}

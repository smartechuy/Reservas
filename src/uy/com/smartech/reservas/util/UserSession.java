package uy.com.smartech.reservas.util;

public class UserSession {

	private static  String userId;

	public static String getUserId() {
		return userId;
	}

	public static void setUserId(String userId) {
		UserSession.userId = userId;
	}
	
}

package com.lxt008.common;

public class LxtAndroidUtil {
	
	/* ≈–∂œsdcard «∑Ò¥Ê‘⁄ */
	public static boolean checkSDCard() {		
		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}


}

package com.lxt008.common;

public class LxtAndroidUtil {
	
	/* �ж�sdcard�Ƿ���� */
	public static boolean checkSDCard() {		
		if (android.os.Environment.getExternalStorageState().equals(
				android.os.Environment.MEDIA_MOUNTED)) {
			return true;
		} else {
			return false;
		}
	}


}

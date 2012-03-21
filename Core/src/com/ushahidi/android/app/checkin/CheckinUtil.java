
package com.ushahidi.android.app.checkin;

import android.content.Context;
import android.database.Cursor;
import android.telephony.TelephonyManager;

import com.ushahidi.android.app.MainApplication;
import com.ushahidi.android.app.data.Database;

/**
 * Created by IntelliJ IDEA. User: Ahmed Date: 2/21/11 Time: 7:08 PM To change
 * this template use File | Settings | File Templates.
 */
public class CheckinUtil {
    public static String IMEI(Context appContext) {
        TelephonyManager TelephonyMgr = (TelephonyManager)appContext
                .getSystemService(Context.TELEPHONY_SERVICE);
        return TelephonyMgr.getDeviceId(); // Requires READ_PHONE_STATE
    }
    
    public static String getCheckinUser(String userId) {
        Cursor cursor = MainApplication.mDb.fetchUsersById(userId);
        String user = null;
        if (cursor.moveToFirst()) {
            int userName = cursor.getColumnIndexOrThrow(Database.USER_NAME);
            user = cursor.getString(userName);
        }
        cursor.close();
        return user;
    }
    
    public static String getCheckinMedia(String checkinId) {
        Cursor cursor = MainApplication.mDb.fetchCheckinsMediaByCheckinId(checkinId);
        String mediaLink = null;
        if (cursor.moveToFirst()) {
            int mediaMediumLink = cursor.getColumnIndexOrThrow(Database.MEDIA_MEDIUM_LINK);
            mediaLink = cursor.getString(mediaMediumLink);
        }
        cursor.close();
        
        return mediaLink;
    }
    
    public static String getCheckinThumbnail(String checkinId) {
        Cursor cursor = MainApplication.mDb.fetchCheckinsMediaByCheckinId(checkinId);
        String thumbnail = null;
        if (cursor.moveToFirst()) {
            int mediaMediumLink = cursor.getColumnIndexOrThrow(Database.MEDIA_THUMBNAIL_LINK);
            return cursor.getString(mediaMediumLink);
        }
        cursor.close();
        return thumbnail;
    }
}

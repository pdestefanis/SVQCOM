/** 
 ** Copyright (c) 2010 Ushahidi Inc
 ** All rights reserved
 ** Contact: team@ushahidi.com
 ** Website: http://www.ushahidi.com
 ** 
 ** GNU Lesser General Public License Usage
 ** This file may be used under the terms of the GNU Lesser
 ** General Public License version 3 as published by the Free Software
 ** Foundation and appearing in the file LICENSE.LGPL included in the
 ** packaging of this file. Please review the following information to
 ** ensure the GNU Lesser General Public License version 3 requirements
 ** will be met: http://www.gnu.org/licenses/lgpl.html.	
 **	
 **
 ** If you have questions regarding the use of this file, please contact
 ** Ushahidi developers at team@ushahidi.com.
 ** 
 **/

package com.ushahidi.android.app.net;

import java.io.IOException;

import org.apache.http.HttpResponse;

import com.ushahidi.android.app.Preferences;

public class Categories {
	
	public static boolean getAllCategoriesFromWeb() throws IOException {
		
		HttpResponse response;
		String categories = "";
		
		StringBuilder uriBuilder = new StringBuilder( Preferences.domain);
		uriBuilder.append("/api?task=categories");
		uriBuilder.append("&resp=xml");
		
		response = MainHttpClient.GetURL(uriBuilder.toString());
		
		if( response == null ) {
			return false;
		}
		
		final int statusCode = response.getStatusLine().getStatusCode();
		
		if( statusCode == 200 ) {
			categories = MainHttpClient.GetText(response);
			Preferences.categoriesResponse = categories;
			return true;
			
		} else {
			return false;
		}
		
	}

}

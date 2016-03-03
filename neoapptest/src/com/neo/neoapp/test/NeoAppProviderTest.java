package com.neo.neoapp.test;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.AndroidTestCase;
import android.test.suitebuilder.TestSuiteBuilder;
import android.util.Log;


public class NeoAppProviderTest extends AndroidTestCase {

	private static final String AUTHORITY = "content://com.neo.providers.RecordProvider";

	
	public void testInsert(){
		Uri uri = Uri.parse(AUTHORITY+"/records");
		ContentResolver resolver = this.getContext().getContentResolver();
		ContentValues values = new ContentValues();
		values.put("name", "test");
		values.put("content","hello provider");
		resolver.insert(uri, values);
		values.put("name", "test3");
		values.put("content","hello provider3");
		resolver.insert(uri, values);
		
	}
	
	public void testQuery(){
		Uri uri = Uri.parse(AUTHORITY+"/records/1");
		ContentResolver resolver = this.getContext().getContentResolver();

        Cursor c = resolver.query(uri, new String[]{"name","tag","content","created","modified"}, 
        		null, null, "_id asc");
        
        if(c.moveToFirst()){
        	Log.i("query",c.getString(c.getColumnIndex("name")));
        }
        
	}
	
	public void testzDelete1(){
		Uri uri = Uri.parse(AUTHORITY+"/records");
		ContentResolver resolver = this.getContext().getContentResolver();
		int count = resolver.delete(uri, null, null);
		
		Log.i("count", String.valueOf(count));
	}
	
	public void testzDelete2(){
		Uri uri = Uri.parse(AUTHORITY+"/records/1");
		ContentResolver resolver = this.getContext().getContentResolver();
		int count = resolver.delete(uri, " name = ?", new String[]{"test"});
		
		Log.i("count", String.valueOf(count));
		
	}
	
	public void testUpdate(){
		Uri uri = Uri.parse(AUTHORITY+"/records/2");
		ContentResolver resolver = this.getContext().getContentResolver();
		ContentValues values = new ContentValues();
		values.put("name", "test1");
		values.put("content","hello world");
		int rowAffect = resolver.update(uri, values, null, null);
		Log.i("rowAffect", String.valueOf(rowAffect));
		
	}
	
}

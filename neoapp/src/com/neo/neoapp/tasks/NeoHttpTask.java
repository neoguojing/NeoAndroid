package com.neo.neoapp.tasks;

import android.content.Context;
import android.os.AsyncTask;

public class NeoHttpTask extends AsyncTask<String, Integer, Object> {
		
	private Context mContext;
	
	private HttpDataProcessCallBack mCallBack;
	
	public NeoHttpTask(Context ctx){
		mContext = ctx;
	}
	/** 
	* �÷�����ui�߳̽��е��ã��û����������ﾡ��ķ���ui����� 
	* �ܶ�ʱ�����ǻ���������ʾһ��������ɶ�ģ���ʾ��̨���� 
	* ִ��ĳ��ܡ� 
	*/
	@Override
	protected void onPreExecute() {
	super.onPreExecute();
	}

	/** 
	* �÷����ɺ�̨���̽��е��ã�������Ҫ�ĺ�ʱ����Щ���㡣 
	* �÷�����onPreExecute����֮����е��á���Ȼ��ִ�й����� 
	* ���ǿ���ÿ��������͵���һ��publishProgress���������� 
	* ������Ϣ 
	*/
	@Override
	protected Object doInBackground(String... params) {
	return null;
	}

	/** 
	* doInBackground�е�����publishProgress֮��ui�߳̾ͻ� 
	* ���ø÷���������������ﶯ̬�ĸı�������Ľ��ȣ����û�֪�� 
	* ��ǰ�Ľ��ȡ� 
	*/
	@Override
	protected void onProgressUpdate(Integer... values) {
	super.onProgressUpdate(values);
	}

	/** 
	* ��doInBackgroundִ�����֮����ui�̵߳��á����������� 
	* �������Ǽ�������ս�����û��� 
	*/
	@Override
	protected void onPostExecute(Object result) {
	super.onPostExecute(result);
	}
	
	
	public static interface HttpDataProcessCallBack{
		
		void processHttpData();
	}

}

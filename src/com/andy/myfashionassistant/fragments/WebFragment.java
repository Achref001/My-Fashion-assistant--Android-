package com.andy.myfashionassistant.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.fashion.R;


public class WebFragment extends Fragment {
	View rootView;
	WebView mWebView ;
	String mlink;
	@Override
	public View onCreateView(LayoutInflater inflater,
			@Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		rootView = inflater.inflate(R.layout.web_view, container, false);
		mWebView=(WebView) rootView.findViewById(R.id.showFromWeb);
		mWebView.getSettings().setJavaScriptEnabled(true);
		
		Bundle args = getArguments();
		
		String s = args.getString("link"); 
		
		if (args != null) {
			if (s!=null) {
				 mlink = args.getString("link");
				
				 mWebView.loadUrl(mlink);
		
			}
		}
		mWebView.setWebViewClient(new HelloWebClient());
		return rootView;
	}
	private class HelloWebClient extends WebViewClient{
		@Override
		public boolean shouldOverrideUrlLoading(WebView webview , String url){
			webview.loadUrl(url);
			return true ;
			
		}
	


}
}




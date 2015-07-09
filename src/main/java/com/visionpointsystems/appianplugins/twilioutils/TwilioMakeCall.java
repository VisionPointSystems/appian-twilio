package com.visionpointsystems.appianplugins.twilioutils;
	
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.CallFactory;
import com.twilio.sdk.resource.instance.Call;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
	  
	public class TwilioMakeCall { 
	  
	  public static String makecall(String callerId, String to, String twimlUrl, String accountSID, String authToken) throws TwilioRestException {
	    TwilioRestClient client = new TwilioRestClient(accountSID, authToken);
	  
	    List<NameValuePair> params = new ArrayList<NameValuePair>();
	    	      
	    params.add(new BasicNameValuePair("Url", twimlUrl));
	    params.add(new BasicNameValuePair("To", to));
	    params.add(new BasicNameValuePair("From", callerId));
	      
	    CallFactory callFactory = client.getAccount().getCallFactory();
	    Call call = callFactory.create(params);
	    
	    return call.getSid();
	    
	  }
	}


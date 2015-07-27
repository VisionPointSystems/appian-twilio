package com.visionpointsystems.appianplugins.twilioutils2;
	
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.factory.MessageFactory;
import com.twilio.sdk.resource.instance.Message;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
	  
	public class TwilioSendSMS { 
	  
	  public static String[] send(String[] to, String from, String body, String accountSID, String authToken) throws TwilioRestException {
	    TwilioRestClient client = new TwilioRestClient(accountSID, authToken);
	  
	    // Build a filter for the SmsList
	    List<NameValuePair> params = new ArrayList<NameValuePair>();
	    String[] messageSids = new String[to.length];
	    
	    for (int i = 0; i < to.length; i++) {
	    	
		      
		    params.add(new BasicNameValuePair("Body", body));
		    params.add(new BasicNameValuePair("To", to[i]));
		    params.add(new BasicNameValuePair("From", from));
		      
		    MessageFactory messageFactory = client.getAccount().getMessageFactory();
		    Message message = messageFactory.create(params);
		    messageSids[i] = message.getSid();
		    
	    }
	    return messageSids;
	  }
	}


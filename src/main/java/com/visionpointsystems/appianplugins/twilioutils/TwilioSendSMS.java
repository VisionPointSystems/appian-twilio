package com.visionpointsystems.appianplugins.twilioutils;
	
	import com.twilio.sdk.TwilioRestClient;
	import com.twilio.sdk.TwilioRestException;
	import com.twilio.sdk.resource.factory.SmsFactory;
	import com.twilio.sdk.resource.instance.Sms;
	import com.twilio.sdk.resource.list.SmsList;
	import java.util.HashMap;
	import java.util.Map;
	  
	public class TwilioSendSMS { 
	  
	  public static void send(String[] to, String from, String body, String accountSID, String authToken) throws TwilioRestException {
	    TwilioRestClient client = new TwilioRestClient(accountSID, authToken);
	  
	    // Build a filter for the SmsList
	    Map<String, String> params = new HashMap<String, String>();
	    
	    for (int i = 0; i < to.length; i++) {
	    	
		      
		    params.put("Body", body);
		    params.put("To", to[i]);
		    params.put("From", from);
		      
		    SmsFactory messageFactory = client.getAccount().getSmsFactory();
		    Sms message = messageFactory.create(params);
		    System.out.println(message.getSid());
		    
	    }
	  }
	}


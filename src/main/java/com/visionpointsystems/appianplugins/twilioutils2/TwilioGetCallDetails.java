package com.visionpointsystems.appianplugins.twilioutils2;
	
import com.twilio.sdk.TwilioRestClient;
import com.twilio.sdk.TwilioRestException;
import com.twilio.sdk.resource.instance.Call;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;


	  
	public class TwilioGetCallDetails { 
	  
	  public static CallDetails getCallDetails(String callSid, String accountSid, String authToken) throws TwilioRestException {
	    TwilioRestClient client = new TwilioRestClient(accountSid, authToken);

	    Call call = client.getAccount().getCall(callSid);
	    
	    CallDetails callDetails = new CallDetails();
	    callDetails.setDuration(Long.parseLong(call.getDuration()));
	    callDetails.setStatus(call.getStatus());
	        
	    try{
	        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z"); //RFC 2822
	        Date parsedDate = dateFormat.parse(call.getStartTime());
	        Timestamp startTimestamp = new java.sql.Timestamp(parsedDate.getTime());
	        callDetails.setStartTime(startTimestamp);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    
	    try{
	        SimpleDateFormat dateFormat = new SimpleDateFormat("EEE, dd MMM yyyy HH:mm:ss Z"); //RFC 2822
	        Date parsedDate = dateFormat.parse(call.getEndTime());
	        Timestamp endTimestamp = new java.sql.Timestamp(parsedDate.getTime());
	        callDetails.setEndTime(endTimestamp);
	    }catch(Exception e){
	    	e.printStackTrace();
	    }
	    
	    return callDetails;
	    
	  }
	}


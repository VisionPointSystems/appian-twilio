package com.visionpointsystems.appianplugins.twilioutils2;

import org.apache.log4j.Logger;

import com.twilio.Twilio;
import com.twilio.rest.taskrouter.v1.workspace.Worker;

public class TwilioWorker {
	
	private static final Logger LOG = Logger
			.getLogger(TwilioWorker.class);

  public static String create(String name, String attributes, String workspaceSID, String accountSID, String authToken)  {
    Twilio.init(accountSID, authToken);

    Worker worker = Worker
        .creator(workspaceSID, name)
        .setAttributes(attributes)
        .create();

    return worker.getSid();
  }
  
  public static String updateAttributes(String workerSID, String attributes, String workspaceSID, String accountSID, String authToken)  {
	    Twilio.init(accountSID, authToken);

	    Worker worker = Worker
	        .updater(workspaceSID, workerSID)
	        .setAttributes(attributes)
	        .update();

	    return worker.getSid();
	  }
  
  public static Boolean updateActivity(String workerSID, String activitySID, String workspaceSID, String accountSID, String authToken)  {
	    Twilio.init(accountSID, authToken);
	    
	    try{
	    	Worker worker = Worker
	        .updater(workspaceSID, workerSID)
	        .setActivitySid(activitySID)
	        .update();

	    	return true;
	    }
		  catch (Exception e) {
		  	LOG.error(e.getMessage());
		  	return false;
		  }
	  }
  
  public static boolean delete(String workerSID, String workspaceSID, String accountSID, String authToken)  {
	    Twilio.init(accountSID, authToken);

	    try {
	    	Worker
	        .deleter(workspaceSID, workerSID)
	        .delete();
	    	return true;
	    }
	    catch (Exception e) {
	    	LOG.error(e.getMessage());
	    	return false;
	    }

	  }
}
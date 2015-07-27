package com.visionpointsystems.appianplugins.twilioutils2;

import java.sql.Timestamp;
import java.util.Map;

import org.apache.log4j.Logger;

import com.appiancorp.exceptions.InsufficientPrivilegesException;
import com.appiancorp.exceptions.ObjectNotFoundException;
import com.appiancorp.suiteapi.common.Name;
import com.appiancorp.suiteapi.process.exceptions.SmartServiceException;
import com.appiancorp.suiteapi.process.framework.AppianSmartService;
import com.appiancorp.suiteapi.process.framework.Input;
import com.appiancorp.suiteapi.process.framework.MessageContainer;
import com.appiancorp.suiteapi.process.framework.Required;
import com.appiancorp.suiteapi.process.framework.SmartServiceContext;

import com.appiancorp.suiteapi.process.palette.PaletteInfo; 
import com.appiancorp.suiteapi.security.external.SecureCredentialsStore;
import com.twilio.sdk.TwilioRestException;

@PaletteInfo(paletteCategory = "Integration Services", palette = "Connectivity Services") 
public class GetCallDetails extends AppianSmartService {

	private static final Logger LOG = Logger.getLogger(GetCallDetails.class);
	private final SmartServiceContext smartServiceCtx;
	private final SecureCredentialsStore scs;
	private String callSid;
	private String accountSid;
	private String authToken;
	private String authTokenScsKey;
	private Timestamp startTime;
	private Timestamp endTime;
	private String status;
	private Long duration;

	private void setCallDetails(CallDetails callDetails) {
		this.status = callDetails.getStatus();
		this.duration = callDetails.getDuration();
		this.startTime = callDetails.getStartTime();
		this.endTime = callDetails.getEndTime();
	}
	
	@Override
	public void run() throws SmartServiceException {
		try {
			Map<String, String> credentials = scs.getSystemSecuredValues(authTokenScsKey);
			authToken = credentials.get("authtoken");
		} catch (InsufficientPrivilegesException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (ObjectNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		CallDetails callDetails = new CallDetails();
		try {
			callDetails = TwilioGetCallDetails.getCallDetails(callSid, accountSid, authToken);
			this.setCallDetails(callDetails);
		} catch (TwilioRestException e) {
			
			e.printStackTrace();
		}

	}

	public GetCallDetails(SmartServiceContext smartServiceCtx, SecureCredentialsStore scs) {
		super();
		this.smartServiceCtx = smartServiceCtx;
		this.scs = scs;
	}

	public void onSave(MessageContainer messages) {
	}

	public void validate(MessageContainer messages) {
	}

	@Input(required = Required.ALWAYS)
	@Name("callSid")
	public void setCallSid(String val) {
		this.callSid = val;
	}

	@Input(required = Required.ALWAYS)
	@Name("accountSid")
	public void setAccountSid(String val) {
		this.accountSid = val;
	}

	@Input(required = Required.ALWAYS)
	@Name("authTokenScsKey")
	public void setAuthTokenScsKey(String val) {
		this.authTokenScsKey = val;
	}

	@Name("startTime")
	public Timestamp getStartTime() {
		return startTime;
	}

	@Name("endTime")
	public Timestamp getEndTime() {
		return endTime;
	}

	@Name("status")
	public String getStatus() {
		return status;
	}

	@Name("duration")
	public Long getDuration() {
		return duration;
	}

}

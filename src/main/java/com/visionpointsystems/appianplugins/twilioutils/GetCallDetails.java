package com.visionpointsystems.appianplugins.twilioutils;

import java.sql.Timestamp;

import org.apache.log4j.Logger;

import com.appiancorp.suiteapi.common.Name;
import com.appiancorp.suiteapi.process.exceptions.SmartServiceException;
import com.appiancorp.suiteapi.process.framework.AppianSmartService;
import com.appiancorp.suiteapi.process.framework.Input;
import com.appiancorp.suiteapi.process.framework.MessageContainer;
import com.appiancorp.suiteapi.process.framework.Required;
import com.appiancorp.suiteapi.process.framework.SmartServiceContext;

import com.appiancorp.suiteapi.process.palette.PaletteInfo; 
import com.twilio.sdk.TwilioRestException;

@PaletteInfo(paletteCategory = "Integration Services", palette = "Connectivity Services") 
public class GetCallDetails extends AppianSmartService {

	private static final Logger LOG = Logger.getLogger(GetCallDetails.class);
	private final SmartServiceContext smartServiceCtx;
	private String callSid;
	private String accountSid;
	private String authToken;
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
		CallDetails callDetails = new CallDetails();
		try {
			callDetails = TwilioGetCallDetails.getCallDetails(callSid, accountSid, authToken);
			this.setCallDetails(callDetails);
		} catch (TwilioRestException e) {
			
			e.printStackTrace();
		}

	}

	public GetCallDetails(SmartServiceContext smartServiceCtx) {
		super();
		this.smartServiceCtx = smartServiceCtx;
	}

	public void onSave(MessageContainer messages) {
	}

	public void validate(MessageContainer messages) {
	}

	@Input(required = Required.OPTIONAL)
	@Name("callSid")
	public void setCallSid(String val) {
		this.callSid = val;
	}

	@Input(required = Required.OPTIONAL)
	@Name("accountSid")
	public void setAccountSid(String val) {
		this.accountSid = val;
	}

	@Input(required = Required.OPTIONAL)
	@Name("authToken")
	public void setAuthToken(String val) {
		this.authToken = val;
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

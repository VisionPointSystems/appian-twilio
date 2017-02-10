package com.visionpointsystems.appianplugins.twilioutils2;


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

@PaletteInfo(paletteCategory = "Integration Services", palette = "Connectivity Services") 
public class UpdateWorkerActivity extends AppianSmartService {

	private static final Logger LOG = Logger
			.getLogger(UpdateWorkerActivity.class);
	private final SmartServiceContext smartServiceCtx;
	private final SecureCredentialsStore scs;
	private String workspaceSid;
	private String workerSid;
	private String activitySid;
	private String accountSid;
	private String authToken;
	private String authTokenScsKey;
	private Boolean success;
	

	@Override
	public void run() throws SmartServiceException {
		try {
			Map<String, String> credentials = scs.getSystemSecuredValues(authTokenScsKey);
			authToken = credentials.get("authtoken");
		} catch (InsufficientPrivilegesException e1) {
			LOG.error(e1.getMessage());
		} catch (ObjectNotFoundException e1) {
			LOG.error(e1.getMessage());
		}
	
		
		success = TwilioWorker.updateActivity(workerSid, activitySid, workspaceSid, accountSid, authToken);

	}

	public UpdateWorkerActivity(SmartServiceContext smartServiceCtx, SecureCredentialsStore scs) {
		super();
		this.smartServiceCtx = smartServiceCtx;
		this.scs = scs;
	}

	public void onSave(MessageContainer messages) {
	}

	public void validate(MessageContainer messages) {
	}
	
	@Input(required = Required.ALWAYS)
	@Name("WorkspaceSid")
	public void setCallSid(String val) {
		this.workspaceSid = val;
	}

	@Input(required = Required.ALWAYS)
	@Name("WorkerSid")
	public void setWorkerSid(String val) {
		this.workerSid = val;
	}
	
	@Input(required = Required.ALWAYS)
	@Name("ActivitySid")
	public void setActivitySid(String val) {
		this.activitySid = val;
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
	
	@Name("success")
	public Boolean getSuccess() {
		return success;
	}

}

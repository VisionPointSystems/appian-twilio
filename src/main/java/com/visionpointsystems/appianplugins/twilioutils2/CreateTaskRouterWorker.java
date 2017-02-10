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
public class CreateTaskRouterWorker extends AppianSmartService {

	private static final Logger LOG = Logger
			.getLogger(CreateTaskRouterWorker.class);
	private final SmartServiceContext smartServiceCtx;
	private final SecureCredentialsStore scs;
	private String workspaceSid;
	private String attributes;
	private String name;
	private String accountSid;
	private String authToken;
	private String authTokenScsKey;
	private String workerSid;
	

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
		
		this.workerSid = TwilioWorker.create(name, attributes, workspaceSid, accountSid, authToken);

	}

	public CreateTaskRouterWorker(SmartServiceContext smartServiceCtx, SecureCredentialsStore scs) {
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
	@Name("accountSid")
	public void setAccountSid(String val) {
		this.accountSid = val;
	}

	@Input(required = Required.ALWAYS)
	@Name("authTokenScsKey")
	public void setAuthTokenScsKey(String val) {
		this.authTokenScsKey = val;
	}
	
	@Input(required = Required.ALWAYS)
	@Name("name")
	public void setName(String val) {
		this.name = val;
	}
	
	@Input(required = Required.ALWAYS)
	@Name("attributes")
	public void setAttributes(String val) {
		this.attributes = val;
	}

	@Name("workerSid")
	public String getStartTime() {
		return workerSid;
	}

}
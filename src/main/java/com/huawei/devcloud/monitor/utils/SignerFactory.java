package com.huawei.devcloud.monitor.utils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import com.apimgt.sdk.auth.signer.DefaultSigner;
import com.apimgt.sdk.auth.signer.RegionSigner;
import com.apimgt.sdk.auth.signer.ServiceSigner;
import com.apimgt.sdk.auth.signer.Signer;
import com.apimgt.sdk.internal.config.InternalConfig;
import com.apimgt.sdk.internal.config.SignerConfig;

public class SignerFactory {

	private static final String DEFAULT_SIGNER = "DefaultSignerType";
	private static final Map<String, Class<? extends Signer>> SIGNERS = new ConcurrentHashMap();

	static
	{
	  SIGNERS.put("DefaultSignerType", DefaultSigner.class);
	}

	public static Signer getSigner(String serviceName, String regionName)
	{
		return createSigner("DefaultSignerType",serviceName,regionName);
//	  return lookupAndCreateSigner(serviceName, regionName);
	}

	private static Signer lookupAndCreateSigner(String serviceName, String regionName)
	{
	  InternalConfig config = InternalConfig.Factory.getInternalConfig();
	  SignerConfig signerConfig = config.getSignerConfig(serviceName, regionName);
	  String signerType = signerConfig.getSignerType();
	  return createSigner(signerType, serviceName, regionName);
	}
	
	private static Signer createSigner(String signerType, String serviceName, String regionName)
	{
	  Class<? extends Signer> signerClass = (Class)SIGNERS.get(signerType);
	  if (signerClass == null)
	  {
	    throw new IllegalArgumentException("unknown signer type: " + signerType);
	  }
	  Signer signer;
	  try
	  {
	    signer = (Signer)signerClass.newInstance();
	  }
	  catch (InstantiationException ex) {
	    
	    throw new IllegalStateException("Cannot create an instance of " + signerClass.getName(), ex);
	  }
	  catch (IllegalAccessException ex)
	  {
	    throw new IllegalStateException("Cannot create an instance of " + signerClass.getName(), ex);
	  }
	  if ((signer instanceof ServiceSigner))
	  {
	    ((ServiceSigner)signer).setServiceName(serviceName);
	  }
	  
	  if ((signer instanceof RegionSigner))
	  {
	    ((RegionSigner)signer).setRegionName(regionName);
	  }
	  return signer;
	}
}

package org.eclipse.vtp.modules.attacheddata.ui.export;

import org.eclipse.vtp.desktop.export.IConfigurationExporter;
import org.eclipse.vtp.desktop.export.IDefinitionBuilder;
import org.eclipse.vtp.desktop.export.IFlowElement;
import org.eclipse.vtp.framework.common.configurations.CommonConstants;
import org.eclipse.vtp.framework.interactions.core.configurations.MetaDataRequestConfiguration;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

public class AttachedDataRequestConfigurationExporter implements IConfigurationExporter, CommonConstants
{

	public AttachedDataRequestConfigurationExporter()
	{
		System.out.println("CONSTRUCTING CONFIGURATION"); //TODO cleanup
	}

	public void exportConfiguration(IFlowElement flowElement, Element actionElement)
	{
		
		System.out.println("EXPORTING CONFIGURATION"); //TODO cleanup
		String uri = "http://www.eclipse.org/vtp/namespaces/config";
		NodeList metadataRequestNodeList = ((Element)flowElement.getConfiguration().getElementsByTagNameNS(
				uri, "custom-config").item(0)).getElementsByTagNameNS(uri, "meta-data-request");
		System.out.println("METADATAREQUESTNODELIST.GETLENGTH(): " + metadataRequestNodeList.getLength()); //TODO cleanup
		if (metadataRequestNodeList.getLength() > 0)
		{
			Element metadataRequestElement = (Element)metadataRequestNodeList.item(0);
			MetaDataRequestConfiguration config = new MetaDataRequestConfiguration();
			config.setInput(metadataRequestElement.getAttribute("input"));
			config.setOutput(metadataRequestElement.getAttribute("output"));
			Element configElement = actionElement.getOwnerDocument().createElementNS(
					IDefinitionBuilder.NAMESPACE_URI_COMMON, "common:meta-data-request");
			config.save(configElement);
			actionElement.appendChild(configElement);
		}
	}

	public String getActionId(IFlowElement flowElement)
	{
		return "org.eclipse.vtp.framework.interactions.core.actions.meta-data-request";
	}

	public String getDefaultPath(IFlowElement flowElement)
	{
		return "Continue";
	}
	
	public String translatePath(IFlowElement flowElement, String uiPath)
	{
		return uiPath;
	}

	public String getTargetId(IFlowElement flowElement, Element afterTransitionElement)
	{
		return flowElement.getDefaultTargetId(afterTransitionElement);
	}
	
	public boolean isEntryPoint(IFlowElement flowElement)
	{
		return false;
	}
}

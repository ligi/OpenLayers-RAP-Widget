package org.polymap.rap.widget.openlayers.base;

import org.polymap.rap.widget.openlayers.OpenLayers;

public class OpenLayersWidgetProvider {

	private static OpenLayersWidgetProvider instance=null;

	private OpenLayers widget;

	public OpenLayers getWidget()	{ 
		System.out.println("giving instance"+this.hashCode());
		return widget;
	}
	
	public void setWidget(OpenLayers widget)	{ 
		this.widget=widget;
	}
	
	private OpenLayersWidgetProvider() {
		System.out.println("creating new instance");
	}

	public synchronized static OpenLayersWidgetProvider getInstance()  
	{  
		if (instance == null)  
			instance = new OpenLayersWidgetProvider();  
		
		return instance;  
	}  
}

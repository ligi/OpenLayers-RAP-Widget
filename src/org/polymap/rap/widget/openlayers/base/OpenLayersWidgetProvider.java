package org.polymap.rap.widget.openlayers.base;

import org.polymap.rap.widget.openlayers.OpenLayers;

public class OpenLayersWidgetProvider {

	private static OpenLayersWidgetProvider instance=null;

	private OpenLayers widget;

	int obj_ref=0;

	
	public OpenLayers getWidget()	{ 
		System.out.println("giving instance"+this.hashCode());
		return widget;
	}
	
	public void setWidget(OpenLayers widget)	{ 
		this.widget=widget;
		// create the initial object space ( hash )
		widget.addCommand("map_eval", "if ( typeof objs == 'undefined' ) objs={};");
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
	
	public String generateObjectReference(String prefix)
	  {
		  obj_ref++;
		  return prefix+obj_ref;
	  }
}

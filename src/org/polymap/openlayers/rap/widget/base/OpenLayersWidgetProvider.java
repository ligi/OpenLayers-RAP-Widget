/*
 * polymap.org
 * Copyright 2009, Polymap GmbH, and individual contributors as indicated
 * by the @authors tag.
 *
 * This is free software; you can redistribute it and/or modify it
 * under the terms of the GNU Lesser General Public License as
 * published by the Free Software Foundation; either version 3 of
 * the License, or (at your option) any later version.
 *
 * This software is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public
 * License along with this software; if not, write to the Free
 * Software Foundation, Inc., 51 Franklin St, Fifth Floor, Boston, MA
 * 02110-1301 USA, or see the FSF site: http://www.fsf.org.
 *
 */

package org.polymap.openlayers.rap.widget.base;

import java.util.HashMap;

import org.eclipse.rwt.SessionSingletonBase;
import org.polymap.openlayers.rap.widget.OpenLayersWidget;

/**
 * Widget Provider holding a reference to the widget and generate client side
 * object hash / id's
 * 
 * @author Marcus -LiGi- B&uuml;schleb < mail: ligi (at) polymap (dot) de >
 * 
 */

public class OpenLayersWidgetProvider extends SessionSingletonBase {

	private OpenLayersWidget widget;

	public HashMap<String,OpenLayersObject> obj_ref2obj;
	
	private int obj_ref = 0;


	private OpenLayersWidgetProvider() {
		obj_ref2obj=new HashMap<String,OpenLayersObject>();
	}
	
	public OpenLayersWidget getWidget() {
		return widget;
	}

	public void setWidget(OpenLayersWidget widget) {
		this.widget = widget;
		// create the initial object space ( hash )
		widget.addCommand("eval",
				"if ( typeof objs == 'undefined' ) objs={};");
	}


	public synchronized static OpenLayersWidgetProvider getInstance() {
		return (OpenLayersWidgetProvider) getInstance(OpenLayersWidgetProvider.class);
	}

	public String generateObjectReference(String prefix,OpenLayersObject src_obj) {
		obj_ref++;
		obj_ref2obj.put(prefix + obj_ref, src_obj);
		return prefix + obj_ref;
	}
}

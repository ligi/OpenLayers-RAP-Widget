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

import org.polymap.openlayers.rap.widget.OpenLayers;

/**
 * Client Side OpenLayers Object Base Class holding a reference to the widget
 * and keeps track of changes to the object
 * 
 * @author Marcus -LiGi- B&uuml;schleb < mail: ligi (at) polymap (dot) de >
 * 
 */

public class OpenLayersObject {

	private OpenLayers widget = null;
	private String obj_ref = null;
	private String obj_mod_code = "";

	public void addObjModCode(String code) {
		obj_mod_code += code;
		changes2widget();
	}

	public OpenLayers getWidget() {
		return widget;
	}

	public void create(String js_create_code) {
		OpenLayersWidgetProvider wp = OpenLayersWidgetProvider.getInstance();
		this.widget = wp.getWidget();
		this.setObjRef(wp.generateObjectReference("o"));
		widget.addCommand("map_eval", getJSObjRef() + " = " + js_create_code);
	}

	public void changes2widget() {
		if (widget != null) {
			if (obj_mod_code != "")
				widget.addCommand("map_eval", "obj=" + getJSObjRef() + "; "
						+ obj_mod_code);

			obj_mod_code = "";
		}
	}

	public void setObjRef(String obj_ref) {
		this.obj_ref = obj_ref;
	}

	public String getObjRef() {
		return obj_ref;
	}

	public String getJSObjRef() {
		return "objs['" + obj_ref + "']";
	}

	public String getJSObj(OpenLayersObject object) {
		if (object==null)
			return "null";
		else 
			return object.getJSObjRef();
	}

	public String getJSObj(OpenLayersObject[] oa) {
		if (oa==null)
			return "[null]";
		else 
		{
			String res="[";
			for ( OpenLayersObject obj : oa)
			{
				if (!res.equals("["))
					res+=",";
				res+=getJSObj(obj);
			}
			return res+"]";
		}
			
	}
	
}

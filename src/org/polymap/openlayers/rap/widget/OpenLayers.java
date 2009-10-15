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

package org.polymap.openlayers.rap.widget;

import java.util.Vector;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;

import org.polymap.openlayers.rap.widget.base.OpenLayersEvents;
import org.polymap.openlayers.rap.widget.base.OpenLayersWidgetProvider;
import org.polymap.openlayers.rap.widget.controls.Control;
import org.polymap.openlayers.rap.widget.layers.Layer;

/**
 * 
 * Composite part for the OpenLayers RAP Widget
 * 
 * @author Marcus -LiGi- B&uuml;schleb < mail: ligi (at) polymap (dot) de >
 * 
 */

public class OpenLayers extends Composite {

	public boolean init_done;
	public Vector<Object[]> cmd_stack;
	public OpenLayersEvents events;

	// default external location
	public String js_location = "http://www.openlayers.org/api/OpenLayers.js";

	public OpenLayers(final Composite parent, final int style) {
		super(parent, style);
		prepare();
	}

	public OpenLayers(final Composite parent, final int style,
			String lib_location) {
		super(parent, style);
		prepare();
		js_location = lib_location;
	}

	public void prepare() {
		cmd_stack = new Vector<Object[]>();
		OpenLayersWidgetProvider.getInstance().setWidget(this);
		events = new OpenLayersEvents(this);
	}

	//
	// String js_location="/lib/OpenLayers/OpenLayers.js";

	public String getJSLocation() {
		return js_location;
	}

	// no layout
	public void setLayout(final Layout layout) {
	}

	public void addCommand(String cmd, Object[] params) {
		Object[] cmd_arr = { cmd, params };
		cmd_stack.add(cmd_arr);
	}

	// with a single parameter
	public void addCommand(String cmd, String param) {
		Object[] param_arr = { param };
		addCommand(cmd, param_arr);
	}

	public boolean hasCommand() {
		return (!cmd_stack.isEmpty());
	}

	public Object[] getCommand() {
		Object[] res = cmd_stack.elementAt(0);
		cmd_stack.removeElementAt(0);
		return res;
	}

	public void addLayer(Layer layer2add) {
		addCommand("addLayer", layer2add.getObjRef());
	}

	public void addControl(Control control2add) {
		addCommand("addControl", control2add.getObjRef());
	}

	public void removeControl(Control control2rm) {
		addCommand("map_eval", "this._map.removeControl("
				+ control2rm.getJSObjRef() + ");");
	}

	public void zoomTo(int zoom) {
		Object[] param_arr = { zoom };
		addCommand("zoomTo", param_arr);
	}

	public void setCenter(double center_lon, double center_lat) {
		Object[] param_arr = { center_lon, center_lat };
		addCommand("setCenter", param_arr);
	}

	public void setBaseLayer(Layer layer) {
		addCommand("map_eval", "this._map.setBaseLayer(" + layer.getJSObjRef()
				+ ");");
	}

}

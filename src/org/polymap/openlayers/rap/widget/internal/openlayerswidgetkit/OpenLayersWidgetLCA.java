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

package org.polymap.openlayers.rap.widget.internal.openlayerswidgetkit;

import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;

import org.polymap.openlayers.rap.widget.OpenLayersWidget;
import org.polymap.openlayers.rap.widget.base.OpenLayersCommand;
import org.polymap.openlayers.rap.widget.base.OpenLayersObject;
import org.polymap.openlayers.rap.widget.base.OpenLayersWidgetProvider;
import org.eclipse.rwt.lifecycle.AbstractWidgetLCA;
import org.eclipse.rwt.lifecycle.ControlLCAUtil;
import org.eclipse.rwt.lifecycle.JSWriter;
import org.eclipse.rwt.lifecycle.WidgetLCAUtil;
import org.eclipse.rwt.lifecycle.WidgetUtil;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;

/**
 * 
 * Life Cycle Adapter for the OpenLayers RAP Widget
 * 
 * @author Marcus -LiGi- B&uuml;schleb < mail: ligi (at) polymap (dot) de >
 * 
 */

public class OpenLayersWidgetLCA extends AbstractWidgetLCA {

	// Boolean init_done = false;

	public void preserveValues(final Widget widget) {
		ControlLCAUtil.preserveValues((Control) widget);

		// only needed for custom variants (theming)
		WidgetLCAUtil.preserveCustomVariant(widget);
	}

	/*
	 * Read the parameters transfered from the client
	 */
	public void readData(final Widget widget) {
		OpenLayersWidget map = (OpenLayersWidget) widget;
		if (!OpenLayersWidgetProvider.getInstance().lib_init_done) {
			String init_done_s = WidgetLCAUtil.readPropertyValue(map,
					"load_lib_done");
			if (init_done_s != null)
			    OpenLayersWidgetProvider.getInstance().lib_init_done = init_done_s.equals("true");
		}

		String event = WidgetLCAUtil.readPropertyValue(map, "event_name");

		if (event != null) {
			OpenLayersWidgetProvider wp = OpenLayersWidgetProvider.getInstance();

			OpenLayersObject src = wp.obj_ref2obj.get(WidgetLCAUtil
					.readPropertyValue(map, "event_src_obj"));

			HashMap<String, String> payload_map = new HashMap<String, String>();

			if (src.events.payload_by_name.get(event) != null) {
				Iterator<String> it = src.events.payload_by_name.get(event)
						.iterator();

				while (it.hasNext()) {
					String act = it.next();
					payload_map.put(act, WidgetLCAUtil.readPropertyValue(map,
							"event_payload_" + act));
				}
			}
			src.events.process_event(event, payload_map);

		}

	}

	/*
	 * Initial creation procedure of the widget
	 */
	public void renderInitialization(final Widget widget) throws IOException {
		JSWriter writer = JSWriter.getWriterFor(widget);
		String id = WidgetUtil.getId(widget);
		writer.newWidget("org.polymap.openlayers.rap.widget.OpenLayersWidget",
				new Object[] { id });
		writer.set("appearance", "composite");
		writer.set("overflow", "hidden");
		ControlLCAUtil.writeStyleFlags((OpenLayersWidget) widget);
		 writer.call((OpenLayersWidget) widget, "load_lib",
				new Object[] { ((OpenLayersWidget) widget).getJSLocation() });
	}

	public void renderChanges(final Widget widget) throws IOException {
		OpenLayersWidget open_layers = (OpenLayersWidget) widget;
		ControlLCAUtil.writeChanges(open_layers);
		JSWriter writer = JSWriter.getWriterFor(widget);

		while (open_layers.hasCommand() && OpenLayersWidgetProvider.getInstance().lib_init_done) {
			OpenLayersCommand cmd = open_layers.getCommand();
		/*	String cmd = (String) cmd_pack[0];
			Object[] args = (Object[]) cmd_pack[1]; */
			writer.call(open_layers, "eval" , cmd.getCommandForWriter());
		}

		// only needed for custom variants (theming)
		WidgetLCAUtil.writeCustomVariant(widget);
	}

	public void renderDispose(final Widget widget) throws IOException {
		JSWriter writer = JSWriter.getWriterFor(widget);
		writer.dispose();
	}

	public void createResetHandlerCalls(String typePoolId) throws IOException {
	}

	public String getTypePoolId(Widget widget) {
		return null;
	}
}

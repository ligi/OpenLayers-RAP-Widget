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
import java.util.Iterator;
import java.util.Set;

import org.polymap.openlayers.rap.widget.OpenLayersWidget;

/**
 * Client Side OpenLayers Object Base Class holding a reference to the widget
 * and keeps track of changes to the object
 * 
 * @author Marcus -LiGi- B&uuml;schleb < mail: ligi (at) polymap (dot) de >
 * 
 */

public class OpenLayersEvents {

	OpenLayersObject obj;
	OpenLayersEventListener listener;
	public HashMap<String, Set<String>> payload_by_name;

	public OpenLayersEvents(OpenLayersObject obj) {
		this.obj = obj;
		payload_by_name = new HashMap<String, Set<String>>();
	}

	public void register(OpenLayersEventListener listener, String name,
			HashMap<String, String> payload_request) {
		String payload_code = "";

		if (payload_request != null) {
			payload_by_name.put(name, payload_request.keySet());

			Iterator<String> it;

			it = payload_request.keySet().iterator();
			while (it.hasNext()) {
				String key = it.next();
				payload_code += "req.addParameter( openlayersId + '.event_payload_"
						+ key + "' , " + payload_request.get(key) + "  );";
			}
		}

		obj.addObjModCode(
		//.addCommand(
//						"map_eval",
						"obj.events.register('"
								+ name
								+ "', this,"
								+ "function (event) {"
								+ "if( !org_eclipse_rap_rwt_EventUtil_suspend ) {"
								+ "var openlayersId = org.eclipse.swt.WidgetManager.getInstance().findIdByWidget( this );"
								+ "var req = org.eclipse.swt.Request.getInstance();"
								+ "req.addParameter( openlayersId + '.event_name', event.type );"
								+ "req.addParameter( openlayersId + '.event_src_obj', '" + obj.getObjRef() + "' );"

								+ "" + payload_code + "req.send();" + "}"
								+ "});");
		this.listener = listener;
	}

	public void process_event(String name, HashMap<String, String> payload) {
		listener.process_event(obj,name, payload);
	}

}

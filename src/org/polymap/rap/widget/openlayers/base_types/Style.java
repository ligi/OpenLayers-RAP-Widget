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

package org.polymap.rap.widget.openlayers.base_types;

import org.polymap.rap.widget.openlayers.base.OpenLayersObject;

/**
 * 
 * @author Marcus -LiGi- B&uuml;schleb < mail: ligi (at) polymap (dot) de >
 * 
 */

public class Style extends OpenLayersObject {

	public Style() {
		super.create("OpenLayers.Util.extend({}, "
				+ "OpenLayers.Feature.Vector.style[ 'default'])");
	}

	public void setAttribute(String attribute_name, String attribute_value) {
		super.addObjModCode("obj."+attribute_name+"='" + attribute_value + "';");
	}
	
	public void setAttribute(String attribute_name, int attribute_value) {
		super.addObjModCode("obj."+attribute_name+"=" + attribute_value + ";");
	}

}

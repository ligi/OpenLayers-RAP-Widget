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

package org.polymap.openlayers.rap.widget.layers;

import org.polymap.openlayers.rap.widget.base.OpenLayersObject;

;

/**
 * 
 * @author Marcus -LiGi- B&uuml;schleb < mail: ligi (at) polymap (dot) de >
 * 
 */

public class Layer extends OpenLayersObject {

	String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setIsBaseLayer(Boolean is_base_layer) {
		addObjModCode("setIsBaseLayer", is_base_layer);
	}

	public void setVisibility(Boolean is_visible) {
		addObjModCode("setVisibility", is_visible);
	}

	public void display(Boolean flag) {
		addObjModCode("display", flag);
	}

	public void setOpacity(double newOpacity) {
		addObjModCode("setOpacity", newOpacity);
	}

	public void redraw() {
		addObjModCode("obj.redraw();");
	}

}

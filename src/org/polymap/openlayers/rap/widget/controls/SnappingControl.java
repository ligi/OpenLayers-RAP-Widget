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

package org.polymap.openlayers.rap.widget.controls;

import org.polymap.openlayers.rap.widget.layers.VectorLayer;

/**
 * Acts as a snapping agent while editing vector features.
 * 
 * @author Marcus -LiGi- B&uuml;schleb < mail: ligi (at) polymap (dot) de >
 */
public class SnappingControl extends Control {

	public SnappingControl(VectorLayer layer, VectorLayer target, Boolean greedy) {
		_create(layer, target.getJSObjRef(), greedy);
	}


    /**
     * @param layer {OpenLayers.Layer.Vector} The editable layer. Features from
     *        this layer that are digitized or modified may have vertices
     *        snapped to features from any of the target layers.
     * @param targets {Array(Object | OpenLayers.Layer.Vector)} A list of
     *        objects for configuring target layers. See valid properties of the
     *        target objects. If the items in the targets list are vector layers
     *        (instead of configuration objects), the defaults from the
     *        <defaults> property will apply. The editable layer itself may be a
     *        target
     * @param greedy
     */
	public SnappingControl(VectorLayer layer, VectorLayer[] targets,
			Boolean greedy) {
		String targets_code = "";
		for (VectorLayer target : targets) {
			if (!targets_code.equals(""))
				targets_code += ",";
			targets_code += target.getJSObjRef();
		}
		_create(layer, targets_code, greedy);
	}

	private void _create(VectorLayer layer, String target_code, Boolean greedy) {
		super.create("new OpenLayers.Control.Snapping({ " + "layer:"
				+ layer.getJSObjRef() + "," + "targets: [" + target_code + "],"
				+ "greedy:" + greedy + "});");
	}
}

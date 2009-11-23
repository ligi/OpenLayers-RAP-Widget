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

import org.polymap.openlayers.rap.widget.layers.Layer;

/**
 * 
 * @author Marcus -LiGi- B&uuml;schleb < mail: ligi (at) polymap (dot) de >
 * 
 */

public class SelectFeatureControl extends Control {

    /** Triggered before a feature is highlighted. */
    public static String        EVENT_BEFORE_HIGHLIGHTED = "beforefeaturehighlighted";
    
    /** Triggered when a feature is highlighted. */
    public static String        EVENT_HIGHLIGHTED = "featurehighlighted";

    /** Triggered when a feature is unhighlighted. */
    public static String        EVENT_UNHIGHLIGHTED = "featureunhighlighted";
    
    public boolean started_with_hover_enabled = false;
	
	public SelectFeatureControl(Layer layer) {
		super.create("new OpenLayers.Control.SelectFeature("
				+ layer.getJSObjRef() + ");");
	}
	
	public SelectFeatureControl(Layer layer,boolean hover) {
		started_with_hover_enabled=hover;
		super.create("new OpenLayers.Control.SelectFeature("
				+ layer.getJSObjRef() + ", {    multiple: false, hover: " + hover + " } );");
	}

	public SelectFeatureControl(Layer[] layers) {
		super.create("new OpenLayers.Control.SelectFeature("
				+ getJSObj(layers) + ");");
	}
	
	/*
	 * 	{Boolean} Select on mouse over and deselect on mouse out. 
	 */
	
	public void setHover(boolean hover) {
		assert started_with_hover_enabled : "if you want to toggle hoover you have to start with hover=true in the constructor";
		super.setObjAttr("hover", hover);
	}
	
	public void setRenderIntent(String intent) {
		super.setObjAttr("renderIntent", intent);
	}
	/*
	 * {Boolean} If true do not actually select features (i.e. place them in the layers selected features array), just highlight them.  This property has no effect if hover is false.  Defaults to false.
	 */
	public void setHighlightOnly(Boolean highlight_only) {
		super.setObjAttr("highlightOnly",highlight_only);
	}
}

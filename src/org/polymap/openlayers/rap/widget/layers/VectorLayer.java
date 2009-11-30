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

import org.polymap.openlayers.rap.widget.base_types.Protocol;
import org.polymap.openlayers.rap.widget.base_types.Style;
import org.polymap.openlayers.rap.widget.base_types.StyleMap;
import org.polymap.openlayers.rap.widget.features.VectorFeature;

/**
 * 
 * @author Marcus -LiGi- B&uuml;schleb < mail: ligi (at) polymap (dot) de >
 * 
 */

public class VectorLayer
        extends Layer {

    public VectorLayer( String name ) {
        super.setName( name );
        super.create( "new OpenLayers.Layer.Vector( '" + name + "' );" );
    }


    public VectorLayer( String name, Protocol protocol ) {
        super.setName( name );
        super.create( "new OpenLayers.Layer.Vector( '" + name + "',{" + ""
                + "strategies: [new OpenLayers.Strategy.Fixed()]," + "protocol:"
                + protocol.getJSObjRef() + "} );" );
    }


    public VectorLayer( String name, Protocol protocol, Style style ) {
        super.setName( name );
        super.create( "new OpenLayers.Layer.Vector( '" + name + "',{" + ""
                + "strategies: [new OpenLayers.Strategy.Fixed()]," + "protocol:"
                + protocol.getJSObjRef() + "," + "style:" + style.getJSObjRef() + "} );" );
    }


    public VectorLayer( String name, Protocol protocol, StyleMap style_map ) {
        super.setName( name );
        super.create( "new OpenLayers.Layer.Vector( '" + name + "',{" + ""
                + "strategies: [new OpenLayers.Strategy.Fixed()]," + "protocol:"
                + protocol.getJSObjRef() + "," + "styleMap:" + style_map.getJSObjRef() + "} );" );
    }


    public void addFeatures( VectorFeature vf ) {
        super.addObjModCode( "addFeatures", vf );
    }
}

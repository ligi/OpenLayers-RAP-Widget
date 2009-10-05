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
 */
 
 
 
 /**
 * JavaScript Part for the OpenLayers RAP Widget
 *  
 * @author 	Marcus -LiGi- Bueschleb
 * mail to	ligi (at) polymap (dot) de                 
 *   
 */
 
 
qx.Class.define( "org.polymap.rap.widget.openlayers.OpenLayers", {
    extend: qx.ui.layout.CanvasLayout,
    
    construct: function( id ) {
        this.base( arguments );
        this.setHtmlAttribute( "id", id );
        this._id = id;
        this._map = null;
        this._event_id = 0;
        
	},
    
    properties : {}, 
    
    members : {
    	map_init : function() {
    			qx.ui.core.Widget.flushGlobalQueues(); 
				if( this._map == null )
					{
						this._map = new OpenLayers.Map({div:  document.getElementById( this._id ), controls: [] });
					//	this._map.events.register('click', this,this.process_event);
					}
				
	    },
    	
	    map_eval : function( code2eval ) {
    			eval(code2eval);
	    },
    	
	    addLayer : function(layer_ref) {
			this._map.addLayer(objs[layer_ref]);
	    },	

    	addControl : function(control_ref) {
    		this._map.addControl(objs[control_ref]);
	    },

	    zoomTo: function(zoom_level)	{
        	this._map.zoomTo(	zoom_level);
	    },

	    setCenter: function(center_lon,center_lat) {
    		qx.ui.core.Widget.flushGlobalQueues();
    		try {
        		this._map.setCenter(new OpenLayers.LonLat(center_lon,center_lat));
          	}
          	// if there is no map layer yet setting the center could lead to problems
          	catch ( e) {  }
	    },
	    
	    process_event: function(e) {
	        if( !org_eclipse_rap_rwt_EventUtil_suspend ) {

                var openlayersId = org.eclipse.swt.WidgetManager.getInstance().findIdByWidget( this );
  
                var req = org.eclipse.swt.Request.getInstance();
                req.addParameter( openlayersId + ".last_event_id", e.type );
                req.send();
            } 
	        
	    }
    }
   
} );

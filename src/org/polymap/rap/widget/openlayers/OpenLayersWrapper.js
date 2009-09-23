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
 * $Id: $
 *
 * @author 	Marcus -LiGi- Bueschleb
 * 	mail to 		ligi (at) polymap (dot) de
 *                  
 * @version $Revision: $
 *
 * Intension of this class:
 *   JavaScript Part for the OpenLayers RAP Widget
 *   
 */
 
qx.Class.define( "org.polymap.rap.widget.openlayers.OpenLayers", {
    extend: qx.ui.layout.CanvasLayout,
    
    construct: function( id ) {
        this.base( arguments );
        this.setHtmlAttribute( "id", id );
        this._id = id;
        this._map = null;
        this._active_wms = {};
    },
    
    properties : {
        WMS: {
        	init : [],
        	apply: "wms_change"
        	}
    }, // EOProperties
    
    members : {
        _doActivate : function() {
            var shell = null;
            var parent = this.getParent();
            while( shell == null && parent != null ) {
                if( parent.classname == "org.eclipse.swt.widgets.Shell" ) {
                    shell = parent;
                }
                parent = parent.getParent();
            }
            if( shell != null ) {
                shell.setActiveChild( this );
            }
        },
        wms_change: function()
        	{
        	this.create_map();
        	var tmp_wms_arr= this.getWMS();
        	
        	for ( i=0;i<tmp_wms_arr.length/4;i++)
        		{
        			var wms_id=tmp_wms_arr[i*4];
        			if(! (this._active_wms[wms_id] ))
        				{
             	
        					var wms_label=tmp_wms_arr[i*4+1];
        					var wms_url=tmp_wms_arr[i*4+2];
        					var wms_layers=tmp_wms_arr[i*4+3];
     
        					this._active_wms[wms_id]=new   OpenLayers.Layer.WMS(wms_label,wms_url,{layers:wms_layers});
        					this._map.addLayer(this._active_wms[wms_id]);
        				}
        		}
        	            
        	},

        create_map : function()
        	{
        	qx.ui.core.Widget.flushGlobalQueues();
            if( this._map == null )
            	{
           			this._map = new OpenLayers.Map({div:  document.getElementById( this._id )
           			, controls: [] });
           	
            		//this._map.setCenter(new OpenLayers.LonLat(5.0, 40.0), 5.0);
          
          			// add some controls - TODO: make adjustable by widget
         			this._map.addControl(new OpenLayers.Control.LayerSwitcher());
         			this._map.addControl(new OpenLayers.Control.MouseDefaults());
         			this._map.addControl(new OpenLayers.Control.KeyboardDefaults());
         	
         			this._map.addControl(new OpenLayers.Control.PanZoomBar());
         		}
            }
        
    }
    
} );

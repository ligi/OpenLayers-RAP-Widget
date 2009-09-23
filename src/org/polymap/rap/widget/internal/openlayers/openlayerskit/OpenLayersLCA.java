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
 *   Life Cycle Adapter for the OpenLayers RAP Widget
 *   
 */

package org.polymap.rap.widget.internal.openlayers.openlayerskit;

import java.io.IOException;

import org.polymap.rap.widget.openlayers.OpenLayers;
import org.eclipse.rwt.lifecycle.AbstractWidgetLCA;
import org.eclipse.rwt.lifecycle.ControlLCAUtil;
import org.eclipse.rwt.lifecycle.IWidgetAdapter;
import org.eclipse.rwt.lifecycle.JSWriter;
import org.eclipse.rwt.lifecycle.WidgetLCAUtil;
import org.eclipse.rwt.lifecycle.WidgetUtil;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Widget;

public class OpenLayersLCA extends AbstractWidgetLCA {

  private static final String JS_PROP_WMS = "WMS";
  private static final String PROP_WMS = "WMS";


  private static final String PARAM_CENTER = "centerLocation";

  public void preserveValues( final Widget widget ) {
    ControlLCAUtil.preserveValues( ( Control )widget );
    IWidgetAdapter adapter = WidgetUtil.getAdapter( widget );
    adapter.preserve( PROP_WMS, ( ( OpenLayers )widget ).getWMS() );
    
    // only needed for custom variants (theming)
    WidgetLCAUtil.preserveCustomVariant( widget );
  }

  /*
   * Read the parameters transfered from the client
   */
  public void readData( final Widget widget ) {
    OpenLayers map = ( OpenLayers )widget;
    String location = WidgetLCAUtil.readPropertyValue( map, PARAM_CENTER );
    map.setCenterLocation( location );
  }

  /*
   * Initial creation procedure of the widget
   */
  public void renderInitialization( final Widget widget ) throws IOException {
    JSWriter writer = JSWriter.getWriterFor( widget );
    String id = WidgetUtil.getId( widget );
    writer.newWidget( "org.polymap.rap.widget.openlayers.OpenLayers", new Object[]{
    	
      id
    } );
    writer.set( "appearance", "composite" );
    writer.set( "overflow", "hidden" );
    ControlLCAUtil.writeStyleFlags( ( OpenLayers )widget );
  }

  public void renderChanges( final Widget widget ) throws IOException {
    OpenLayers open_layers = ( OpenLayers )widget;
    ControlLCAUtil.writeChanges(  open_layers );
    JSWriter writer = JSWriter.getWriterFor( widget );
    writer.set( PROP_WMS, JS_PROP_WMS,  open_layers.getWMS() );
    
    // only needed for custom variants (theming)
    WidgetLCAUtil.writeCustomVariant( widget );
  }

  public void renderDispose( final Widget widget ) throws IOException {
    JSWriter writer = JSWriter.getWriterFor( widget );
    writer.dispose();
  }

  public void createResetHandlerCalls( String typePoolId ) throws IOException {
  }

  public String getTypePoolId( Widget widget ) {
    return null;
  }
}

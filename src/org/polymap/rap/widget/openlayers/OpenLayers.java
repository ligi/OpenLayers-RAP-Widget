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
 *   Composite part for the OpenLayers RAP Widget
 *   
 */

package org.polymap.rap.widget.openlayers;

/*******************************************************************************
 * 
 *  for <a href="http://polymap.org">Polymap</a>
 *  
 * @Licence LGPL V3 ( see lgpl-3.0.txt in the root of this Project ) 
 * 
 *  ******************************************************************************/
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;

public class OpenLayers extends Composite {

  private HashMap wms_hash;
	  
  private String centerLocation;
  
  public OpenLayers( final Composite parent, final int style ) {
    super( parent, style );
    wms_hash=new HashMap();
  }

  public void addWMS(String id,String label,String url,String layers)
  {
	  String[] tmp_str_arr={label,url,layers};
	  wms_hash.put(id,tmp_str_arr);
  }

  public String[] getWMS()
  {
	  // ugly style here but RAP cant pass HashMaps or multidimensional Arrays to JS side
	  String[] res=new String[wms_hash.size()*4];
	  Iterator i = wms_hash.entrySet().iterator();
	  int arr_pos=0;
	  while (i.hasNext())
	  {
		  Map.Entry me=(Map.Entry)i.next();
		  res[arr_pos]=(String)me.getKey();
		  res[arr_pos+1]=((String[])me.getValue())[0];
		  res[arr_pos+2]=((String[])me.getValue())[1];
		  res[arr_pos+3]=((String[])me.getValue())[2];
		  arr_pos+=4;
	  }
	  
	  return res;	
	  
  }
  
  public void setCenterLocation( String location ) {
    this.centerLocation = location;
  }

  public String getCenterLocation() {
    return this.centerLocation;
  }

  /*
   * Intentionally commented out as a map cannot have a layout
   */
  public void setLayout( final Layout layout ) {  }
  
}

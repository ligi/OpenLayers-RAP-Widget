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

import java.util.Vector;

import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.Layout;

public class OpenLayers extends Composite {
  
  
  Vector<Object[]> cmd_stack;
  int obj_ref=0;

  public OpenLayers( final Composite parent, final int style ) {
	    super( parent, style );
	    cmd_stack=new Vector<Object[]>();
	  }
  
  // no layout
  public void setLayout( final Layout layout ) {  }

  public String generateObjectReference(String prefix)
  {
	  obj_ref++;
	  return prefix+obj_ref;
  }

  public void addCommand(String cmd,Object[] params)
  {
	  Object[] cmd_arr={cmd,params};
	  cmd_stack.add(cmd_arr);
  }
  
  public boolean hasCommand()
  {
	  return (!cmd_stack.isEmpty());  
  }
  
  public Object[] getCommand()
  {
	  Object[] res=cmd_stack.elementAt(0);
	  cmd_stack.removeElementAt(0);
	  return res;
  }
  
  
  public String addWMS(String id,String label,String url,String layers)
  {
	  String[] param_arr={generateObjectReference("wms"),label,url,layers};
	  addCommand("addWMS",param_arr);
	  return param_arr[0];
  }

  public String addControl(String control_def)
  {
	  String[] param_arr={generateObjectReference("ctrl"),control_def};
	  addCommand("addControl",param_arr);
	  return param_arr[0];
  }

  public void zoomTo(int zoom) {
	  Object[] param_arr={zoom};
	  addCommand("zoomTo",param_arr);
  }

  public void setCenter(double center_lon,double center_lat)
  {
	  Object[] param_arr={center_lon,center_lat};
	  addCommand("setCenter",param_arr);
  }
 
  
}

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
 *   API Resource for the OpenLayers RAP Widget
 *   
 */

package org.polymap.rap.widget.openlayers;

import org.eclipse.rwt.internal.util.HTML;
import org.eclipse.rwt.resources.IResource;
import org.eclipse.rwt.resources.IResourceManager.RegisterOptions;

public class OpenLayersAPIResource implements IResource {

 private String location;

  public String getCharset() {
    return HTML.CHARSET_NAME_ISO_8859_1;
  }

  public ClassLoader getLoader() {
    return this.getClass().getClassLoader();
  }

  public RegisterOptions getOptions() {
    return RegisterOptions.VERSION;
  }

  public String getLocation() {
    return "http://www.openlayers.org/api/OpenLayers.js";
  }

  public boolean isJSLibrary() {
    return true;
  }

  public boolean isExternal() {
    return true;
  }
}

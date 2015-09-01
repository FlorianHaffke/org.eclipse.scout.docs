/**
 * 
 */
package org.eclipsescout.contacts.shared;

import java.security.BasicPermission;

/**
 * @author mzi
 */
public class ReadContactPermission extends BasicPermission {

  private static final long serialVersionUID = 1L;

  /**
   * 
   */
  public ReadContactPermission() {
    super("ReadContact");
  }
}
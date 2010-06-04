/*
 * Created on Mar 8, 2006
 */
package org.soulspace.xmi.util;

import org.soulspace.xmi.uml14.repository.*;

public class XMILoader {

  public XMILoader() {
    super();
  }

  public static void main(String[] args) {
	XmiRepositoryImpl xmiRepository;
    if(args.length > 0) {
      for(int i = 0; i < args.length; i++) {
        System.out.println("Model: " + args[i]);
        try {
          xmiRepository = new XmiRepositoryImpl(args[i]);
          xmiRepository.initRepository();
          //System.out.println("Elements: " + xmiRepository.getXmiIdMap().size());          
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }
  
}

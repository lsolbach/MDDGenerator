/*
 * Created on Mar 8, 2006
 */
package org.soulspace.xmi.util;

import org.soulspace.modelling.uml14.impl.*;

public class XMILoader {

  public XMILoader() {
    super();
  }

  public static void main(String[] args) {
	  Uml14RepositoryImpl xmiRepository;
    if(args.length > 0) {
      for(int i = 0; i < args.length; i++) {
        System.out.println("Model: " + args[i]);
        try {
          xmiRepository = new Uml14RepositoryImpl(args[i]);
          xmiRepository.initRepository();
          //System.out.println("Elements: " + xmiRepository.getXmiIdMap().size());          
        } catch (Exception e) {
          e.printStackTrace();
        }
      }
    }
  }
  
}

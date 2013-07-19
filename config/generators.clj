{:std-generators
 [{
   :element "Model"
   :template "modelbuilder/java/factory-interface"
   :includes ["lib" "model/lib" "java/lib" "java/interface" "modelbuilder/java/lib"]
   :basename "ModelFactory"
   :subdir "src"
   :extension "java"
   :namespaceReplacement "org.soulspace.modelling.repository.builder"
   :stereotypes "NONE"
   }
  {:element "Model"
   :template "modelbuilder/java/factory-class"
   :includes ["lib" "model/lib" "java/lib" "java/class" "modelbuilder/java/lib"]
   :subdir "src"
   :basename "ModelFactory"
   :prefix "Abstract"
   :extension "java"
   :namespaceReplacement "org.soulspace.modelling.repository.builder.impl"
   :stereotypes "NONE"
   }]
 }
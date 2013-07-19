{:std-generators
 [{
   :element "Class"
   :template "mdd/java/element-interface"
   :includes ["lib" "model/lib" "java/lib" "java/interface" "mdd/java/lib"]
   :subdir "src"
   :extension "java"
   :stereotypes "NONE"
   }
  {:element "Class"
   :template "mdd/java/element-class"
   :includes ["lib" "model/lib" "java/lib" "java/class" "mdd/java/lib"]
   :subdir "src"
   :prefix "Abstract"
   :extension "java"
   :stereotypes "NONE"
   }
  {:element "Class"
   :template "mdd/java/element-impl-class"
   :includes ["lib" "model/lib" "java/lib" "java/class" "mdd/java/lib"]
   :subdir "tmp"
   :suffix "Impl"
   :extension "java"
   :namespaceSuffix "impl"
   :stereotypes "NONE"
   }
  {:element "Model"
   :template "mdd/java/repository-interface"
   :includes ["lib" "model/lib" "java/lib" "java/interface" "mdd/java/lib"]
   :basename "ModelRepository"
   :subdir "src"
   :extension "java"
   :namespaceReplacement "org.soulspace.modelling.repository"
   :stereotypes "NONE"
   }
  {:element "Model"
   :template "mdd/java/repository-class"
   :includes ["lib" "model/lib" "java/lib" "java/class" "mdd/java/lib"]
   :subdir "src"
   :basename "ModelRepository"
   :suffix "Impl"
   :extension "java"
   :namespaceReplacement "org.soulspace.modelling.repository.impl"
   :stereotypes  "NONE"
   }]
 }